import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ScoreTracker {
    JFrame game;

    JPanel playerPanel;
    Player[] players;
    JComboBox<Player> list;
    JLabel score;
    JTextField playerScore;

    JPanel buttonsPanel;

    JPanel roundPanel;
    Round round;

    Font font;
    GridLayout grid;

    ScoreTracker(final ArrayList<String> names) {
        round = new Round();
        font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        grid = new GridLayout();

        prepareGUI();
        setPlayerPanel(names);
        setButtonsPanel();
        setRoundPanel();

        game.add(playerPanel, BorderLayout.NORTH);
        game.add(buttonsPanel, BorderLayout.CENTER);
        game.add(roundPanel, BorderLayout.SOUTH);
        game.setVisible(true);    }

    private void prepareGUI() {
        game = new JFrame("Five Crowns");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setExtendedState(JFrame.MAXIMIZED_BOTH);
        game.setLayout(new BorderLayout());
    }

    private void setPlayerPanel(ArrayList<String> names) {
        playerPanel = new JPanel();
        players = new Player[names.size()];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(names.get(i));
        }

        list = new JComboBox<>(players);
        list.setFont(font);
        list.addActionListener(e -> playerScore.setText(players[list.getSelectedIndex()].getStringScore()));

        score = new JLabel("Score: ");
        score.setFont(font);
        score.setHorizontalAlignment(SwingConstants.RIGHT);

        playerScore = new JTextField();
        JButton edit = new JButton("Edit");

        playerScore.setFont(font);
        playerScore.setText(players[list.getSelectedIndex()].getStringScore());
        edit.addActionListener(e -> {
            players[list.getSelectedIndex()].setScore(Integer.parseInt(playerScore.getText()));
            playerScore.setText(players[list.getSelectedIndex()].getStringScore());
        });

        playerPanel.setLayout(grid);
        playerPanel.add(list);
        playerPanel.add(score);
        playerPanel.add(playerScore);
        playerPanel.add(edit);
    }

    private void setButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(grid);
        JButton[] buttons = new JButton[round.getRounds().length];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(round.getRounds()[i]);
            buttons[i].setName(round.getRounds()[i]);
            buttons[i].setFont(font);
            int finalI = i;
            buttons[i].addActionListener((ActionEvent e) -> {
                final JButton o = (JButton) e.getSource();
                final String n = o.getName();
                if (n.equals(round.getRounds()[round.getRoundNum()])) {
                    players[list.getSelectedIndex()].addScore(20);
                } else {
                    if (buttons[finalI].getName().equals("Jack")) {
                        players[list.getSelectedIndex()].addScore(11);
                    } else if (buttons[finalI].getName().equals("Queen")) {
                        players[list.getSelectedIndex()].addScore(12);
                    } else if (buttons[finalI].getName().equals("King")) {
                        players[list.getSelectedIndex()].addScore(13);
                    } else if (buttons[finalI].getName().equals("Joker")) {
                        players[list.getSelectedIndex()].addScore(50);
                    } else {
                        int points = Integer.parseInt(buttons[finalI].getName());
                        players[list.getSelectedIndex()].addScore(points);
                    }
                }
                playerScore.setText(players[list.getSelectedIndex()].getStringScore());
            });
            buttonsPanel.add(buttons[i]);
        }
    }

    private void setRoundPanel() {
        roundPanel = new JPanel();
        roundPanel.setLayout(grid);

        JLabel roundCount = new JLabel("Round " + round.getRounds()[round.getRoundNum()] + "'s");
        roundCount.setFont(font);

        JButton up = new JButton("+");
        up.addActionListener((event) -> {
            round.upRound();
            roundCount.setText("Round " + round.getRounds()[round.getRoundNum()] + "'s");
            if (round.getRoundNum() == 10) {
                up.setVisible(false);
            }
        });

        roundPanel.add(roundCount);
        roundPanel.add(up);
    }

    public static void main(String[] args) {
        OpenScreen open = new OpenScreen();
        System.out.println(open);
    }
}
