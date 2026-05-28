package it.unicam.cs.mpgc.rpg130718.model;

public class Strega extends Esploratore {

    // La Strega si ricorda se il nemico attuale è vulnerabile a Incantesimo
    private boolean buffAttivo;

    public Strega(String nome) {
        super(nome, 90, 25);
    }

    @Override
    public void entraInBattaglia(Mostro avversario) {
        System.out.print(getNome() + " usa l'Incantesimo! ");

        if (avversario.getDebolezza() == Debolezza.INCANTESIMO) {
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