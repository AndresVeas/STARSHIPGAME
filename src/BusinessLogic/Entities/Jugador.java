package BusinessLogic.Entities;

public class Jugador {
    private int score = 0;
    private boolean gameOver = false;

    public Jugador (){}
    
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }


}
