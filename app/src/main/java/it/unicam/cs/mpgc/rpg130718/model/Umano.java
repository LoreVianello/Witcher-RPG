package it.unicam.cs.mpgc.rpg130718.model;

public class Umano extends Esploratore {

    public Umano(String nome, int puntiVita) {
        super(nome, puntiVita);
    }

    @Override
    public void eseguiAzioneSpeciale() {
        System.out.println(getNome() + " attacca con la spada! Un fendente travolge l'area.");
    }
}