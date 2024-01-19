package com.labuhn.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MineSweeperApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = getClass().getResource("/com/labuhn/minesweeper/MineSweeper.fxml");
        Parent root = FXMLLoader.load(resource);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.setWidth(300);
        primaryStage.setHeight(450);
        primaryStage.show();
    }

}
