package UserInterface.Form;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import BusinessLogic.ScoreBL;
import BusinessLogic.Entities.Alien;
import BusinessLogic.Entities.Bala;
import BusinessLogic.Entities.Sistema;
import DataAcces.DTO.ScoreDTO;
import UserInterface.AppStyle;
import UserInterface.CustomerControl.G6Button;
import UserInterface.CustomerControl.G6Label;

public class StarShipGamePanel extends JPanel implements ActionListener, KeyListener {
    private Sistema sistema;
    public Timer gameLoop;
    private int idJugador;
    private ScoreBL scoreBL = new ScoreBL();

    public StarShipGamePanel(int idJugador, MainForm mainForm) {
        this.idJugador = idJugador;
        sistema = new Sistema();
        customizeComponent();
    
        btnMenu.addActionListener(e -> mainForm.setPanel(mainForm.menuPanel));
        btnRanking.addActionListener(e -> mainForm.setPanel(new RankingPanel(mainForm, this)));
        btnSalir.addActionListener(e-> System.exit(0));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(0, 0, sistema.getBoardWidth()-1, sistema.getBoardHeight()-1);
    }
    
    private void customizeComponent() {
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.black);
        setPreferredSize(new Dimension(sistema.getBoardWidth(), sistema.getBoardHeight()));
        setLayout(new BorderLayout());
    
        gameOverLabel.setForeground(new Color(216, 4, 4));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVisible(false);
        add(gameOverLabel, BorderLayout.CENTER);
    
        // Panel principal para la zona derecha
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setOpaque(false);
    

        pnlBotones.setLayout(new BoxLayout(pnlBotones, BoxLayout.Y_AXIS));
        pnlBotones.setOpaque(false);
        
        // Agregar espacio entre botones
        btnMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRanking.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        pnlBotones.add(Box.createVerticalGlue()); // Espacio flexible arriba
        pnlBotones.add(btnMenu);
        pnlBotones.add(Box.createRigidArea(new Dimension(0, 10))); // 10 píxeles de espacio
        pnlBotones.add(btnRanking);
        pnlBotones.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlBotones.add(btnSalir);
        pnlBotones.add(Box.createVerticalGlue()); // Espacio flexible abajo
    
        // Agregar los paneles al panel derecho
        rightPanel.add(pnlBotones, BorderLayout.CENTER);
    
        // Agregar el panel derecho al componente principal
        add(rightPanel, BorderLayout.EAST);
    
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }
    
    public void draw(Graphics g) {
        g.drawImage(sistema.ship.img, sistema.ship.x, sistema.ship.y, sistema.ship.width, sistema.ship.height, null);
        
        for (int i = 0; i < sistema.alienArray.size(); i++) {
            Alien alien = sistema.alienArray.get(i);
            if (alien.alive) {
                g.drawImage(alien.img, alien.x, alien.y, alien.width, alien.height, null);
            }
        }
        
        g.setColor(Color.white);
        for (int i = 0; i < sistema.bulletArray.size(); i++) {
            Bala bullet = sistema.bulletArray.get(i);
            if (!bullet.used) {
                g.drawRect(bullet.x, bullet.y, sistema.balaWidth, sistema.balaHeight);
            }
        }
        
        g.setColor(Color.white);
        g.setFont(AppStyle.FONT_GAME_OVER);
        g.drawString("SCORE:" + String.valueOf((int) sistema.jugador.score), sistema.getBoardWidth() + 20, 50);
        try {
            Font smallerFont = AppStyle.FONT_GAME_OVER.deriveFont(15f); // Cambia el tamaño de la fuente aquí
            g.setFont(smallerFont);
            g.drawString("RECORD PERSONAL: " + "\n" +String.valueOf(scoreBL.getRecord(idJugador)), sistema.getBoardWidth() 
            + 20, 100);
        } catch (Exception e) {
            e.printStackTrace();
        };

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        sistema.move();
        repaint();
        if (sistema.jugador.gameOver) {
            gameOverLabel.setVisible(true);
            gameLoop.stop();
            ScoreBL scoreBL = new ScoreBL();
            try {
                scoreBL.create(new ScoreDTO(this.idJugador, sistema.jugador.score));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (sistema.jugador.gameOver) { //any key to restart
            sistema.ship.x = sistema.shipX;
            sistema.bulletArray.clear();
            sistema.alienArray.clear();
            sistema.jugador.gameOver = false;
            sistema.jugador.score = 0;
            sistema.alienColumns = 3;
            sistema.alienRows = 2;
            sistema.alienVelocityX = 1;
            sistema.createAliens();
            gameOverLabel.setVisible(false);
            gameLoop.start();
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT  && sistema.ship.x - sistema.shipVelocityX >= 0) {
            sistema.ship.x -= sistema.shipVelocityX; //move left one tile
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT  && sistema.ship.x + sistema.shipVelocityX 
                + sistema.ship.width <= sistema.getBoardWidth()) {
            sistema.ship.x += sistema.shipVelocityX; //move right one tile
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Bala bullet = new Bala(sistema.ship.x + sistema.ship.width * 15 / 32, sistema.ship.y,
                                 sistema.balaWidth, sistema.balaHeight);
            sistema.bulletArray.add(bullet);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    private G6Label 
            gameOverLabel = new G6Label("Game Over", 79, AppStyle.FONT_GAME_OVER);
    private G6Button 
            btnMenu     = new G6Button("Menu"),
            btnRanking  = new G6Button("Ranking"),
            btnSalir    = new G6Button("Salir");
    private JPanel
            pnlBotones  = new JPanel();
}