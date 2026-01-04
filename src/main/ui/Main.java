package ui;

import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            try {
                new QuoteGame();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
