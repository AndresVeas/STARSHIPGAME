package UserInterface.Validador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    
    public static boolean validarUsuario(String Jugador, String contrasena){
        String sql = "SELECT * FROM Jugador WHERE nombre = ? AND contrasena = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, Jugador);
            pstmt.setString(2, contrasena);
            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // si hay resultado, credenciales validas 

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
