package it.unicam.cs.mpgc.rpg130718.gui.controllers;

import it.unicam.cs.mpgc.rpg130718.gui.GestoreFinestre;
import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import it.unicam.cs.mpgc.rpg130718.utils.GestoreDati;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    private GestoreFinestre gestoreFinestre;
    private GestoreDati gestoreDati;

    @FXML
    private Button btnContinua;

    public void setDipendenze(GestoreFinestre gestoreFinestre, GestoreDati gestoreDati) {
        this.gestoreFinestre = gestoreFinestre;
        this.gestoreDati = gestoreDati;

        // Disabilita graficamente il bottone se non c'è il file JSON del salvataggio
        if (!gestoreDati.esisteSalvataggio()) {
            btnContinua.setDisable(true);
        }
    }

    @FXML
    public void onNuovaPartitaClick(ActionEvent event) {
        gestoreFinestre.mostraCreazioneEroe();
    }

    @FXML
    public void onContinuaClick(ActionEvent event) {
        Esploratore eroe = gestoreDati.caricaPartita();
        // Se il file di salvataggio risulta presente, ma corrotto, lo elimina
        if (eroe == null) {
            gestoreDati.cancellaSalvataggio();
            btnContinua.setDisable(true);
            return;
        }
        gestoreFinestre.mostraForesta(eroe);
    }

    @FXML
    public void onEsciClick(ActionEvent event) {
        Platform.exit();
    }
}