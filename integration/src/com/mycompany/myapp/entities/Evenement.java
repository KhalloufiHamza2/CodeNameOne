/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.codename1.ui.Image;
import java.util.Date;



/**
 *
 * @author ASUS
 */
public class Evenement {
    private int id;
    private String titre;
    private Date date;
    private String lieu;
    private String image;
    private Date updated_at;
    private Image imageFile;

    public Evenement() {
    }

    public Evenement(int id, String titre, Date date, String lieu, String image, Date updated_at, Image imageFile) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.lieu = lieu;
        this.image = image;
        this.updated_at = updated_at;
        this.imageFile = imageFile;
    }

    
    
    public Evenement(String titre, Date date, String lieu, String image) {
        this.titre = titre;
        this.date = date;
        this.lieu = lieu;
        this.image = image;
    }
    
    public Evenement(int id, String titre, Date date, String lieu, String image, Date updated_at) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.lieu = lieu;
        this.image = image;
        this.updated_at = updated_at;
    }
    


    public Evenement(String titre, Date date, String lieu, String image, Date updated_at) {
        this.titre = titre;
        this.date = date;
        this.lieu = lieu;
        this.image = image;
        this.updated_at = updated_at;
    }

    public Evenement(String titre, Date date, String lieu, String image, Date updated_at, Image imageFile) {
        this.titre = titre;
        this.date = date;
        this.lieu = lieu;
        this.image = image;
        this.updated_at = updated_at;
        this.imageFile = imageFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Image getImageFile() {
        return imageFile;
    }

    public void setImageFile(Image imageFile) {
        this.imageFile = imageFile;
    }

  

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", titre=" + titre + ", date=" + date + ", lieu=" + lieu + ", image=" + image + ", updated_at=" + updated_at + ", imageFile=" + imageFile + '}';
    }

 
    
}
