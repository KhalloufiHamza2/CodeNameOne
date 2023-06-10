/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package Equipement.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.mycompany.gui.BaseForm;
import com.mycompany.myapp.entities.employe;
import com.mycompany.myapp.entities.equipement;
import com.mycompany.myapp.utils.Mailling;
import com.mycompany.myapp.entities.services.ServiceEquipement;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class AddEquipement extends BaseForm {

                       equipement fi = new equipement();
String fileName ="";
                    ServiceEquipement sp = new ServiceEquipement();
           int selectedId = 0;
           Mailling m=new Mailling();


    public AddEquipement(Resources res,Form previous) {
        super("Ajouter Equipement", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Equipement");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
                add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(2, 
                            facebook, twitter
                    )
                )
        ));


                        
    

        ComboBox<String> ComboBox = new ComboBox<>();
        ArrayList<employe> employes = sp.getAllEmployes();
for (employe employe : employes) {
ComboBox.addItem(String.valueOf(employe.getId()));
}
        addStringValue("Choissiez un employe :",ComboBox);

       




        TextField type = new TextField();
        type.setUIID("TextFieldBlack");
        addStringValue("Type", type);


            TextField marque = new TextField();
        marque.setUIID("TextFieldBlack");
        addStringValue("Marque", marque);

        
        Picker disponible = new Picker();
        disponible.setType(Display.PICKER_TYPE_STRINGS);
        disponible.setStrings("oui","non");
        disponible.setUIID("TextFieldBlack");
        addStringValue("Disponibilité", disponible);
        
        TextField etat = new TextField();
        etat.setUIID("TextFieldBlack");
        addStringValue("etat", etat);
         
       
        TextField matricule = new TextField();
        matricule.setUIID("TextFieldBlack");
        addStringValue("Matricule", matricule);
        
        
        Button Ajouter = new Button("Ajouter");
        
        
           System.err.print(employes);
          ComboBox.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        int selectedIndex = ComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
             selectedId = employes.get(selectedIndex).getId();
            fi.setIdEmploye(selectedId);
        }
    }
}); 
           
           
           
           
           
           
        Ajouter.addActionListener((evt) -> {

            
            if (marque.getText().equals("")||etat.getText().equals("")||(type.getText().equals("")))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
           
            if(selectedId == 0){
          Dialog.show("Alert", "Please select employe", new Command("OK"));

            }else{
                          
                fi.setType(type.getText());   
                fi.setMarque(marque.getText());
                fi.setEtat(etat.getText());
                fi.setMatricule(matricule.getText());           
                if ( disponible.getText().equals("oui")){
                    fi.setDisponnible(true);
                }else if ( disponible.getText().equals("non")){
                    fi.setDisponnible(false);
                }
            sp.addEquipement(fi);
            Dialog.show("Success","Equipement Ajouter avec success",new Command("OK"));
              m.sendemaill();
              new AllEquipement(res).show() ;
            }
;
                
            }      
        });

        addStringValue("", FlowLayout.encloseRightMiddle(Ajouter));
        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }


}
