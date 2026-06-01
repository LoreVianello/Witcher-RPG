//package it.unicam.cs.mpgc.rpg130718;
//
//import it.unicam.cs.mpgc.rpg130718.model.combattimento.Combattimento;
//import it.unicam.cs.mpgc.rpg130718.model.esploratori.*;
//import it.unicam.cs.mpgc.rpg130718.model.mostri.Mostro;
//import it.unicam.cs.mpgc.rpg130718.model.oggetti.Oggetto;
//import it.unicam.cs.mpgc.rpg130718.utils.GestoreDati;
//
//import java.util.List;
//import java.util.Random;
//import java.util.Scanner;
//
//public class InterfacciaConsole {
//
//    private Scanner scanner;
//    private GestoreDati gestoreDati;
//
//    public InterfacciaConsole() {
//        this.scanner = new Scanner(System.in);
//        this.gestoreDati = new GestoreDati();
//    }
//
//    public void avvia() {
//        boolean inEsecuzione = true;
//
//        while (inEsecuzione) {
//            System.out.println("\n========================================");
//            System.out.println("       BENVENUTO IN THE WITCHER RPG     ");
//            System.out.println("========================================");
//            System.out.println("=== MENU' PRINCIPALE ===");
//            System.out.println("1. Nuova Partita");
//
//            boolean salvataggioPresente = gestoreDati.esisteSalvataggio();
//            if (salvataggioPresente) {
//                System.out.println("2. Continua Partita");
//            } else {
//                System.out.println("2. Continua Partita (Non disponibile)");
//            }
//
//            System.out.println("3. Esci dal Gioco");
//            System.out.print("Scegli un'opzione: ");
//
//            String input = scanner.nextLine().trim();
//
//            switch (input) {
//                case "1":
//                    avviaNuovaPartita();
//                    break;
//                case "2":
//                    if (salvataggioPresente) {
//                        continuaPartita();
//                    } else {
//                        System.out.println("\n[!] Nessun salvataggio trovato. Avvia una Nuova Partita.");
//                    }
//                    break;
//                case "3":
//                    System.out.println("\nUscita in corso... Addio, Esploratore.");
//                    inEsecuzione = false;
//                    break;
//                default:
//                    System.out.println("\n[!] Scelta non valida. Inserisci 1, 2 o 3.");
//            }
//        }
//        scanner.close();
//    }
//
//    private void avviaNuovaPartita() {
//        System.out.println("\n=== CREAZIONE PERSONAGGIO ===");
//        System.out.println("Scegli la tua classe:");
//        System.out.println("1. Witcher (Spadaccino mutante, usa i Segni)");
//        System.out.println("2. Strega (Potente maga, usa gli Incantesimi)");
//        System.out.println("3. Bardo (Supporto, si cura attaccando)");
//        System.out.println("4. Umano (Guerriero standard, usa l'Argento)");
//        System.out.print("Scelta (1-4): ");
//
//        String sceltaClasse = scanner.nextLine().trim();
//
//        System.out.print("Inserisci il nome del tuo eroe: ");
//        String nome = scanner.nextLine().trim();
//
//        if (nome.isEmpty()) {
//            nome = "Sconosciuto";
//        }
//
//        Esploratore eroe;
//        switch (sceltaClasse) {
//            case "1": eroe = new Witcher(nome); break;
//            case "2": eroe = new Strega(nome); break;
//            case "3": eroe = new Bardo(nome); break;
//            case "4": eroe = new Umano(nome); break;
//            default:
//                System.out.println("Scelta non valida. Ti e' stato assegnato 'Umano' di default.");
//                eroe = new Umano(nome);
//        }
//
//        System.out.println("\nEroe creato con successo! Benvenuto, " + eroe.getNome() + ".");
//        esploraForesta(eroe);
//    }
//
//    private void continuaPartita() {
//        Esploratore eroe = gestoreDati.caricaPartita();
//        if (eroe != null) {
//            System.out.println("\nSalvataggio caricato con successo!");
//            System.out.println("Bentornato, " + eroe.getNome() + " (" + eroe.getClass().getSimpleName() + ").");
//            System.out.println("HP attuali: " + eroe.getPuntiVita() + "/" + eroe.getPuntiVitaMax());
//            esploraForesta(eroe);
//        } else {
//            System.out.println("\n[!] Errore: impossibile caricare il salvataggio.");
//        }
//    }
//
//    private void esploraForesta(Esploratore eroe) {
//        List<Mostro> catalogo = gestoreDati.caricaCatalogoMostri();
//        if (catalogo.isEmpty()) {
//            System.out.println("Errore: catalogo mostri vuoto. Ritorno al menu' principale.");
//            return;
//        }
//
//        boolean inForesta = true;
//        Random random = new Random();
//
//        while (inForesta && eroe.isVivo()) {
//            System.out.println("\n=== FORESTA DI BROKILON ===");
//            System.out.println("1. Vai avanti (Cerca un incontro)");
//            System.out.println("2. Consulta Bestiario (" + eroe.getBestiario().getNumeroMostriScoperti() + "/" + catalogo.size() + ")");
//            System.out.println("3. Salva ed Esci");
//            System.out.print("Cosa vuoi fare? ");
//
//            String scelta = scanner.nextLine().trim();
//
//            switch (scelta) {
//                case "1":
//                    List<Mostro> mostriDisponibili = catalogo.stream()
//                            .filter(m -> !eroe.getBestiario().haScoperto(m))
//                            .toList();
//
//                    // Controllo di sicurezza: se la lista è vuota, abbiamo già ucciso tutti!
//                    if (mostriDisponibili.isEmpty()) {
//                        System.out.println("\nLa foresta è stranamente silenziosa... Non c'è più nulla da cacciare!");
//                        break; // Interrompe il caso e torna al menù (dove scatterà il controllo vittoria)
//                    }
//
//                    // Peschiamo un mostro casuale, ma stavolta dalla lista filtrata
//                    Mostro base = mostriDisponibili.get(random.nextInt(mostriDisponibili.size()));
//                    System.out.println("\n[!] Un " + base.getNome() + " selvatico balza fuori dai cespugli!");
//
//                    // 2. Fase Inventario (Menù Numerico Dinamico) - [Questo blocco rimane identico]
//                    List<Oggetto> oggettiPosseduti = eroe.getInventario().getOggetti();
//                    if (!oggettiPosseduti.isEmpty()) {
//                        System.out.println("\nHai degli oggetti nello zaino. Vuoi usarne uno?");
//                        System.out.println("0. Non usare nulla");
//                        for (int i = 0; i < oggettiPosseduti.size(); i++) {
//                            System.out.println((i + 1) + ". Usa " + oggettiPosseduti.get(i).getNome());
//                        }
//                        System.out.print("Scelta (0-" + oggettiPosseduti.size() + "): ");
//                        String sceltaOgg = scanner.nextLine().trim();
//
//                        try {
//                            int indice = Integer.parseInt(sceltaOgg);
//                            if (indice > 0 && indice <= oggettiPosseduti.size()) {
//                                Oggetto oggScelto = oggettiPosseduti.get(indice - 1);
//                                eroe.usaOggetto(oggScelto.getNome());
//                            }
//                        } catch (NumberFormatException e) {
//                            System.out.println("Scelta non numerica, ignori lo zaino e ti prepari a combattere.");
//                        }
//                    }
//
//                    // 3. Fase Combattimento
//                    Combattimento scontro = new Combattimento(eroe, base);
//                    boolean vittoria = scontro.autoRisolvi();
//
//                    if (!vittoria) {
//                        System.out.println("\n========================================");
//                        System.out.println("               GAME OVER                ");
//                        System.out.println("========================================");
//                        System.out.println("Sei caduto in battaglia. Il tuo viaggio finisce qui.");
//                        gestoreDati.cancellaSalvataggio();
//                        System.out.println("Premi INVIO per ricominciare...");
//                        scanner.nextLine();
//                        inForesta = false;
//                    } else {
//                        // Stampiamo gli HP rimanenti dell'eroe dopo aver vinto!
//                        System.out.println("Ti rimangono " + eroe.getPuntiVita() + " HP su " + eroe.getPuntiVitaMax() + ".");
//
//                        // Controllo Vittoria Globale
//                        if (eroe.getBestiario().getNumeroMostriScoperti() >= catalogo.size()) {
//                            System.out.println("\n========================================");
//                            System.out.println("               VITTORIA!                ");
//                            System.out.println("========================================");
//                            System.out.println("Incredibile! Hai sconfitto e registrato TUTTI i mostri di Brokilon!");
//                            System.out.println("Il tuo nome echeggera' per sempre nei canti dei Bardi.");
//                            gestoreDati.cancellaSalvataggio();
//                            System.out.println("Premi INVIO per ritornare al menu' principale...");
//                            scanner.nextLine();
//                            inForesta = false;
//                        }
//                    }
//                    break;
//
//                case "2":
//                    apriBestiario(eroe);
//                    break;
//
//                case "3":
//                    gestoreDati.salvaPartita(eroe);
//                    inForesta = false;
//                    break;
//
//                default:
//                    System.out.println("Azione non valida. Inserisci 1, 2 o 3.");
//            }
//        }
//    }
//
//    private void apriBestiario(Esploratore eroe) {
//        if (eroe.getBestiario().getMostroCorrente() == null) {
//            System.out.println("\nIl tuo bestiario e' ancora vuoto. Sconfiggi qualche mostro per iniziare a compilarlo!");
//            return;
//        }
//
//        boolean guardaBestiario = true;
//        while (guardaBestiario) {
//            Mostro m = eroe.getBestiario().getMostroCorrente();
//            System.out.println("\n--- PAGINA DEL BESTIARIO ---");
//            System.out.println("Nome: " + m.getNome());
//            System.out.println("Categoria: " + m.getCategoria());
//            System.out.println("Debolezza: " + m.getDebolezza());
//            System.out.println("Forza: " + m.getPuntiVita() + " HP | " + m.getDanno() + " Danno");
//            System.out.println("----------------------------");
//            System.out.println("1. Prossimo Mostro");
//            System.out.println("2. Mostro Precedente");
//            System.out.println("3. Chiudi Bestiario");
//            System.out.print("Azione: ");
//
//            String scelta = scanner.nextLine().trim();
//            switch (scelta) {
//                case "1": eroe.getBestiario().mostroSuccessivo(); break;
//                case "2": eroe.getBestiario().mostroPrecedente(); break;
//                case "3": guardaBestiario = false; break;
//                default: System.out.println("Scelta non valida.");
//            }
//        }
//    }
//}