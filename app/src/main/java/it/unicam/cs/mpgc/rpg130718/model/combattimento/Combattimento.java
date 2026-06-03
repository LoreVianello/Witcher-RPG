package it.unicam.cs.mpgc.rpg130718.model.combattimento;

import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;
import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.Buff;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.GeneratoreLoot;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.Oggetto;

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

        esploratore.preparaBattaglia(nemico);
        logDiBattaglia.append(esploratore.getLogIngressoBattaglia()).append("\n");

        while (esploratore.isVivo() && nemico.isVivo()) {

            int dannoEroe = esploratore.eseguiAttacco(nemico);
            if (esploratore.getBuffAttivo() != null) {
                if (esploratore.getBuffAttivo() instanceof Buff buff) {
                    // Se il danno calcolato dal buff è maggiore, significa che l'oggetto è stato efficace
                    int dannoModificato = buff.applicaBuffCombattimento(dannoEroe, nemico);
                    if (dannoModificato > dannoEroe) {
                        logDiBattaglia.append("-> Colpo critico: Il Buff ha reagito alla debolezza del mostro!\n");
                    }
                    dannoEroe = dannoModificato;
                }
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

            Oggetto loot = GeneratoreLoot.generaDropCasuale();
            String messaggioLoot = esploratore.getInventario().aggiungiOggetto(loot);

            logDiBattaglia.append(messaggioLoot).append("\n");
            logDiBattaglia.append("Ti rimangono ").append(esploratore.getPuntiVita()).append(" HP.");
        } else {
            logDiBattaglia.append("\nSei caduto in battaglia... Game Over.");
        }

        return logDiBattaglia.toString();
    }
}