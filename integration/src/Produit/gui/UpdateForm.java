/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Produit.gui;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.services.ServiceCategorie;
import com.mycompany.myapp.entities.services.ServiceProduit;

import java.util.ArrayList;

public class UpdateForm extends Form {
    private ShowForm showForm;

    public UpdateForm(Form previous,Produit produit) {

        setTitle("Update");
        setLayout(BoxLayout.y());
        Label lblNom = new Label("Nom:");
        TextField tfNom = new TextField(produit.getNom(), "Nom");

        Label lblDescription = new Label("Description:");
        TextField tfDescription = new TextField(produit.getDescription(), "Description");

        Label lblPrix = new Label("Prix:");
        TextField tfPrix = new TextField(String.valueOf(produit.getPrix()), "Prix");
        Label lblCategorieId = new Label("Categorie ID:");
        ComboBox<String> cbCategorie = new ComboBox<>();
        for (Categorie c : ServiceCategorie.getInstance().getAllTasks()) {
            cbCategorie.addItem(String.valueOf(c.getId()));
        }
        cbCategorie.setSelectedIndex(produit.getCategorie_id());
        Label lblStatus = new Label("Statut:");
        CheckBox cbStatus = new CheckBox("Statut");
        cbStatus.setSelected(produit.getStatus());

        Label lblImage = new Label("Image URL:");
        TextField tfImage = new TextField(produit.getImage(), "Image URL");

        tfNom.getAllStyles().setFgColor(0x000000); // set text color to black
        tfDescription.getAllStyles().setFgColor(0x000000); // set text color to black
        tfPrix.getAllStyles().setFgColor(0x000000); // set text color to black
        cbCategorie.getAllStyles().setFgColor(0x000000); // set text color to black
        tfImage.getAllStyles().setFgColor(0x000000); // set text color to black

        tfImage.getAllStyles().setFgColor(0x000000); // set text color to black
        Button btnValider = new Button("Update");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) || (tfDescription.getText().length() == 0)
                        || (tfPrix.getText().length() == 0) || (cbCategorie.getSelectedIndex() == -1)
                        || (tfImage.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        boolean status = cbStatus.isSelected();
                        float prix = Float.parseFloat(tfPrix.getText());
                        int categorieId = Integer.parseInt(cbCategorie.getSelectedItem());
                        Produit p = new Produit(produit.getId(), tfNom.getText(), tfDescription.getText(), status,
                                categorieId, tfImage.getText(), prix);
                        System.out.println(p);
                        boolean i=  ServiceProduit.getInstance().updateProduit(p);
                        if (i) {
                            if (i) {
                                Dialog.show("Success", "Product updated successfully", "OK", null);
                             
                            } else {
                                Dialog.show("Error", "Error updating product", "OK", null);
                            }                        
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Prix and Categorie ID must be numbers", new Command("OK"));
                    }
                }
            }
        });
        btnValider.getAllStyles().setFgColor(0xffffff); // set text color to black
        Button backButton = new Button("Back");
        backButton.addActionListener(evt -> {
            previous.showBack();
        });

        Command backCommand = new Command("Back") {
        @Override
        public void actionPerformed(ActionEvent evt) {
            previous.showBack();
        }
        };

        getToolbar().getAllStyles().setBgColor(0xFF0000); // set toolbar background color to red
        getToolbar().setBackCommand(backCommand); // Set the backCommand as the back command for the toolbar

        getToolbar().getAllStyles().setBgColor(0xFF0000); // set toolbar background color to red
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> {
//            ShowForm.showBack();
//        });
        getToolbar().getAllStyles().setBgColor(0x80FF0000); // set toolbar background color to semi-transparent red
        addAll(lblNom, tfNom, lblDescription, tfDescription, lblPrix, tfPrix, lblCategorieId, cbCategorie, lblStatus, cbStatus, lblImage, tfImage, btnValider,backButton);
    }

}
