package fr.colin.topoguide.database.table;

import static fr.colin.topoguide.util.builder.DepartBuilder.aDepart;
import static fr.colin.topoguide.util.builder.SommetBuilder.aSommet;
import static fr.colin.topoguide.util.builder.TopoGuideBuilder.aTopoGuide;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import fr.colin.topoguide.database.DatabaseOpenHelper;
import fr.colin.topoguide.model.TopoGuide;

public class TopoGuideTableTest extends AndroidTestCase {

   private TopoGuideTable topoGuideTable = new TopoGuideTable();
   private SommetTable sommetTable = new SommetTable();
   private DepartTable departTable = new DepartTable();
   private SQLiteDatabase database;

   @Override
   protected void setUp() throws Exception {
      super.setUp();
      database = new DatabaseOpenHelper(getContext()).getWritableDatabase();
      topoGuideTable.setDatabase(database);
      sommetTable.setDatabase(database);
      departTable.setDatabase(database);
   }

   @Override
   protected void tearDown() throws Exception {
      super.tearDown();
      topoGuideTable.empty();
      sommetTable.empty();
      departTable.empty();
      database.close();
   }

   public void testCreate() throws Exception {
      long id = topoGuideTable.add(aTopoGuide().build());

      assertNotSame(id, -1L);
   }
   
   public void testFindByIdReturnUnknownTopoGuideWhenTopoGuideNotInDB() {

      TopoGuide topo = topoGuideTable.get(1L);

      assertTrue(topo.isUnknown());
   }

   public void testFindById() throws Exception {
      TopoGuide topo = aTopoGuide().withSommet(aSommet().id(1L).build())
            .withDepart(aDepart().id(1L).build())
            .build();
      topo.id = topoGuideTable.add(topo);
      
      TopoGuide fetchedTopoGuide = topoGuideTable.get(topo.id);
      
      assertFalse(fetchedTopoGuide.isUnknown());
      assertEquals(topo.id, fetchedTopoGuide.id);
      assertEquals(topo.nom, fetchedTopoGuide.nom);
      assertEquals(topo.numero, fetchedTopoGuide.numero);
      assertEquals(topo.remarques, fetchedTopoGuide.remarques);
      assertEquals(topo.sommet.id, fetchedTopoGuide.sommet.id);
      assertEquals(topo.depart.id, fetchedTopoGuide.depart.id);
   }
}
