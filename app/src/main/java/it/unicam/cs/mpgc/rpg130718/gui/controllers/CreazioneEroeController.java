package it.unicam.cs.mpgc.rpg130718.gui.controllers;

import it.unicam.cs.mpgc.rpg130718.gui.GestoreFinestre;
import it.unicam.cs.mpgc.rpg130718.model.esploratori.*;
import it.unicam.cs.mpgc.rpg130718.utils.GestoreDati;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreazioneEroeController {

    private GestoreFinestre gestoreFinestre;
    private GestoreDati gestoreDati;

    @FXML
    private TextField txtNome;

    @FXML
    private ComboBox<String> cmbClasse;

    public void setDipendenze(GestoreFinestre gestoreFinestre, GestoreDati gestoreDati) {
        this.gestoreFinestre = gestoreFinestre;
        this.gestoreDati = gestoreDati;

        cmbClasse.getItems().addAll("Witcher", "Strega", "Bardo", "Umano");
        // Seleziona il Witcher di default
        cmbClasse.getSelectionModel().selectFirst();
    }

    @FXML
    public void onConfermaClick(ActionEvent event) {
        String nomeInserito = txtNome.getText().trim();
        if (nomeInserito.isEmpty()) {
            nomeInserito = "Sconosciuto";
        }

        String classeScelta = cmbClasse.getValue();

        // Nella Classe TipoEsploratore viene fatta la ricerca della classe tramite Stringa, evitando uno switch
        Esploratore nuovoEroe = it.unicam.cs.mpgc.rpg130718.utils.TipoEsploratore.creaEroe(classeScelta, nomeInserito);

        gestoreFinestre.mostraForesta(nuovoEroe);
    }
}