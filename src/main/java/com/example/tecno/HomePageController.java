package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
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

public class HomePageController implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private MenuButton dropDown;

    @FXML
    private Button favoritesButton;

    private List<ProductTileView> tiles = new ArrayList<>();

    int column = 0;
    int row = 1;

    private List getData(){
        List<ProductTileView> tiles = new ArrayList<>();
        ProductTileView tile = new ProductTileView();

        for(int a = 0; a < 6; a++){
            tile.setProductName("Google Pixel 2 XL");
            tile.setSellerName("by Pawan Kumar");
            tile.setPrice(90000);
            tile.setImgURL("/img/google_pixel_2_XL.jpg");
//            tile.setImg(new Image(getClass().getResourceAsStream("/img/google_pixel_2_XL.jpg")));
            tiles.add(tile);
            tile = new ProductTileView();

            tile.setProductName("Yeezys");
            tile.setSellerName("by Pawan Kumar");
            tile.setPrice(90000);
            tile.setImgURL("/img/Adidas-Yeezy.png");

            tiles.add(tile);
            tile = new ProductTileView();
        }

        return tiles;
    }

    public void switchToFavorites(ActionEvent event) throws IOException {

        grid.getChildren().removeAll(grid.getChildren());
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
                if(tiles.get(i).getProductName().equals("Yeezys")){
                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10, 5, 10, 5));
                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tiles.addAll(getData());
        column = 0;
        row = 1;

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
