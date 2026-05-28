package it.unicam.cs.mpgc.rpg130718.model;

public class Witcher extends Esploratore {

    public Witcher(String nome, int puntiVita) {
        super(nome, puntiVita);
    }

    @Override
    public void eseguiAzioneSpeciale() {
        System.out.println(getNome() + " lancia il Segno Igni! Una fiammata travolge l'area.");
    }
}