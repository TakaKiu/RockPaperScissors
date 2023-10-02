import java.util.Random;

/**
 * The RandomStrategy class represents a strategy for choosing a move in the Rock-Paper-Scissors game.
 * It randomly selects one of the three possible moves: Rock, Paper, or Scissors.
 */
public class RandomStrategy implements Strategy {
    private static final String NAME = "Random";

    @Override
    public String determineMove() {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        int randomIndex = random.nextInt(choices.length);
        return choices[randomIndex];
    }

    @Override
    public String getName() {
        return NAME;
    }
}
