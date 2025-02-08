package UserInterface.Form;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import BusinessLogic.Entities.Alien;
import BusinessLogic.Entities.Bala;
import BusinessLogic.Entities.Sistema;

public class StarShipGamePanel extends JPanel implements ActionListener, KeyListener {
    Sistema sistema;
    Timer gameLoop;
    Timer moveTimer;

    boolean leftPressed = false;
    boolean rightPressed = false;

    public StarShipGamePanel() {
        sistema = new Sistema();
        setPreferredSize(new Dimension(sistema.boardWidth, sistema.boardHeight));
        setBackground(Color.black);
        
        setFocusable(true);
        addKeyListener(this);

        //game timer
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        //move timer
        moveTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leftPressed && sistema.ship.x - sistema.tileSize * 1.5 >= 0) {
                    sistema.ship.x -= sistema.tileSize * 1.5;
                }
                if (rightPressed && sistema.ship.x + sistema.tileSize * 1.5 + sistema.ship.width <= sistema.boardWidth) {
                    sistema.ship.x += sistema.tileSize * 1.5;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

        // Dibujar el borde rojo alrededor del área del sistema
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(0, 0, sistema.boardWidth-1, sistema.boardHeight-1);
    }


    public void draw(Graphics g) {
        //ship
        g.drawImage(sistema.ship.img, sistema.ship.x, sistema.ship.y, sistema.ship.width, sistema.ship.height, null);

        //aliens
        for (int i = 0; i < sistema.alienArray.size(); i++) {
            Alien alien = sistema.alienArray.get(i);
            if (alien.alive) {
                g.drawImage(alien.img, alien.x, alien.y, alien.width, alien.height, null);
            }
        }

        //bullets
        g.setColor(Color.white);
        for (int i = 0; i < sistema.bulletArray.size(); i++) {
            Bala bullet = sistema.bulletArray.get(i);
            if (!bullet.used) {
                g.drawRect(bullet.x, bullet.y, bullet.width, bullet.height);
            }
        }

        //score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (sistema.jugador.gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) sistema.jugador.score), 10, 35);
        } else {
            g.drawString(String.valueOf((int) sistema.jugador.score), 10, 35);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sistema.move();
        repaint();
        if (sistema.jugador.gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (sistema.jugador.gameOver) { 
            sistema.ship.x = sistema.tileSize * sistema.columns / 2 - sistema.tileSize;
            sistema.bulletArray.clear();
            sistema.alienArray.clear();
            sistema.jugador.reset();
            sistema.alienColumns = 3;
            sistema.alienRows = 2;
            sistema.alienVelocityX = 1;
            sistema.createAliens();
            gameLoop.start();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
            moveTimer.start();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
            moveTimer.start();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Bala bullet = new Bala(sistema.ship.x + sistema.ship.width * 15 / 32, sistema.ship.y, sistema.bulletWidth, sistema.bulletHeight);
            sistema.bulletArray.add(bullet);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (sistema.jugador.gameOver) { 
            sistema.ship.x = sistema.tileSize * sistema.columns / 2 - sistema.tileSize;
            sistema.bulletArray.clear();
            sistema.alienArray.clear();
            sistema.jugador.reset();
            sistema.alienColumns = 3;
            sistema.alienRows = 2;
            sistema.alienVelocityX = 1;
            sistema.createAliens();
            gameLoop.start();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
            if (!rightPressed) {
                moveTimer.stop();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
            if (!leftPressed) {
                moveTimer.stop();
            }
        }
    }
}