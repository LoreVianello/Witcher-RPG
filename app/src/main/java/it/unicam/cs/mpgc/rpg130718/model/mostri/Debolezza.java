package it.unicam.cs.mpgc.rpg130718.model.mostri;

public enum Debolezza {
    NESSUNA("Nessuna"),
    ARGENTO("Lama d'Argento"),
    SEGNO_YRDEN("Segno Yrden"),
    INCANTESIMO("Incantesimo"),
    OLIO_NECROFAGI("Olio per Necrofagi");

    private final String nomeFormattato;

    Debolezza(String nomeFormattato) {
        this.nomeFormattato = nomeFormattato;
    }

    @Override
    public String toString() {
        return this.nomeFormattato;
    }
}
