package edu.canisius.csc213.project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(int deckSize) {
        if (deckSize != 24 && deckSize != 28) {
            throw new IllegalArgumentException("Deck size must be 24 or 28.");
        }
        initializeDeck(deckSize);
        shuffle();
    }

    // Initialize deck w/ unique cards
    private void initializeDeck(int deckSize) {
        cards = new ArrayList<>();
        Card.Suit[] suits = Card.Suit.values();
        Card.Rank[] ranks = Card.Rank.values();

        int suitIndex = 0;
        int rankIndex = 0;

        for (int i = 0; i < deckSize; i++) {
            cards.add(new Card(suits[suitIndex], ranks[rankIndex]));

            // next rank and suit
            rankIndex++;
            if (rankIndex >= ranks.length) {
                rankIndex = 0;
                suitIndex = (suitIndex + 1) % suits.length;
            }
        }
    }

    // Shuffle 
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Draw a hand 
    public List<Card> drawHand(int handSize) {
        if (handSize > cards.size()) {
            throw new IllegalArgumentException("Not enough cards to draw a hand.");
        }

        List<Card> hand = new ArrayList<>(cards.subList(0, handSize));
        cards.removeAll(hand);
        return hand;
    }

    // Reset the deck
    public void reset(int deckSize) {
        initializeDeck(deckSize);
        shuffle();
    }

    public int remainingCards() {
        return cards.size();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);  // Return a copy 
    }
}