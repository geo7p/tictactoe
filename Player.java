public class Player {
    private String symbol;
    private int score;

    public Player(String symbol, int score) {
        this.symbol = symbol;
        this.score = score;
    }

    public String getSymbol() {
        return this.symbol;
    }
    public int getScore() {
        return this.score;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void incrementScore() {
        this.score += 1;
    }
}
