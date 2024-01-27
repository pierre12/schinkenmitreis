package com.labuhn.minesweeper;

import com.labuhn.minesweeper.domain.Cell;
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

        List<Cell> cells = createDummyFields();

        MineSweeperController controller =  loader.getController();
        controller.setMineFieldCreator(new MineFieldCreator());
        controller.render(cells);
    }

    private static List<Cell> createDummyFields() {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(false, true, 0));
        cells.add(new Cell(false, false, 6));
        cells.add(new Cell(true, true, 0));
        cells.add(new Cell(true, false, 0));
        cells.add(new Cell(true, true, 6));
        cells.add(new Cell(true, false, 6));
        return cells;
    }

}
