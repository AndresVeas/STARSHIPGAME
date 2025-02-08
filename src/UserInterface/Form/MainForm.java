package UserInterface.Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainForm extends JFrame {

    public MenuPanel menuPanel = new MenuPanel();
    private JPanel panel = new JPanel();
    
    public MainForm() {
        customizeComponent();
        menuPanel.btnLogin.addActionListener(e -> setPanel(new LoginPanel(this)));
        menuPanel.btnRanking.addActionListener(e -> setPanel(new RankingPanel (this)));
        menuPanel.btnSalir.addActionListener(e -> dispose());
    }


    private void customizeComponent (){
        setTitle("STARSHIP GAME");
        setSize(960, 620);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPanel.setBackground(Color.BLACK);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        container.add(menuPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    
    public void setPanel(JPanel formularioPanel) {
        Container container = getContentPane();
        container.removeAll();
        panel = formularioPanel;
        panel.setBackground(Color.BLACK);
        container.add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

}