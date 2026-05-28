package it.unicam.cs.mpgc.rpg130718.model;

public class Bardo extends Esploratore {

    public Bardo(String nome, int puntiVita) {
        super(nome, puntiVita);
    }

    @Override
    public void eseguiAzioneSpeciale() {
        System.out.println(getNome() + " lancia una canzone di Cura! Una melodia soave travolge l'area.");
    }
}