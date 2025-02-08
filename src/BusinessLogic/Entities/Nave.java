package BusinessLogic.Entities;

import java.awt.Image;

public class Nave extends Block {
    private int shipVelocityX;

    public Nave(int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
    }

    public int getShipVelocityX() {
        return shipVelocityX;
    }
    public void setShipVelocityX(int shipVelocityX) {
        this.shipVelocityX = shipVelocityX;
    }
}