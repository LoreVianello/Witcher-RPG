package it.unicam.cs.mpgc.rpg130718;

import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import it.unicam.cs.mpgc.rpg130718.model.esploratori.Witcher;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.PozioneCura;
import it.unicam.cs.mpgc.rpg130718.utils.GestoreDati;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== THE WITCHER RPG - TEST DI SISTEMA ===\n");

        // 1. Inizializziamo il gestore dei file
        GestoreDati gestoreDati = new GestoreDati();

        // 2. Testiamo la lettura del catalogo mostri
        List<Mostro> catalogo = gestoreDati.caricaCatalogoMostri();
        if (catalogo.isEmpty()) {
            System.out.println("ERRORE: Catalogo mostri vuoto o non trovato. Interruzione test.");
            return;
        }

        System.out.println("\n--- FASE DI CARICAMENTO ---");
        // 3. Testiamo se esiste un salvataggio
        Esploratore eroe = gestoreDati.caricaPartita();

        if (eroe == null) {
            System.out.println("Nessun salvataggio trovato. Creo una Nuova Partita...");
            eroe = new Witcher("Geralt");

            // Diamo all'eroe un oggetto di partenza per testare il polimorfismo dell'inventario
            eroe.getInventario().aggiungiOggetto(new PozioneCura());
        } else {
            System.out.println("Salvataggio trovato! Bentornato " + eroe.getNome() + " (" + eroe.getClass().getSimpleName() + ")");
            System.out.println("I tuoi HP attuali: " + eroe.getPuntiVita() + " / " + eroe.getPuntiVitaMax());
            eroe.getInventario().mostraInventario();

            // Mettiamo alla prova il metodo usaOggetto per consumare la pozione se c'è
            System.out.println("\nProvo a usare una Pozione di Cura...");
            eroe.usaOggetto("Pozione di Cura");
        }

        System.out.println("\n--- FASE DI SIMULAZIONE ---");
        System.out.println(eroe.getNome() + " cammina lungo il sentiero... e si ferisce su un rovo spinoso!");
        eroe.subisciDanno(15);
        System.out.println("HP scesi a: " + eroe.getPuntiVita());

        System.out.println("\n--- FASE DI SALVATAGGIO ---");
        // 4. Salviamo lo stato attuale alterato
        gestoreDati.salvaPartita(eroe);

        System.out.println("\nTest concluso. Controlla il file salvataggio.json nella cartella data!");
    }
}