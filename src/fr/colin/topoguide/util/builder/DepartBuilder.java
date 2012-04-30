package fr.colin.topoguide.util.builder;

import fr.colin.topoguide.model.Depart;

public class DepartBuilder {

   private long id = -1L;
   private String nom = "Chartreuse de Curière";
   private String acces = "Grenoble -> Col de la Placette -> St Laurent du Pont -> Chartreuse de Curière";
   private int altitude = 853;
   
   public static DepartBuilder aDepart() {
      return new DepartBuilder();
   }
   
   public DepartBuilder id(long id) {
      this.id = id;
      return this;
   }
   
   public DepartBuilder nom(String nom) {
      this.nom = nom;
      return this;
   }
   
   public DepartBuilder acces(String acces) {
      this.acces = acces;
      return this;
   }
   
   public DepartBuilder altitude(int altitude) {
      this.altitude = altitude;
      return this;
   }
   
   public Depart build() {
      Depart depart = new Depart();
      depart.id = id;
      depart.nom = nom;
      depart.acces = acces;
      depart.altitude = altitude;
      return depart;
   }
}
