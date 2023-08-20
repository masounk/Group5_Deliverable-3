/*
 * DELIVERABLE 3
Group 5 members: 
Karanveer Singh
Aniket Sharma
Beenu Puri
Jimit Pandya
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    //List of cards in deck
    private final List<Card> cards;

    public Deck() {
        // Initializing the list of cards as an empty ArrayList
        cards = new ArrayList<>();
        // Iteraing over each suit using for loop.
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                // Creating a new card with the current rank and suit, and adding it to the deck
                cards.add(new Card(rank, suit));
            }
        }
    }
    
    public void shuffle() {
        // Shuffling the order of the cards using the Collections.shuffle() method
        Collections.shuffle(cards);
    }
    
    public Card drawCard() {
        //Removing and returning the card at the top of the deck (last index of the list)
        return cards.remove(cards.size() - 1);
    }
}

    