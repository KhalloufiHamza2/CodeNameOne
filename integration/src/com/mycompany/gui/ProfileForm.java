/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.services.UserService;
import com.mycompany.myapp.utils.SessionManager;

/**
 *
 * @author user
 */
public class ProfileForm extends BaseForm{
   
     public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(true);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Button modiff = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));
        
        String us = SessionManager.getNom();
        System.out.println(us);

        TextField Nom = new TextField(us);
        Nom.setUIID("TextFieldBlack");
        addStringValue("Nom", Nom);
        
        TextField Prenom = new TextField(us);
        Nom.setUIID("TextFieldBlack");
        addStringValue("Prenom", Prenom);
        
        TextField Email = new TextField(SessionManager.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        Email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", Email);
        
        TextField Password = new TextField(SessionManager.getPassword(), "Password", 20, TextField.PASSWORD);
        Password.setUIID("TextFieldBlack");
        addStringValue("Password", Password);
        
        Supprimer.setUIID("Update");
        modiff.setUIID("Edit");
        addStringValue("",Supprimer);
        addStringValue("",modiff);

        modiff.addActionListener((ActionEvent edit)->{
        InfiniteProgress ip = new InfiniteProgress();
        //final Dialog ipDlg = ip.showInifinieteBlooking();
            UserService.EditUser(SessionManager.getId(), Nom.getText(), Prenom.getText(),Password.getText(), Email.getText());
        SessionManager.setNom(Nom.getText());
        SessionManager.setPassword(Password.getText());
        SessionManager.setEmail(Email.getText());
        Dialog.show("Succes","Modifications des coordonnees avec succes","OK",null);
       // ipDlg.dispose();
        refreshTheme();
         
    });
        
        Supprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer Votre Compte ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
               
                if(UserService.getInstance().deleteUser(SessionManager.getId())) {
                    new SignInForm(res).show();
                }
           
        });
        
        

        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        
        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
}
