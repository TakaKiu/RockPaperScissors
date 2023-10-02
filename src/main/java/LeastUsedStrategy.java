import java.util.HashMap;
import java.util.Map;

/**
 * The LeastUsedStrategy class represents a strategy for choosing a move in the Rock-Paper-Scissors game.
 * It selects the move that the player has used the least in previous rounds.
 */
public class LeastUsedStrategy implements Strategy {
    private static final String NAME = "Least Used";

    // Stores the count of each player's choice
    private Map<String, Integer> playerChoiceCount = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public String determineMove() {
        String[] choices = {"Rock", "Paper", "Scissors"};

        // Initialize choice counts to zero
        for (String choice : choices) {
            playerChoiceCount.put(choice, 0);
        }

        // Find the least used choice among the player's previous selections
        String leastUsedChoice = findLeastUsedChoice(choices);

        // Determine the winning move for the computer based on the least used choice
        String computerChoice = determineWinningMove(leastUsedChoice);

        return computerChoice;
    }

    /**
     * Find the player's least used choice among the given options.
     *
     * @param choices The available choices ("Rock", "Paper", "Scissors").
     * @return The least used choice among the player's previous selections.
     */
    private String findLeastUsedChoice(String[] choices) {
        String leastUsedChoice = choices[0];
        int leastUsedCount = playerChoiceCount.get(leastUsedChoice);

        for (String choice : choices) {
            int count = playerChoiceCount.get(choice);
            if (count < leastUsedCount) {
                leastUsedChoice = choice;
                leastUsedCount = count;
            }
        }

        return leastUsedChoice;
    }

    /**
     * Determine the winning move for the computer based on the player's choice.
     *
     * @param playerChoice The choice made by the player ("Rock", "Paper", or "Scissors").
     * @return The computer's winning move against the player's choice.
     */
    private String determineWinningMove(String playerChoice) {
        switch (playerChoice) {
            case "Rock":
                return "Paper";
            case "Paper":
                return "Scissors";
            case "Scissors":
                return "Rock";
            default:
                return "Rock"; // Default choice (e.g., if input is invalid)
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return NAME;
    }
}
