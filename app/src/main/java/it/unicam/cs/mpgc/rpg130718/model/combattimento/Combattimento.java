package it.unicam.cs.mpgc.rpg130718.model.combattimento;

import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.Oggetto;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.OlioNecrofagi;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.PozioneCura;

public class Combattimento {
    private Esploratore esploratore;
    private Mostro mostro;
    private Mostro nemico;

    public Combattimento(Esploratore esploratore, Mostro mostro) {
        this.esploratore = esploratore;
        this.mostro = mostro;
        this.nemico = new Mostro(
                mostro.getNome(),
                mostro.getPuntiVita(),
                mostro.getDanno(),
                mostro.getCategoria(),
                mostro.getDebolezza()
        );
    }

    public String autoRisolvi() {
        StringBuilder logDiBattaglia = new StringBuilder();
        logDiBattaglia.append("--- INIZIA LO SCONTRO con un ").append(nemico.getNome()).append("! ---\n");

        logDiBattaglia.append(esploratore.entraInBattaglia(nemico)).append("\n");

        while (esploratore.isVivo() && nemico.isVivo()) {

            int dannoEroe = esploratore.eseguiAttacco();
            if (esploratore.getBuffAttivo() != null) {
                // Se il danno calcolato dal buff è maggiore, significa che l'oggetto è stato efficace
                int dannoModificato = esploratore.getBuffAttivo().applicaBuffCombattimento(dannoEroe, nemico);
                if (dannoModificato > dannoEroe) {
                    logDiBattaglia.append("-> Il tuo Buff ha reagito alla debolezza del mostro!\n");
                }
                dannoEroe = dannoModificato;
            }

            nemico.subisciDanno(dannoEroe);
            if (nemico.isVivo()) {
                esploratore.subisciDanno(nemico.getDanno());
            }
        }

        esploratore.resetBuff();

        // Controllo Post-Battaglia
        if (esploratore.isVivo()) {
            logDiBattaglia.append("\nVittoria! I dati di ").append(mostro.getNome()).append(" sono stati registrati.\n");
            esploratore.getBestiario().aggiungiMostro(mostro);

            Oggetto loot = (new java.util.Random().nextBoolean()) ? new PozioneCura() : new OlioNecrofagi();
            String messaggioLoot = esploratore.getInventario().aggiungiOggetto(loot);

            logDiBattaglia.append(messaggioLoot).append("\n");
            logDiBattaglia.append("Ti rimangono ").append(esploratore.getPuntiVita()).append(" HP.");
        } else {
            logDiBattaglia.append("\nSei caduto in battaglia... Game Over.");
        }

        return logDiBattaglia.toString();
    }
}