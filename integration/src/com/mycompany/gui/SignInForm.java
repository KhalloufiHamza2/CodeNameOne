/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;


import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.services.UserService;


/**
 *
 * @author user
 */
public class SignInForm extends BaseForm {
    public SignInForm(Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
           
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
        
        TextField email = new TextField("", "Email", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        
        //mp oublié
        Button  mp = new Button("oublier mot de passe?","CenterLabel");
        
        
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Vous n'avez aucune compte?");
        
        
        
        
        
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp),mp
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        
        signIn.addActionListener(e -> 
        {
               UserService.getInstance().signin(email, password, res);
               //new WalkthruForm(res).show();

           
        });
        
        
        
        
        
    }
}
