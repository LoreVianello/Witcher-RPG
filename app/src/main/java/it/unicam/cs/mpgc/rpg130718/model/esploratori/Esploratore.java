package it.unicam.cs.mpgc.rpg130718.model.esploratori;

import it.unicam.cs.mpgc.rpg130718.model.mostri.Debolezza;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.Inventario;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.Oggetto;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Bestiario;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;

public abstract class Esploratore {
    private String nome;
    private String titolo;
    private int puntiVita;
    private int puntiVitaMax;
    private int dannoBase;
    private Inventario inventario;
    private Bestiario bestiario;
    private Oggetto buffAttivo;

    // Campi per generalizzare le abilità
    // Transient serve a evitare che Gson salvi queste variabili nel file JSON
    private transient boolean abilitaAttiva;
    private transient String nomeAbilita;
    private transient Debolezza debolezzaTarget;
    private transient int moltiplicatoreAbilita;

    public Esploratore(String nome, String titolo, int puntiVitaMax, int dannoBase, String nomeAbilita, Debolezza debolezzaTarget, int moltiplicatoreAbilita) {
        this.nome = nome;
        this.titolo = titolo;
        this.puntiVitaMax = puntiVitaMax;
        this.puntiVita = puntiVitaMax;
        this.dannoBase = dannoBase;
        this.nomeAbilita = nomeAbilita;
        this.debolezzaTarget = debolezzaTarget;
        this.moltiplicatoreAbilita = moltiplicatoreAbilita;
        this.inventario = new Inventario();
        this.bestiario = new Bestiario();
    }

    public String getNome() {
        return nome;
    }

    public String getTitolo() {
        return titolo;
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

    public void preparaBattaglia(Mostro mostro) {
        this.abilitaAttiva = (debolezzaTarget != null && mostro.getDebolezza() == debolezzaTarget);
    }

    public String getLogIngressoBattaglia() {
        if (this.abilitaAttiva) {
            return nome + " sfrutta la debolezza del mostro attivando " + nomeAbilita + "!";
        }
        return nome + " si prepara al combattimento.";
    }

    public int eseguiAttacco(Mostro bersaglio) {
        int dannoCalcolato = dannoBase;

        // Applica il moltiplicatore dell'abilità di classe (se attiva)
        if (abilitaAttiva) {
            dannoCalcolato = (dannoCalcolato * moltiplicatoreAbilita);
        }

        return dannoCalcolato;
    }

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