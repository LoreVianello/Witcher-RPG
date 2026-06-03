package it.unicam.cs.mpgc.rpg130718.model.esploratori;

import it.unicam.cs.mpgc.rpg130718.model.mostri.Debolezza;

public class Strega extends Esploratore {

    public Strega(String nome) {
        super(nome, 80, 35, "Incantesimo", Debolezza.INCANTESIMO, 2);
    }
}