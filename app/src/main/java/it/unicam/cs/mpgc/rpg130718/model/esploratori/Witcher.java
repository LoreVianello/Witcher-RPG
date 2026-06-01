package it.unicam.cs.mpgc.rpg130718.model.esploratori;

import it.unicam.cs.mpgc.rpg130718.model.mostri.Debolezza;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public class Witcher extends Esploratore {

    // Tiene conto se l'avversario è debole all'abilità
    // Transient serve a evitare che Gson salvi questa variabile nel file JSON
    private transient boolean abilitaAttiva;

    public Witcher(String nome) {
        super(nome, 100, 25);
    }

    @Override
    public String entraInBattaglia(Mostro avversario) {
        String log = getNome() + " usa il Segno Yrden! ";
        if (avversario.getDebolezza() == Debolezza.SEGNO_YRDEN) {
            this.abilitaAttiva = true;
            return log + "Il mostro è debole! Danni raddoppiati.";
        } else {
            this.abilitaAttiva = false;
            return log + "Ma non succede niente.";
        }
    }

    @Override
    public int eseguiAttacco() {
        // Se il buff è attivo infligge il doppio, altrimenti danno base
        return abilitaAttiva ? (getDannoBase() * 2) : getDannoBase();
    }
}