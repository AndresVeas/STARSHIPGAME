package BusinessLogic.Entities;

import java.awt.Image;

public class Nave extends Block {

    private int shipWidth;
    private int shipHeight;
    private int shipX;
    private int shipY;
    private int shipVelocityX;
    
    public Nave(int x, int y, int width, int height, Image img,int tileSize, int columns, int rows) {
        shipWidth = tileSize*2;
        shipHeight = tileSize;
        shipX = tileSize * columns/2 - tileSize;
        shipY = tileSize * rows - tileSize*2;
        shipVelocityX = tileSize;
        super(x, y, width, height, img);
    }

    public int getShipWidth() {
        return shipWidth;
    }

    public void setShipWidth(int shipWidth) {
        this.shipWidth = shipWidth;
    }

    public int getShipHeight() {
        return shipHeight;
    }

    public void setShipHeight(int shipHeight) {
        this.shipHeight = shipHeight;
    }

    public int getShipX() {
        return shipX;
    }

    public void setShipX(int shipX) {
        this.shipX = shipX;
    }

    public int getShipY() {
        return shipY;
    }

    public void setShipY(int shipY) {
        this.shipY = shipY;
    }

    public int getShipVelocityX() {
        return shipVelocityX;
    }

    public void setShipVelocityX(int shipVelocityX) {
        this.shipVelocityX = shipVelocityX;
    }


}