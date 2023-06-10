/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.gui;


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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.myapp.entities.services.EventService;
import java.util.Date;


/**
 *
 * @author user
 */
public class AddEvent extends BaseForm {

    public AddEvent(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
               
     
     
        
        TextField titre = new TextField("", "Titre", 20, TextField.ANY);
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);
        date.setUIID("TextFieldBlack");

        TextField lieu = new TextField("", "Lieu", 20, TextField.ANY);
        TextField image = new TextField("", "Image", 20, TextField.EMAILADDR);
        Picker updated_at = new Picker();
        updated_at.setType(Display.PICKER_TYPE_DATE);
        updated_at.setUIID("TextFieldBlack");
       
         
       
       
        Button Ajouter = new Button("Ajouter");
        Ajouter.setUIID("Link");
        
         Container content = BoxLayout.encloseY(
                new Label("Ajouter Evenement", "LogoLabel"),
                new FloatingHint(titre),
                createLineSeparator(),
                new Label("Date Evenement", "PaddedLabel"),
                date,
                createLineSeparator(),
                new FloatingHint(lieu),
                createLineSeparator(),
                new FloatingHint(image),
                createLineSeparator(),
                new Label("Date Evenement", "PaddedLabel"),
                updated_at,
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                Ajouter
        ));
        Ajouter.requestFocus();
        Ajouter.addActionListener((e) -> {

            Date date99 = date.getDate();
            Date updated_at99 = updated_at.getDate();

            EventService.getInstance().AddEvent(titre, date99, lieu, image, updated_at99, res);
            Dialog.show("Success","account is saved","OK",null);
            new AddEvent(res).show();
        });
    }
    }
