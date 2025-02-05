package DataAcces.DAO;

import DataAcces.DTO.JugadorDTO;
import DataAcces.IDAO;
import DataAcces.SQLiteDataHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO extends SQLiteDataHelper implements IDAO <JugadorDTO> {
    @Override
    public JugadorDTO readBy(Integer id) throws Exception {
        JugadorDTO dto = new JugadorDTO();
        String query =" SELECT IdJugador     " 
                     +"       ,Nombre               " 
                     +"       ,Apellido             " 
                     +"       ,Contrasena           "
                     +"       ,PuntajeMax           "
                     +"       ,Estado               " 
                     +"       ,FechaCreacion        " 
                     +"       ,FechaModifica        " 
                     +" FROM  Jugador        "
                     +" WHERE IdJugador =    " + id.toString() ;
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new JugadorDTO( rs.getInt(1)           // IdJugador
                                    ,rs.getString(2)        // Nombre            
                                    ,rs.getString(3)        // Apellido
                                    ,rs.getString(4)        // Contraseña      
                                    ,rs.getInt(5)           // PuntajeMax
                                    ,rs.getString(6)        // Estado
                                    ,rs.getString(7)        // FechaCrea
                                    ,rs.getString(8));      // FechaMod
            }
        } 
        catch (SQLException e) {
            throw e;
        }
        return dto;
    }

    @Override
    public List<JugadorDTO> readAll() throws Exception {
        JugadorDTO dto;
        List<JugadorDTO> lst = new ArrayList<>();
        String query =" SELECT IdJugador     " 
                     +"       ,Nombre               " 
                     +"       ,Apellido             " 
                     +"       ,Contrasena           "
                     +"       ,PuntajeMax           "
                     +"       ,Estado               " 
                     +"       ,FechaCreacion        " 
                     +"       ,FechaModifica        " 
                     +" FROM  Jugador        ";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new JugadorDTO( rs.getInt(1)          // IdJugador
                                    ,rs.getString(2)        // Nombre            
                                    ,rs.getString(3)        // Apellido
                                    ,rs.getString(4)        // Contraseña
                                    ,rs.getInt(5)           // PuntajeMAx  
                                    ,rs.getString(6)        // Estado
                                    ,rs.getString(7)        // FechaCrea
                                    ,rs.getString(8));      // FechaMod
                lst.add(dto);
            }
        } 
        catch (SQLException e) {
            throw e;
        }
        return lst;
    }

    @Override
    public boolean create(JugadorDTO entity) throws Exception {
        String query = " INSERT INTO Jugador (Nombre, Apellido, Contrasena, PuntajeMax, Estado) VALUES (?,?,?,?,?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, entity.getContrasena());
            pstmt.setInt(4, entity.getPuntajeMax());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e;  
        }
    }

    @Override
    public boolean update(JugadorDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE Jugador SET Nombre = ?,Apellido = ?, FechaModifica = ? , Estado = ? WHERE IdJugador = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setString(4, entity.getEstado());
            pstmt.setInt(5, entity.getIdJugador());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e; 
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
            return true;
    }

    
    public Integer getMaxRow()  throws Exception  {
        String query =" SELECT COUNT(*) TotalReg FROM Jugador";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                return rs.getInt(1);                    // TotalReg
            }
        } 
        catch (SQLException e) {
            throw e; 
        }
        return 0;
    }
}