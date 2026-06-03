package it.unicam.cs.mpgc.rpg130718.model.mostri;

public enum CategoriaMostro {
    NECROFAGO("Necrofago"),
    VESTIGIA("Vestigia"),
    MALEDETTO("Maledetto"),
    IBRIDO("Ibrido"),
    BESTIA("Bestia");

    private final String nomeFormattato;

    CategoriaMostro(String nomeFormattato) {
        this.nomeFormattato = nomeFormattato;
    }

    @Override
    public String toString() {
        return nomeFormattato;
    }
}
