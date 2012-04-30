package fr.colin.topoguide.repository;

import static fr.colin.topoguide.util.builder.ItineraireBuilder.aVariante;
import static fr.colin.topoguide.util.builder.TopoGuideBuilder.aTopoGuide;
import android.test.AndroidTestCase;
import fr.colin.topoguide.model.Itineraire;
import fr.colin.topoguide.model.TopoGuide;

public class LocalTopoGuideRepositoryTest extends AndroidTestCase {

   private LocalTopoGuideRepository localTopoGuideRepository;

   @Override
   protected void setUp() throws Exception {
      super.setUp();
      localTopoGuideRepository = LocalTopoGuideRepository.fromContext(getContext());
      localTopoGuideRepository.open();
   }

   @Override
   protected void tearDown() throws Exception {
      super.tearDown();
      localTopoGuideRepository.empty();
      localTopoGuideRepository.close();
   }

   public void testCreateTopoGuide() throws Exception {
      TopoGuide topo = aTopoGuide().withVariante(aVariante().build()).build();
      
      TopoGuide createdTopo = localTopoGuideRepository.create(topo);

      verifyModelNotNull(createdTopo);
      verifyModelIdsAreUpdated(createdTopo);
      verifyItinerairesTopoIdCorrespondToTopoId(createdTopo);
   }

   private void verifyModelNotNull(TopoGuide createdTopo) {
      assertNotNull(createdTopo);
      assertNotNull(createdTopo.sommet);
      assertNotNull(createdTopo.depart);
      assertNotNull(createdTopo.itineraire);
   }

   private void verifyModelIdsAreUpdated(TopoGuide createdTopo) {
      assertNotSame(createdTopo.id, -1L);
      assertNotSame(createdTopo.sommet.id, -1L);
      assertNotSame(createdTopo.depart.id, -1L);
      assertNotSame(createdTopo.itineraire.id, -1L);
   }

   private void verifyItinerairesTopoIdCorrespondToTopoId(TopoGuide createdTopo) {
      assertEquals(createdTopo.id, createdTopo.itineraire.topoId);
      for (Itineraire variante : createdTopo.variantes) {
         assertEquals(createdTopo.id, variante.topoId);
      }
   }

   public void testFindTopoByIdReturnUnknownTopoIfNotInDb() throws Exception {

      TopoGuide topo = localTopoGuideRepository.findTopoById(1L);

      assertNotNull(topo);
      assertTrue(topo.isUnknown());
   }

   public void testFindTopoById() throws Exception {
      TopoGuide topo = aTopoGuide().withVariante(aVariante().build()).build();
      TopoGuide createdTopo = localTopoGuideRepository.create(topo);
      
      TopoGuide fetchedTopo = localTopoGuideRepository.findTopoById(createdTopo.id);

      assertEquals(createdTopo, fetchedTopo);
   }
   
   public void testCreateTopoDontInsertNewDepartAndSommetIfAlreadyExistingInDB() throws Exception {
      TopoGuide firstTopo = localTopoGuideRepository.create(aTopoGuide().build());
      
      TopoGuide secondTopoGuide = localTopoGuideRepository.create(aTopoGuide().build());
      
      assertEquals(secondTopoGuide.sommet, firstTopo.sommet);
      assertEquals(secondTopoGuide.depart, firstTopo.depart);
   }
}
