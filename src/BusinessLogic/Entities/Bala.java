package BusinessLogic.Entities;

public class Bala extends Block {
    public boolean used = false;

    public Bala(int x, int y, int width, int height) {
        super(x, y, width, height, null);
    }
}