package fr.colin.topoguide.model;

import static fr.colin.topoguide.util.builder.DepartBuilder.aDepart;
import static fr.colin.topoguide.util.builder.ItineraireBuilder.aVariante;
import static fr.colin.topoguide.util.builder.ItineraireBuilder.anItinerairePrincipal;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.test.AndroidTestCase;
import fr.colin.topoguide.model.Depart;
import fr.colin.topoguide.model.Itineraire;
import fr.colin.topoguide.model.Sommet;
import fr.colin.topoguide.model.TopoGuide;
import fr.colin.topoguide.util.builder.SommetBuilder;
import fr.colin.topoguide.util.builder.TopoGuideBuilder;

public class ParcellableTest extends AndroidTestCase {

   /** */
   public void testDepartParcellable() throws Exception {
      Depart depart = aDepart().build();
      
      assertEquals(depart, getParcelable(depart, Depart.CREATOR));
   }
   
   /** */
   public void testItineraireParcellable() throws Exception {
      Itineraire itineraire = anItinerairePrincipal().build();

      assertEquals(itineraire, getParcelable(itineraire, Itineraire.CREATOR));
   }
   
   /** */
   public void testVarianteParcelable() {
      Itineraire variante = aVariante().build();
      
      assertEquals(variante, getParcelable(variante, Itineraire.CREATOR));
   }
   
   /** */
   public void testSommetParcelable() throws Exception {
      Sommet sommet = SommetBuilder.aSommet().build();
      
      assertEquals(sommet, getParcelable(sommet, Sommet.CREATOR));
   }
   
   /** */
   public void testTopoGuideParcelable() throws Exception {
      TopoGuide topo = TopoGuideBuilder.aTopoGuide().build();
      
      assertEquals(topo, getParcelable(topo, TopoGuide.CREATOR));
   }
   
   private <T extends Parcelable> T getParcelable(T object, Creator<T> creator) {
      Parcel parcel = Parcel.obtain();
      object.writeToParcel(parcel, 0);
      parcel.setDataPosition(0);
      return creator.createFromParcel(parcel);
   }
}
