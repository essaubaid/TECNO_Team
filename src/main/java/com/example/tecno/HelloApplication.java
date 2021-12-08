package com.example.tecno;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

import java.io.IOException;

public class HelloApplication extends Application {

    static Connection conn;

    @Override
    public void start(@NotNull Stage stage) throws IOException, SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/techno","root","DB@1234");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("TECNO");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}