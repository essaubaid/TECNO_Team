package com.example.tecno;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ProductTileView;
import model.stockTile;

public class stockTileController {
    @FXML
    private ImageView Image;

    @FXML
    private CheckBox inStockBox;

    @FXML
    private Label productDescription;

    @FXML
    private Label productNameLabel;

    @FXML
    private Label productRam;

    @FXML
    private Label productStorage;

    @FXML
    private TextField warrantyTextBox;

    public int productID;

    public void setData(stockTile tile) {

        this.productNameLabel.setText(tile.getProductName());
        this.productID=tile.getProductID();
        productRam.setText(tile.getRam());
        productStorage.setText(tile.getStorage());
        productDescription.setText(tile.getDescription());
        warrantyTextBox.setText(tile.getWarranty());
        inStockBox.setSelected(tile.isInStock());
        System.out.println(tile.getImgURL());
        if(tile.getImg().isError()==false){

            this.Image.setImage(tile.getImg());}

    }
}
