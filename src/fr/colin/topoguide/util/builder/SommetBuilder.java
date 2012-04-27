package fr.colin.topoguide.util.builder;

import fr.colin.topoguide.model.Sommet;

public class SommetBuilder {

   public long id = -1L;
   public String nom = "Grande Sure";
   public String massif = "Chartreuse";
   public String secteur = "Chartreuse";
   public int altitude = 1920;

   public static SommetBuilder aSommet() {
      return new SommetBuilder();
   }
   
   public SommetBuilder withId(long id) {
      this.id = id;
      return this;
   }
   
   public Sommet build() {
      Sommet sommet = new Sommet();
      sommet.id = id;
      sommet.nom = nom;
      sommet.massif = massif;
      sommet.secteur = secteur;
      sommet.altitude = altitude;
      return sommet;
   }
}
