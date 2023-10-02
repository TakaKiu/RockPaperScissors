import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The RockPaperScissorsFrame class represents the main GUI frame for the Rock-Paper-Scissors game.
 * It allows the user to play the game, select the computer's strategy, and displays game statistics.
 */
public class RockPaperScissorsFrame extends JFrame {
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;
    private JTextArea resultsTextArea;
    private JTextField playerWinsTextField;
    private JTextField computerWinsTextField;
    private JTextField tiesTextField;

    private Strategy computerStrategy;

    /**
     * Constructs the RockPaperScissorsFrame and initializes the GUI components.
     */
    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choices"));

        JButton rockButton = new JButton("Rock", new ImageIcon("rock.png"));
        JButton paperButton = new JButton("Paper", new ImageIcon("paper.png"));
        JButton scissorsButton = new JButton("Scissors", new ImageIcon("scissors.png"));
        JButton quitButton = new JButton("Quit");

        String[] strategyOptions = {"Least Used", "Most Used", "Last Used", "Random", "Cheat"};
        JComboBox<String> strategyComboBox = new JComboBox<>(strategyOptions);

        rockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playGame("Rock");
            }
        });

        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playGame("Paper");
            }
        });

        scissorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playGame("Scissors");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        strategyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStrategy = (String) strategyComboBox.getSelectedItem();
                updateComputerStrategy(selectedStrategy);
            }
        });

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(strategyComboBox);

        JPanel statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));

        JLabel playerWinsLabel = new JLabel("Player Wins:");
        JLabel computerWinsLabel = new JLabel("Computer Wins:");
        JLabel tiesLabel = new JLabel("Ties:");
        playerWinsTextField = new JTextField(5);
        computerWinsTextField = new JTextField(5);
        tiesTextField = new JTextField(5);

        playerWinsTextField.setEditable(false);
        computerWinsTextField.setEditable(false);
        tiesTextField.setEditable(false);

        statsPanel.add(playerWinsLabel);
        statsPanel.add(playerWinsTextField);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(computerWinsTextField);
        statsPanel.add(tiesLabel);
        statsPanel.add(tiesTextField);

        resultsTextArea = new JTextArea(10, 30);
        resultsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);

        add(buttonPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        computerStrategy = new RandomStrategy();

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Plays a round of the game based on the player's choice and updates the UI accordingly.
     *
     * @param playerChoice The choice made by the player ("Rock", "Paper", or "Scissors").
     */
    private void playGame(String playerChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = computerStrategy.determineMove();

        String gameOutcome = determineGameOutcome(playerChoice, computerChoice);

        String result = getResultString(playerChoice, computerChoice, gameOutcome);
        resultsTextArea.append(result + " (" + computerStrategy.getName() + ")\n");

        updateStatistics(gameOutcome);

        playerWinsTextField.setText(Integer.toString(playerWins));
        computerWinsTextField.setText(Integer.toString(computerWins));
        tiesTextField.setText(Integer.toString(ties));
    }

    /**
     * Determines the outcome of a game round based on the player's and computer's choices.
     *
     * @param playerChoice   The choice made by the player ("Rock", "Paper", or "Scissors").
     * @param computerChoice The choice made by the computer.
     * @return The outcome of the game round ("Player wins," "Computer wins," or "Tie").
     */
    private String determineGameOutcome(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "Tie";
        } else if (
                (playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                        (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                        (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))
        ) {
            playerWins++;
            return "Player wins";
        } else {
            computerWins++;
            return "Computer wins";
        }
    }

    /**
     * Generates a formatted result string for displaying the game outcome.
     *
     * @param playerChoice   The choice made by the player ("Rock", "Paper", or "Scissors").
     * @param computerChoice The choice made by the computer.
     * @param gameOutcome    The outcome of the game round ("Player wins," "Computer wins," or "Tie").
     * @return The formatted result string.
     */
    private String getResultString(String playerChoice, String computerChoice, String gameOutcome) {
        return playerChoice + " vs. " + computerChoice + " (" + gameOutcome + ")";
    }

    /**
     * Updates the game statistics based on the outcome of a game round.
     *
     * @param gameOutcome The outcome of the game round ("Player wins," "Computer wins," or "Tie").
     */
    private void updateStatistics(String gameOutcome) {
        if (gameOutcome.equals("Player wins")) {
            playerWins++;
        } else if (gameOutcome.equals("Computer wins")) {
            computerWins++;
        } else {
            ties++;
        }
    }

    /**
     * Updates the computer's strategy based on the selected strategy from the JComboBox.
     *
     * @param selectedStrategy The selected strategy for the computer.
     */
    private void updateComputerStrategy(String selectedStrategy) {
        switch (selectedStrategy) {
            case "Least Used":
                computerStrategy = new LeastUsedStrategy();
                break;
            case "Most Used":
                computerStrategy = new MostUsedStrategy();
                break;
            case "Last Used":
                computerStrategy = new LastUsedStrategy();
                break;
            case "Random":
                computerStrategy = new RandomStrategy();
                break;
            case "Cheat":
                computerStrategy = new CheatStrategy();
                break;
            default:
                computerStrategy = new RandomStrategy();
                break;
        }
    }

    /**
     * The main method to launch the RockPaperScissorsFrame application.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RockPaperScissorsFrame frame = new RockPaperScissorsFrame();
                frame.setVisible(true);
            }
        });
    }
}
