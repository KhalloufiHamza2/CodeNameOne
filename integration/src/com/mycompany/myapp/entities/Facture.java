/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;
import java.util.Date;

/**
 *
 * @author msi
 */
public class Facture {
    
    private int id;    
    private String type;
    private float montantTotale;
    private Date dateFacture;
    private String datee;
    private int idContabilite;
    private float ValeurContabilite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getMontantTotale() {
        return montantTotale;
    }

    public void setMontantTotale(float montantTotale) {
        this.montantTotale = montantTotale;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public int getIdContabilite() {
        return idContabilite;
    }

    public void setIdContabilite(int idContabilite) {
        this.idContabilite = idContabilite;
    }

    public float getValeurContabilite() {
        return ValeurContabilite;
    }

    public void setValeurContabilite(float ValeurContabilite) {
        this.ValeurContabilite = ValeurContabilite;
    }

  
    
    

}
