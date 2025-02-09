package BusinessLogic.Entities;

import java.awt.Image;

public class Alien extends Block{
    private boolean alive;
    private int     alienVelocityX;
    private int     alienX;
    private int     alienY;
    private int     alienWidth;
    private int     alienHeight;

    public Alien(int x, int y, int width, int height, Image img,int tileSize) {
        alive = true;
        alienVelocityX = 1;
        alienX = tileSize;
        alienY = tileSize;
        alienWidth = tileSize * 2;
        alienHeight = tileSize;
        super(x, y, width, height, img);
    }

    public boolean isAlive() {
        return alive;
    }


    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    public int getAlienVelocityX() {
        return alienVelocityX;
    }


    public void setAlienVelocityX(int alienVelocityX) {
        this.alienVelocityX = alienVelocityX;
    }


    public int getAlienX() {
        return alienX;
    }


    public void setAlienX(int alienX) {
        this.alienX = alienX;
    }


    public int getAlienY() {
        return alienY;
    }


    public void setAlienY(int alienY) {
        this.alienY = alienY;
    }


    public int getAlienWidth() {
        return alienWidth;
    }


    public void setAlienWidth(int alienWidth) {
        this.alienWidth = alienWidth;
    }


    public int getAlienHeight() {
        return alienHeight;
    }


    public void setAlienHeight(int alienHeight) {
        this.alienHeight = alienHeight;
    }



}
