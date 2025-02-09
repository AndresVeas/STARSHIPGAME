package UserInterface.Form;


import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BusinessLogic.JugadorBL;
import BusinessLogic.LoginBL;
import DataAcces.DTO.JugadorDTO;
import UserInterface.AppStyle;
import UserInterface.CustomerControl.G6Button;
import UserInterface.CustomerControl.G6Label;
import UserInterface.CustomerControl.G6TextBox;

import java.awt.*;

public class RegisterPanel extends JPanel{
    private MainForm mainForm;
    private LoginBL loginBL = new LoginBL();
    private JugadorBL jugadorBL = new JugadorBL();

    public RegisterPanel(MainForm mainForm) {
        this.mainForm = mainForm;
        customizeComponent();
        btnRegistrar.addActionListener(e -> btnRegistrar());
        btnVolver.addActionListener( e-> mainForm.setPanel(new LoginPanel(mainForm)));
    }

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
        pnlCredenciales.add(lblRegistrar, gbcCred);

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
        pnlBtns.add(btnRegistrar, gbcBtns);

        gbcBtns.gridy = 1;
        pnlBtns.add(btnVolver, gbcBtns);

        // Añadir pnlCredenciales y pnlBtns al RegisterPanel
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
   
    private void btnRegistrar (){
        String usuario  = txtUsuario.getText().trim();
        String clave    = txtPassword.getText().trim();
        if (usuario.isEmpty() || clave.isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            if (loginBL.search(usuario)){
                JOptionPane.showMessageDialog(this, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            jugadorBL.create(new JugadorDTO(usuario,clave));
            mainForm.setPanel(new LoginPanel(mainForm));
        } catch (Exception e) {
            AppStyle.showMsgError(e.getMessage());
        }
    }
    private G6Button
            btnRegistrar = new G6Button("Registrar", 30),
            btnVolver = new G6Button("Volver", 30);
    private G6TextBox 
            txtUsuario = new G6TextBox(),
            txtPassword = new G6TextBox();
    private G6Label 
            lblRegistrar = new G6Label("Registrar Usuario", 50),
            lblUsuario = new G6Label("Usuario: "),
            lblPassword = new G6Label("Contraseña: ");
    private JPanel 
            pnlCredenciales = new JPanel(new FlowLayout()),
            pnlBtns = new JPanel(new FlowLayout());
    private Image 
            backgroundImage;
}

