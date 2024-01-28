package com.labuhn.minesweeper;

import com.labuhn.minesweeper.ui.controller.IconProvider;
import com.labuhn.minesweeper.ui.controller.MineFieldCreator;
import com.labuhn.minesweeper.ui.controller.MineSweeperController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class MineSweeperApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = getClass().getResource("/com/labuhn/minesweeper/MineSweeper.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent mineSweeperUI = loader.load();
        MineSweeperController controller = loader.getController();

        Scene scene = new Scene(mineSweeperUI, 450, 450);
        scene.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("/com/labuhn/minesweeper/MineSweeper.css")).toExternalForm());
        configureStage(primaryStage, scene);


        controller.setMineFieldCreator(new MineFieldCreator(new IconProvider()));
        controller.startGame(16,16);
    }

    private static void configureStage(Stage primaryStage, Scene scene) {
        primaryStage.setTitle("Mine Sweeper Deluxe");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);
        primaryStage.setResizable(true);
        primaryStage.show();
    }


}
