package fr.colin.topoguide.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.InstrumentationTestCase;
import fr.colin.topoguide.views.test.R;

public class ImageRepositoryTest extends InstrumentationTestCase {

   private ImageRepository imageRepository;

   @Override
   protected void setUp() throws Exception {
      imageRepository = new ImageRepository(getInstrumentation().getContext());
   }

   @Override
   protected void tearDown() throws Exception {
      FileUtils.deleteDirectory(imageRepository.getBaseFolder());
   }
   
   private void addImageToRepository(long topoId, long imageId, int res) throws RepositoryException, IOException {
      InputStream in = getRawStream(res);
      imageRepository.create(topoId, imageId, IOUtils.toByteArray(in));
      in.close();
   }

   private InputStream getRawStream(int id) {
      return getInstrumentation().getContext().getResources().openRawResource(id);
   }
   
   private boolean sameHeightAndWidth(Bitmap expected, Bitmap actual) {
      return expected.getHeight() == actual.getHeight()
         && expected.getWidth() == actual.getWidth();
   }

   private Bitmap getBitmap(int id) {
      return BitmapFactory.decodeStream(getRawStream(id));
   }

   public void testCreate() throws Exception {
      InputStream in = getInstrumentation().getContext().getResources().openRawResource(R.raw.sure);
      
      imageRepository.create(1L, 0L, IOUtils.toByteArray(in));
      in.close();
      
      assertTrue(imageRepository.image(1L, 0L).exists());
      assertNotNull(BitmapFactory.decodeFile(imageRepository.image(1L, 0L).getAbsolutePath()));
   }
   
   public void testFindByTopoId() throws Exception {
      addImageToRepository(1L, 0L, R.raw.sure);
      addImageToRepository(1L, 1L, R.raw.sure2);
      
      List<Bitmap> bitmaps = imageRepository.findByTopoId(1L);
      
      assertEquals(2, bitmaps.size());
      assertTrue(sameHeightAndWidth(bitmaps.get(0), getBitmap(R.raw.sure)));
      assertTrue(sameHeightAndWidth(bitmaps.get(1), getBitmap(R.raw.sure2)));
   }
   
   public void testFindByTopoIdReturnEmptyListIfNoImagesFound() throws Exception {
      
      List<Bitmap> bitmaps = imageRepository.findByTopoId(1L);

      assertNotNull(bitmaps);
      assertEquals(0, bitmaps.size());
   }

   public void testGetReturnNullIfNotFound() throws Exception {
      
      Bitmap image = imageRepository.get(1L, R.raw.sure);
      
      assertNull(image);
   }
   
   public void testGet() throws Exception {
      addImageToRepository(1L, 0L, R.raw.sure);
      addImageToRepository(1L, 1L, R.raw.sure2);
      
      Bitmap image = imageRepository.get(1L, 1L);
      
      assertNotNull(image);
      assertTrue(sameHeightAndWidth(getBitmap(R.raw.sure2), image));
   }
   
   public void testGetWhenSdNotMountedThrowException() throws Exception {
     // Pas trouvé comment simuler umount sd par les tests .... pas cool      
   }
}
