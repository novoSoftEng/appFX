package models;


public class Produit {
  Long id_produit;
  public String nomProduit;

  public String fornisseur;

  public Produit(Long id , String nom , String fornisseur){
    id_produit=id;
    nomProduit=nom;
    this.fornisseur=fornisseur;
  }
  public Produit(String nom , String fornisseur){
    nomProduit=nom;
    this.fornisseur=fornisseur;
  }

  public Long getId_produit() {
    return id_produit;
  }

  public void setId_produit(Long id_produit) {
    this.id_produit = id_produit;
  }

  public String getFornisseur() {
    return fornisseur;
  }

  public void setFornisseur(String fornisseur) {
    this.fornisseur = fornisseur;
  }

  public String getNomProduit() {
    return nomProduit;
  }

  public void setNomProduit(String nomProduit) {
    this.nomProduit = nomProduit;
  }



    /**
    * 
    *
   */


}