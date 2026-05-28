package it.unicam.cs.mpgc.rpg130718.model;

public abstract class Esploratore {
    private String nome;
    private int puntiVita;
    private Bestiario bestiario;
    // L'inventario lo aggiungeremo in un secondo momento per procedere per gradi!

    public Esploratore(String nome, int puntiVita) {
        this.nome = nome;
        this.puntiVita = puntiVita;
        this.bestiario = new Bestiario();
    }

    public String getNome() {
        return nome;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public Bestiario getBestiario() {
        return bestiario;
    }

    // Metodo per gestire i danni subiti
    public void subisciDanno(int danno) {
        this.puntiVita -= danno;
        if (this.puntiVita < 0) {
            this.puntiVita = 0;
        }
    }

    // Metodo astratto per evitare controlli di tipo
    public abstract void eseguiAzioneSpeciale();
}