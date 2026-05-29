package it.unicam.cs.mpgc.rpg130718.model.esploratori;

import it.unicam.cs.mpgc.rpg130718.model.mostri.Debolezza;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public class Witcher extends Esploratore {

    // Il Witcher si ricorda se il nemico attuale è vulnerabile a Yrden
    private boolean buffAttivo;

    public Witcher(String nome) {
        super(nome, 100, 20);
    }

    @Override
    public void entraInBattaglia(Mostro avversario) {
        System.out.print(getNome() + " usa il Segno Yrden! ");

        if (avversario.getDebolezza() == Debolezza.SEGNO_YRDEN) {
            System.out.println("Il mostro è debole! Danni raddoppiati.");
            this.buffAttivo = true;
        } else {
            System.out.println("Ma non succede niente.");
            this.buffAttivo = false;
        }
    }

    @Override
    public int eseguiAttacco() {
        // Se il buff è attivo infligge il doppio, altrimenti danno base
        return buffAttivo ? (getDannoBase() * 2) : getDannoBase();
    }
}