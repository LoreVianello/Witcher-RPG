package it.unicam.cs.mpgc.rpg130718.model.esploratori;

import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public class Bardo extends Esploratore {

    public Bardo(String nome) {
        super(nome, 100, 20);
    }

    @Override
    public String entraInBattaglia(Mostro avversario) {
        return getNome() + " intona un Canto Soave! Si curerà ad ogni attacco.";
    }

    @Override
    public int eseguiAttacco() {
        cura(10);
        return getDannoBase();
    }
}