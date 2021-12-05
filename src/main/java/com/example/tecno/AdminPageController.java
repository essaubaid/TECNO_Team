package com.example.tecno;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import model.ProductTileView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    private List<ProductTileView> tiles = new ArrayList<>();

    private List getData(){
        List<ProductTileView> tiles = new ArrayList<>();
        ProductTileView tile = new ProductTileView();

        for(int a = 0; a < 12; a++){
            tile.setProductName("Google Pixel 2 XL");
            tile.setSellerName("by Pawan Kumar");
            tile.setPrice(90000);
            tile.setImgURL("/img/google_pixel_2_XL.jpg");
//            tile.setImg(new Image(getClass().getResourceAsStream("/img/google_pixel_2_XL.jpg")));

            tiles.add(tile);
        }

        return tiles;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tiles.addAll(getData());
        int column = 0;
        int row = 1;

        for(int i = 0; i < tiles.size(); i++){
            try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Tile.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            TilesController tilesController = fxmlLoader.getController();
            tilesController.setData(tiles.get(i));

            if(column==3){
                column=0;
                row++;
            }


            grid.add(anchorPane, column++, row); //(child,column,row)
            //set grid width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            //set grid height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10, 5, 10,5));

            }
             catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
