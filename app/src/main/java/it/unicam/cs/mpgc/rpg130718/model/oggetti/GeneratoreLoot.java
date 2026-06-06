package it.unicam.cs.mpgc.rpg130718.model.oggetti;

import java.util.List;
import java.util.Random;

/**
 * Genera oggetti loot usando il Pattern Prototype:
 * i PROTOTIPI sono istanze modello da cui si ricavano copie pulite a ogni drop.
 */
public class GeneratoreLoot {

    private GeneratoreLoot() {}

    // La lista contenente i prototipi
    private static final List<Oggetto> PROTOTIPI = List.of(
            new PozioneCura(),
            new OlioNecrofagi()
    );

    public static Oggetto generaDropCasuale() {
        // Estraiamo a caso
        int indiceCasuale = new Random().nextInt(PROTOTIPI.size());
        Oggetto oggettoPescato = PROTOTIPI.get(indiceCasuale);

        // Restituiamo una copia dell'oggetto
        return oggettoPescato.copia();
    }
}