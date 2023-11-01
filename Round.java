public class Round {
    private String[] rounds = {"3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Joker"};
    private int roundNum;

    public Round() {
        roundNum = 0;
    }

    public void upRound() {
        roundNum++;
    }

    public int getRoundNum() {
        return roundNum;
    }

    public String[] getRounds() {
        return rounds;
    }
}
