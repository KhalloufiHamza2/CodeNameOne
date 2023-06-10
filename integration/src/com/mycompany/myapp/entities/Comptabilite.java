/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;
import java.util.Date;

/**
 *
 * @author 21692
 */
public class Comptabilite {

    
    
    private int id;
    
    private Date dateComptabilite;
    
    
    private float valeur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateComptabilite() {
        return dateComptabilite;
    }

    public void setDateComptabilite(Date dateComptabilite) {
        this.dateComptabilite = dateComptabilite;
    }



    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }


        
}
