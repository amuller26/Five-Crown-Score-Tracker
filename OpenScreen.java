import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

public class OpenScreen {
    private JFrame frame;
    private final JTextField namesTextField;
    private ArrayList<String> names;
    private ScoreTracker st;

    OpenScreen() {
        prepareGUI();
        final JLabel namesLabel = new JLabel("Enter player name: ");
        namesTextField = new JTextField(5);
        final JButton namesButton = new JButton("Enter");
        namesTextField.setPreferredSize(namesButton.getPreferredSize());
        final JButton done = new JButton("Done");
        names = new ArrayList<>();


        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(namesLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(namesTextField, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        frame.add(namesButton, gbc);
        namesButton.addActionListener((event) -> {
            names.add(namesTextField.getText());
            namesTextField.setText("");
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        frame.add(done, gbc);
        done.addActionListener((event) -> {
            frame.setVisible(false);
            st = new ScoreTracker(names);
            st.game.setVisible(true);
        });
        frame.setVisible(true);
    }

    private void prepareGUI() {
        frame = new JFrame("Player Information");
        frame.setSize(300, 100);
        frame.setLayout(new GridBagLayout());
        frame.setUndecorated(true);
    }

    public String toString() {
        return "done";
    }
}
