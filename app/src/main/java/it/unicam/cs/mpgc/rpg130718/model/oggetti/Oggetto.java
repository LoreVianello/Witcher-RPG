package it.unicam.cs.mpgc.rpg130718.model.oggetti;

import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public interface Oggetto {
    String getNome();

    String usa(Esploratore esploratore);

    // Azione passiva durante il combattimento (se l'oggetto è un buff)
    int applicaBuffCombattimento(int dannoAttuale, Mostro avversario);
}