package BusinessLogic.Entities;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Sistema {

    public int tileSize = 32;
    public int rows = 18;
    public int columns = 19;
    public int boardWidth = tileSize * columns;
    public int boardHeight = tileSize * rows;

    public Image shipImg;
    public Image alienImg;
    public Image alienCyanImg;
    public Image alienMagentaImg;
    public Image alienYellowImg;
    public ArrayList<Image> alienImgArray;

    public Nave ship;
    public ArrayList<Alien> alienArray;
    public ArrayList<Bala> bulletArray;

    public int alienWidth = tileSize * 2;
    public int alienHeight = tileSize;
    public int alienX = tileSize;
    public int alienY = tileSize;

    public int alienRows = 2;
    public int alienColumns = 3;
    public int alienCount = 0;
    public int alienVelocityX = 3;

    public int bulletWidth = tileSize / 8;
    public int bulletHeight = tileSize / 2;
    public int bulletVelocityY = -12;

    public Jugador jugador = new Jugador();

    public Sistema() {
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
        alienArray = new ArrayList<Alien>();
        bulletArray = new ArrayList<Bala>();

        createAliens();
    }

    public void move() {
        for (int i = 0; i < alienArray.size(); i++) {
            Alien alien = alienArray.get(i);
            if (alien.alive) {
                alien.x += alienVelocityX;

                //if alien touches the borders
                if (alien.x + alien.width >= boardWidth || alien.x <= 0) {
                    alienVelocityX *= -1;
                    alien.x += alienVelocityX * 2;

                    //move all aliens up by one row
                    for (int j = 0; j < alienArray.size(); j++) {
                        alienArray.get(j).y += alienHeight;
                    }
                }

                if (alien.y >= ship.y) {
                    jugador.gameOver = true;
                }
            }
        }

        //bullets
        for (int i = 0; i < bulletArray.size(); i++) {
            Bala bullet = bulletArray.get(i);
            bullet.y += bulletVelocityY;

            //bullet collision with aliens
            for (int j = 0; j < alienArray.size(); j++) {
                Alien alien = alienArray.get(j);
                if (!bullet.used && alien.alive && detectCollision(bullet, alien)) {
                    bullet.used = true;
                    alien.alive = false;
                    alienCount--;
                    jugador.score += 100;
                }
            }
        }

        //clear bullets
        while (bulletArray.size() > 0 && (bulletArray.get(0).used || bulletArray.get(0).y < 0)) {
            bulletArray.remove(0); //removes the first element of the array
        }

        //next level
        if (alienCount == 0) {
            //increase the number of aliens in columns and rows by 1
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
        return a.x < b.x + b.width &&  //a's top left corner doesn't reach b's top right corner
                a.x + a.width > b.x &&  //a's top right corner passes b's top left corner
                a.y < b.y + b.height && //a's top left corner doesn't reach b's bottom left corner
                a.y + a.height > b.y;   //a's bottom left corner passes b's top left corner
    }
}