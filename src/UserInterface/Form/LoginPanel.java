package UserInterface.Form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import UserInterface.CustomerControl.G6Button;
import UserInterface.CustomerControl.G6Label;
import UserInterface.CustomerControl.G6TextBox;


import java.awt.*;


public class LoginPanel extends JPanel implements ActionListener {
    
    public LoginPanel (){
        customizeComponent();
    }
    @Override
    
    
    public void actionPerformed(ActionEvent e) {}
    
    
    
    
    private G6Button
    btnIngresar         = new G6Button("Ingresar",30),
    btnSalir            = new G6Button("Salir",30);
    private G6TextBox
    txtUsuario          = new G6TextBox(),
    txtPassword         = new G6TextBox();
    private G6Label
    lblIniciarSesion    = new G6Label("Iniciar Sesión"),
    lblUsuario          = new G6Label("Usuario: "),
    lblPassword         = new G6Label("Contraseña: ");
    
    private void customizeComponent(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Configurar las propiedades de GridBagConstraints
        gbc.insets = new Insets(5, 5, 5, 5); // Margen alrededor de los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
        
        // Añadir lblIniciarSesion
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 5; // Ocupa 16 columnas
        gbc.weightx = 1.0;
        add(lblIniciarSesion, gbc);
        
        // Añadir lblUsuario
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Ocupa 1 columna
        gbc.weightx = 0.5;
        add(lblUsuario, gbc);
        
        // Añadir txtUsuario
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 15; // Ocupa 15 columnas
        gbc.weightx = 1.0;
        add(txtUsuario, gbc);
        
        // Añadir lblPassword
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Ocupa 1 columna
        gbc.weightx = 0.5;
        add(lblPassword, gbc);
        
        // Añadir txtPassword
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 15; // Ocupa 15 columnas
        gbc.weightx = 1.0;
        add(txtPassword, gbc);
        
        // Añadir btnIngresar
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 8; // Ocupa 8 columnas
        gbc.weightx = 1.0;
        add(btnIngresar, gbc);

        // Añadir btnSalir
        gbc.gridx = 8;
        gbc.gridy = 3;
        gbc.gridwidth = 8; // Ocupa 8 columnas
        gbc.weightx = 1.0;
        add(btnSalir, gbc);

    }
}
