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

    public boolean autoRisolvi() {
        System.out.println("\n--- INIZIA LO SCONTRO ---");
        esploratore.entraInBattaglia(nemico);

        while (esploratore.isVivo() && nemico.isVivo()) {

            int dannoEroe = esploratore.eseguiAttacco();

            if (esploratore.getBuffAttivo() != null) {
                dannoEroe = esploratore.getBuffAttivo().applicaBuffCombattimento(dannoEroe, nemico);
            }

            nemico.subisciDanno(dannoEroe);

            if (nemico.isVivo()) {
                esploratore.subisciDanno(nemico.getDanno());
            }
        }

        esploratore.resetBuff();

        if (esploratore.isVivo()) {
            System.out.println("Vittoria! I dati di " + mostro.getNome() + " sono stati registrati.");
            esploratore.getBestiario().aggiungiMostro(mostro);

            Oggetto loot = (new java.util.Random().nextBoolean()) ? new PozioneCura() : new OlioNecrofagi();
            esploratore.getInventario().aggiungiOggetto(loot);

            return true;
        } else {
            System.out.println("Sei morto... Game Over.");
            return false;
        }
    }
}