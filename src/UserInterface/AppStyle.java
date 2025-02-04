package UserInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AppStyle {
    public static final Color COLOR_FONT = new Color(200, 200, 200); //(218, 8, 40)
    public static final Color COLOR_FONT_LIGHT = new Color(255, 255,255);
    public static final Color COLOR_FONT_DARK = new Color(50, 50, 50); // Gris más oscuro
    public static final Color COLOR_FONT_MAGENTA = new Color(204, 0, 102); // Gris más oscuro
    public static final Color COLOR_CURSOR = Color.black;
    public static final Color COLOR_BORDER = Color.lightGray;
    public static final Font  FONT         = new Font("JetBrains Mono", Font.PLAIN, 15);
    public static final Font  FONT_BOLD    = new Font("JetBrains Mono", Font.BOLD | Font.PLAIN, 14);
    public static final Font  FONT_BOLD_BIG= new Font("JetBrains Mono", Font.BOLD | Font.PLAIN, 25);
    public static final Font  FONT_MAIN;

    static {
        Font font = null;
        try {
            InputStream is = AppStyle.class.getResourceAsStream("/UserInterface/Resources/EvilEmpire.otf");
            if (is != null) {
                font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 50);
            } else {
                System.err.println("No se pudo encontrar el archivo de la fuente.");
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            font = new Font("Serif", Font.BOLD, 20);
        }
        FONT_MAIN = font;
    }
    public static final Font  FONT_SMALL   = new Font("JetBrains Mono", Font.PLAIN| Font.PLAIN, 12);

    public static final int ALIGNMENT_LEFT  = SwingConstants.LEFT;
    public static final int ALIGNMENT_RIGHT = SwingConstants.RIGHT;
    public static final int ALIGNMENT_CENTER= SwingConstants.CENTER;

    public static final Cursor CURSOR_HAND    = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor CURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

    public static final URL URL_MAIN  = AppStyle.class.getResource("/UserInterface/Resource/Img/FondoBiblioteca.jpg");
    public static final URL URL_LOGO  = AppStyle.class.getResource("/UserInterface/Resource/Img/Logo.png");
    public static final URL URL_SPLASH= AppStyle.class.getResource("/UserInterface/Resource/Img/Polibiblio.jpg");

    public static final CompoundBorder createBorderRect(){
        return BorderFactory.createCompoundBorder(  new LineBorder(new Color(173, 216, 230)),
                                                    new EmptyBorder(0, 0, 0, 0));
    }

    public static final void showMsg(String msg){
        JOptionPane.showMessageDialog(null, msg, "😏 POLIBIBLIO", JOptionPane.INFORMATION_MESSAGE);
    }
    public static final void showMsgError(String msg){
        JOptionPane.showMessageDialog(null, msg, "💀 POLIBIBLIO", JOptionPane.OK_OPTION);
    }

    public static final boolean showConfirmYesNo(String msg){
        return (JOptionPane.showConfirmDialog(null, msg, "😞 POLIBIBLIO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
}
