import java.util.Random;

/**
 * The CheatStrategy class represents a strategy for choosing a move in the Rock-Paper-Scissors game.
 * It occasionally cheats by selecting a move that wins against the player's choice with a defined probability.
 */
public class CheatStrategy implements Strategy {
    private static final String NAME = "Cheat";
    private static final double CHEAT_PROBABILITY = 0.10;

    /**
     * {@inheritDoc}
     */
    @Override
    public String determineMove() {
        double randomValue = Math.random();

        // If the random value falls within the cheat probability, cheat and select a winning move
        if (randomValue <= CHEAT_PROBABILITY) {
            String playerChoice = getPlayerChoice();
            return determineWinningMove(playerChoice);
        } else {
            // Otherwise, select a random move
            return getRandomMove();
        }
    }

    /**
     * Get a random choice among "Rock," "Paper," and "Scissors."
     *
     * @return A random choice.
     */
    private String getPlayerChoice() {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        int randomIndex = random.nextInt(choices.length);
        return choices[randomIndex];
    }

    /**
     * Determine the winning move against the specified player choice.
     *
     * @param playerChoice The choice made by the player ("Rock," "Paper," or "Scissors").
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
     * Get a random move among "Rock," "Paper," and "Scissors."
     *
     * @return A random move.
     */
    private String getRandomMove() {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        int randomIndex = random.nextInt(choices.length);
        return choices[randomIndex];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return NAME;
    }
}

