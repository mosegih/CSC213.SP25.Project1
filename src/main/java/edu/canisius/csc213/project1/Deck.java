package edu.canisius.csc213.project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();
    private int currentIndex = 0;

    public Deck(int deckSize) {
        for (int i = 1; i <= deckSize; i++) {
            cards.add(new Card(i));
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
        currentIndex = 0;
    }

    public List<Card> drawHand(int handSize) {
        if (currentIndex + handSize > cards.size()) {
            throw new IllegalStateException("Not enough cards remaining to draw a hand.");
        }
        List<Card> hand = new ArrayList<>(cards.subList(currentIndex, currentIndex + handSize));
        currentIndex += handSize;
        return hand;
    }

    public int remainingCards() {
        return cards.size() - currentIndex;
    }

    public void reset(int deckSize) {
        cards.clear();
        for (int i = 1; i <= deckSize; i++) {
            cards.add(new Card(i));
        }
        shuffle();
    }

    public static void main(String[] args) {
        Deck deck = new Deck(52);
        System.out.println("Initial deck: " + deck.cards);
        List<Card> hand = deck.drawHand(5);
        System.out.println("Drawn hand: " + hand);
        System.out.println("Remaining cards: " + deck.remainingCards());
        deck.reset(52);
        System.out.println("Deck after reset: " + deck.cards);
    }



}