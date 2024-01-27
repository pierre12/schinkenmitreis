module minesweeper {
    requires javafx.fxml;
    requires javafx.controls;

    opens com.labuhn.minesweeper.ui.controller to javafx.graphics,javafx.fxml;
    opens com.labuhn.minesweeper to javafx.graphics;

    exports com.labuhn.minesweeper.ui.controller;
    exports com.labuhn.minesweeper.domain;
}