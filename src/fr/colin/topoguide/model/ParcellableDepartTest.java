package fr.colin.topoguide.model;

import static fr.colin.topoguide.model.Depart.UNKNOWN_DEPART;
import static fr.colin.topoguide.util.ParcellableUtils.getParcelable;
import static fr.colin.topoguide.util.builder.DepartBuilder.aDepart;
import android.test.AndroidTestCase;

public class ParcellableDepartTest extends AndroidTestCase {

   public void testDepartParcellable() throws Exception {
      Depart depart = aDepart().build();
      
      assertEquals(depart, getParcelable(depart, Depart.CREATOR));
   }
   
   public void testUnknownDepartIsParcellable() throws Exception {
      Depart depart = UNKNOWN_DEPART;
      
      assertEquals(depart, getParcelable(depart, Depart.CREATOR));
   }
}
