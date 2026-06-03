package it.unicam.cs.mpgc.rpg130718.gui.controllers;

import it.unicam.cs.mpgc.rpg130718.gui.GestoreFinestre;
import it.unicam.cs.mpgc.rpg130718.model.combattimento.Combattimento;
import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.Oggetto;
import it.unicam.cs.mpgc.rpg130718.utils.GestoreDati;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceDialog;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.Random;

public class ForestaController {

    private GestoreFinestre gestoreFinestre;
    private GestoreDati gestoreDati;
    private Esploratore eroe;
    private List<Mostro> catalogoMostri;
    private int totaleMostri;

    @FXML
    private Label lblStatistiche;

    @FXML
    private TextArea txtLog;

    public void setDipendenze(GestoreFinestre gestoreFinestre, GestoreDati gestoreDati, Esploratore eroe) {
        this.gestoreFinestre = gestoreFinestre;
        this.gestoreDati = gestoreDati;
        this.eroe = eroe;
        this.catalogoMostri = gestoreDati.caricaCatalogoMostri();
        this.totaleMostri = this.catalogoMostri.size();

        aggiornaStatistiche();
        txtLog.setText("Ti addentri nell'oscura Foresta di Brokilon...\nL'aria è pesante. Cosa vuoi fare?");
    }

    private void aggiornaStatistiche() {
        lblStatistiche.setText(eroe.getNome() + " (" + eroe.getTitolo() + ") - HP: "
                + eroe.getPuntiVita() + "/" + eroe.getPuntiVitaMax());
    }

    @FXML
    public void onCercaIncontroClick(ActionEvent event) {
        // Filtriamo per i mostri non ancora scoperti
        List<Mostro> mostriDisponibili = this.catalogoMostri.stream()
                .filter(m -> !eroe.getBestiario().haScoperto(m))
                .toList();

        // Controlliamo prima del combattimento se non ci sono mostri da aggiungere al bestiario
        if (mostriDisponibili.isEmpty()) {
            gestoreDati.cancellaSalvataggio();
            gestoreFinestre.mostraSchermataFineGioco("VITTORIA!", "Hai sconfitto tutti i mostri di Brokilon. Il tuo nome echeggerà nei canti dei Bardi!");
            return;
        }

        Mostro scelta = mostriDisponibili.get(new Random().nextInt(mostriDisponibili.size()));
        StringBuilder logIncontro = new StringBuilder();
        logIncontro.append("Un ").append(scelta.getNome()).append(" selvatico balza fuori dai cespugli!\n");

        // Controlliamo se l'esploratore ha degli oggetti nell'inventario
        List<Oggetto> oggettiPosseduti = eroe.getInventario().getOggetti();
        if (!oggettiPosseduti.isEmpty()) {

            // Creiamo una lista di stringhe con i nomi degli oggetti + l'opzione "Non usare nulla"
            List<String> nomiOggetti = new ArrayList<>();
            nomiOggetti.add("Non usare nulla");
            for (Oggetto o : oggettiPosseduti) {
                nomiOggetti.add(o.getNome());
            }

            // Mostriamo una finestra a comparsa con menù a tendina
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Non usare nulla", nomiOggetti);
            dialog.setTitle("Scelta Oggetto");
            dialog.setHeaderText("Hai incontrato un " + scelta.getNome() + "!\nScegli un oggetto dal tuo inventario prima di attaccare:");
            dialog.setContentText("Oggetto:");

            Optional<String> risultato = dialog.showAndWait();

            // Se l'utente ha scelto qualcosa di diverso da "Non usare nulla"
            if (risultato.isPresent() && !risultato.get().equals("Non usare nulla")) {
                String esitoOggetto = eroe.usaOggetto(risultato.get());
                logIncontro.append(esitoOggetto).append("\n");
            }
        }

        Combattimento scontro = new Combattimento(eroe, scelta);
        String reportScontro = scontro.autoRisolvi();

        logIncontro.append(reportScontro);
        txtLog.setText(logIncontro.toString());
        aggiornaStatistiche();

        if (!eroe.isVivo()) {
            gestoreDati.cancellaSalvataggio();
            gestoreFinestre.mostraSchermataFineGioco("GAME OVER", "Sei caduto in battaglia... Il tuo viaggio finisce qui.");
            return;
        }

        if (eroe.getBestiario().getNumeroMostriScoperti() >= totaleMostri) {
            gestoreDati.cancellaSalvataggio();
            gestoreFinestre.mostraSchermataFineGioco("VITTORIA!", "Incredibile! Hai registrato l'ultimo mostro e liberato la foresta.");
        }
    }

    @FXML
    public void onSalvaEsciClick(ActionEvent event) {
        // Salviamo solo se l'eroe è vivo e non ha già completato il bestiario
        if (eroe.isVivo() && eroe.getBestiario().getNumeroMostriScoperti() < totaleMostri) {
            gestoreDati.salvaPartita(eroe);
        }
        gestoreFinestre.mostraMenuPrincipale();
    }

    @FXML
    public void onApriBestiarioClick(ActionEvent event) {
        gestoreFinestre.mostraBestiario(eroe);
    }
}