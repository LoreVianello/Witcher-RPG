package it.unicam.cs.mpgc.rpg130718.model;

public class Combattimento {
    private Esploratore esploratore;
    private Mostro mostro;

    public Combattimento(Esploratore esploratore, Mostro mostro) {
        this.esploratore = esploratore;
        this.mostro = mostro;
    }

    /**
     * Risolve lo scontro in automatico fino alla morte di uno dei due.
     * @return true se l'esploratore vince, false se l'esploratore muore.
     */
    public boolean autoRisolvi() {
        // Valori di attacco temporanei per testare la logica
        int attaccoEroe = 20;
        int attaccoMostro = 15;

        System.out.println("Inizia lo scontro! " + esploratore.getNome() + " VS " + mostro.getNome());

        // Il ciclo continua finché ENTRAMBI sono vivi
        while (esploratore.isVivo() && mostro.isVivo()) {

            // 1. Turno dell'Eroe
            mostro.subisciDanno(attaccoEroe);

            // Se il mostro muore per questo colpo
            if (!mostro.isVivo()) {
                System.out.println("Vittoria! Hai sconfitto: " + mostro.getNome());

                // Aggiunta automatica al catalogo
                esploratore.getBestiario().aggiungiMostro(mostro);
                System.out.println("I dati di " + mostro.getNome() + " sono stati registrati nel Bestiario.");
                return true;
            }

            // 2. Turno del Mostro (se è sopravvissuto all'attacco)
            esploratore.subisciDanno(attaccoMostro);

            // Se l'eroe muore
            if (!esploratore.isVivo()) {
                System.out.println("Sei morto... Game Over.");
                return false;
            }
        }
        return false;
    }
}