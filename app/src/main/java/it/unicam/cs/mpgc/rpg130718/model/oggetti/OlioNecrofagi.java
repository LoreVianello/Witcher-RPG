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
    public void usa(Esploratore esploratore) {
        System.out.println(esploratore.getNome() + " applica l'Olio per Necrofagi sull'arma!");
        // Viene aggiunto il buff all'esploratore
        esploratore.setBuffAttivo(this);
    }

    @Override
    public int applicaBuffCombattimento(int dannoAttuale, Mostro avversario) {
        if (avversario.getDebolezza() == Debolezza.OLIO_NECROFAGI) {
            System.out.println("L'Olio per Necrofagi reagisce! Danni aumentati.");
            return dannoAttuale * 2;
        }else System.out.println("Ma non succede niente.");
        return dannoAttuale;
    }
}