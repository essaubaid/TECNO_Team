package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.productDetails;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductPageController implements Initializable {

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label phoneNoLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Text productNameLabel;

    @FXML
    private Label ramLabel;

    @FXML
    private Label sellerNameLabel;

    @FXML
    private Label shopNameLabel;

    @FXML
    private Label shopNoLabel;

    @FXML
    private Label storageLabel;

    @FXML
    private Label warrantyLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private productDetails productdetails;

    public void setData(){
        productNameLabel.setText("Google Pixel 2 XL");

        priceLabel.setText("Rs "+ 90000);
        ramLabel.setText("8GB");
        storageLabel.setText("256GB");
        warrantyLabel.setText("1 Year");

        sellerNameLabel.setText("Pawan Kumar");
        shopNameLabel.setText("Pawan Electronics");
        phoneNoLabel.setText("0300-0000000");
        shopNoLabel.setText("23");

        descriptionLabel.setText("Example");
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
        setData();
    }
}
