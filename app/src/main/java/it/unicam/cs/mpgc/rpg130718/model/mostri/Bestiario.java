package it.unicam.cs.mpgc.rpg130718.model.mostri;

import java.util.ArrayList;
import java.util.List;

public class Bestiario {
    private List<Mostro> mostriScoperti;

    public Bestiario() {
        this.mostriScoperti = new ArrayList<>();
    }

    /**
     * Verifica se un determinato mostro è già stato registrato nel bestiario.
     * Il controllo viene fatto sul nome per garantire la compatibilità con i salvataggi.
     */
    public boolean haScoperto(Mostro mostro) {
        return mostriScoperti.stream()
                .anyMatch(m -> m.getNome().equals(mostro.getNome()));
    }

    public int getNumeroMostriScoperti() {
        return this.mostriScoperti.size();
    }

    public List<Mostro> getMostriScoperti() {
        return mostriScoperti;
    }

    public void aggiungiMostro(Mostro mostro) {
        if (!haScoperto(mostro)) {
            mostriScoperti.add(mostro);
        }
    }
}