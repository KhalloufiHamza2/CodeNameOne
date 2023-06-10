/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.services.EventService;
import java.util.ArrayList;


/**
 *
 * @author ASUS
 */
public class IndexEvent extends BaseForm{
    
 

    public IndexEvent(Resources res) {
        
        super("Evenements", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Listes des evenemnts");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

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
                
     
        


        ButtonGroup barGroup = new ButtonGroup();
                  Container co=new Container(BoxLayout.xCenter());;
                    ArrayList <Evenement> evenement = new ArrayList();
                    EventService sa =new EventService();
                    evenement=sa.getAllEvent();
                    
              

            TextField searchField = new TextField("", "Search");
add(searchField);

                    
                    ArrayList<Evenement> evenementCopy = new ArrayList<>(evenement);

        searchField.addActionListener(new ActionListener() {
   

            @Override
            public void actionPerformed(ActionEvent evt) {
                String text = searchField.getText();
    ArrayList<Evenement> filteredList = new ArrayList<>();
    for (Evenement t : evenementCopy) {
        if (t.getTitre().toLowerCase().contains(text.toLowerCase())) {
            filteredList.add(t);
        }
    }
    System.out.println("FilteredList size: " + filteredList.size());
    if(filteredList.size()>0){
         removeAll();
                         Button showall = new Button("return");
        showall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            new IndexEvent(res).show(); 
            }
        });
                                                                add(showall);


    for (Evenement fi: filteredList) {

                            Container ct = new Container(BoxLayout.y());
      
                            Label l = new Label("ID : "+fi.getId());
                            Label l3 = new Label("Titre: "+fi.getTitre());
                            Label mon = new Label("Date: "+fi.getDate());
                            Label lieu = new Label("Lieu: "+fi.getLieu());
                            Label image = new Label ("Image: "+fi.getImage());
                            Label updated = new Label ("Updated_at: "+fi.getUpdated_at());
                            ct.add(l);
                            ct.add(l3);
                            ct.add(mon);
                            ct.add(lieu);
                            ct.add(image);
                            ct.add(updated);

    }   
    
    }

    revalidate();
            }
        });
         for (Evenement fi: evenement) {

                            Container mm = new Container(BoxLayout.y());
      
                            Label l = new Label("ID : "+fi.getId());
                            Label l3 = new Label("Titre: "+fi.getTitre());
                            Label mon = new Label("Date: "+fi.getDate());
                            Label lieu = new Label("Lieu: "+fi.getLieu());
                            Label image = new Label ("Image: "+fi.getImage());
                            Label updated = new Label ("Updated_at: "+fi.getUpdated_at());
                            mm.add(l);
                            mm.add(l3);
                            mm.add(mon);
                            mm.add(lieu);
                            mm.add(image);
                            mm.add(updated);

                
         
         }
         
    }
         }
