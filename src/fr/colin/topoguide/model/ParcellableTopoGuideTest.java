package fr.colin.topoguide.model;

import static fr.colin.topoguide.model.TopoGuide.UNKNOWN_TOPOGUIDE;
import static fr.colin.topoguide.util.ParcellableUtils.getParcelable;
import static junit.framework.Assert.assertEquals;
import fr.colin.topoguide.util.builder.TopoGuideBuilder;

public class ParcellableTopoGuideTest {

   public void testTopoGuideParcelable() throws Exception {
      TopoGuide topo = TopoGuideBuilder.aTopoGuide().build();
      
      assertEquals(topo, getParcelable(topo, TopoGuide.CREATOR));
   }
   
   public void testUnknownDepartIsParcellable() throws Exception {
      TopoGuide topo = UNKNOWN_TOPOGUIDE;
      
      assertEquals(topo, getParcelable(topo, TopoGuide.CREATOR));
   }
}
