package UserInterface.CustomerControl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

import UserInterface.AppStyle;

public class G6Button extends JButton implements MouseListener {

    public G6Button (String text){
        customizeComponent(text);
    }

    public G6Button (String text, int tamano){
        customizeComponent(text,tamano);
    }
    private void customizeComponent (String text){
        setText(text);
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setForeground(AppStyle.COLOR_FONT_MAGENTA);
        setHorizontalAlignment(AppStyle.ALIGNMENT_LEFT);
        setFont(AppStyle.FONT_MAIN);
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void customizeComponent (String text,int tamano){
        setText(text);
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setForeground(AppStyle.COLOR_FONT_MAGENTA);
        setHorizontalAlignment(AppStyle.ALIGNMENT_LEFT);
        setFont(AppStyle.FONT_MAIN.deriveFont((float) tamano));
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        setForeground(Color.BLACK);
        setCursor(AppStyle.CURSOR_HAND);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setForeground(Color.GRAY);
        setCursor(AppStyle.CURSOR_DEFAULT);
    }
    
}
