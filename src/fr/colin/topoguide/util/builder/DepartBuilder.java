package fr.colin.topoguide.util.builder;

import fr.colin.topoguide.model.Depart;

public class DepartBuilder {

   private long id = -1L;
   private String nom = "Chartreuse de Curière";
   private String access = "Grenoble -> Col de la Placette -> St Laurent du Pont -> Chartreuse de Curière";
   private int altitude = 853;
   
   public static DepartBuilder aDepart() {
      return new DepartBuilder();
   }
   
   public Depart build() {
      Depart depart = new Depart();
      depart.id = id;
      depart.nom = nom;
      depart.acces = access;
      depart.altitude = altitude;
      return depart;
   }

   public DepartBuilder withId(long id) {
      this.id = id;
      return this;
   }
}
