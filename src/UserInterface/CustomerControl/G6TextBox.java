package UserInterface.CustomerControl;

import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import UserInterface.AppStyle;

public class G6TextBox extends JTextField{
    
    public G6TextBox (){
        customizeComponent();
    }
    
    private void customizeComponent() {
        setBorderRect();
        setFont(AppStyle.FONT);  
        setForeground(AppStyle.COLOR_FONT_LIGHT);  
        setCaretColor(AppStyle.COLOR_CURSOR);
        setMargin(new Insets(5, 5, 5, 5));
        setOpaque(false);
        //setUI(new BasicTextFieldUI());  // Para deshabilitar el subrayado por defecto
    }
    
    public void setBorderRect() {
        Border lineBorder = BorderFactory.createLineBorder(AppStyle.COLOR_BORDER);
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5); 
        setBorder( new CompoundBorder(lineBorder, emptyBorder));
    }
    
    public void setBorderLine(){
        int thickness = 1;
        setBorder( BorderFactory.createCompoundBorder(
        BorderFactory.createEmptyBorder(0, 0, thickness, 0),
        BorderFactory.createMatteBorder(0, 0, thickness, 0, AppStyle.COLOR_BORDER) 
        ));
    }
}
