package it.unicam.cs.mpgc.rpg130718.model;

public class Strega extends Esploratore {

    public Strega(String nome, int puntiVita) {
        super(nome, puntiVita);
    }

    @Override
    public void eseguiAzioneSpeciale() {
        System.out.println(getNome() + " lancia incantesimo di Fuoco! Una fiammata travolge l'area.");
    }
}