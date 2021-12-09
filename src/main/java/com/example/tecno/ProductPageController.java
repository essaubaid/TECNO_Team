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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.example.tecno.HelloApplication.conn;

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
    private int customer_id;
    private int product_id;

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

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public void addToFavorites(ActionEvent event) throws SQLException {
        System.out.println("This is running");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(customer_id+"Error");
        System.out.println(product_id+"Error");

        String query = "INSERT INTO Favorites (prod_id, cust_id, date_of_saved) " +
                "VALUES (?, ?, ?);"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
        st.setInt(1, product_id);
        st.setInt(2, customer_id);
        st.setString(3, dtf.format(now));

        st.executeUpdate();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
