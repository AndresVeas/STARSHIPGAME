package BusinessLogic.Entities;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Sistema {

    private int tileSize = 32;
    private int rows = 18;
    private int columns = 19;
    private int boardWidth = tileSize * columns;
    private int boardHeight = tileSize * rows;
    private Image shipImg;
    private Image alienImg;
    private Image alienCyanImg;
    private Image alienMagentaImg;
    private Image alienYellowImg;
    private ArrayList<Image> alienImgArray;

    public Nave ship;
    public int shipWidth = tileSize*2;
    public int shipHeight = tileSize;
    public int shipX = tileSize * columns/2 - tileSize;
    public int shipY = tileSize * rows - tileSize*2;
    public int shipVelocityX = tileSize;
    public ArrayList<Alien> alienArray;
    public ArrayList<Bala> bulletArray;

    public int alienRows = 2;
    public int alienColumns = 3;
    public int alienCount = 0;
    public int alienVelocityX = 1;
    public int alienX = tileSize;
    public int alienY = tileSize;
    public int alienWidth = tileSize * 2;
    public int alienHeight = tileSize;

    public int balaWidth   = tileSize / 8;
    public int balaHeight = tileSize / 2;
    public int bulletVelocityY = -13;

    public Jugador jugador = new Jugador();

    public Sistema() {
        customizeComponent();
    }

    public void customizeComponent(){
        shipImg = new ImageIcon("src\\UserInterface\\Resources\\ship.png").getImage();
        alienImg = new ImageIcon("src\\UserInterface\\Resources\\alien.png").getImage();
        alienCyanImg = new ImageIcon("src\\UserInterface\\Resources\\alien-cyan.png").getImage();
        alienMagentaImg = new ImageIcon("src\\UserInterface\\Resources\\alien-magenta.png").getImage();
        alienYellowImg = new ImageIcon("src\\UserInterface\\Resources\\alien-yellow.png").getImage();

        alienImgArray = new ArrayList<Image>();
        alienImgArray.add(alienImg);
        alienImgArray.add(alienCyanImg);
        alienImgArray.add(alienMagentaImg);
        alienImgArray.add(alienYellowImg);

        ship = new Nave(shipX, shipY, shipWidth, shipHeight, shipImg);
        alienArray = new ArrayList<Alien>();
        bulletArray = new ArrayList<Bala>();

        createAliens();
    }

    public void move() {
        for (int i = 0; i < alienArray.size(); i++) {
            Alien alien = alienArray.get(i);
            if (alien.alive) {
                alien.x += alienVelocityX;

                if (alien.x + alien.width >= boardWidth || alien.x <= 0) {
                    alienVelocityX *= -1;
                    if (alienVelocityX < 3 || alienVelocityX > -3) {
                        alienVelocityX = (alienVelocityX < 0) ? --alienVelocityX : ++alienVelocityX; // Aumentar la velocidad de los alienígenas
                    }
                    alien.x += alienVelocityX; // Mover un paso extra después del cambio de dirección
                    for (Alien otherAlien : alienArray) {
                        otherAlien.y += alienHeight; // Bajar alienígenas
                    }
                }

                if (alien.y >= ship.y) {
                    jugador.gameOver = true;
                }
            }
        }

        for (int i = 0; i < bulletArray.size(); i++) {
            Bala bullet = bulletArray.get(i);
            bullet.y += bulletVelocityY;

            for (int j = 0; j < alienArray.size(); j++) {
                Alien alien = alienArray.get(j);
                if (!bullet.used && alien.alive && detectCollision(bullet, alien)) {
                    bullet.used = true;
                    alien.alive = false;
                    alienCount--;
                    jugador.score += 5;
                }
            }
        }

        while (bulletArray.size() > 0 && (bulletArray.get(0).used || bulletArray.get(0).y < 0)) {
            bulletArray.remove(0); 
        }

        if (alienCount == 0) {
            jugador.score += 50; //bonus points :)
            alienColumns = Math.min(alienColumns + 1, columns/2 -2); //cap at 16/2 -2 = 6
            alienRows = Math.min(alienRows + 1, rows-6);  //cap at 16-6 = 10
            alienArray.clear();
            bulletArray.clear();
            createAliens();
        }
    }

    public void createAliens() {
        Random random = new Random();
        double probability = 0.35; 
    
        for (int c = 0; c < alienColumns; c++) {
            for (int r = 0; r < alienRows; r++) {
                if (random.nextDouble() < probability) { 
                    int randomImgIndex = random.nextInt(alienImgArray.size());
                    Alien alien = new Alien(
                        alienX + c * alienWidth,
                        alienY + r * alienHeight,
                        alienWidth,
                        alienHeight,
                        alienImgArray.get(randomImgIndex)
                    );
                    alienArray.add(alien);
                }
            }
        }
        alienCount = alienArray.size();
    }

    public boolean detectCollision(Block a, Block b) {
        return a.x < b.x + b.width &&   
                a.x + a.width > b.x &&  
                a.y < b.y + b.height && 
                a.y + a.height > b.y;   
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }


}