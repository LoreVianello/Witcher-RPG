package it.unicam.cs.mpgc.rpg130718.utils;

import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import it.unicam.cs.mpgc.rpg130718.model.esploratori.Umano;

public class TipoEsploratore {

    /**
     * Crea dinamicamente un'istanza di Esploratore basandosi sul nome della classe.
     */
    public static Esploratore creaEroe(String tipoClasse, String nome) {
        String percorsoCompleto = "it.unicam.cs.mpgc.rpg130718.model.esploratori." + tipoClasse;

        try {
            // Trova la classe dinamicamente a partire dalla stringa
            Class<?> classeCorretta = Class.forName(percorsoCompleto);

            // Cerca il costruttore che accetta una String e istanzia l'oggetto
            return (Esploratore) classeCorretta.getConstructor(String.class).newInstance(nome);

        } catch (Exception e) {
            System.err.println("Errore: Classe '" + tipoClasse + "' non trovata o non valida");
            System.err.println("Ti è stato assegnato 'Umano' di default");
            return new Umano(nome);
        }
    }
}