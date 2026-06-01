package it.unicam.cs.mpgc.rpg130718.model.esploratori;

import it.unicam.cs.mpgc.rpg130718.model.oggetti.Inventario;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.Oggetto;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Bestiario;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public abstract class Esploratore {
    private String nome;
    private int puntiVita;
    private int puntiVitaMax;
    private int dannoBase;
    private Inventario inventario;
    private Bestiario bestiario;
    private Oggetto buffAttivo;

    public Esploratore(String nome, int puntiVitaMax, int dannoBase) {
        this.nome = nome;
        this.puntiVitaMax = puntiVitaMax;
        this.puntiVita = puntiVitaMax;
        this.dannoBase = dannoBase;
        this.inventario = new Inventario();
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

    public Inventario getInventario() {
        return inventario;
    }

    public Bestiario getBestiario() {
        return bestiario;
    }

    public Oggetto getBuffAttivo() {
        return buffAttivo;
    }

    public void setBuffAttivo(Oggetto buff) {
        this.buffAttivo = buff;
    }

    public void resetBuff() {
        this.buffAttivo = null;
    }

    public abstract String entraInBattaglia(Mostro avversario);

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

    public String usaOggetto(String nomeOggetto) {
        Oggetto oggettoDaUsare = inventario.consumaOggetto(nomeOggetto);
        if (oggettoDaUsare != null) {
            return oggettoDaUsare.usa(this);
        } else {
            return "Non hai " + nomeOggetto + " nell'inventario.";
        }
    }
}