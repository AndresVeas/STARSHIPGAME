package UserInterface.CustomerControl;

import javax.swing.*;
import java.awt.*;

import UserInterface.AppStyle;

public class G6Label extends JLabel {

    public G6Label(String text) {
        customizeComponent(text, AppStyle.FONT_BOLD_BIG.getSize());
    }

    public G6Label(String text, int tamano) {
        customizeComponent(text, tamano);
    }

    public G6Label(String text, int tamano, Font font) {
        customizeComponent(text, tamano, font);
    }

    private void customizeComponent(String text, int tamano) {
        setText(text);
        setCustomizeComponent(text, AppStyle.FONT_BOLD_BIG.deriveFont((float) tamano), AppStyle.COLOR_FONT_LIGHT, AppStyle.ALIGNMENT_LEFT);
    }

    private void customizeComponent(String text, int tamano,Font font) {
        setText(text);
        setCustomizeComponent(text, font.deriveFont((float) tamano), AppStyle.COLOR_FONT_LIGHT, AppStyle.ALIGNMENT_LEFT);
    }


    private void setCustomizeComponent(String text, Font font, Color color, int alignment) {
        setText(text);
        setFont(font);
        setForeground(color);
        setHorizontalAlignment(alignment);
    }
}
