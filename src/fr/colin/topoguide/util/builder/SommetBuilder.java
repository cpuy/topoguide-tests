package fr.colin.topoguide.util.builder;

import fr.colin.topoguide.model.Sommet;

public class SommetBuilder {

   private long id = -1L;
   private String nom = "Grande Sure";
   private String massif = "Chartreuse";
   private String secteur = "Chartreuse";
   private int altitude = 1920;

   public static SommetBuilder aSommet() {
      return new SommetBuilder();
   }
   
   public SommetBuilder id(long id) {
      this.id = id;
      return this;
   }
   
   public SommetBuilder nom(String nom) {
      this.nom = nom;
      return this;
   }
   
   public SommetBuilder massif(String massif) {
      this.massif = massif;
      return this;
   }
   
   public SommetBuilder secteur(String secteur) {
      this.secteur = secteur;
      return this;
   }
   
   public SommetBuilder altitude(int altitude) {
      this.altitude = altitude;
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
