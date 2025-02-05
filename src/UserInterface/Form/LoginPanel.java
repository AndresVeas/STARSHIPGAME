package UserInterface.Form;

import UserInterface.CustomerControl.G6Button;
import UserInterface.CustomerControl.G6Label;
import UserInterface.CustomerControl.G6TextBox;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class LoginPanel extends JPanel implements ActionListener {
    private Image backgroundImage;
    public LoginPanel (){
        customizeComponent();
    }
    @Override
    
    
    public void actionPerformed(ActionEvent e) {}
    
    private G6Button
    btnIngresar         = new G6Button("Ingresar",30);
	G6Button btnRegistro         = new G6Button ("Crear un nuevo usuario", 30);
	G6Button btnSalir            = new G6Button("Salir",30);
    private G6TextBox
    txtUsuario          = new G6TextBox(),
    txtPassword         = new G6TextBox();
    private G6Label
    lblIniciarSesion    = new G6Label("Iniciar Sesión",50),
    lblUsuario          = new G6Label("Usuario: "),
    lblPassword         = new G6Label("Contraseña: ");
    private JPanel 
    pnlCredenciales   = new JPanel(new FlowLayout()),
    pnlBtns  = new JPanel(new FlowLayout());    
    private void customizeComponent() {
        URL imageUrl = getClass().getResource("/UserInterface/Resources/FondoEstrellas.jpg");
        backgroundImage = new ImageIcon(imageUrl).getImage();
        
        pnlCredenciales.setOpaque(false);
        pnlBtns.setOpaque(false);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Configurar las propiedades de GridBagConstraints
        gbc.insets = new Insets(5, 5, 5, 5); // Margen alrededor de los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
        
        // Añadir componentes a pnlCredenciales
        pnlCredenciales.setLayout(new GridBagLayout());
        GridBagConstraints gbcCred = new GridBagConstraints();
        gbcCred.insets = new Insets(5, 5, 5, 5);
        gbcCred.fill = GridBagConstraints.HORIZONTAL;
        
        gbcCred.gridx = 0;
        gbcCred.gridy = 0;
        pnlCredenciales.add(lblIniciarSesion, gbcCred);
        
        gbcCred.gridy = 1;
        pnlCredenciales.add(lblUsuario, gbcCred);
        
        gbcCred.gridy = 2;
        pnlCredenciales.add(txtUsuario, gbcCred);
        
        gbcCred.gridy = 3;
        pnlCredenciales.add(lblPassword, gbcCred);
        
        gbcCred.gridy = 4;
        pnlCredenciales.add(txtPassword, gbcCred);
        
        // Añadir componentes a pnlBtns
        pnlBtns.setLayout(new GridBagLayout());
        GridBagConstraints gbcBtns = new GridBagConstraints();
        gbcBtns.insets = new Insets(5, 5, 5, 5);
        gbcBtns.fill = GridBagConstraints.CENTER;
        
        gbcBtns.gridx = 0;
        gbcBtns.gridy = 0;
        pnlBtns.add(btnIngresar, gbcBtns);
        
        gbcBtns.gridy = 1;
        pnlBtns.add(btnRegistro, gbcBtns);
        
        gbcBtns.gridy = 2;
        pnlBtns.add(btnSalir, gbcBtns);
        
        // Añadir pnlCredenciales y pnlBtns al LoginPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pnlCredenciales, gbc);
        
        gbc.gridy = 1;
        add(pnlBtns, gbc);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
    