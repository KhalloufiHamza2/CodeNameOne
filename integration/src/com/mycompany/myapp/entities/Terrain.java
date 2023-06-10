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
public class Terrain {
    
    private int id;    
    private int numero;
    private int surface;
    private String lieu;
    private int idCutlure;
    private String TypeCutlure;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getIdCutlure() {
        return idCutlure;
    }

    public void setIdCutlure(int idCutlure) {
        this.idCutlure = idCutlure;
    }

    public String getTypeCutlure() {
        return TypeCutlure;
    }

    public void setTypeCutlure(String TypeCutlure) {
        this.TypeCutlure = TypeCutlure;
    }

 
  
    
    

}
