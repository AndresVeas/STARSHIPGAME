package UserInterface.Form;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import UserInterface.AppStyle;
import UserInterface.CustomerControl.G6Button;
import UserInterface.CustomerControl.G6Label;

public class MenuPanel extends JPanel {
    private Image backgroundImage;
    private MainForm mainForm;

    public MenuPanel() {
        customizeComponent();
    }

    public MenuPanel (MainForm mainForm){
        this.mainForm = mainForm;
        customizeComponent();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g2d.dispose();
    }

    private void customizeComponent (){
        backgroundImage = new ImageIcon(getClass().getResource("/UserInterface/Resources/FondoEspacio.jpg")).getImage();
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        lblCopyRight.setForeground(AppStyle.COLOR_FONT_LIGHT);
        
        Image alienCyanImg = new ImageIcon(getClass().getResource("/UserInterface/Resources/alien-cyan.png")).getImage();
        
        double rotationRequired = Math.toRadians(15);
        double locationX = alienCyanImg.getWidth(null) / 2;
        double locationY = alienCyanImg.getHeight(null) / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);

        int w = alienCyanImg.getWidth(null);
        int h = alienCyanImg.getHeight(null);
        BufferedImage rotatedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.setTransform(tx);
        g2d.drawImage(alienCyanImg, 0, 0, null);
        g2d.dispose();

        JLabel alienCyanLabel = new JLabel(new ImageIcon(rotatedImage));
        
        gbc.gridy = 1;
        add(alienCyanLabel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(btnLogin, gbc);
        gbc.gridy = 4;
        add(btnRanking, gbc);
        gbc.gridy = 5;
        add(btnSalir, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(lblCopyRight, gbc);
        
    }
    
    public G6Button 
            btnLogin    = new G6Button("Iniciar Sesión"),
            btnRanking  = new G6Button("Ranking"),
            btnSalir    = new G6Button("Salir");
    private G6Label
            lblCopyRight    = new G6Label("\u00A9 2025 Grupo 6 GR2SW",15);
    
}