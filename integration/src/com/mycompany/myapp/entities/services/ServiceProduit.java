
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ServiceProduit {
    public ArrayList<Produit> produits;

    public ConnectionRequest request;
    private static ServiceProduit instance;
    public boolean resultOK;

    public ServiceProduit() {
        this.request = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if(instance== null)
            instance = new ServiceProduit();
        return instance;
    }

    public ArrayList<Produit> parseProduits(String jsonText) {
        try {
            ArrayList<Produit> produits = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> produitsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) produitsListJson.get("root");

            for (Map<String, Object> obj : list) {
                Produit p = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                p.setNom(obj.get("nom").toString());
                p.setDescription(obj.get("description").toString());
                p.setStatus(Boolean.parseBoolean(obj.get("status").toString()));
                Random random = new Random(5);
                p.setCategorie_id(3);
                p.setImage(obj.get("image").toString());
                p.setPrix(Float.parseFloat(String.valueOf(obj.get("prix"))));
                produits.add(p);
            }return produits;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public ArrayList<Produit> getAllProduits() {
        String url = Statics.BASE_URL + "getall/produitsJson";
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        System.out.println(produits);
        return produits;
    }

    public boolean addProduit (Produit produit){
        String nom = produit.getNom();
        boolean status =  produit.getStatus();
        String desc = produit.getDescription();
        float prix = produit.getPrix();
        int categorie = produit.getCategorie_id();
        String image = produit.getImage();
        String  value =null;
        if(status)
            value = "yes" ;
        else
            value= "no";

        String url = Statics.BASE_URL+"produit/produitJson/new"
                +"?nom="+nom+"&image="+image+"&categorie_id="+categorie+"&prix="+prix+"&status="+value+"&description="+desc;
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
    }

    public boolean deleteProduit(int id) {
        try {
            String url = Statics.BASE_URL + "produit/produitJsonDelete/" + id;
            System.out.println(url);
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(url);
            request.setPost(false);

            request.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    if (request.getResponseCode() == 200) {
                        // Success
                        System.out.println("Produit deleted successfully");
                    } else {
                        // Error
                        System.out.println("Error deleting produit");
                    }
                    request.removeResponseListener(this);
                }
            });

            NetworkManager.getInstance().addToQueueAndWait(request);

            return request.getResponseCode() == 200;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateProduit(Produit produit) {
        try {
            String nom = produit.getNom();
            boolean status = produit.getStatus();
            String desc = produit.getDescription();
            float prix = produit.getPrix();
            int categorie = produit.getCategorie_id();
            String image = produit.getImage();
            String value = status ? "yes" : "no";

        String url = Statics.BASE_URL + "produit/produitJsonUpdate/" + produit.getId()
                + "?nom=" + nom
                + "&image=" + image
                + "&categorie_id=" + categorie
                + "&prix=" + prix
                + "&status=" + value
                + "&description=" + desc;
            System.out.println(url);
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(url);
            request.setPost(false);

            request.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    if (request.getResponseCode() == 200) {
                        // Success
                        System.out.println("Produit updated successfully");
                    } else {
                        // Error
                        System.out.println("Error updating produit");
                    }
                    request.removeResponseListener(this);
                }
            });

            NetworkManager.getInstance().addToQueueAndWait(request);

            return request.getResponseCode() == 200;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Produit> searchProduits(String query) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "search/produitsJson?nom=" + query);
        con.setHttpMethod("GET");

        NetworkManager.getInstance().addToQueueAndWait(con);

        if (con.getResponseCode() == 200) {
            JSONParser parser = new JSONParser();
            try {
                ArrayList<Produit> produits = new ArrayList<>();
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(con.getResponseData()), "UTF-8"));
                ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) response.get("root");
                for (Map<String, Object> obj : list) {
                    int id = (int) Float.parseFloat(obj.get("id").toString());
                    String nom = obj.get("nom").toString();
                    String description = obj.get("description").toString();
                    boolean status = Boolean.parseBoolean(obj.get("status").toString());
                    float prix = Float.parseFloat(obj.get("prix").toString());
                    int categorie_id = 3;
                    String image = obj.get("image").toString();
                    produits.add(new Produit(id, nom, description ,status,categorie_id, image, prix));
                }
                System.out.println("Base"+produits);
                return produits;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }



}
