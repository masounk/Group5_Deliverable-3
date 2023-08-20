/*
 * DELIVERABLE 3
Group 5 members: 
Karanveer Singh
Aniket Sharma
Beenu Puri
Jimit Pandya
 */

public class Card {

//Instance variables of type Rank and Suit
private final Rank rank;
private final Suit suit;

//Constructor for Card class
public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
}

//Getters
public Rank getRank() {
    return rank;
}

public Suit getSuit() {
    return suit;
}

@Override
public String toString() {
    // Returns a String of the card, e.g., "Ace of Hearts"
    return rank + " of " + suit;
    }
}
