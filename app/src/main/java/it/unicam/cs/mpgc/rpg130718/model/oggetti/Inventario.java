package it.unicam.cs.mpgc.rpg130718.model.oggetti;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Oggetto> oggetti;

    public Inventario() {
        this.oggetti = new ArrayList<>();
    }

    public List<Oggetto> getOggetti() {
        return this.oggetti;
    }

    public void aggiungiOggetto(Oggetto oggetto) {
        this.oggetti.add(oggetto);
        System.out.println("Ottenuto: " + oggetto.getNome());
    }

    public Oggetto consumaOggetto(String nomeOggetto) {
        Oggetto daConsumare = oggetti.stream()
                .filter(o -> o.getNome().equals(nomeOggetto))
                .findFirst()
                .orElse(null);

        if (daConsumare != null) {
            oggetti.remove(daConsumare);
        }

        return daConsumare;
    }

    public void mostraInventario() {
        if (oggetti.isEmpty()) {
            System.out.println("L'inventario è vuoto.");
        } else {
            System.out.println("Inventario:");
            oggetti.forEach(o -> System.out.println("- " + o.getNome()));
        }
    }
}