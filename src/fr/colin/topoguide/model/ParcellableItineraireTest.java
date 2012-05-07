package fr.colin.topoguide.model;

import static fr.colin.topoguide.model.Itineraire.UNKNOWN_ITINERAIRE;
import static fr.colin.topoguide.util.ParcellableUtils.getParcelable;
import static fr.colin.topoguide.util.builder.ItineraireBuilder.aVariante;
import static fr.colin.topoguide.util.builder.ItineraireBuilder.anItinerairePrincipal;
import android.test.AndroidTestCase;

public class ParcellableItineraireTest extends AndroidTestCase {

   public void testItineraireParcellable() throws Exception {
      Itineraire itineraire = anItinerairePrincipal().build();

      assertEquals(itineraire, getParcelable(itineraire, Itineraire.CREATOR));
   }
   
   public void testVarianteParcelable() {
      Itineraire variante = aVariante().build();
      
      assertEquals(variante, getParcelable(variante, Itineraire.CREATOR));
   }
   
   public void testUnknownItineraireIsParcellable() throws Exception {
      Itineraire itineraire = UNKNOWN_ITINERAIRE;
      
      assertEquals(itineraire, getParcelable(itineraire, Itineraire.CREATOR));
   }
}
