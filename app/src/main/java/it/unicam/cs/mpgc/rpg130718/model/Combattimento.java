package it.unicam.cs.mpgc.rpg130718.model;

public class Combattimento {
    private Esploratore esploratore;
    private Mostro mostro;

    public Combattimento(Esploratore esploratore, Mostro mostro) {
        this.esploratore = esploratore;
        this.mostro = mostro;
    }

    public boolean autoRisolvi() {
        System.out.println("\n--- INIZIA LO SCONTRO ---");
        esploratore.entraInBattaglia(mostro);

        while (esploratore.isVivo() && mostro.isVivo()) {

            int dannoEroe = esploratore.eseguiAttacco();

            if (esploratore.getBuffAttivo() != null) {
                dannoEroe = esploratore.getBuffAttivo().applicaBuffCombattimento(dannoEroe, mostro);
            }

            mostro.subisciDanno(dannoEroe);

            if (mostro.isVivo()) {
                esploratore.subisciDanno(mostro.getDanno());
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