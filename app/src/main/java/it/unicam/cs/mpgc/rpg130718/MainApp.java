package it.unicam.cs.mpgc.rpg130718;

import it.unicam.cs.mpgc.rpg130718.gui.GestoreFinestre;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        GestoreFinestre gestoreFinestre = new GestoreFinestre(primaryStage);

        gestoreFinestre.mostraMenuPrincipale();
    }

    public static void main(String[] args) {
        launch(args);
    }
}