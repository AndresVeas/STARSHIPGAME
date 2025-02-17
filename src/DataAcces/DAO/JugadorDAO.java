package DataAcces.DAO;

import DataAcces.DTO.JugadorDTO;
import Framework.GameException;
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
                     +"       ,Nickname      " 
                     +"       ,Clave         "
                     +"       ,Estado        " 
                     +"       ,FechaCreacion " 
                     +"       ,FechaModifica " 
                     +" FROM  Jugador        "
                     +" WHERE IdJugador =    " + id.toString() ;
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new JugadorDTO( rs.getInt(1)           // IdJugador
                                    ,rs.getString(2)        // Nickname
                                    ,rs.getString(3)        // Clave      
                                    ,rs.getString(4)        // Estado
                                    ,rs.getString(5)        // FechaCrea
                                    ,rs.getString(6));      // FechaModifica
            }
        } 
        catch (SQLException e) {
            throw new GameException(e.getMessage(), getClass().getName(),"readBy()");
        }
        return dto;
    }

    @Override
    public List<JugadorDTO> readAll() throws Exception {
        JugadorDTO dto;
        List<JugadorDTO> lst = new ArrayList<>();
        String query =" SELECT IdJugador     " 
                     +"       ,Nickname      " 
                     +"       ,Clave         "
                     +"       ,Estado        " 
                     +"       ,FechaCreacion " 
                     +"       ,FechaModifica " 
                     +" FROM  Jugador        ";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new JugadorDTO( rs.getInt(1)           // IdJugador
                                    ,rs.getString(2)        // Nickname
                                    ,rs.getString(3)        // Clave      
                                    ,rs.getString(4)        // Estado
                                    ,rs.getString(5)        // FechaCrea
                                    ,rs.getString(6));      // FechaModifica   // FechaMod
                lst.add(dto);
            }
        } 
        catch (SQLException e) {
            throw new GameException(e.getMessage(), getClass().getName(),"readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(JugadorDTO entity) throws Exception {
        String query = " INSERT INTO Jugador (Nickname, Clave) VALUES (?,?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNickname());
            pstmt.setString(2, entity.getClave());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new GameException(e.getMessage(), getClass().getName(),"create()"); 
        }
    }

    @Override
    public boolean update(JugadorDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE Jugador SET Nickname = ?,Clave = ?, FechaModifica = ? , Estado = ? WHERE IdJugador = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNickname());
            pstmt.setString(2, entity.getClave());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setString(4, entity.getEstado());
            pstmt.setInt(5, entity.getIdJugador());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new GameException(e.getMessage(), getClass().getName(),"update()"); 
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
            return true;
    }
    
    public Integer getId (String nickname) throws Exception {
        String query = "SELECT IdJugador FROM Jugador WHERE Nickname = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nickname);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("IdJugador");
            } else {
                return null; // or throw an exception if preferred
            }
        } catch (SQLException e) {
            throw new GameException(e.getMessage(), getClass().getName(),"getId()");
        }
    }

    public List<JugadorDTO> getRanking() throws Exception {
        List<JugadorDTO> lst = new ArrayList<>();
        String query = "SELECT j.nickname AS 'Nickname Jugador', " +
                       "       s.puntaje AS 'Mejor Puntaje', " +
                       "       j.estado AS 'Estado Jugador', " +
                       "       s.FechaCreacion AS 'Fecha' " +
                       "FROM Jugador j " +
                       "JOIN Score s ON j.idJugador = s.idJugador " +
                       "WHERE j.estado = 'A' " +
                       "ORDER BY s.puntaje DESC " +
                       "LIMIT 5";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs = stmt.executeQuery(query);  // ejecutar la consulta
            while (rs.next()) {
                JugadorDTO dto = new JugadorDTO();
                dto.setNickname(rs.getString("Nickname Jugador"));
                dto.setPuntaje(rs.getInt("Mejor Puntaje"));
                dto.setEstado(rs.getString("Estado Jugador"));
                dto.setFechaCreacion(rs.getString("Fecha"));
                lst.add(dto);
            }
        } 
        catch (SQLException e) {
            throw new GameException(e.getMessage(), getClass().getName(),"getRanking()");
        }
        return lst;
    }
}