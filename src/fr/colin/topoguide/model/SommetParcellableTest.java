package fr.colin.topoguide.model;

import static fr.colin.topoguide.model.Sommet.UNKNOWN_SOMMET;
import static fr.colin.topoguide.util.ParcellableUtils.getParcelable;
import static junit.framework.Assert.assertEquals;
import fr.colin.topoguide.util.builder.SommetBuilder;

public class SommetParcellableTest {

   public void testSommetParcelable() throws Exception {
      Sommet sommet = SommetBuilder.aSommet().build();
      
      assertEquals(sommet, getParcelable(sommet, Sommet.CREATOR));
   }
   
   public void testUnknownSommetIsParcellable() throws Exception {
      Sommet sommet = UNKNOWN_SOMMET;
      
      assertEquals(sommet, getParcelable(sommet, Sommet.CREATOR));
   }
}
