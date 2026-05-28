package it.unicam.cs.mpgc.rpg130718.model;

public class Mostro {
    private String nome;
    private int puntiVita;
    private int livello;
    private CategoriaMostro categoria;
    private Debolezza debolezza;

    public Mostro(String nome, int puntiVita, int livello, CategoriaMostro categoria, Debolezza debolezza) {
        this.nome = nome;
        this.puntiVita = puntiVita;
        this.livello = livello;
        this.categoria = categoria;
        this.debolezza = debolezza;
    }

    public String getNome() {
        return nome;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getLivello() {
        return livello;
    }

    public CategoriaMostro getCategoria() {
        return categoria;
    }

    public Debolezza getDebolezza() {
        return debolezza;
    }
}
