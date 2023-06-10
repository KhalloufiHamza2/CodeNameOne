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

public class AddForm extends Form {
    public AddForm(Form previous) {
        setTitle("Add");
        setLayout(BoxLayout.y());

        Label lblNom = new Label("Nom:");
        TextField tfNom = new TextField("", "Nom");
        Label lblDescription = new Label("Description:");
        TextField tfDescription = new TextField("", "Description");
        Label lblPrix = new Label("Prix:");
        TextField tfPrix = new TextField("", "Prix");
        Label lblCategorieId = new Label("Categorie ID:");
        ComboBox<String> cbCategorie = new ComboBox<>();
        for (Categorie c : ServiceCategorie.getInstance().getAllTasks()) {
            cbCategorie.addItem(String.valueOf(c.getId()));
        }        CheckBox cbStatus = new CheckBox("Statut");
        Label lblImage = new Label("Image URL:");
        TextField tfImage = new TextField("", "Image URL");
        Button btnValider = new Button("Add");

        tfNom.getUnselectedStyle().setFgColor(0x000000);
        tfDescription.getUnselectedStyle().setFgColor(0x000000);
        tfPrix.getUnselectedStyle().setFgColor(0x000000);
        cbCategorie.getUnselectedStyle().setFgColor(0x000000);
        tfImage.getUnselectedStyle().setFgColor(0x000000);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) || (tfDescription.getText().length() == 0)
                        || (tfPrix.getText().length() == 0) ||  (cbCategorie.getSelectedIndex() == -1)
                        || (tfImage.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        boolean status = cbStatus.isSelected();
                        float prix = Float.parseFloat(tfPrix.getText());
                        int categorieId = Integer.parseInt(cbCategorie.getSelectedItem());
                        Produit p = new Produit(tfNom.getText(), tfDescription.getText(), status,
                                categorieId, tfImage.getText(),prix);
                        System.out.println(p);
                        if (ServiceProduit.getInstance().addProduit(p)) {
                            Dialog.show("Success", "Produit ajouté avec succès", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Prix and Categorie ID must be numbers", new Command("OK"));
                    }

                }
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> {
            previous.showBack();
        });
        addAll(lblNom, tfNom, lblDescription, tfDescription, lblPrix, tfPrix,
                lblCategorieId, cbCategorie, cbStatus, lblImage, tfImage, btnValider);    }
}
