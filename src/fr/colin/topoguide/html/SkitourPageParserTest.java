package fr.colin.topoguide.html;

import static fr.colin.topoguide.util.builder.DepartBuilder.aDepart;
import static fr.colin.topoguide.util.builder.ItineraireBuilder.aVariante;
import static fr.colin.topoguide.util.builder.ItineraireBuilder.anItinerairePrincipal;
import static fr.colin.topoguide.util.builder.SommetBuilder.aSommet;
import static java.util.Arrays.asList;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.test.InstrumentationTestCase;
import fr.colin.topoguide.html.SkitourPageParser;
import fr.colin.topoguide.model.Depart;
import fr.colin.topoguide.model.Itineraire;
import fr.colin.topoguide.model.Sommet;
import fr.colin.topoguide.model.TopoGuide;
import fr.colin.topoguide.views.test.R;

public class SkitourPageParserTest extends InstrumentationTestCase {
   
   private SkitourPageParser skitourParser;

   
   @Override
   protected void setUp() throws Exception {
      Document grandeSure = Jsoup.parse(
            getInstrumentation().getContext().getResources().openRawResource(R.raw.grande_sure_139),
            "ISO-8859-1", "http://www.skitour.fr/topos/,139.html");
      
      skitourParser = new SkitourPageParser(grandeSure, 139);
   }

   /** */
   public void testParseItemTopoLine() throws Exception {
      
      String parsedLine = skitourParser.parseItemTopoLine("<strong>Massif : </strong>Chartreuse  ");
      
      assertEquals("Chartreuse", parsedLine);
   }

   /** */
   public void testGetOnlyNumbers() throws Exception {
      assertEquals(853, skitourParser.getOnlyNumbers("853 m."));
      assertEquals(1, skitourParser.getOnlyNumbers("1"));
   }

   private Sommet expectedSommet() {
      return aSommet().nom("Grande Sure").massif("Chartreuse").secteur("Chartreuse").altitude(1920).build();
   }

   /** */
   public void testParseSommet() throws Exception {
      
      Sommet sommet = skitourParser.parseSommet();
      
      verifySommet(expectedSommet(), sommet);
   }
   
   private void verifySommet(Sommet expected, Sommet actual) {
      assertEquals(expected.nom, actual.nom);
      assertEquals(expected.massif, actual.massif);
      assertEquals(expected.secteur, actual.secteur);
      assertEquals(expected.altitude, actual.altitude);
   }

   private Depart expectedDepart() {
      String expectedAcces = "Grenoble -> Col de la Placette -> St Laurent du Pont -> Chartreuse de Curière";
      return aDepart().nom("Chartreuse de Curière").acces(expectedAcces).altitude(853).build();
   }

   /** */
   public void testParseDepart() throws Exception {
      
      Depart depart = skitourParser.parseDepart();
      
      verifyDepart(expectedDepart(), depart);
   }
   
   private void verifyDepart(Depart expectedDepart, Depart actualDepart) {
      assertEquals(expectedDepart.nom, actualDepart.nom);
      assertEquals(expectedDepart.acces, actualDepart.acces);
      assertEquals(expectedDepart.altitude, actualDepart.altitude);
   }

   private Itineraire expectedVariante1() {
      String description = "descendre le couloir direct (50 m au S de la Croix) en face W (env. 120 m "
            + "de déniv. a 45° / 2 passages a 50° - 55°, suivant enneigement = E3) puis descendre "
            + "eventuellement jusqu'au Chalet de Jusson. casque / pioche /crabes / 1 bout de ficelou pas "
            + "forcément inutile";
      return aVariante().voie("Couloir de Jusson en A/R").denivele(1600).difficulteSki("5.1")
            .orientation("W").description(description).build();
   }

   private Itineraire expectedVariante2() {
      String description = "Du sommet, descendre au Cul de Lampe, par des pentes douces dégagées ou en " +
      		"forêt clairsemée. De là, remonter à la Crête des Charmilles, en passant un peu à gauche et" +
      		" au-dessus du col. En descendre le versant Ouest forestier et raide (quelques barres rocheuses)" +
      		" puis vers 1400 m tirer à droite pour rejoindre la route forestière de la None . Redescendre " +
      		"selon les conditions en coupant dans la forêt, où en utilisant la route bien descendante.";
      return aVariante().voie("Huit des Charmilles").denivele(1350).difficulteSki("3.2")
            .orientation("NW").description(description).build();
   }

