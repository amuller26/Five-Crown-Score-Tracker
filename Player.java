public class Player {
    private final String name;
    private int score;

    public Player(final String name) {
        this.name = name;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public String getStringScore() {
        return String.valueOf(score);
    }

    public void addScore(final int num) {
        score += num;
    }

    public void setScore(int newScore) {
        score = newScore;
    }

    public String toString() {
        return name + "              " + score;
    }
}
