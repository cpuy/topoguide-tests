package fr.colin.topoguide.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.test.AndroidTestCase;
import fr.colin.topoguide.utils.IOUtils;
import fr.colin.topoguide.views.test.R;

public class ImageRepositoryTest extends AndroidTestCase {

   private ImageRepository imageRepository;

   @Override
   protected void setUp() throws Exception {
      imageRepository = new ImageRepository(getContext());
   }

   @Override
   protected void tearDown() throws Exception {
      FileUtils.deleteDirectory(imageRepository.getBaseFolder());
   }
   
   private void addImageToRepository(long topoId, long imageId) throws RepositoryException, IOException {
      InputStream in = getContext().getResources().openRawResource((int) imageId);
      imageRepository.create(topoId, imageId, IOUtils.inputStreamToBytes(in));
      in.close();
   }

   public void testCreate() throws Exception {
      InputStream in = getContext().getResources().openRawResource(R.raw.sure);
      
      File file = imageRepository.create(1L, 0L, IOUtils.inputStreamToBytes(in));
      in.close();
      
      assertTrue(file.exists());
   }
   
   public void testFindByTopoId() throws Exception {
      addImageToRepository(1L, R.raw.sure);
      addImageToRepository(1L, R.raw.sure2);
      
      List<Bitmap> bitmaps = imageRepository.findByTopoId(1L);
      
      assertEquals(2, bitmaps.size());
   }
   
   public void testGetReturnNullIfNotFound() throws Exception {
      
      Bitmap image = imageRepository.get(1L, R.raw.sure);
      
      assertNull(image);
   }
   
   public void testGet() throws Exception {
      addImageToRepository(1L, R.raw.sure);
      addImageToRepository(1L, R.raw.sure2);
      
      Bitmap image = imageRepository.get(1L, R.raw.sure2);
      
      assertEquals(imageRepository.findByTopoId(1L).get(1), image);
   }
}
