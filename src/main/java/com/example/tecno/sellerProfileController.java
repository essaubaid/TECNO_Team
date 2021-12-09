package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.customerClass;
import model.sellerClass;

public class sellerProfileController {
    @FXML
    private TextField NameTextBox;

    @FXML
    private TextField addressTextBox;

    @FXML
    private TextField emailTextBox;

    @FXML
    private TextField passwordTextBox;

    @FXML
    private TextField phoneTextBox;

    @FXML
    private TextField shopNameTextBox;

    @FXML
    private TextField usernameTextBox;

    @FXML
    void update_adminProfile(ActionEvent event) {

    }

    private int ID;


    public void setData(sellerClass admin) {
        this.ID = admin.getSeller_id();

        usernameTextBox.setText(admin.getUsername());
        passwordTextBox.setText(admin.getPassword());
        NameTextBox.setText(admin.getName());
        shopNameTextBox.setText(admin.getShop_name());
        emailTextBox.setText(admin.getEmail());
        phoneTextBox.setText(admin.getPhone_no());
        addressTextBox.setText(admin.getAddress());
//        Image image = new Image(getClass().getResourceAsStream(tile.getImgURL()));
//
//        this.image.setImage(image);
    }
}
