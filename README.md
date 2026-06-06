# 📌 The Witcher RPG

Applicazione Java che implementa un gioco di ruolo testuale con interfaccia grafica ispirato all'universo di The Witcher.
Il giocatore sceglie una classe tra quattro disponibili (Witcher, Strega, Bardo, Umano) e si avventura nella Foresta di
Brokilon, affrontando mostri estratti casualmente. L'obiettivo è sopravvivere e completare il Bestiario personale
sconfiggendo tutte le creature della foresta.

---

## 🚀 Come eseguire il progetto

### Prerequisiti

- Java 25 (LTS)
- Gradle (wrapper incluso nel repository)

### Istruzioni

```bash
git clone https://github.com/LoreVianello/Witcher-RPG.git
cd Witcher-RPG
```

### Build del progetto

```bash
./gradlew build
```

### Esecuzione

```bash
./gradlew run
```

---

## 🤖 Uso di strumenti di AI

Per lo sviluppo di questo progetto è stato utilizzato esclusivamente **Gemini 3.1 Pro (modello a ragionamento esteso)**. Lo strumento è stato impiegato rigorosamente come assistente di supporto per la code review e lo studio, e mai come generatore sostitutivo alla scrittura del codice sorgente.

### Attività svolte con il supporto dell'AI:
* **Analisi incrementale del codice:** Revisione iterativa del codice scritto (analizzato poco per volta) per individuare vulnerabilità logiche, fare troubleshooting e scovare *code smells* (es. violazioni del principio DRY o disallineamenti architetturali).
* **Supporto Teorico:** Richiesta di spiegazioni concettuali avanzate, in particolare sul funzionamento della **Reflection nativa in Java** (es. `Class.forName()`), argomento che è stato successivamente studiato e approfondito in completa autonomia per implementarlo consapevolmente nelle Factory e negli Adapter GSON.

### Livello di intervento personale:
Il livello di intervento personale sul codice è stato totale. Qualsiasi suggerimento architetturale o di ottimizzazione fornito dall'AI è stato:
* **Visionato e compreso** a fondo nelle sue implicazioni tecniche.
* **Riadattato e riscritto manualmente** per integrarsi in modo coerente con il dominio specifico del gioco (The Witcher RPG) e con il pattern architetturale MVC stabilito.
* **Sottoposto a test diretti** per validarne l'effettiva utilità, scartando le soluzioni inutilmente complesse in favore di un approccio più pragmatico (*KISS*).

---
📌 Per una descrizione più dettagliata dell’uso dell’AI, consultare la **Wiki del repository**.

---

## ⚠️ Nota

Per la documentazione completa del progetto — scelte progettuali, architettura, principi SOLID, persistenza e
funzionalità — fare riferimento alla **Wiki** associata al repository GitHub.
