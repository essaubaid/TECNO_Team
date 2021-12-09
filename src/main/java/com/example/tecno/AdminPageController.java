package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.ProductTileView;
import model.adminClass;
import model.adminProductDetails;

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

    public Button recomendationsButton;
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

    public Connection conn = HelloApplication.conn;
    private List<ProductTileView> tiles = new ArrayList<>();
    private int count =0;
    public adminClass admin;

    public void logOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private List getData() throws SQLException {
        List<ProductTileView> tiles = new ArrayList<>();

        String query = "select * from product_listing;"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
                ResultSet rs = st.executeQuery();


        while(rs.next()){
            ProductTileView tile = new ProductTileView();
            tile.setProductName(rs.getString(2) + " " + rs.getString(3)  );

            tile.setProductID(rs.getInt(1));

            tile.setImgURL(rs.getString(4));

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
    System.out.println(count);
        return tiles;
    }

    public void switchToProfile(ActionEvent event) throws IOException, SQLException {
        Title.setText("My Profile");
        grid.getChildren().removeAll(grid.getChildren());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("view-profile.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        profileController profileController = fxmlLoader.getController();

        System.out.println(1111);

        profileController.setData(getAdminData());



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

    public void switchToAddProduct(ActionEvent event) throws IOException, SQLException {
        Title.setText("My Profile");
        grid.getChildren().removeAll(grid.getChildren());
        addproductController addProduct = new addproductController();
        addProduct.setData();
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

                int finalI = i;
                anchorPane.setOnMouseClicked(
                        click -> {
                            try {
                                Title.setText("Product");
                                grid.getChildren().removeAll(grid.getChildren());

                                FXMLLoader infxmlLoader = new FXMLLoader();
                                infxmlLoader.setLocation(getClass().getResource("product-details(admin).fxml"));
                                AnchorPane inanchorPane = infxmlLoader.load();
                                String query = "select * from (select @input_int:=? p) parm , techno.view_admin_product_detail s;"; // query to check if id exists
                                tiles.get(finalI);
                                PreparedStatement st = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
                                st.setInt(1,tiles.get(finalI).getProductID());
                                ResultSet rs = st.executeQuery();

                                adminProductDetails product = new adminProductDetails();
                                product.setData(rs);


                                AdminProductPageController AdminProductPageController = infxmlLoader.<AdminProductPageController>getController();
                                AdminProductPageController.setData(product);

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
                                infxmlLoader.setLocation(getClass().getResource("product-details(admin).fxml"));
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

    public adminClass getAdminData() throws SQLException {
        adminClass adminClass = new adminClass();

        String query = "select * from (select @input_int:=? p) parm , techno.view_admin_profile_detail s;"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
        st.setInt(1, this.admin.getAdmin_ID());
        System.out.println(st);
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            System.out.println(rs.getInt(2));
            adminClass.setAdmin_ID(rs.getInt(2));
            adminClass.setUsername(rs.getString(3));
            adminClass.setEmail(rs.getString(4));
            adminClass.setPassword(rs.getString(5));
            adminClass.setFirst_name(rs.getString(6));
            adminClass.setLast_name(rs.getString(7));
            adminClass.setAddress(rs.getString(8));
            adminClass.setPhone_no(rs.getString(9));
            adminClass.setAdmin_icon_URL(rs.getString(10));
        }

        return adminClass;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        recomendationsButton.setOnAction(actionEvent -> {
            try {
                switchToRecommendation(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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

                int finalI = i;
                int finalI1 = i;
                anchorPane.setOnMouseClicked(
                        click -> {
                            try {

                                Title.setText("Product");
                                grid.getChildren().removeAll(grid.getChildren());

                                FXMLLoader infxmlLoader = new FXMLLoader();
                                infxmlLoader.setLocation(getClass().getResource("product-details(admin).fxml"));
                                AnchorPane inanchorPane = infxmlLoader.load();
                                String query = "select * from (select @input_int:=? p) parm , techno.view_admin_product_detail s;"; // query to check if id exists
                                tiles.get(finalI);
                                PreparedStatement st = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
                                st.setInt(1,tiles.get(finalI).getProductID());
                                ResultSet rs = st.executeQuery();

                                adminProductDetails product = new adminProductDetails();
                                product.setData(rs);

                                AdminProductPageController AdminProductPageController = infxmlLoader.getController();
                                AdminProductPageController.setData(product);

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
