package UserInterface.Form;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

public class MainForm extends JFrame {
    private int tamanoCuadro = 32;
    private int filas = 20;
    private int columnas = 30;
    private int altura = tamanoCuadro * filas;
    private int ancho = tamanoCuadro * columnas;

    MenuPanel menuPanel = new MenuPanel();
    JPanel panel = new JPanel();

    public MainForm (){
        customizeComponent();
        menuPanel.btnLogin.addActionListener(e -> setPanel(new LoginPanel()));
        menuPanel.btnSalir.addActionListener(e -> dispose());
    }

    private void customizeComponent (){
        setTitle("STARSHIP GAME");
        setSize(ancho, altura);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPanel.setBackground(Color.BLACK);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        container.add(menuPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    
    private void setPanel(JPanel formularioPanel) {
        Container container = getContentPane();
        container.remove(menuPanel);
        panel = formularioPanel;
        panel.setBackground(Color.BLACK);
        container.add(panel);
        revalidate();
        repaint();
    }

    public int getTamanoCuadro() {
        return tamanoCuadro;
    }
    public int getAltura() {
        return altura;
    }
}
