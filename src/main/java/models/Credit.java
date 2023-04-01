package models;

import java.sql.Date;

public class Credit {
    Long id_credit;
    Long id_client;
    double montant;
    Date DateCredit;
    Boolean etat;

    public Credit(Long id_credit,Long id_client, double montant ,Date dateCredit, Boolean etat ) {
        this.id_credit = id_credit;
        this.id_client=id_client;
        DateCredit = dateCredit;
        this.montant=montant;
        this.etat=etat;
    }

    public Credit( double montant ,Date dateCredit, Boolean etat ) {
        DateCredit = dateCredit;
        this.montant=montant;
        this.etat=etat;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Long getId_credit() {
        return id_credit;
    }

    public void setId_credit(Long id_credit) {
        this.id_credit = id_credit;
    }

    public Date getDateCredit() {
        return DateCredit;
    }

    public void setDateCredit(Date dateCredit) {
        DateCredit = dateCredit;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
}
