package Terrain.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
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
import com.mycompany.myapp.entities.Culture;
import com.mycompany.myapp.entities.Terrain;
import com.mycompany.myapp.entities.services.ServiceTerrain;
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
public class ModifierTerrain extends BaseForm {
   String Imagecode;
   String fileName="";
      int idR= 0;

                       ServiceTerrain sp = new ServiceTerrain();
           int selectedId = 0;


    public ModifierTerrain(Resources res,Form previous,Terrain fi) {
        super("Modifier Terrain", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Terrain");
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
        ArrayList<Culture> comptabilites = sp.getAllCultures();
for (Culture comptabilite : comptabilites) {
ComboBox.addItem(String.valueOf(comptabilite.getId()));
}        addStringValue("Choissiez une culture :",ComboBox);

         TextField numero = new TextField();
        numero.setUIID("TextFieldBlack");
        numero.setText(Float.toString(fi.getNumero()));
        addStringValue("Numero", numero);


            TextField surface = new TextField();
                    surface.setText(Float.toString(fi.getSurface()));

        surface.setUIID("TextFieldBlack");
        addStringValue("Surface", surface);

        TextField lieu = new TextField();
                            lieu.setText(fi.getLieu());

        lieu.setUIID("TextFieldBlack");
        addStringValue("Lieu", lieu);
        
        
        
        
        
        
        
        selectedId = fi.getIdCutlure();




         
        Button Ajouter = new Button("Modifier");
        
        
        
          ComboBox.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        int selectedIndex = ComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            selectedId = comptabilites.get(selectedIndex).getId();
        }
    }
}); 
        Ajouter.addActionListener((evt) -> {

                  if (surface.getText().equals("")||(numero.getText().equals("")))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
           

            fi.setNumero((int) Float.parseFloat(numero.getText()));
            fi.setSurface((int) Float.parseFloat(surface.getText()));
            fi.setLieu(lieu.getText());

            sp.editTerrain(fi);
            Dialog.show("Success","Terrain modifier avec success",new Command("OK"));
            new AllTerrain(res).show();
                
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