package it.unicam.cs.mpgc.rpg130718.model.oggetti;

import java.util.List;
import java.util.Random;

public class GeneratoreLoot {

    private GeneratoreLoot() {}

    // La lista che hai suggerito tu, contenente i "modelli base" (i prototipi)
    private static final List<Oggetto> PROTOTIPI = List.of(
            new PozioneCura(),
            new OlioNecrofagi()
    );

    public static Oggetto generaDropCasuale() {
        // Estraiamo a caso
        int indiceCasuale = new Random().nextInt(PROTOTIPI.size());
        Oggetto oggettoPescato = PROTOTIPI.get(indiceCasuale);

        // Restituiamo una copia pulita dell'oggetto!
        return oggettoPescato.copia();
    }
}