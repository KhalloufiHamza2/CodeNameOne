/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;



public class Produit {
    private int id;
    private String nom;
    private String description;
    private boolean status;
    private float prix;
    private int categorie_id;
    private String image;



    public Produit(int id, String nom, String description, boolean status, int categorie_id, String image, float prix) {
        this.id=id;
        this.nom = nom;
        this.description = description;
        this.status = status;
        this.categorie_id = categorie_id;
        this.image = image;
        this.prix = prix;
    }
    public Produit( String nom, String description, boolean status, int categorie_id, String image, float prix) {
        this.nom = nom;
        this.description = description;
        this.status = status;
        this.categorie_id = categorie_id;
        this.image = image;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;

    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean getStatus() {
        return this.status;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", prix=" + prix +
                ", categorie_id=" + categorie_id +
                ", image='" + image + '\'' +
                '}';
    }

    public Produit() {
    }
}

