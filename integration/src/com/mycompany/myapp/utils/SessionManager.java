/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.utils;

import com.codename1.io.Preferences;

public class SessionManager {
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id;
    private static String nom;
    private static String prenom;
    private static int cin;
    private static String telephone;
    private static String email;
    private static String password;
    private static String roles;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getNom() {
        return pref.get("Nom",nom);
    }

    public static void setNom(String nom) {
         pref.set("Nom",nom);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassword() {
        return pref.get("password",password);
    }

    public static void setPassword(String password) {
         pref.set("password",password);
    }

    public static String getPrenom() {
        return prenom;
    }

    public static int getCin() {
        return cin;
    }

    public static String getTelephone() {
        return telephone;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setPrenom(String prenom) {
        SessionManager.prenom = prenom;
    }

    public static void setCin(int cin) {
        SessionManager.cin = cin;
    }

    public static void setTelephone(String telephone) {
        SessionManager.telephone = telephone;
    }

    public static void setRoles(String roles) {
        SessionManager.roles = roles;
    }
    
    
    
}
