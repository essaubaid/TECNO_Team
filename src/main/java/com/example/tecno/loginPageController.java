package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ProductTileView;
import model.adminClass;
import model.customerClass;
import model.sellerClass;

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
    private PasswordField passwordTextBox;

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

        st.setString(1, username);
        // Ye delete karna mat bholna
        st.setString(2, password);
        ResultSet rs = st.executeQuery();


        while(rs.next()){
            Return = rs.getString(1);
        }

        switch (Return){
            case "admin":
                query = "select * from administrator where username = ?;";
                st = conn.prepareStatement(query);
                st.setString(1, username);

                rs = st.executeQuery();

                adminClass admin = new adminClass();
                admin.setData(rs);
                System.out.println(admin.getUsername()+"1");

                switchToAdminPage(event, admin);
                break;
            case "user":
                query = "select * from customer where username = ?;";
                st = conn.prepareStatement(query);
                st.setString(1, username);

                rs = st.executeQuery();

                customerClass customer = new customerClass();
                customer.setData(rs);
                System.out.println(customer.getUsername()+"1");

                switchToHomePage(event, customer);

                break;
            case "shopkeeper":
                query = "select * from shopkeeper where username = ?;";
                st = conn.prepareStatement(query);
                st.setString(1, username);

                rs = st.executeQuery();

                sellerClass sellerClass = new sellerClass();
                sellerClass.setData(rs);

                switchToSellerHomePage(event, sellerClass);
                break;
        }

    }

    public void switchToSellerHomePage(ActionEvent event, sellerClass seller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sellerHomePage.fxml"));
        root = fxmlLoader.load();
        sellerHomePageController sellerPageController = fxmlLoader.getController();
        sellerPageController.seller = seller;
        sellerPageController.initialized();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToHomePage(ActionEvent event, customerClass customer) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        root = fxmlLoader.load();
        HomePageController homePageController = fxmlLoader.getController();
        homePageController.customer = customer;

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToAdminPage(ActionEvent event, adminClass admin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminHomePage.fxml"));
        root = fxmlLoader.load();
        AdminPageController adminPageController = fxmlLoader.getController();
        adminPageController.admin = admin;

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}