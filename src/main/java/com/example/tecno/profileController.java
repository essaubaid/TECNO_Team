package com.example.tecno;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import model.ProductTileView;
import model.adminClass;

import java.net.URL;
import java.util.ResourceBundle;

public class profileController {

    @FXML
    private TextField emailTextBox;

    @FXML
    private TextField firstNameTextBox;

    @FXML
    private TextField lastNameTextBox;

    @FXML
    private TextField passwordTextBox;

    @FXML
    private TextField phoneTextBox;

    @FXML
    private TextField usernameTextBox;

    @FXML
    private TextField addressTextBox;

    private adminClass admin;

    public void setData(adminClass admin) {
        this.admin = admin;

        usernameTextBox.setText(admin.getUsername());
        passwordTextBox.setText(admin.getPassword());
        firstNameTextBox.setText(admin.getFirst_name());
        lastNameTextBox.setText(admin.getLast_name());
        emailTextBox.setText(admin.getEmail());
        phoneTextBox.setText(admin.getPhone_no());
        addressTextBox.setText(admin.getAddress());
//        Image image = new Image(getClass().getResourceAsStream(tile.getImgURL()));
//
//        this.image.setImage(image);
    }

}
