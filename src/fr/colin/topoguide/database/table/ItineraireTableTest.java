package fr.colin.topoguide.database.table;

import static fr.colin.topoguide.util.builder.ItineraireBuilder.aVariante;
import static fr.colin.topoguide.util.builder.ItineraireBuilder.anItinerairePrincipal;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import fr.colin.topoguide.database.DatabaseOpenHelper;
import fr.colin.topoguide.database.table.ItineraireTable;
import fr.colin.topoguide.model.Itineraire;

public class ItineraireTableTest extends AndroidTestCase {

   private ItineraireTable itineraireTable = new ItineraireTable();
   private SQLiteDatabase database;

   @Override
   protected void setUp() throws Exception {
      super.setUp();
      database = new DatabaseOpenHelper(getContext()).getWritableDatabase();
      itineraireTable.setDatabase(database);
   }

   @Override
   protected void tearDown() throws Exception {
      super.tearDown();
      itineraireTable.empty();
      database.close();
   }
   
   public void testCreate() throws Exception {
      Itineraire expectedItineraire = anItinerairePrincipal().build();
      
      expectedItineraire.id = itineraireTable.add(expectedItineraire);
      
      assertNotSame(expectedItineraire.id, -1L);
   }
   
   public void testFindPrincipalByTopoIdReturnUnknownItineraireWhenTopoNotInDB() {
      
      Itineraire itineraire = itineraireTable.findPrincipalByTopoId(1L);
      
      assertTrue(itineraire.isUnknown());
   }
   
   public void testFindPrincipalByTopoId() {
      long expectedTopoId = 1L;
      Itineraire expectedItineraire = anItinerairePrincipal().withTopoId(expectedTopoId).build();
      expectedItineraire.id = itineraireTable.add(expectedItineraire);
      
      Itineraire itineraire = itineraireTable.findPrincipalByTopoId(expectedTopoId);
      
      assertFalse(itineraire.isUnknown());
      assertEquals(expectedItineraire, itineraire);
   }
   
   public void testFindVariantesByTopoIdReturnEmptyListWhenNoVariantesForTopo() throws Exception {
      
      List<Itineraire> variantes = itineraireTable.findVariantesByTopoId(1L);
      
      assertTrue(variantes.isEmpty());
   }

   public void testFindVariantesByTopoId() throws Exception {
      Itineraire created1 = aVariante().withTopoId(1L).build(); 
      created1.id = itineraireTable.add(created1);
      Itineraire created2 = aVariante().withTopoId(1L).build();
      created2.id = itineraireTable.add(created2);
      
      List<Itineraire> itineraires = itineraireTable.findVariantesByTopoId(1L);
      
      assertEquals(itineraires.size(), 2);
      assertEquals(itineraires.get(0), created1);
      assertEquals(itineraires.get(1), created2);
   }
}
