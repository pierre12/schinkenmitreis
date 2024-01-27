package com.labuhn.minesweeper;

import com.labuhn.minesweeper.domain.Field;
import com.labuhn.minesweeper.ui.controller.MineFieldCreator;
import com.labuhn.minesweeper.ui.controller.MineSweeperController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MineSweeperApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = getClass().getResource("/com/labuhn/minesweeper/MineSweeper.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();
        primaryStage.setTitle("Mine Sweeper Deluxe");
        primaryStage.setScene(new Scene(root, 450, 450));
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);
        primaryStage.setResizable(true);
        primaryStage.show();

        List<Field> fields = createDummyFields();

        MineSweeperController controller =  loader.getController();
        controller.setMineFieldCreator(new MineFieldCreator());
        controller.render(fields);
    }

    private static List<Field> createDummyFields() {
        List<Field> fields = new ArrayList<>();
        fields.add(new Field(false, true, 0));
        fields.add(new Field(false, false, 6));
        fields.add(new Field(true, true, 0));
        fields.add(new Field(true, false, 0));
        fields.add(new Field(true, true, 6));
        fields.add(new Field(true, false, 6));
        return fields;
    }

}
