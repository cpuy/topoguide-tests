package fr.colin.topoguide.repository;

import static fr.colin.topoguide.repository.RemoteTopoGuideRepository.skitour;
import junit.framework.TestCase;
import fr.colin.topoguide.model.TopoGuide;

public class RemoteRepositoryTest extends TestCase {

   public void testSimple() throws Exception {
      long expectedTopoNumero = 139L;
      TopoGuide topo = skitour().fetchTopoById(expectedTopoNumero);
      
      assertNotNull(topo);
      assertEquals(topo.numero, expectedTopoNumero);
      assertEquals(topo.nom, "Grande Sure, Par le Col de la Charmille");
   }
}
