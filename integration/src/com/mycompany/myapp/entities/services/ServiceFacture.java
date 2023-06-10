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
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Comptabilite;
import com.mycompany.myapp.entities.Facture;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Display;
import java.io.IOException;
import java.io.OutputStream;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class ServiceFacture {

    public ArrayList<Facture> facture;
        public ArrayList<Comptabilite> comptabilite;

    
    public static ServiceFacture instance=null;
    public boolean resultOK;
    
    private ConnectionRequest req;
    public ServiceFacture() {
         req = new ConnectionRequest();
    }

    public static ServiceFacture getInstance() {
        if (instance == null) {
            instance = new ServiceFacture();
        }
        return instance;
    }
    


    public ArrayList<Facture> parseFactures(String jsonText){
                try {

                    System.out.println(jsonText);
            facture=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Facture a = new Facture();
                                       
                
      
              String id = ((Map<String, Object>) obj.get("comptabilite")).get("id").toString();

              String valeur = ((Map<String, Object>) obj.get("comptabilite")).get("valeur").toString();
                            
                a.setId((int) Float.parseFloat(obj.get("id").toString()));                
                a.setType(obj.get("type").toString());
                a.setMontantTotale((float) Float.parseFloat(obj.get("montantTotale").toString()));
try {
    String dateString = (String) obj.get("dateFacture");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = formatter.parse(dateString);
    a.setDateFacture(date);
} catch (ParseException e) {
    // Handle the exception, such as logging an error message or throwing a custom exception
}                a.setIdContabilite((int) Float.parseFloat(id));
                a.setValeurContabilite((float) Float.parseFloat(valeur));


                facture.add(a);
            }
        } catch (IOException ex) {
            
        }
        return facture;
    }
    
    
        public ArrayList<Comptabilite> parseComptabilites(String jsonText){
                try {

                    System.out.println(jsonText);
            comptabilite=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Comptabilite a = new Comptabilite();
                                       
                
                  
                a.setId((int) Float.parseFloat(obj.get("id").toString()));   
                try {
    String dateString = (String) obj.get("date_comptabilite");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = formatter.parse(dateString);
    a.setDateComptabilite(date);
} catch (ParseException e) {
    // Handle the exception, such as logging an error message or throwing a custom exception
}  
                
                
                a.setValeur((float) Float.parseFloat(obj.get("valeur").toString()));


                comptabilite.add(a);
            }
        } catch (IOException ex) {
            
        }
        return comptabilite;
    }
    public ArrayList<Facture> getAllFactures(){
        String url = Statics.BASE_URL+"facture/mobile/all";
       System.out.print(url);
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                facture = parseFactures(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return facture;
    }
    
    public ArrayList<Comptabilite> getAllComptabilites(){
        String url = Statics.BASE_URL+"facture/mobile/allComptabilite";
       System.out.print(url);
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                comptabilite = parseComptabilites(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return comptabilite;
    }
    


public boolean addFacture(Facture u) {
    String url = Statics.BASE_URL + "facture/mobile/add"
            + "?type="+u.getType()+""
            + "&montantTotale="+u.getMontantTotale()+""
            + "&comptabiliteId="+u.getIdContabilite()+""
            + "&dateFacture="+u.getDatee()+"";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    System.out.println(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; // Code HTTP 200 OK
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}


        public boolean editFacture(Facture u) {
    String url = Statics.BASE_URL + "facture/mobile/edit/"+u.getId()+""
            + "?type="+u.getType()+""
            + "&montantTotale="+u.getMontantTotale()+""
            + "&comptabiliteId="+u.getIdContabilite()+""
            + "&dateFacture="+u.getDatee()+"";


    req.setUrl(url);
    System.out.println(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; // HTTP OK status
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    }

    public boolean deleteFacture(int id) {
    String url = Statics.BASE_URL + "facture/mobile/delete/"+id;
    req.setUrl(url);
    req.setHttpMethod("DELETE");
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    }

public boolean pdf() {
    String url = Statics.BASE_URL + "facture/pdf";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    System.out.println(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            byte[] data = (byte[]) req.getResponseData();
            try {
                String filePath = FileSystemStorage.getInstance().getAppHomePath() + "facture.pdf";
OutputStream os = FileSystemStorage.getInstance().openOutputStream(filePath);

                os.write(data);
                os.close();
                // Display the PDF file using the default PDF viewer
                Display.getInstance().execute(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            resultOK = req.getResponseCode() == 200; 
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}


}
