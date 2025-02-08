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

    public  Nave ship;
    public ArrayList<Alien> alienArray;
    public ArrayList<Bala> bulletArray;

    private int alienWidth = tileSize * 2;
    private int alienHeight = tileSize;
    private int alienX = tileSize;
    private int alienY = tileSize;

    private int alienRows = 1;
    private int alienColumns = 1;
    private int alienCount = 0;
    private int alienVelocityX = 1;

    private int bulletWidth = tileSize / 8;
    private int bulletHeight = tileSize / 2;
    private int bulletVelocityY = -12;

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

        ship = new Nave(tileSize * columns / 2 - tileSize, tileSize * rows - tileSize * 2, tileSize * 2, tileSize, shipImg);
        ship.setShipVelocityX(tileSize);
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
                    for (int j = 0; j < alienArray.size(); j++) {
                        alienArray.get(j).y += alienHeight;
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
                    jugador.score += 10;
                }
            }
        }


        while (bulletArray.size() > 0 && (bulletArray.get(0).used || bulletArray.get(0).y < 0)) {
            bulletArray.remove(0); 
        }

        //next level
        if (alienCount == 0) {
            alienVelocityX++;
            jugador.score += alienColumns * alienRows * 100; //bonus points :)
            alienColumns = Math.min(alienColumns + 1, columns / 2 - 2); //cap at 16/2 -2 = 6
            alienRows = Math.min(alienRows + 1, rows - 6);  //cap at 16-6 = 10
            alienArray.clear();
            bulletArray.clear();
            createAliens();
        }
    }

    public void createAliens() {
        Random random = new Random();
        for (int i = 0; i < alienColumns * alienRows; i++) {
            boolean positionValid;
            int randomX, randomY;
            do {
                positionValid = true;
                randomX = random.nextInt(boardWidth - alienWidth);
                randomY = random.nextInt(boardHeight / 2); // Limita la posición Y a la mitad superior del tablero

                // Verifica si la posición está ocupada por otro alienígena
                for (Alien alien : alienArray) {
                    if (detectCollision(new Alien(randomX, randomY, alienWidth, alienHeight, null), alien)) {
                        positionValid = false;
                        break;
                    }
                }
            } while (!positionValid);

            int randomImgIndex = random.nextInt(alienImgArray.size());
            Alien alien = new Alien(
                    randomX,
                    randomY,
                    alienWidth,
                    alienHeight,
                    alienImgArray.get(randomImgIndex)
            );
            alienArray.add(alien);
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