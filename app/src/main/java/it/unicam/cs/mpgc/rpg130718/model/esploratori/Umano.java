package it.unicam.cs.mpgc.rpg130718.model.esploratori;

import it.unicam.cs.mpgc.rpg130718.model.mostri.Debolezza;

public class Umano extends Esploratore {

    public Umano(String nome) {
        super(nome, 130, 20, "Lama d'Argento", Debolezza.ARGENTO, 2);
    }

}