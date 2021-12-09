package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.tecno.HelloApplication.conn;

public class setUpShopController {

    @FXML
    private TextField NameTextBox;

    @FXML
    private TextField emailTextBox;

    @FXML
    private TextField passwordTextBox;

    @FXML
    private TextField phoneTextBox;

    @FXML
    private Button setUpButton;

    @FXML
    private TextField shopNameTextBox;

    @FXML
    private Button signInButton;

    @FXML
    private TextField usernameTextBox;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSignIN(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void setUpNewShop(ActionEvent event) throws SQLException {
        String query = "insert into shopkeeper(username, email, password, shopkeeper_name, shop_name, phone_no) values(?, ?, ?, ?, ?, ?)"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
        st.setString(1, usernameTextBox.getText());
        st.setString(2, emailTextBox.getText());
        st.setString(3, passwordTextBox.getText());
        st.setString(4, NameTextBox.getText());
        st.setString(5, shopNameTextBox.getText());
        st.setString(6, phoneTextBox.getText());

        st.executeUpdate();
    }
}
