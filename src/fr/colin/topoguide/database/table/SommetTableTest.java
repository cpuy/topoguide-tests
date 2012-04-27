package fr.colin.topoguide.database.table;

import static fr.colin.topoguide.util.builder.SommetBuilder.aSommet;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import fr.colin.topoguide.database.DatabaseOpenHelper;
import fr.colin.topoguide.database.table.SommetTable;
import fr.colin.topoguide.model.Sommet;

public class SommetTableTest extends AndroidTestCase {

   private SommetTable sommetTable = new SommetTable();
   private SQLiteDatabase database;

   @Override
   protected void setUp() throws Exception {
      super.setUp();
      database = new DatabaseOpenHelper(getContext()).getWritableDatabase();
      sommetTable.setDatabase(database);
   }

   @Override
   protected void tearDown() throws Exception {
      super.tearDown();
      sommetTable.empty();
      database.close();
   }

   public void testCreate() throws Exception {
      
      long id = sommetTable.add(aSommet().build());

      assertNotSame(id, -1L);
   }

   public void testFindByIdReturnUnknownSommetWhenSommetNotInDB() {

      Sommet sommet = sommetTable.get(1L);

      assertTrue(sommet.isUnknown());
   }

   public void testFindById() {
      Sommet createdSommet = aSommet().build(); 
      createdSommet.id = sommetTable.add(createdSommet);

      Sommet fetchedSommet = sommetTable.get(createdSommet.id);

      assertFalse(fetchedSommet.isUnknown());
      assertEquals(createdSommet, fetchedSommet);
   }

   public void testGetSommetReturnUnknownSommetIfNotInDB() {

      Sommet sommet = sommetTable.get(aSommet().build());

      assertTrue(sommet.isUnknown());
   }

   public void testFindSameReturnSommetInDBIfSame() {
      Sommet sommet = aSommet().build();
      sommet.id = sommetTable.add(sommet);
      
      Sommet fetchedSommet = sommetTable.get(sommet);

      assertEquals(sommet, fetchedSommet);
   }
}
