package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.adminProductDetails;
import model.productDetails;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.tecno.HelloApplication.conn;

public class AdminProductPageController implements Initializable {


    @FXML
    private Label productDescription;
    @FXML
    private Label productRam;
    @FXML
    private Label productStorage;

    @FXML
    private ImageView Image;

    @FXML
    private Label productNameLabel;

    private int productID;


    private Stage stage;
    private Scene scene;
    private Parent root;

    private productDetails productdetails;

    public void setData(adminProductDetails productdetails){
        this.productNameLabel.setText(productdetails.getProductName());
        this.productDescription.setText(productdetails.getDescription());
        this.productRam.setText(productdetails.getRam());
        this.productStorage.setText(productdetails.getStorage());
        this.productID=productdetails.getProductID();
        this.Image.setImage(productdetails.getImg());

    }

    public void delete() throws SQLException {
        String query =  "DELETE FROM product WHERE prod_ID = ?;"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
        st.setInt(1,this.productID);
        st.executeUpdate();



    }



    public void switchToSignIN(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToHomePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToViewProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToViewFavorites(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void getData(){

    }
    public void SearchEngine(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
