package it.unicam.cs.mpgc.rpg130718.gui.controllers;

import it.unicam.cs.mpgc.rpg130718.gui.GestoreFinestre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FineGiocoController {

    private GestoreFinestre gestoreFinestre;
    private Stage popupStage;

    @FXML
    private Label lblTitolo;

    @FXML
    private Label lblSottotitolo;

    public void setDipendenze(GestoreFinestre gestoreFinestre, String titolo, String sottotitolo) {
        this.gestoreFinestre = gestoreFinestre;
        lblTitolo.setText(titolo);
        lblSottotitolo.setText(sottotitolo);

        // Se l'esito è la sconfitta, il testo compare rosso, altrimenti dorato
        if (titolo.equals("GAME OVER")) {
            lblTitolo.setTextFill(Color.RED);
        } else {
            lblTitolo.setTextFill(Color.GOLD);
        }
    }

    public void setStage(Stage popupStage) {
        this.popupStage = popupStage;
    }

    @FXML
    public void onRicominciaClick(ActionEvent event) {
        popupStage.close();
        gestoreFinestre.mostraMenuPrincipale();
    }
}