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
import model.adminClass;
import model.productDetails;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

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

    private Connection conn = HelloApplication.conn;
    private List<ProductTileView> tiles = new ArrayList<>();
    private int count =0;
    public adminClass admin;

    private List getData() throws SQLException {
        List<ProductTileView> tiles = new ArrayList<>();

        String query = "select * from product_listing;"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
                ResultSet rs = st.executeQuery();


        while(rs.next()){
            ProductTileView tile = new ProductTileView();
            tile.setProductName(rs.getString(1) + " " + rs.getString(2)  );
            tile.setSellerName("by Pawan Kumar");
            tile.setPrice(90000);

            tile.setImgURL(rs.getString(3));

//            tile.setImg(new Image(getClass().getResourceAsStream("/img/google_pixel_2_XL.jpg")));

            tiles.add(tile);

            count++;
           /* tile.setProductName("Yeezys");
            tile.setSellerName("by Pawan Kumar");
            tile.setPrice(90000);
            tile.setImgURL("/img/Adidas-Yeezy.png");

            tiles.add(tile);
            tile = new ProductTileView();*/
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

    public void switchToAddProduct(ActionEvent event) throws IOException {
        Title.setText("My Profile");
        grid.getChildren().removeAll(grid.getChildren());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("add-product.fxml"));
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

    public void switchToSetUpShop(ActionEvent event) throws IOException {
        Title.setText("My Profile");
        grid.getChildren().removeAll(grid.getChildren());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("setUpShop.fxml"));
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

    public void switchToReports(ActionEvent event) throws IOException {
        Title.setText("Reports");
        grid.getChildren().removeAll(grid.getChildren());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Reports.fxml"));
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
        try {
            tiles.addAll(getData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int column = 0;
        int row = 1;

        for(int i = 0; i < count; i++){
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

                                String query = "select prod_name, Ram, image_directory from techno.product" +
                                        " where prod_id=7;"; // query to check if id exists

                                PreparedStatement st = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
                                ResultSet rs = st.executeQuery();

                                productDetails product = new productDetails();
                                product.setData(rs);

                                ProductPageController productPageController = infxmlLoader.getController();
                                productPageController.setData(product);

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
                            } catch (SQLException e) {
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

    private class connection {
    }
}
