package it.unicam.cs.mpgc.rpg130718.model.esploratori;

import it.unicam.cs.mpgc.rpg130718.model.mostri.Debolezza;

public class Witcher extends Esploratore {

    public Witcher(String nome) {
        super(nome, "Witcher", 100, 25, "Segno Yrden", Debolezza.SEGNO_YRDEN, 2);
    }
}