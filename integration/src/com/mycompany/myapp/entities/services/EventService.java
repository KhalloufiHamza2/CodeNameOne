/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class EventService {
    
    public static EventService instance = null;
    public static boolean resultOK = true;
    private ConnectionRequest rq;
     public ArrayList<Evenement> evenement;
     
    
    public static EventService getInstance(){
        if (instance == null)
            instance = new EventService();
        return instance;
    }
    
    
     public EventService() {
        rq = new ConnectionRequest();
        
    }
    
     public void AddEvent(TextField titre, Date date, TextField lieu, TextField image, Date updated_at, Resources res  ) {
       
        Date date1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateText = formatter.format(date1);

        Date date101 = new Date();
        SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString11 = formatt.format(date101);

       
        String url = Statics.BASE_URL+"/evenement/mobile/ajout" 
                + "?titre=" +titre.getText()+""
                + "&date=" +dateText+""
                + "&lieu=" +lieu.getText()+""
                + "&image="+image.getText()+""
                + "&updated_at="+dateString11+"";

        rq.setUrl(url);
       
        //Control saisi
        if(titre.getText().equals(" ") && dateText.equals(" ") && lieu.getText().equals(" ") && image.getText().equals(" ") && dateString11.equals(" ")) {
           
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
           
        }
       
        //hethi wa9t tsir execution ta3 url
        rq.addResponseListener((e1)-> {
         
            //njib data ly7atithom fi form
            byte[]data = (byte[]) e1.getMetaData();
            String responseData = new String(data);
           
            System.out.println("data ===>"+responseData);
        }
        );
       
       
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
         NetworkManager.getInstance().addToQueueAndWait(rq);
    }
           
    public ArrayList<Evenement> parseEvenements(String jsonText) throws ParseException {
    try {
        System.out.println(jsonText);
        ArrayList<Evenement> evenement = new ArrayList<>();
        
        JSONParser parser = new JSONParser();
        Map<String, Object> eventListJson = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) eventListJson.get("root");
        
        for (Map<String, Object> obj : list) {
            Evenement e = new Evenement();
            e.setId(Integer.parseInt(obj.get("id").toString()));
            e.setTitre(obj.get("titre").toString());
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(obj.get("date").toString());
            e.setDate(date);
            
            e.setLieu(obj.get("lieu").toString());
            e.setImage(obj.get("image").toString());
            
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date updated_at = dateFormat1.parse(obj.get("updated_at").toString());
            e.setUpdated_at(updated_at);
            
            evenement.add(e);
        }
        
        return evenement;
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    
    return null;
}

     
     
     
     
     

         public ArrayList<Evenement> getAllEvent(){
        String url = Statics.BASE_URL+"evenement/mobile/index1";
       System.out.print(url);
        rq.setUrl(url);
        rq.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    evenement = parseEvenements(new String(rq.getResponseData()));
                    rq.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(rq);
        return evenement;
    }

    public boolean deleteTerrain(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }


    
    
