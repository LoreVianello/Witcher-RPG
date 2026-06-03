package it.unicam.cs.mpgc.rpg130718.model.esploratori;

import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public class Bardo extends Esploratore {

    public Bardo(String nome) {
        super(nome, "Bardo", 100, 20, "Canto Soave", null, 1);
    }

    @Override
    public int eseguiAttacco(Mostro bersaglio) {
        // Passiamo il bersaglio alla classe madre
        int dannoInflitto = super.eseguiAttacco(bersaglio);

        this.cura(10);
        return dannoInflitto;
    }
}