   /** */
   public void testParseVariantes() throws Exception {
      
      List<Itineraire> variantes = skitourParser.parseVariantes();
      
      verifyVariantes(variantes);
   }
   
   private void verifyVariantes(List<Itineraire> variantes) {
      assertNotNull(variantes);
      assertEquals(2, variantes.size());
      verifyVariante(expectedVariante1(), variantes.get(0));
      verifyVariante(expectedVariante2(), variantes.get(1));
   }

   private void verifyVariante(Itineraire expectedVariante, Itineraire actual) {
      assertEquals(expectedVariante.voie, actual.voie);
      assertEquals(expectedVariante.denivele, actual.denivele);
      assertEquals(expectedVariante.difficulteSki, actual.difficulteSki);
      assertEquals(expectedVariante.orientation, actual.orientation);
      assertEquals(expectedVariante.description, actual.description);
      assertTrue(actual.isVariante());
   }

   private Itineraire expectedItineraire() {
      String description = "Du parking 853m de la Chartreuse de Curière, suivre la route du Col de la Charmette, "
            + "traverser 2 tunnels (Agneaux, Galère) puis prendre à droite le chemin de la Petite Vache. Le "
            + "poursuivre jusqu'au Col de la Charmille (1605). Au col, partir en traversée vers le sud-est et "
            + "rejoindre le Col de la Sure (1675). Du col, rejoindre le sommet de la grande Sure en visant "
            + "dans un premier temps un collet au nord de celui-ci.";
      return anItinerairePrincipal().voie("Par le Col de la Charmille").orientation("N").denivele(1200)
            .difficulteSki("2.2").description(description).difficulteMontee("R").materiel("RAS")
            .exposition(1).pente(30).dureeJour(1).build();
   }

   /** */
   public void testParseItineraire() throws Exception {
      
      Itineraire itineraire = skitourParser.parseItineraire();
      
      verifyItineraire(expectedItineraire(), itineraire);
   }
   
   private void verifyItineraire(Itineraire expected, Itineraire actual) {
      assertEquals(expected.voie, actual.voie);
      assertEquals(expected.orientation, actual.orientation);
      assertEquals(expected.denivele, actual.denivele);
      assertEquals(expected.difficulteSki, actual.difficulteSki);
      assertEquals(expected.description, actual.description);
      assertEquals(expected.difficulteMontee, actual.difficulteMontee);
      assertEquals(expected.materiel, actual.materiel);
      assertEquals(expected.exposition, actual.exposition);
      assertEquals(expected.pente, actual.pente);
      assertEquals(expected.dureeJour, actual.dureeJour);
      assertFalse(actual.isVariante());
   }

   /** */
   public void testCreateTopoGuideWithGeneralInformations() throws Exception {
      
      TopoGuide topo = skitourParser.createTopoGuideWithGeneralInformations();

      assertEquals("Grande Sure, Par le Col de la Charmille", topo.nom);
      assertEquals("139", topo.numero);
      assertEquals("Pas de remarques", topo.remarques);
   }
   
   /** */
   public void testParsePage() throws Exception {

      TopoGuide topo = skitourParser.parsePage();

      assertEquals(skitourParser.parseDepart(), topo.depart);
      assertEquals(skitourParser.parseItineraire(), topo.itineraire);
      assertEquals(skitourParser.parseSommet(), topo.sommet);
      assertEquals(skitourParser.parseVariantes(), topo.variantes);
      assertEquals("Grande Sure, Par le Col de la Charmille", topo.nom);
      assertEquals("139", topo.numero);
      assertEquals("Pas de remarques", topo.remarques);
   }
   
   /** */
   public void testParseImagesUrls() throws Exception {
      
      List<String> imagesUrls = skitourParser.parseImagesUrls();
      
      assertEquals(
            asList("http://www.skitour.fr/photos_topos/2754_v.jpg",
                  "http://www.skitour.fr/photos_topos/4315_v.jpg"), imagesUrls);
   }
}
