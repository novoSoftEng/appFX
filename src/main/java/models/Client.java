package models;



public class Client {

  Long id_client;
  public String nom;

  public String prenom;
  public String NumTelephone;

  Double CreditTotal;

  public Double getCreditTotal() {
    return CreditTotal;
  }

  public void setCreditTotal(Double creditTotal) {
    CreditTotal = creditTotal;
  }

  public String nom() {
    return nom;
  }

  public Client(String nom , String prenom , String NumTelephone, Double CreditTotal) {

    this.nom = nom;
    this.prenom=prenom;
    this.NumTelephone=NumTelephone;
    this.CreditTotal=CreditTotal;
  }
  public Client(String nom , String prenom , String NumTelephone) {

    this.nom = nom;
    this.prenom=prenom;
    this.NumTelephone=NumTelephone;
  }
  public Client(Long id,String nom , String prenom , String NumTelephone) {

    this.nom = nom;
    this.prenom=prenom;
    this.NumTelephone=NumTelephone;
    this.id_client= id;
  }
  public Client setNom(String nom) {
    this.nom = nom;
    return this;
  }

  public String getNumTelephone() {
    return NumTelephone;
  }

  public void setNumTelephone(String numTelephone) {
    NumTelephone = numTelephone;
  }



  public String getNom() {
    return nom;
  }
  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }
  public Long getId_client() {
    return id_client;
  }

  public void setId_client(Long id_client) {
    this.id_client = id_client;
  }
}