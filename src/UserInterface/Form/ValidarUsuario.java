package UserInterface.Form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ValidarUsuario {
    private static final String URL = "jdbc:mysql://localhost:3306/loginDB";
    private static final String usuario = "Ethan";
    private static final String contrasena = "";
    
    public static boolean validar(String usuario, String contrasena) {
        String query = "SELECT contrasena * FROM Jugador WHERE usuario = ? AND contrasena = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, usuario, contrasena);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()){
                String contrasenaAlmacenada = rs.getString("contrasena");
                return contrasena.equals(contrasenaAlmacenada); // Compara la contrasena ingresada con la almancenada
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return false;
    }
}
