import javax.swing.*;

public class RockPaperScissorsRunner {
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
