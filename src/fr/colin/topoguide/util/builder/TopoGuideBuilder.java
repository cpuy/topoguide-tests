package fr.colin.topoguide.util.builder;

import static fr.colin.topoguide.util.builder.DepartBuilder.aDepart;
import static fr.colin.topoguide.util.builder.ItineraireBuilder.anItinerairePrincipal;
import static fr.colin.topoguide.util.builder.SommetBuilder.aSommet;

import java.util.ArrayList;
import java.util.List;

import fr.colin.topoguide.model.Depart;
import fr.colin.topoguide.model.Itineraire;
import fr.colin.topoguide.model.Sommet;
import fr.colin.topoguide.model.TopoGuide;

public class TopoGuideBuilder {

   private long id = -1L;
   private String nom = "Grande Sure, Par le Col de la Charmille";
   private long numero = 139L;
   private String remarques = "Pas de remarque particulière";
   private Sommet sommet = aSommet().build();
   private Depart depart = aDepart().build();
   private List<String> imageUrls = new ArrayList<String>();
   private Itineraire itineraire = anItinerairePrincipal().build();
   private List<Itineraire> variantes = new ArrayList<Itineraire>();
   
   public static TopoGuideBuilder aTopoGuide() {
      return new TopoGuideBuilder();
   }
   
   public TopoGuideBuilder withSommet(Sommet sommet) {
      this.sommet = sommet;
      return this;
   }
   
   public TopoGuideBuilder withDepart(Depart depart) {
      this.depart = depart;
      return this;
   }
   
   public TopoGuideBuilder withVariante(Itineraire variante) {
      this.variantes.add(variante);
      return this;
   }
   
   public TopoGuideBuilder nom(String nom) {
      this.nom = nom;
      return this;
   }
   
   public TopoGuide build() {
      TopoGuide topo = new TopoGuide();
      topo.id = id;
      topo.nom = nom;
      topo.numero = numero;
      topo.remarques = remarques;
      topo.sommet = sommet;
      topo.depart = depart;
      topo.imageUrls = imageUrls;
      topo.itineraire = itineraire;
      topo.variantes = variantes;
      return topo;
   }
}
