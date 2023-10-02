/**
 * The LastUsedStrategy class represents a strategy for choosing a move in the Rock-Paper-Scissors game.
 * It selects the move that the player used in the last round, except for the first round where it defaults to "Rock."
 */
public class LastUsedStrategy implements Strategy {
    private static final String NAME = "Last Used";
    private String lastPlayerMove;

    /**
     * {@inheritDoc}
     */
    @Override
    public String determineMove() {
        // If it's the first round, default to "Rock"
        if (lastPlayerMove == null) {
            return "Rock";
        } else {
            // Choose the move used by the player in the last round
            return lastPlayerMove;
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

