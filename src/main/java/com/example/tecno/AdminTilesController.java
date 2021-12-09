package com.example.tecno;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ProductTileView;

import java.io.IOException;

public class AdminTilesController {


    @FXML
    private ImageView image;

    @FXML
    private Label productName;





    private int productID;

    private ProductTileView tile;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setData(ProductTileView tile) {

        this.tile = tile;
        productID=tile.getProductID();
        productName.setText(tile.getProductName());
        if(tile.getImgURL()!=null){
        Image image = new Image(getClass().getResourceAsStream(tile.getImgURL()));

        this.image.setImage(image);}

    }

    public void switchToHomePage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("product-details(admin).fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void setMenuData(ProductTileView tile) {
        this.tile = tile;
        productID= tile.getProductID();
        productName.setText(tile.getProductName());

        Image image = new Image(getClass().getResourceAsStream(tile.getImgURL()));
        this.image.setImage(image);
    }
}
