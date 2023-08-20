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
import java.util.Scanner;

public class BlackJackGame {

    //Definig instance variables of the game
    private final String name;
    private final List<Player> players;
    private Dealer dealer;
    private Deck deck;
    private boolean gamePlayed;

    // Constructor for the BlackJackGame class
    public BlackJackGame(String name) {
        this.name = name;
        players = new ArrayList<>();
        deck = new Deck();
        deck.shuffle();
        dealer = new Dealer("Dealer");
        gamePlayed = false;
    }

    //Getter to get the name
    public String getName() {
        return name;
    }

    // Adding a player to our game
    public void addPlayer(String playerName) {
        Player newPlayer = new Player(playerName);
        players.add(newPlayer);
    }

    // Removing a player from the game
    public void removePlayer(String playerName) {
        players.removeIf(player -> player.getName().equalsIgnoreCase(playerName));
    }

    //Seeing how many players are in the game
    public int getNumberOfPlayers() {
        return players.size();
    }

    // Deal the initial cards to players and dealer
    public void dealInitialCards() {
        for (Player player : players) {
            player.addCard(deck.drawCard());
            player.addCard(deck.drawCard());
        // Showing initial cards of players and dealer's one card
        }
        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());

        System.out.println("--- Initial Cards ---");
        for (Player player : players) {
            System.out.println(player.getName() + "'s cards: " + player.getHand());
        }
        System.out.println("Dealer's card: " + dealer.getHand().get(0));
    }

    public void play() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("--- Player Selection ---");
            // Player selection using for loop
            for (int i = 0; i < players.size(); i++) {
                System.out.println((i + 1) + ". " + players.get(i).getName());
            }

            int playerIndex;
            do {
                System.out.print("Select a player by entering the corresponding number: ");
                playerIndex = scanner.nextInt();
                scanner.nextLine();
            } while (playerIndex < 1 || playerIndex > players.size());

            Player selectedPlayer = players.get(playerIndex - 1);
            System.out.println("Selected player: " + selectedPlayer.getName());

            // Playing turns for selected player and dealer
            playPlayerTurn(selectedPlayer, scanner);
            playDealerTurn(scanner);
            declareWinner(selectedPlayer);
        }
        gamePlayed = true;
    }

    // Player's turn to play
    private void playPlayerTurn(Player player, Scanner scanner) {
        while (player.getScore() < 21) {
            // Player can hit until they bust or choose to stand
            System.out.print(player.getName() + ", do you want to hit? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                player.addCard(deck.drawCard());
                System.out.println(player.getName() + "'s cards: " + player.getHand());
                if (player.getScore() > 21) {
                    System.out.println(player.getName() + " is bust!");
                    break;
                }
            } else if (input.equals("n")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'y' to hit or 'n' to stand.");
            }
        }
    }

    // Dealer's turn to play
    private void playDealerTurn(Scanner scanner) {
        // Dealer must hit until their score is 17 or higher
        while (dealer.getScore() < 17) {

            dealer.addCard(deck.drawCard());
        }
        System.out.println("Dealer's cards: " + dealer.getHand());
        if (dealer.getScore() > 21) {
            System.out.println("Dealer is bust!");
        }
    }

    // Declaring the winner based on the scores of player and dealer
    private void declareWinner(Player player) {
        // If player busts, dealer wins. If dealer busts, player wins or stating tie upon score comparison.
        System.out.println("\n--- Game Result ---");
        System.out.println(player.getName() + "'s score: " + player.getScore());
        System.out.println("Dealer's score: " + dealer.getScore());

        if (player.getScore() > 21) {
            System.out.println(player.getName() + " loses!");
        } else if (dealer.getScore() > 21) {
            System.out.println(player.getName() + " wins!");
        } else if (player.getScore() > dealer.getScore()) {
            System.out.println(player.getName() + " wins!");
        } else if (player.getScore() < dealer.getScore()) {
            System.out.println(player.getName() + " loses!");
        } else {
            System.out.println(player.getName() + " ties with the dealer!");
        }
    }

    //Main method.
    public static void main(String[] args) {
        // Scanner for user input
        try (Scanner scanner = new Scanner(System.in)) {
            BlackJackGame game = new BlackJackGame("Blackjack");

            // Game menu
            System.out.println("Welcome to " + game.getName() + "!");
            System.out.println("1. Add player");
            System.out.println("2. Remove player");
            System.out.println("3. Play game");
            System.out.println("4. Exit");

            boolean exit = false;
            while (!exit) {
                System.out.print("Enter your choice (1-4): ");
                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    
                    // Acting out based on user's choice and following up with the action.
                    switch (choice) {
                        case 1:
                            System.out.print("Enter player name: ");
                            String playerName = scanner.nextLine();
                            game.addPlayer(playerName);
                            System.out.println("Player " + playerName + " added.");
                            break;

                        case 2:
                            System.out.print("Enter player name to remove: ");
                            String playerToRemove = scanner.nextLine();
                            game.removePlayer(playerToRemove);
                            System.out.println("Player " + playerToRemove + " removed.");
                            break;

                        case 3:
                            if (game.getNumberOfPlayers() < 1) {
                                System.out.println("Cannot play the game. Add at least one player.");
                            } else {
                                game.dealInitialCards();
                                game.play();
                                exit = true;
                            }
                            break;

                        case 4:
                            exit = true;
                            break;

                        default:
                            System.out.println("Invalid choice. Please enter a number from 1 to 4.");
                            break;
                    }
                } catch (NumberFormatException e) {
                    // Handling invalid input
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
                }
            }
            System.out.println("Thank you for playing " + game.getName() + "!");
        }
    }
}