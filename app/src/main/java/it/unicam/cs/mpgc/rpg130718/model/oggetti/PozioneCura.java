package it.unicam.cs.mpgc.rpg130718.model.oggetti;

import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public class PozioneCura implements Oggetto {
    @Override
    public String getNome() {
        return "Pozione di Cura";
    }

    @Override
    public String usa(Esploratore esploratore) {
        esploratore.cura(esploratore.getPuntiVitaMax());
        return esploratore.getNome() + " beve una Pozione di Cura! Salute ripristinata al massimo.";
    }

    @Override
    public Oggetto copia() {
        return new PozioneCura();
    }
}