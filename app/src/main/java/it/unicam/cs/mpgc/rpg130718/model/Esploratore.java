package it.unicam.cs.mpgc.rpg130718.model;

public abstract class Esploratore {
    private String nome;
    private int puntiVita;
    private int puntiVitaMax;
    private int dannoBase;
    private Bestiario bestiario;

    public Esploratore(String nome, int puntiVitaMax, int dannoBase) {
        this.nome = nome;
        this.puntiVitaMax = puntiVitaMax;
        this.puntiVita = puntiVitaMax;
        this.dannoBase = dannoBase;
        this.bestiario = new Bestiario();
    }

    public String getNome() {
        return nome;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getPuntiVitaMax() {
        return puntiVitaMax;
    }

    public int getDannoBase() {
        return dannoBase;
    }

    public Bestiario getBestiario() {
        return bestiario;
    }

    public abstract void entraInBattaglia(Mostro avversario);

    public abstract int eseguiAttacco();

    public void subisciDanno(int danno) {
        this.puntiVita -= danno;
        if (this.puntiVita < 0) {
            this.puntiVita = 0;
        }
    }

    // Metodo per curarsi senza mai superare il limite massimo
    public void cura(int quantita) {
        this.puntiVita += quantita;
        if (this.puntiVita > this.puntiVitaMax) {
            this.puntiVita = this.puntiVitaMax;
        }
    }

    public boolean isVivo() {
        return this.puntiVita > 0;
    }
}