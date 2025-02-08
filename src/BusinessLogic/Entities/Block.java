package BusinessLogic.Entities;

import java.awt.Image;

public class Block {
    public int x;
    public int y;
    public int width;
    public int height;
    public Image img;

    public Block(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }
}