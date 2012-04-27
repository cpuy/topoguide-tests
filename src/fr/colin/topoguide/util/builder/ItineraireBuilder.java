package fr.colin.topoguide.util.builder;

import fr.colin.topoguide.model.Itineraire;

public class ItineraireBuilder {

   private long id = -1L;
   public String voie = "Par le Col de la Charmille";
   public String orientation = "N";
   public int denivele = 1200;
   public String difficulteSki = "2.2";
   public String difficulteMontee = "R";
   public String description = "Du parking 853m de la Chartreuse de Curière, suivre la route du Col de la " +
   		"Charmette, traverser 2 tunnels (Agneaux, Galère) puis prendre à droite le chemin de la Petite " +
   		"Vache. Le poursuivre jusqu'au Col de la Charmille (1605). Au col, partir en traversée vers le " +
   		"sud-est et rejoindre le Col de la Sure (1675). Du col, rejoindre le sommet de la grande Sure en " +
   		"visant dans un premier temps un collet au nord de celui-ci.";
   public String materiel = "RAS";
   public int exposition = 1;
   public int pente = 30;
   public int dureeJour = 1;
   public long topoId = -1L;
   private boolean variante = false;
   
   public static ItineraireBuilder anItinerairePrincipal() {
      ItineraireBuilder itineraireBuilder = new ItineraireBuilder();
      itineraireBuilder.variante = false;
      return itineraireBuilder;
   }
   
   public static ItineraireBuilder aVariante() {
      ItineraireBuilder itineraireBuilder = new ItineraireBuilder();
      itineraireBuilder.variante = true;
      return itineraireBuilder;
   }
   
   public Itineraire build() {
      Itineraire itineraire;
      if (variante) {
         itineraire = Itineraire.variante();
      } else {
         itineraire = Itineraire.principal();
      }
      itineraire.id = id;
      itineraire.voie = voie;
      itineraire.orientation = orientation;
      itineraire.denivele = denivele;
      itineraire.difficulteSki = difficulteSki;
      itineraire.difficulteMontee = difficulteMontee;
      itineraire.description = description;
      itineraire.materiel = materiel;
      itineraire.exposition = exposition;
      itineraire.pente = pente;
      itineraire.dureeJour = dureeJour;
      itineraire.topoId = topoId;
      return itineraire;
   }
   
   public ItineraireBuilder withTopoId(long topoId) {
      this.topoId = topoId;
      return this;
   }
}
