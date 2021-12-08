package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ProductTileView;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.tecno.HelloApplication.conn;

public class loginPageController {

    @FXML
    private Button SignupButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordTextBox;

    @FXML
    private Button setShopButton;

    @FXML
    private TextField usernameTextBox;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSignUP(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signupPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void initiateLogin(ActionEvent event) throws SQLException, IOException {
        String username = usernameTextBox.getText();
        String password = passwordTextBox.getText();
        String Return = "";

        String query = "select verifyLogin(?, ?);"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query); //creating and preparing statements

        st.setString(1, "essa");
        st.setString(2, "1234");
        ResultSet rs = st.executeQuery();


        while(rs.next()){
            Return = rs.getString(1);
        }

        switch (Return){
            case "admin":
                switchToAdminPage(event);
                break;
            case "user":
                switchToHomePage(event);
                break;
            case "shopkeeper":
                break;
        }

    }

    public void switchToSetShop(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("setUpShop.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToHomePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToAdminPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("adminHomePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}