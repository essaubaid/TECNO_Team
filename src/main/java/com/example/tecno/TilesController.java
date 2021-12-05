package com.example.tecno;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ProductTileView;

public class TilesController {


    @FXML
    private ImageView image;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private Label sellerName;

    private ProductTileView tile;

    public void setData(ProductTileView tile) {
        this.tile = tile;

        productName.setText(tile.getProductName());
        sellerName.setText(tile.getSellerName());
        productPrice.setText("Rs "+tile.getPrice());
        Image image = new Image(getClass().getResourceAsStream(tile.getImgURL()));
        this.image.setImage(image);
    }

    public void setMenuData(ProductTileView tile) {
        this.tile = tile;

        productName.setText(tile.getProductName());
        sellerName.setText(tile.getSellerName());
        productPrice.setText("Rs "+tile.getPrice());
        Image image = new Image(getClass().getResourceAsStream(tile.getImgURL()));
        this.image.setImage(image);
    }
}
