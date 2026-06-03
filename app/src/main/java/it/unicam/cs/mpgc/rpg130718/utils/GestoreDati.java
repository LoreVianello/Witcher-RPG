package it.unicam.cs.mpgc.rpg130718.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.Oggetto;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestoreDati {

    private static final String DATA_DIR = "data/";
    private static final String FILE_CATALOGO = DATA_DIR + "catalogo_mostri.json";
    private static final String FILE_SALVATAGGIO = DATA_DIR + "salvataggio.json";
    private Gson gson;

    public GestoreDati() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Esploratore.class, new EsploratoreAdapter())
                .registerTypeAdapter(Oggetto.class, new OggettoAdapter())
                // Aggiunge indentazioni al json
                .setPrettyPrinting()
                .create();
    }

    /**
     * Legge il file JSON e restituisce la lista di tutti i mostri del gioco.
     */
    public List<Mostro> caricaCatalogoMostri() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_CATALOGO))) {
            Mostro[] mostri = gson.fromJson(reader, Mostro[].class);
            List<Mostro> catalogo = new ArrayList<>(Arrays.asList(mostri));

            return catalogo;

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Salva lo stato attuale dell'Esploratore (inclusi Inventario e Bestiario) su file.
     */
    public void salvaPartita(Esploratore eroe) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_SALVATAGGIO))) {

            // Serializzazione completa dell'Esploratore e della sua classe tramite Gson e Adapter
            gson.toJson(eroe, Esploratore.class, writer);

        } catch (IOException e) {
        }
    }

    /**
     * Tenta di caricare un salvataggio esistente.
     * Restituisce null se il file non esiste (es. prima partita in assoluto).
     */
    public Esploratore caricaPartita() {
        File file = new File(FILE_SALVATAGGIO);

        if (!file.exists()) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_SALVATAGGIO))) {

            // Deserializzazione completa dell'Esploratore e della sua classe tramite Gson e Adapter
            Esploratore esploratore = gson.fromJson(reader, Esploratore.class);
            return esploratore;

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Verifica se è presente un file di salvataggio.
     */
    public boolean esisteSalvataggio() {
        File file = new File(FILE_SALVATAGGIO);
        return file.exists();
    }

    /**
     * Cancella fisicamente il file di salvataggio in caso di Game Over o Vittoria,
     * impedendo al giocatore di ricaricare una partita conclusa.
     */
    public void cancellaSalvataggio() {
        File file = new File(FILE_SALVATAGGIO);
        if (file.exists()) {
            if (file.delete()) {
            } else {
            }
        }
    }
}