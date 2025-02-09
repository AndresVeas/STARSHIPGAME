package BusinessLogic.Entities;

public class Jugador {
    public int score = 0;
    public boolean gameOver = true;

    public void reset() {
        score = 0;
        gameOver = false;
    }
}
