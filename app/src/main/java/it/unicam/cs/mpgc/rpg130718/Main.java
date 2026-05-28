package it.unicam.cs.mpgc.rpg130718;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Creiamo un semplice testo
        Label label = new Label("Il cammino del Witcher inizia qui! Bestiario pronto.");

        // Lo mettiamo al centro di un pannello
        StackPane root = new StackPane(label);

        // Creiamo la scena (la finestra) di dimensioni 600x400
        Scene scene = new Scene(root, 600, 400);

        // Impostiamo il palcoscenico (Stage)
        stage.setTitle("The Witcher - RPG");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}