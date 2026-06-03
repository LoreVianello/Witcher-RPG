package it.unicam.cs.mpgc.rpg130718.gui.controllers;

import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;

public class BestiarioController {

    private Stage popupStage;
    private List<Mostro> mostriVisualizzati;
    private int indiceCorrente = 0;

    @FXML
    private Label lblNomeMostro;

    @FXML
    private Label lblDettagli;

    @FXML
    private Button btnPrecedente;

    @FXML
    private Button btnSuccessivo;

    public void setStage(Stage popupStage) {
        this.popupStage = popupStage;
    }

    public void setDipendenze(Esploratore eroe) {
        // Recuperiamo la lista intera senza alcun filtro
        this.mostriVisualizzati = eroe.getBestiario().getMostriScoperti();
        this.indiceCorrente = 0;
        visualizzaMostro();
    }

    private void visualizzaMostro() {
        if (mostriVisualizzati == null || mostriVisualizzati.isEmpty()) {
            lblNomeMostro.setText("Bestiario Vuoto");
            lblDettagli.setText("Non hai ancora registrato alcun mostro nel tuo diario.\nEsplora la foresta per iniziare la ricerca!");
            btnPrecedente.setDisable(true);
            btnSuccessivo.setDisable(true);
            return;
        }

        Mostro mostro = mostriVisualizzati.get(indiceCorrente);
        lblNomeMostro.setText(mostro.getNome());

        String dettagli = "CATEGORIA: " + mostro.getCategoria() + "\n" +
                "DEBOLEZZA: " + mostro.getDebolezza() + "\n\n" +
                "PUNTI VITA BASE: " + mostro.getPuntiVita() + "\n" +
                "DANNO BASE: " + mostro.getDanno() + "\n" +
                "Numero: " + (indiceCorrente + 1) + " di " + mostriVisualizzati.size();

        lblDettagli.setText(dettagli);

        // Disabilita i pulsati Precedente e Successivo in caso non ce ne siano
        btnPrecedente.setDisable(indiceCorrente == 0);
        btnSuccessivo.setDisable(indiceCorrente == mostriVisualizzati.size() - 1);
    }

    @FXML
    public void onPrecedenteClick(ActionEvent event) {
        if (indiceCorrente > 0) {
            indiceCorrente--;
            visualizzaMostro();
        }
    }

    @FXML
    public void onSuccessivoClick(ActionEvent event) {
        if (indiceCorrente < mostriVisualizzati.size() - 1) {
            indiceCorrente++;
            visualizzaMostro();
        }
    }

    @FXML
    public void onChiudiClick(ActionEvent event) {
        popupStage.close();
    }
}