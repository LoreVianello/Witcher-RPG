package it.unicam.cs.mpgc.rpg130718.model.mostri;

public class Mostro {
    private String nome;
    private int puntiVita;
    private int danno;
    private CategoriaMostro categoria;
    private Debolezza debolezza;

    public Mostro(String nome, int puntiVita, int danno, CategoriaMostro categoria, Debolezza debolezza) {
        this.nome = nome;
        this.puntiVita = puntiVita;
        this.danno = danno;
        this.categoria = categoria;
        this.debolezza = debolezza;
    }

    public String getNome() {
        return nome;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getDanno() {
        return danno;
    }

    public CategoriaMostro getCategoria() {
        return categoria;
    }

    public Debolezza getDebolezza() {
        return debolezza;
    }

    public void subisciDanno(int dannoRicevuto) {
        this.puntiVita -= dannoRicevuto;
        if (this.puntiVita < 0) {
            this.puntiVita = 0;
        }
    }

    public boolean isVivo() {
        return this.puntiVita > 0;
    }
}