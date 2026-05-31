package it.unicam.cs.mpgc.rpg130718;

public class Main {
    public static void main(String[] args) {
        // Il Main si limita a istanziare l'interfaccia e ad avviarla.
        // Rispetta al 100% il Single Responsibility Principle.
        InterfacciaConsole interfaccia = new InterfacciaConsole();
        interfaccia.avvia();
    }
}