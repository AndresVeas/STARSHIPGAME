package BusinessLogic.Entities;

import java.awt.Image;

public class Alien extends Block{
    public boolean alive = true;

    public Alien(int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
    }
}
