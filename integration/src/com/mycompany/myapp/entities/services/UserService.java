/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.WalkthruForm;
import com.mycompany.myapp.utils.SessionManager;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Vector;


/**
 *
 * @author malek
 */
public class UserService {
    
    
   
     //singleton 
    public static UserService instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static UserService getInstance() {
        if(instance == null )
            instance = new UserService();
        return instance ;
    }
    
    
    
    public UserService() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField nom,TextField prenom,TextField email,TextField telephone ,TextField password,TextField cin, Resources res  ) {
        
     
        
        String url = Statics.BASE_URL+"utilisateur/mobile/adduser?nom="+nom.getText().toString()+ "&prenom="+prenom.getText().toString()+"&telephone="+telephone.getText().toString()+"&email="+email.getText().toString()+"&password="+password.getText().toString()+"&cin="+cin.getText().toString();
        
        req.setUrl(url);
       
        //Control saisi
        if(nom.getText().equals(" ")&& prenom.getText().equals(" ") && cin.getText().equals(" ")&& telephone.getText().equals(" ")&& password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData(); 
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
    
    //SignIn
    
    public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"utilisateur/mobile/loginAction?Email="+email.getText().toString()+"&Password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                 new WalkthruForm(rs).show();
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                
                SessionManager.setPassword(user.get("Password").toString());
                SessionManager.setNom(user.get("Nom").toString());
                SessionManager.setEmail(user.get("Email").toString());
                
               
                
          
              
                System.out.println("currnt user ==>"+SessionManager.getEmail()+","+SessionManager.getPassword()+","+SessionManager.getNom());
                
                
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    
    
    
    
    
    
      public static void EditUser(int id, String Nom,String Prenom, String Password, String Email) {
        String url = Statics.BASE_URL+"utilisateur/mobile/updateUser?id="+id+"&Nom="+Nom+"&Preom="+Prenom+"&Password="+Password+"&Email="+Email;
        MultipartRequest req = new MultipartRequest();
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("id",String.valueOf(SessionManager.getId()));
        req.addArgument("Nom",Nom);
        req.addArgument("Prenom",Prenom);
        req.addArgument("Password",Password);
        req.addArgument("Email",Email);
        System.out.println(Email);
        req.addResponseListener((response)-> {
            
            byte[] data = (byte[]) response.getMetaData();
            String s = new String(data);
            System.out.println(s);
            //if(s.equals("success")){}
            //else {
                //Dialog.show("Erreur","Echec de modification", "Ok", null);
            //}
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

 
    public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"utilisateur/mobile/updatepassword?email="+email;
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
    
    public boolean deleteUser(int id ) {
        String url = Statics.BASE_URL +"utilisateur/mobile/deletedisUser?id="+(int)id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
    
}
