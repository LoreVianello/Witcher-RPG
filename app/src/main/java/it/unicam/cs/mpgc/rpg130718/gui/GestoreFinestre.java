package it.unicam.cs.mpgc.rpg130718.gui;

import it.unicam.cs.mpgc.rpg130718.gui.controllers.*;
import it.unicam.cs.mpgc.rpg130718.utils.GestoreDati;
import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class GestoreFinestre {
    private Stage stagePrincipale;
    private GestoreDati gestoreDati;

    public GestoreFinestre(Stage stagePrincipale) {
        this.stagePrincipale = stagePrincipale;
        this.gestoreDati = new GestoreDati();
    }

    public void mostraMenuPrincipale() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu_principale.fxml"));
            Parent root = loader.load();

            // Prendiamo il Controller e gli iniettiamo i dati
            MenuController controller = loader.getController();
            controller.setDipendenze(this, gestoreDati);

            impostaScena(root, "The Witcher RPG - Menù Principale");

        } catch (IOException e) {
            System.err.println("Errore: Impossibile caricare l'interfaccia del menù.");
            e.printStackTrace();
        }
    }

    private void impostaScena(Parent root, String titolo) {
        Scene scena = new Scene(root, 800, 600);
        stagePrincipale.setTitle(titolo);
        stagePrincipale.setScene(scena);
        stagePrincipale.setResizable(false);
        stagePrincipale.show();
    }

    public void mostraCreazioneEroe() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/creazione_eroe.fxml"));
            Parent root = loader.load();

            CreazioneEroeController controller = loader.getController();
            controller.setDipendenze(this, gestoreDati);

            impostaScena(root, "The Witcher RPG - Creazione Personaggio");
        } catch (IOException e) {
            System.err.println("Errore: Impossibile caricare la schermata di creazione.");
            e.printStackTrace();
        }
    }

    public void mostraForesta(Esploratore eroe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/foresta.fxml"));
            Parent root = loader.load();

            ForestaController controller = loader.getController();
            controller.setDipendenze(this, gestoreDati, eroe);

            impostaScena(root, "Foresta di Brokilon - " + eroe.getNome());
        } catch (IOException e) {
            System.err.println("Errore: Impossibile caricare la foresta.");
            e.printStackTrace();
        }
    }

    public void mostraSchermataFineGioco(String titolo, String sottotitolo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fine_gioco.fxml"));
            Parent root = loader.load();

            FineGiocoController controller = loader.getController();
            controller.setDipendenze(this, titolo, sottotitolo);

            // Creiamo una finestra a comparsa
            Stage popupStage = new Stage();
            // Serve a bloccare la finestra dietro al pop-up
            popupStage.initModality(APPLICATION_MODAL);
            popupStage.initOwner(stagePrincipale);
            popupStage.setTitle("Esito Avventura");
            popupStage.setScene(new Scene(root, 400, 300));
            popupStage.setResizable(false);

            controller.setStage(popupStage);

            popupStage.showAndWait();

        } catch (IOException e) {
            System.err.println("Errore: Impossibile caricare il pop-up di fine gioco.");
            e.printStackTrace();
        }
    }

    public void mostraBestiario(Esploratore eroe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bestiario.fxml"));
            Parent root = loader.load();

            BestiarioController controller = loader.getController();
            controller.setDipendenze(eroe);

            Stage popupStage = new Stage();
            popupStage.initModality(APPLICATION_MODAL);
            popupStage.initOwner(stagePrincipale);
            popupStage.setTitle("Bestiario dell'Esploratore");
            popupStage.setScene(new Scene(root, 500, 400));
            popupStage.setResizable(false);

            controller.setStage(popupStage);
            popupStage.showAndWait();

        } catch (IOException e) {
            System.err.println("Errore: Impossibile caricare il bestiario.");
            e.printStackTrace();
        }
    }
}