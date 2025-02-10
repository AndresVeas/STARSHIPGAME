package UserInterface.Form;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.util.List;

import BusinessLogic.JugadorBL;
import DataAcces.DTO.JugadorDTO;
import UserInterface.AppStyle;
import UserInterface.CustomerControl.G6Button;

import java.awt.*;

public class RankingPanel extends JPanel{
    
    public RankingPanel(MainForm mainForm)  {
        customizeComponent();
        try { showTable(); } catch (Exception e) {AppStyle.showMsgError(e.getMessage());}
        btnVolver.addActionListener( e-> mainForm.setPanel(mainForm.menuPanel));
    }

    public RankingPanel(MainForm main, StarShipGamePanel pantalla)  {
        customizeComponent();
        try { showTable(); } catch (Exception e) {AppStyle.showMsgError(e.getMessage());}
        btnVolver.addActionListener(e -> {
            main.setPanel(pantalla);
            pantalla.gameLoop.start();
            pantalla.requestFocusInWindow();
        });
    }

    private void showTable() throws Exception {
        List<JugadorDTO> bestFive = JugadorBL.getRanking();
        if (bestFive.isEmpty()) return;
        String[] header = {"Nickname","Puntaje","Fecha"};
        Object[][] data = new Object[5][header.length];
        int index = 0;
        for (int i = 0; i < bestFive.size(); i++) {
            JugadorDTO u = bestFive.get(i);
            data[index][0] = u.getNickname();
            data[index][1] = u.getPuntaje();
            data[index][2] = u.getFechaCreacion();
            index++;
        }
        
        JTable table = new JTable(data, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        
        // Set table properties for larger font, white text, and gray background
        table.setFont(new Font("Rockwell Nova Extra Bold", Font.BOLD, 23));
        table.setForeground(Color.WHITE);
        table.setBackground(new Color (60,18,144)); // Set background color to gray

        // Center text in each column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        
        table.setRowHeight(40);

        // Set header font and alignment
        JTableHeader headerRenderer = table.getTableHeader();
        headerRenderer.setFont(AppStyle.FONT_MAIN);
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setBackground(new Color(143,0,0));
        ((DefaultTableCellRenderer) headerRenderer.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        table.setPreferredScrollableViewportSize(new Dimension(800, 200));
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(200); // Nickname
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Puntaje Record
        table.getColumnModel().getColumn(2).setPreferredWidth(200); // Fecha

        // ScrollPane transparente
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null); 

        pnlTabla.removeAll();
        pnlTabla.add(scrollPane);
    }

    private void customizeComponent() {
        URL imageUrl = getClass().getResource("/UserInterface/Resources/FondoEstrellas.jpg");
        backgroundImage = new ImageIcon(imageUrl).getImage();

        pnlBtns.setOpaque(false);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL; 


        pnlBtns.setLayout(new GridBagLayout());
        GridBagConstraints gbcBtns = new GridBagConstraints();
        gbcBtns.insets = new Insets(5, 5, 10, 5);
        gbcBtns.fill = GridBagConstraints.CENTER;

        gbcBtns.gridy = 1;
        pnlBtns.add(btnVolver, gbcBtns);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pnlTabla, gbc);

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
   
    private G6Button
            btnVolver = new G6Button("Volver", 40);
    private JPanel 
            pnlTabla  = new JPanel(),
            pnlBtns   = new JPanel(new FlowLayout());
    private Image 
            backgroundImage;
}
