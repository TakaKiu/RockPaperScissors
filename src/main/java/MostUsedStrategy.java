import java.util.HashMap;
import java.util.Map;

/**
 * The MostUsedStrategy class represents a strategy for choosing a move in the Rock-Paper-Scissors game.
 * It selects the move that the player has used the most in previous rounds.
 */
public class MostUsedStrategy implements Strategy {
    private static final String NAME = "Most Used";

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

        // Find the most used choice among the player's previous selections
        String mostUsedChoice = findMostUsedChoice(choices);

        // Determine the winning move for the computer based on the most used choice
        String computerChoice = determineWinningMove(mostUsedChoice);

        return computerChoice;
    }

    /**
     * Find the player's most used choice among the given options.
     *
     * @param choices The available choices ("Rock", "Paper", "Scissors").
     * @return The most used choice among the player's previous selections.
     */
    private String findMostUsedChoice(String[] choices) {
        String mostUsedChoice = choices[0];
        int mostUsedCount = playerChoiceCount.get(mostUsedChoice);

        for (String choice : choices) {
            int count = playerChoiceCount.get(choice);
            if (count > mostUsedCount) {
                mostUsedChoice = choice;
                mostUsedCount = count;
            }
        }

        return mostUsedChoice;
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

