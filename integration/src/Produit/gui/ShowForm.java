
package Produit.gui;

import Produit.gui.AddForm;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.Terrain;
import com.mycompany.myapp.entities.services.ServiceProduit;
import com.mycompany.myapp.entities.services.ServiceTerrain;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class ShowForm extends BaseForm {
        Form current;
           ImageViewer imgv;

    public ShowForm(Resources res) {
        
        super("Produits", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Produits");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "  ", "", " ");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
                                            
        Button Ajouter = new Button("Ajouter");
        
        
        Ajouter.addActionListener((evt -> new AddForm(this).show()));
        TextField searchField = new TextField("", "Search");
add(searchField);


                            add(Ajouter);

                    ButtonGroup barGroup = new ButtonGroup();
                  Container co=new Container(BoxLayout.xCenter());;
                    ArrayList <Produit> produit = new ArrayList();
                    ServiceProduit sa =new ServiceProduit();
                    produit=sa.getAllProduits();
                    
                    ArrayList<Produit> produitCopy = new ArrayList<>(produit);

            searchField.addActionListener(new ActionListener() {
   

            @Override
            public void actionPerformed(ActionEvent evt) {
                String text = searchField.getText();
    ArrayList<Produit> filteredList = new ArrayList<>();
    for (Produit p : produitCopy) {
        if (p.getNom().toLowerCase().contains(text.toLowerCase())) {
            filteredList.add(p);
        }
    }
    System.out.println("FilteredList size: " + filteredList.size());
    if(filteredList.size()>0){
         removeAll();
                         Button showall = new Button("return");
        showall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            new ShowForm(res).show(); 
            }
        });
                                                                add(showall);

    for (Produit fi : filteredList) {

                            Container ct = new Container(BoxLayout.y());
      
                            Label l = new Label("ID : "+fi.getId());
                            SpanLabel l2 = new SpanLabel("Nom : "+fi.getNom(),"RedLabel");
                            Label l3 = new Label("Prix : "+fi.getPrix(),"SmallLabel");
                            Label mon = new Label("Description : "+fi.getDescription(),"SmallLabel");
                            Label lieu = new Label("Disponnible : "+fi.getStatus(),"SmallLabel");
                            l2.getAllStyles().setFgColor(0xf15f5f);
                            ct.add(l);
                            ct.add(l2);
                            ct.add(l3);
                            ct.add(mon);
                            ct.add(lieu);
                            add(ct);

    }   
    
    }

    revalidate();
            }
        });



        
                    
                    for (Produit fi : produit) {
                            Container ct = new Container(BoxLayout.y());
      
                          Label l = new Label("ID : "+fi.getId());
                            SpanLabel l2 = new SpanLabel("Nom : "+fi.getNom(),"RedLabel");
                            Label l3 = new Label("Prix : "+fi.getPrix(),"SmallLabel");
                            Label mon = new Label("Description : "+fi.getDescription(),"SmallLabel");
                            Label lieu = new Label("Disponible : "+fi.getStatus(),"SmallLabel");
                            l2.getAllStyles().setFgColor(0xf15f5f);
                            ct.add(l);
                            ct.add(l2);
                            ct.add(l3);
                            ct.add(mon);
                            ct.add(lieu);
                            

                            Button Modif = new Button("Modifier");

                            Modif.addActionListener((evt -> new UpdateForm(this,fi).show()));
                            
                            
                            Button Supprimer = new Button("Supprimer");
                            
                          Supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous supprimer cette terrain ?", "Oui", "Annuler")) {

                  if( ServiceProduit.getInstance().deleteProduit(fi.getId()))
                            {
                                Dialog.show("Success","supprimer",new Command("OK"));
                                new ShowForm(res).show();
                            }

                            }
                   
                }   
        });
                       ct.add(Modif);
                       ct.add(Supprimer);


                       Label separator = new Label("","Separator");
                       ct.add(separator);
                       add(ct);
               }
    }
        
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
    }
