package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label Title;

    @FXML
    private MenuButton dropDown;

    @FXML
    private Button favoritesButton;

    @FXML
    private Button letSeeButton;

    private List<ProductTileView> tiles = new ArrayList<>();


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

    public void switchToProfile(ActionEvent event) throws IOException {
        Title.setText("My Profile");
        grid.getChildren().removeAll(grid.getChildren());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("view-profile.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        grid.add(anchorPane, 1, 1); //(child,column,row)
        //set grid width
        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
        grid.setMaxWidth(Region.USE_PREF_SIZE);

        //set grid height
        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
        grid.setMaxHeight(Region.USE_PREF_SIZE);

        GridPane.setMargin(anchorPane, new Insets(10, 5, 10, 70));
    }

    public void switchToRecommendation(ActionEvent event) throws IOException {
        Title.setText("My Recommendation");
        grid.getChildren().removeAll(grid.getChildren());

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

                anchorPane.setOnMouseClicked(
                        click -> {
                            try {
                                Title.setText("Product");
                                grid.getChildren().removeAll(grid.getChildren());

                                FXMLLoader infxmlLoader = new FXMLLoader();
                                infxmlLoader.setLocation(getClass().getResource("product-details(ahmad).fxml"));
                                AnchorPane inanchorPane = infxmlLoader.load();

                                grid.add(inanchorPane, 1, 1); //(child,column,row)
                                //set grid width
                                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                                grid.setMaxWidth(Region.USE_PREF_SIZE);

                                //set grid height
                                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                                grid.setMaxHeight(Region.USE_PREF_SIZE);

                                GridPane.setMargin(inanchorPane, new Insets(10, 5, 10, 70));
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );

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


    public void switchToFavorites(ActionEvent event) throws IOException {
        Title.setText("My Favorites");
        int column = 0;
        int row = 1;

        grid.getChildren().removeAll(grid.getChildren());
        for(int i = 0; i < tiles.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Tile.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                anchorPane.setOnMouseClicked(
                        click -> {
                            try {
                                Title.setText("Product");
                                grid.getChildren().removeAll(grid.getChildren());

                                FXMLLoader infxmlLoader = new FXMLLoader();
                                infxmlLoader.setLocation(getClass().getResource("product-details(ahmad).fxml"));
                                AnchorPane inanchorPane = infxmlLoader.load();

                                grid.add(inanchorPane, 1, 1); //(child,column,row)
                                //set grid width
                                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                                grid.setMaxWidth(Region.USE_PREF_SIZE);

                                //set grid height
                                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                                grid.setMaxHeight(Region.USE_PREF_SIZE);

                                GridPane.setMargin(inanchorPane, new Insets(10, 5, 10, 70));
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );

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

            anchorPane.setOnMouseClicked(
                    click -> {
                        try {
                            Title.setText("Product");
                            grid.getChildren().removeAll(grid.getChildren());

                            FXMLLoader infxmlLoader = new FXMLLoader();
                            infxmlLoader.setLocation(getClass().getResource("product-details(ahmad).fxml"));
                            AnchorPane inanchorPane = infxmlLoader.load();

                            grid.add(inanchorPane, 1, 1); //(child,column,row)
                            //set grid width
                            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                            grid.setMaxWidth(Region.USE_PREF_SIZE);

                            //set grid height
                            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                            grid.setMaxHeight(Region.USE_PREF_SIZE);

                            GridPane.setMargin(inanchorPane, new Insets(10, 5, 10, 70));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );

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
