package it.unicam.cs.mpgc.rpg130718.model.esploratori;

import it.unicam.cs.mpgc.rpg130718.model.mostri.Debolezza;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public class Umano extends Esploratore {

    // Tiene conto se l'avversario è debole all'abilità
    // Transient serve a evitare che Gson salvi questa variabile nel file JSON
    private transient boolean abilitaAttiva;

    public Umano(String nome) {
        super(nome, 130, 20);
    }

    @Override
    public String entraInBattaglia(Mostro avversario) {
        String log = getNome() + " usa la Spada d'Argento! ";
        if (avversario.getDebolezza() == Debolezza.ARGENTO) {
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