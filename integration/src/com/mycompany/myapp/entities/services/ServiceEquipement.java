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
import com.mycompany.myapp.entities.employe;
import com.mycompany.myapp.entities.equipement;
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
import org.json.JSONArray;

/**
 *
 * @author msi
 */
public class ServiceEquipement {

    public ArrayList<equipement> equipement;
        public ArrayList<employe> employe;

    
    public static ServiceEquipement instance=null;
    public boolean resultOK;
    
    private ConnectionRequest req;
    public ServiceEquipement() {
         req = new ConnectionRequest();
    }

    public static ServiceEquipement getInstance() {
        if (instance == null) {
            instance = new ServiceEquipement();
        }
        return instance;
    }
    


    public ArrayList<equipement> parseEquipements(String jsonText){
                try {

                    System.out.println(jsonText);
            equipement=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                equipement a = new equipement();
                                       
           
                a.setId((int) Float.parseFloat(obj.get("id").toString()));   
                a.setIdEmploye((int) Float.parseFloat(obj.get("employe_id").toString()));
                a.setType(obj.get("type").toString());
                a.setMarque(obj.get("marque").toString());
                a.setDisponnible(Boolean.parseBoolean(obj.get("disponnible").toString()));
                a.setEtat(obj.get("etat").toString());
                a.setMatricule(obj.get("matricule").toString());


                                


                equipement.add(a);
            }
        } catch (IOException ex) {
            
        }
        return equipement;
    }
    
    
        public ArrayList<employe> parseEmployes(String jsonText){
                try {

                    System.out.println(jsonText);
            employe=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                employe a = new employe();
                                       
                
                  
                a.setId((int) Float.parseFloat(obj.get("id").toString()));   


                employe.add(a);
            }
        } catch (IOException ex) {
            
        }
        return employe;
    }
    public ArrayList<equipement> getAllEquipements(){
        String url = Statics.BASE_URL+"equipement/mobile/all";
       System.out.print(url);
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                equipement = parseEquipements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return equipement;
    }
    
    public ArrayList<employe> getAllEmployes(){
        String url = Statics.BASE_URL+"equipement/mobile/employes-without-equipement";
       System.out.print(url);
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                employe = parseEmployes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return employe;
    }
    


public boolean addEquipement(equipement u) {
    String url = Statics.BASE_URL + "equipement/mobile/add"
            + "?type="+u.getType()+
            "&marque="+u.getMarque()+
            "&disponnible="+u.isDisponnible()+
            "&etat="+u.getEtat()+
            "&matricule="+u.getMatricule()+
            "&employe_id="+u.getIdEmploye()+"";
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


        public boolean editEquipement(equipement u) {
    String url = Statics.BASE_URL + "equipement/mobile/edit/"+u.getId()+""
            + "?type="+u.getType()+
            "&marque="+u.getMarque()+
            "&disponnible="+u.isDisponnible()+
            "&etat="+u.getEtat()+
            "&matricule="+u.getMatricule()+
            "&employe_id="+u.getIdEmploye()+"";
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

    public boolean deleteEquipement(int id) {
    String url = Statics.BASE_URL + "equipement/mobile/delete/"+id;
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

}
