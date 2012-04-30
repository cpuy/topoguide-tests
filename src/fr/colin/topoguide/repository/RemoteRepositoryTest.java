package fr.colin.topoguide.repository;

import static fr.colin.topoguide.repository.RemoteTopoGuideRepository.skitour;
import junit.framework.TestCase;
import fr.colin.topoguide.model.TopoGuide;

public class RemoteRepositoryTest extends TestCase {

   public void testSimple() throws Exception {
      
      TopoGuide topo = skitour().fetchTopoById(139L);
      
      assertNotNull(topo);
      assertEquals(topo.numero, "139");
      assertEquals(topo.nom, "Grande Sure, Par le Col de la Charmille");
   }
}
