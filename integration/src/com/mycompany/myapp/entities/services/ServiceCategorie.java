/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceCategorie {
    public ArrayList<Categorie> categories;
    public static ServiceCategorie instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCategorie() {
        this.req = new ConnectionRequest();
    }

    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }

    public ArrayList<Categorie> parseTasks(String jsonText) {
        try {
            categories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Categorie c = new Categorie();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                c.setLabel((obj.get("label").toString()));
                if (obj.get("label") == null) {
                    c.setLabel("null");
                } else {
                    c.setLabel(obj.get("label").toString());
                }
                categories.add(c);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }

    public ArrayList<Categorie> getAllTasks() {
        String url = Statics.BASE_URL + "categorie/getall/categoriesJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }
}



