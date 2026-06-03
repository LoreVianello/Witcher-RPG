package it.unicam.cs.mpgc.rpg130718.model.oggetti;

import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Debolezza;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public class OlioNecrofagi implements Oggetto {
    @Override
    public String getNome() {
        return "Olio per Necrofagi";
    }

    @Override
    public String usa(Esploratore esploratore) {
        // Viene aggiunto il buff all'esploratore
        esploratore.setBuffAttivo(this);
        return esploratore.getNome() + " applica l'Olio per Necrofagi sull'arma!";
    }

    @Override
    public int applicaBuffCombattimento(int dannoAttuale, Mostro avversario) {
        if (avversario.getDebolezza() == Debolezza.OLIO_NECROFAGI) {
            return dannoAttuale * 2;
        }
        return dannoAttuale;
    }

    @Override
    public Oggetto copia() {
        return new OlioNecrofagi();
    }
}