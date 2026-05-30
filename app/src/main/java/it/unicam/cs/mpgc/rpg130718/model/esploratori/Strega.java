package it.unicam.cs.mpgc.rpg130718.model.esploratori;

import it.unicam.cs.mpgc.rpg130718.model.mostri.Debolezza;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public class Strega extends Esploratore {

    // Tiene conto se l'avversario è debole all'abilità
    // Transient serve a evitare che Gson salvi questa variabile nel file JSON
    private transient boolean abilitaAttiva;

    public Strega(String nome) {
        super(nome, 90, 25);
    }

    @Override
    public void entraInBattaglia(Mostro avversario) {
        System.out.print(getNome() + " usa l'Incantesimo! ");

        if (avversario.getDebolezza() == Debolezza.INCANTESIMO) {
            System.out.println("Il mostro è debole! Danni raddoppiati.");
            this.abilitaAttiva = true;
        } else {
            System.out.println("Ma non succede niente.");
            this.abilitaAttiva = false;
        }
    }

    @Override
    public int eseguiAttacco() {
        // Se il buff è attivo infligge il doppio, altrimenti danno base
        return abilitaAttiva ? (getDannoBase() * 2) : getDannoBase();
    }
}