package fr.colin.topoguide.util.builder;

import fr.colin.topoguide.model.Itineraire;

public class ItineraireBuilder {

   private long id = -1L;
   private String voie = "Par le Col de la Charmille";
   private String orientation = "N";
   private int denivele = 1200;
   private String difficulteSki = "2.2";
   private String difficulteMontee = "R";
   private String description = "Du parking 853m de la Chartreuse de Curière, suivre la route du Col de la " +
   		"Charmette, traverser 2 tunnels (Agneaux, Galère) puis prendre à droite le chemin de la Petite " +
   		"Vache. Le poursuivre jusqu'au Col de la Charmille (1605). Au col, partir en traversée vers le " +
   		"sud-est et rejoindre le Col de la Sure (1675). Du col, rejoindre le sommet de la grande Sure en " +
   		"visant dans un premier temps un collet au nord de celui-ci.";
   private String materiel = "RAS";
   private int exposition = 1;
   private int pente = 30;
   private int dureeJour = 1;
   private long topoId = -1L;
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
   
   public ItineraireBuilder voie(String voie) {
      this.voie = voie;
      return this;
   }
   
   public ItineraireBuilder denivele(int denivele) {
      this.denivele = denivele;
      return this;
   }
   
   public ItineraireBuilder difficulteSki(String difficulteSki) {
      this.difficulteSki = difficulteSki;
      return this;
   }
   
   public ItineraireBuilder orientation(String orientation) {
      this.orientation = orientation;
      return this;
   }
   
   public ItineraireBuilder description(String description) {
      this.description = description;
      return this;
   }
   
   public ItineraireBuilder difficulteMontee(String difficulteMontee) {
      this.difficulteMontee = difficulteMontee;
      return this;
   }
   
   public ItineraireBuilder materiel(String materiel) {
      this.materiel = materiel;
      return this;
   }
   
   public ItineraireBuilder exposition(int exposition) {
      this.exposition = exposition;
      return this;
   }
   
   public ItineraireBuilder pente(int pente) {
      this.pente = pente;
      return this;
   }
   
   public ItineraireBuilder dureeJour(int dureeJour) {
      this.dureeJour = dureeJour;
      return this;
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
