package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import model.ProductTileView;
import model.adminClass;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.tecno.HelloApplication.conn;

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

    public void setImageDirectoryI(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        System.out.println(file.getAbsoluteFile());
        File newFile = new File("new.jpg");

        Files.copy(file.toPath(), newFile.toPath());

    }

    public void update_adminProfile(ActionEvent event) throws SQLException {
        String query = "select update_admin_profile(?, ?, ?, ?, ?, ?, ?, ?);"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
        st.setInt(1, this.admin.getAdmin_ID());
        st.setString(2, usernameTextBox.getText());
        st.setString(3, emailTextBox.getText());
        st.setString(4, passwordTextBox.getText());
        st.setString(5, firstNameTextBox.getText());
        st.setString(6, lastNameTextBox.getText());
        st.setString(8, phoneTextBox.getText());
        st.setString(7, addressTextBox.getText());
        st.executeQuery();
    }

}
