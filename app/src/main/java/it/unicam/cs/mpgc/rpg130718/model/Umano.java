package it.unicam.cs.mpgc.rpg130718.model;

public class Umano extends Esploratore {

    // L'Umano si ricorda se il nemico attuale è vulnerabile all'Argento
    private boolean buffAttivo;

    public Umano(String nome) {
        super(nome, 100, 20);
    }

    @Override
    public void entraInBattaglia(Mostro avversario) {
        System.out.print(getNome() + " usa la Spada d'Argento! ");

        if (avversario.getDebolezza() == Debolezza.ARGENTO) {
            System.out.println("Il mostro è debole! Danni raddoppiati.");
            this.buffAttivo = true;
        } else {
            System.out.println("Ma non succede niente.");
            this.buffAttivo = false;
        }
    }

    @Override
    public int eseguiAttacco() {
        // Se il buff è attivo infligge il doppio, altrimenti danno base
        return buffAttivo ? (getDannoBase() * 2) : getDannoBase();
    }
}