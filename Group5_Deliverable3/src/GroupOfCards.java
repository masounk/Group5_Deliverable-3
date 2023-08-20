/*
 * DELIVERABLE 3
Group 5 members: 
Karanveer Singh
Aniket Sharma
Beenu Puri
Jimit Pandya
 */

import java.util.ArrayList;
import java.util.Random;

public class GroupOfCards {
private ArrayList<Card> cards;
private int nextCardIndex;

public GroupOfCards() {
    // Initializing the list of cards as an empty ArrayList
    cards = new ArrayList<>();
    for (Suit suit : Suit.values()) {
        for (Rank rank : Rank.values()) {
            // Creating a new card with the current rank and suit, and add it to the group
            cards.add(new Card(rank, suit));
        }
    }
    nextCardIndex = 0;
}

public void shuffle() {
    Random random = new Random();

    for (int i = 0; i < cards.size(); i++) {
        // Generating a random index j within the range of the cards list
        int j = random.nextInt(cards.size());
        Card temp = cards.get(i);
        // Swapping the card at index i with the card at index j
        cards.set(i, cards.get(j));
        // Placing the card from the temporary variable at index j
        cards.set(j, temp);
    }

    nextCardIndex = 0;
}

public Card drawCard() {
    // Getting the card at the current nextCardIndex
    Card card = cards.get(nextCardIndex);
    nextCardIndex++;
    return card;
}

public int size() {
    // Calculating and returning the number of cards remaining in the group
    return cards.size() - nextCardIndex;
    }
}
