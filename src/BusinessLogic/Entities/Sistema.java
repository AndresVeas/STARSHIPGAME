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
    private ArrayList<Alien> alienArray;
    private ArrayList<Bala> bulletArray;
    
    private int alienRows = 1;
    private int alienColumns = 1;
    private int alienCount = 0;
    private int alienVelocidad = 1;
    
    public Jugador jugador = new Jugador();
    public Nave ship;
    public Bala bala;
    public Alien alien;
    
    
    public Sistema() {
        jugador = new Jugador();
        ship = new Nave(tileSize * columns / 2 - tileSize, tileSize * rows - tileSize * 2,
        tileSize * 2, tileSize, null,tileSize,columns,rows);
        alien = new Alien(tileSize, tileSize, tileSize * 2, tileSize, null,tileSize);
        bala = new Bala(0, 0, tileSize / 8, tileSize / 2,tileSize);                        
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
        
        ship = new Nave(ship.getShipX(), ship.getShipY(), ship.getShipWidth(), ship.getShipHeight(), shipImg,tileSize,columns,rows);
        alienArray = new ArrayList<Alien>();
        bulletArray = new ArrayList<Bala>();
        
        createAliens();
    }
    
    public void move() {
        for (int i = 0; i < alienArray.size(); i++) {
            Alien alien = alienArray.get(i);
            if (alien.isAlive()) {
                alien.x += alien.getAlienVelocityX();
                
                if (alien.x + alien.width >= boardWidth || alien.x <= 0) {
                    alien.setAlienVelocityX(alien.getAlienVelocityX() *-1);
                    if (alien.getAlienVelocityX() < 4 || alien.getAlienVelocityX() > -4) {
                        if (alien.getAlienVelocityX() < 0) {
                            alienVelocidad = alien.getAlienVelocityX()-1;
                            alien.setAlienVelocityX(alienVelocidad);
                        }
                        else {
                            alienVelocidad = alien.getAlienVelocityX()+1;
                            alien.setAlienVelocityX(alienVelocidad);
                        }  // Aumentar la velocidad de los alienígenas
                    }
                    alien.x += alien.getAlienVelocityX(); // Mover un paso extra después del cambio de dirección
                    for (Alien otherAlien : alienArray) {
                        otherAlien.y += alien.getAlienHeight(); // Bajar alienígenas
                    }
                }
                
                if (alien.y >= ship.y) {
                    jugador.setGameOver (true);
                    alienVelocidad = 1;
                }
            }
        }
        
        for (int i = 0; i < bulletArray.size(); i++) {
            Bala bullet = bulletArray.get(i);
            bullet.y += bala.getBulletVelocityY();
            
            for (int j = 0; j < alienArray.size(); j++) {
                Alien alien = alienArray.get(j);
                if (!bullet.isUsed() && alien.isAlive() && detectCollision(bullet, alien)) {
                    bullet.setUsed(true);
                    alien.setAlive(false);
                    alienCount--;
                    jugador.setScore (jugador.getScore()+ 5);
                }
            }
        }
        
        while (bulletArray.size() > 0 && (bulletArray.get(0).isUsed() || bulletArray.get(0).y < 0)) {
            bulletArray.remove(0); 
        }
        
        if (alienCount == 0) {
            jugador.setScore(jugador.getScore()+50); //bonus points :)
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
                    tileSize + c * tileSize * 2,  // Ajusta la posición X del alienígena
                    tileSize + r * tileSize,      // Ajusta la posición Y del alienígena
                    tileSize * 2,                 // Ancho del alienígena
                    tileSize,
                    alienImgArray.get(randomImgIndex),
                    tileSize
                    );
                    alien.setAlienVelocityX(alienVelocidad);

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
    
    public ArrayList<Image> getAlienImgArray() {
        return alienImgArray;
    }
    
    public void setAlienImgArray(ArrayList<Image> alienImgArray) {
        this.alienImgArray = alienImgArray;
    }
    
    public ArrayList<Alien> getAlienArray() {
        return alienArray;
    }
    
    public void setAlienArray(ArrayList<Alien> alienArray) {
        this.alienArray = alienArray;
    }
    
    public ArrayList<Bala> getBulletArray() {
        return bulletArray;
    }
    
    public void setBulletArray(ArrayList<Bala> bulletArray) {
        this.bulletArray = bulletArray;
    }
    
    public int getAlienRows() {
        return alienRows;
    }
    
    public void setAlienRows(int alienRows) {
        this.alienRows = alienRows;
    }
    
    public int getAlienColumns() {
        return alienColumns;
    }
    
    public void setAlienColumns(int alienColumns) {
        this.alienColumns = alienColumns;
    }
    
    public int getAlienCount() {
        return alienCount;
    }
    
    public void setAlienCount(int alienCount) {
        this.alienCount = alienCount;
    }
    
}