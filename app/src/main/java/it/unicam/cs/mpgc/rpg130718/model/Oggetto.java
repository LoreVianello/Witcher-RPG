package it.unicam.cs.mpgc.rpg130718.model;

public interface Oggetto {
    String getNome();

    void usa(Esploratore esploratore);

    // Azione passiva durante il combattimento (se l'oggetto è un buff)
    int applicaBuffCombattimento(int dannoAttuale, Mostro avversario);
}