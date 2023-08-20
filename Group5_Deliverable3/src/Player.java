/*
 * DELIVERABLE 3
Group 5 members: 
Karanveer Singh
Aniket Sharma
Beenu Puri
Jimit Pandya
 */

import java.util.ArrayList;
import java.util.List;

public class Player {
    
    private final String name;
    private final List<Card> hand;
    
    public Player(String name) {
        this.name = name;
        // Initialization of the list of cards in the hand as an empty ArrayList
        hand = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public List<Card> getHand() {
        // Returning the list of cards in the player's hand
        return hand;
    }
    
    public void addCard(Card card) {
        // Add a card to the player's hand
        hand.add(card);
    }
    
    public int getScore() {
        // Initialize the score and aces to 0
        int score = 0;
        int aceCount = 0;
    
        for (Card card : hand) {
            // Using for to iterate over each card in the player's hand
            Rank rank = card.getRank();
            if (rank == Rank.ACE) {
                aceCount++;
                // Adding 11 to the score (Aces initially count as 11)
                score += 11;
            } else if (rank == Rank.KING || rank == Rank.QUEEN || rank == Rank.JACK) {
                // Adding 10 to the score for King, Queen, and Jack
                score += 10;
            } else {
                // Adding the value of the rank to the score
                score += rank.getValue();
            }
        }
    
        // Checking if the score is over 21 and there are Aces in the hand
        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }
        
        // Returning the final score
        return score;
    }
}

    