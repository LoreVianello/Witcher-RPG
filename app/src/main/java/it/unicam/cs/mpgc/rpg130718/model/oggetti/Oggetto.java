package it.unicam.cs.mpgc.rpg130718.model.oggetti;

import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;

public interface Oggetto {
    String getNome();
    String usa(Esploratore esploratore);
    Oggetto copia();
}