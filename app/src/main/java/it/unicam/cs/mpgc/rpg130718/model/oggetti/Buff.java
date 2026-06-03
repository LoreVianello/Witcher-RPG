package it.unicam.cs.mpgc.rpg130718.model.oggetti;

import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public interface Buff extends Oggetto {
    int applicaBuffCombattimento(int dannoAttuale, Mostro avversario);
}
