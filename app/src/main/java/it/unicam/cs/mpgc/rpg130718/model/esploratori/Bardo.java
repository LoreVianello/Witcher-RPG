package it.unicam.cs.mpgc.rpg130718.model.esploratori;

import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public class Bardo extends Esploratore {

    public Bardo(String nome) {
        super(nome, 80, 15);
    }

    @Override
    public void entraInBattaglia(Mostro avversario) {
        System.out.println(getNome() + " intona un Canto Soave! Si curerà ad ogni attacco.");
    }

    @Override
    public int eseguiAttacco() {
        cura(5);
        return getDannoBase();
    }
}