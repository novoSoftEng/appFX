package models;

import java.sql.Date;
import java.time.LocalDate;


public class Commande {
  Long id_commande;

  public Commande(Long id_client, Date dateCommande) {
    this.id_client = id_client;
    this.dateCommande= dateCommande;
  }
  public Commande(Long id_commande,Long id_client,Date dateCommande) {
    this.id_commande=id_commande;
    this.id_client = id_client;
    this.dateCommande=dateCommande;
  }
  public Commande(Long id_commande,Long id_client,LocalDate dateCommande) {
    this.id_commande=id_commande;
    this.id_client = id_client;
    this.dateCommande=Date.valueOf(dateCommande);
  }
  public Commande(Long id_client, LocalDate dateCommande) {
    this.id_client = id_client;
    this.dateCommande=Date.valueOf(dateCommande) ;
  }


  public Long getId_commande() {
    return id_commande;
  }

  public void setId_commande(Long id_commande) {
    this.id_commande = id_commande;
  }

  public Date dateCommande;
  public Long id_client;

  public Long getId_client() {
    return id_client;
  }

  public void setId_client(Long id_client) {
    this.id_client = id_client;
  }

  public Date getDateCommande() {
    return dateCommande;
  }

  public void setDateCommande(Date dateCommande) {
    this.dateCommande = dateCommande;
  }
  /**
    * 
    *
   */


}