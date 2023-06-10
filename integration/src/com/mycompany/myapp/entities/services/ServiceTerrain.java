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
import com.mycompany.myapp.entities.Culture;
import com.mycompany.myapp.entities.Terrain;
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
public class ServiceTerrain {

    public ArrayList<Terrain> terrain;
        public ArrayList<Culture> culture;

    
    public static ServiceTerrain instance=null;
    public boolean resultOK;
    
    private ConnectionRequest req;
    public ServiceTerrain() {
         req = new ConnectionRequest();
    }

    public static ServiceTerrain getInstance() {
        if (instance == null) {
            instance = new ServiceTerrain();
        }
        return instance;
    }
    


    public ArrayList<Terrain> parseTerrains(String jsonText){
                try {

                    System.out.println(jsonText);
            terrain=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Terrain a = new Terrain();
                                       
                
      
              String id = ((Map<String, Object>) obj.get("culture")).get("id").toString();

              String type = ((Map<String, Object>) obj.get("culture")).get("type").toString();
                            
                a.setId((int) Float.parseFloat(obj.get("id").toString()));                
                a.setNumero((int) Float.parseFloat(obj.get("numero").toString()));
                a.setSurface((int) Float.parseFloat(obj.get("surface").toString()));
                a.setLieu(obj.get("lieu").toString());
                a.setIdCutlure((int) Float.parseFloat(id));
                a.setTypeCutlure(type);


                terrain.add(a);
            }
        } catch (IOException ex) {
            
        }
        return terrain;
    }
    
    
        public ArrayList<Culture> parseCultures(String jsonText){
                try {

                    System.out.println(jsonText);
            culture=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Culture a = new Culture();
                                       
                
                  
                a.setId((int) Float.parseFloat(obj.get("id").toString()));   
                a.setType(obj.get("type").toString());


                culture.add(a);
            }
        } catch (IOException ex) {
            
        }
        return culture;
    }
    public ArrayList<Terrain> getAllTerrains(){
        String url = Statics.BASE_URL+"terrain/mobile/all";
       System.out.print(url);
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                terrain = parseTerrains(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return terrain;
    }
    
    public ArrayList<Culture> getAllCultures(){
        String url = Statics.BASE_URL+"terrain/mobile/allCulture";
       System.out.print(url);
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                culture = parseCultures(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return culture;
    }
    


public boolean addTerrain(Terrain u) {
    String url = Statics.BASE_URL + "terrain/mobile/add"
            + "?numero="+u.getNumero()+""
            + "&surface="+u.getSurface()+""
            + "&lieu="+u.getLieu()+""
            + "&culture_id="+u.getIdCutlure()+"";
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


        public boolean editTerrain(Terrain u) {
    String url = Statics.BASE_URL + "terrain/mobile/edit/"+u.getId()+""
            + "?numero="+u.getNumero()+""
            + "&surface="+u.getSurface()+""
            + "&lieu="+u.getLieu()+""
            + "&culture_id="+u.getIdCutlure()+"";


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

    public boolean deleteTerrain(int id) {
    String url = Statics.BASE_URL + "terrain/mobile/delete/"+id;
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
public void email() {
    String url = Statics.BASE_URL + "terrain/mobile/email";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    System.out.println(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            byte[] data = (byte[]) req.getResponseData();

            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueue(req);
}






}
