package it.unicam.cs.mpgc.rpg130718.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bestiario {
    private List<Mostro> mostriScoperti;
    private int indiceCorrente;

    public Bestiario() {
        this.mostriScoperti = new ArrayList<>();
        this.indiceCorrente = 0;
    }

    public void aggiungiMostro(Mostro mostro) {
        // Evitiamo di inserire cloni dello stesso mostro
        if (!mostriScoperti.contains(mostro)) {
            mostriScoperti.add(mostro);
        }
    }

    public Mostro getMostroCorrente() {
        if (mostriScoperti.isEmpty()) return null;
        return mostriScoperti.get(indiceCorrente);
    }

    public Mostro mostroSuccessivo() {
        if (mostriScoperti.isEmpty()) return null;
        // Uso il simbolo Modulo per tornare a 0 se siamo alla fine della lista
        indiceCorrente = (indiceCorrente + 1) % mostriScoperti.size();
        return mostriScoperti.get(indiceCorrente);
    }

    public Mostro mostroPrecedente() {
        if (mostriScoperti.isEmpty()) return null;
        // Aggiungo size() per evitare di andare in numeri negativi
        indiceCorrente = (indiceCorrente - 1 + mostriScoperti.size()) % mostriScoperti.size();
        return mostriScoperti.get(indiceCorrente);
    }

    public List<Mostro> cercaPerCategoria(CategoriaMostro categoria) {
        // Uso il flusso stream per scorrere gli elementi uno alla volta, filtrarli per categoria e metterli in
        // una lista
        return mostriScoperti.stream()
                .filter(m -> m.getCategoria() == categoria)
                .toList();
    }
}