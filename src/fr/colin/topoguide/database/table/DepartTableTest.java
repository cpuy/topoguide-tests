package fr.colin.topoguide.database.table;

import static fr.colin.topoguide.util.builder.DepartBuilder.aDepart;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import fr.colin.topoguide.database.DatabaseOpenHelper;
import fr.colin.topoguide.model.Depart;

public class DepartTableTest extends AndroidTestCase {

   private DepartTable departTable = new DepartTable();
   private SQLiteDatabase database;

   @Override
   protected void setUp() throws Exception {
      super.setUp();
      database = new DatabaseOpenHelper(getContext()).getWritableDatabase();
      departTable.setDatabase(database);
   }

   @Override
   protected void tearDown() throws Exception {
      super.tearDown();
      departTable.empty();
      database.close();
   }
   
   public void testCreate() throws Exception {
      
      long departId = departTable.add(aDepart().build());
      
      assertTrue(departId > 0);
   }
   
   public void testFindByIdReturnUnknownDepartWhenDepartNotInDB() {
      
      Depart depart = departTable.get(1L);
      
      assertTrue(depart.isUnknown());
   }
   
   public void testFindById() throws Exception {
      Depart expectedDepart = aDepart().build();
      expectedDepart.id = departTable.add(expectedDepart);
      
      Depart fetchedDepart = departTable.get(expectedDepart.id);
      
      assertFalse(fetchedDepart.isUnknown());
      assertEquals(expectedDepart, fetchedDepart);
   }
   
   public void testGetDepartReturnUnknownDepartIfNotInDB() {

      Depart depart = departTable.get(aDepart().build());

      assertTrue(depart.isUnknown());
   }

   public void testGetDepartReturnDepartInDBIfSame() {
      Depart depart = aDepart().build();
      depart.id = departTable.add(depart);
      
      Depart fetchedDepart = departTable.get(depart);

      assertEquals(depart, fetchedDepart);
   }
}
