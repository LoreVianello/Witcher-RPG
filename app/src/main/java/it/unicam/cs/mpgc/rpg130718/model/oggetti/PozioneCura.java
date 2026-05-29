package it.unicam.cs.mpgc.rpg130718.model.oggetti;

import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public class PozioneCura implements Oggetto {
    @Override
    public String getNome() {
        return "Pozione di Cura";
    }

    @Override
    public void usa(Esploratore esploratore) {
        System.out.println(esploratore.getNome() + " beve una Pozione di Cura! Salute ripristinata al massimo.");
        esploratore.cura(esploratore.getPuntiVitaMax());
    }

    @Override
    public int applicaBuffCombattimento(int dannoAttuale, Mostro avversario) {
        return dannoAttuale; // La pozione non altera i danni in combattimento
    }
}