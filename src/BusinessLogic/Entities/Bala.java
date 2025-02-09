package BusinessLogic.Entities;

public class Bala extends Block {
    private boolean used = false;
    private int     balaWidth;
    private int     balaHeight;
    private int     bulletVelocityY;

    
    public Bala(int x, int y, int width, int height,int tileSize) {
        balaWidth   = tileSize / 8;
        balaHeight = tileSize / 2;
        bulletVelocityY = -13;
        super(x, y, width, height, null);
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public int getBalaWidth() {
        return balaWidth;
    }

    public void setBalaWidth(int balaWidth) {
        this.balaWidth = balaWidth;
    }

    public int getBalaHeight() {
        return balaHeight;
    }

    public void setBalaHeight(int balaHeight) {
        this.balaHeight = balaHeight;
    }

    public int getBulletVelocityY() {
        return bulletVelocityY;
    }

    public void setBulletVelocityY(int bulletVelocityY) {
        this.bulletVelocityY = bulletVelocityY;
    }

}