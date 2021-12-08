package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.productDetails;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductPageController implements Initializable {

    @FXML
    private Label sellerShopno;
    @FXML
    private Label sellerShop;
    @FXML
    private Label sellerPhone;
    @FXML
    private TextField rating;
    @FXML
    private Label productDescription;
    @FXML
    private Label productRam;
    @FXML
    private Label productStorage;
    @FXML
    private Label productWarranty;
    @FXML
    private ImageView Image;

    @FXML
    private Label productNameLabel;

    @FXML
    private Label sellerNameLabel;

    @FXML
    private Label bestprice;


    private Stage stage;
    private Scene scene;
    private Parent root;

    private productDetails productdetails;

    public void setData(productDetails productdetails){
        this.productNameLabel.setText(productdetails.getProductName());
        this.sellerNameLabel.setText(productdetails.getSellerName());
        this.bestprice.setText("RS "+productdetails.getPrice());
        this.productDescription.setText(productdetails.getDescription());
        this.productRam.setText(productdetails.getRam());
        this.productStorage.setText(productdetails.getStorage());
        this.productWarranty.setText(productdetails.getWarranty());
        this.Image.setImage(productdetails.getImg());
        this.sellerPhone.setText(productdetails.getPhoneNo());
        this.sellerShop.setText(productdetails.getShopName());
        this.sellerShopno.setText(productdetails.getShopNo());



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
