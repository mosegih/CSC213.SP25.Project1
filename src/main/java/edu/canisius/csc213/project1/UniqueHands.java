package edu.canisius.csc213.project1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UniqueHands {
    public static void main(String[] args) {
        
        int[] deckSizes = {24, 28};
        int[] handSizes = {6, 7};

        
        for (int deckSize : deckSizes) {
            for (int handSize : handSizes) {
                System.out.println("Running simulation: Deck size = " + deckSize + ", Hand size = " + handSize);

                // Run the simulation
                int draws = runSimulation(deckSize, handSize);

                
                writeResultsToCSV(deckSize, handSize, draws);
                System.out.println("Simulation complete: " + draws + " draws.");
            }
        }
    }

    // run th simulation
    public static int runSimulation(int deckSize, int handSize) {
        Set<Set<Card>> uniqueHands = new HashSet<>();
        Deck deck = new Deck(deckSize);

        int drawCount = 0;

        // unique hands are seen
        while (uniqueHands.size() < calculateUniqueHandCount(deckSize, handSize)) {
            if (deck.remainingCards() < handSize) {
                deck.reset(deckSize);
            }

            List<Card> hand = deck.drawHand(handSize);
            uniqueHands.add(new HashSet<>(hand));
            drawCount++;
        }

        return drawCount;
    }

    // Method results to a CSV file
    private static void writeResultsToCSV(int deckSize, int handSize, int draws) {
        String fileName = "unique_hands_results.csv";
        boolean fileExists = new java.io.File(fileName).exists();

        try (FileWriter writer = new FileWriter(fileName, true)) {
            if (!fileExists) {
                writer.append("Deck Size,Hand Size,Draws\n");
            }
            writer.append(deckSize + "," + handSize + "," + draws + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    // calculate the total of unique hands
    public static long calculateUniqueHandCount(int deckSize, int handSize) {
        return factorial(deckSize) / (factorial(handSize) * factorial(deckSize - handSize));
    }

    // Factor-method
    private static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}