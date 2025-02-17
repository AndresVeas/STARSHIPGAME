package DataAcces.DAO;

import DataAcces.DTO.ScoreDTO;
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

public class ScoreDAO extends SQLiteDataHelper implements IDAO <ScoreDTO> {
    @Override
    public ScoreDTO readBy(Integer id) throws Exception {
        ScoreDTO dto = new ScoreDTO();
        String query =" SELECT IdScore     " 
                     +"       ,IdJugador            " 
                     +"       ,Puntaje              " 
                     +"       ,Estado               " 
                     +"       ,FechaCreacion        " 
                     +"       ,FechaModifica        " 
                     +" FROM  Score        "
                     +" WHERE IdScore =    " + id.toString() ;
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new ScoreDTO( rs.getInt(1)            // IdScore
                                    ,rs.getInt(2)           // IdJugador
                                    ,rs.getInt(3)           // Puntaje
                                    ,rs.getString(4)        // Estado
                                    ,rs.getString(5)        // FechaCrea
                                    ,rs.getString(6));      // FechaMod
            }
        } 
        catch (SQLException e) {
            throw new GameException(e.getMessage(), getClass().getName(),"readBy()");
        }
        return dto;
    }

    @Override
    public List<ScoreDTO> readAll() throws Exception {
        ScoreDTO dto;
        List<ScoreDTO> lst = new ArrayList<>();
        String query =" SELECT IdScore     " 
                     +"       ,IdScore               " 
                     +"       ,Puntaje             " 
                     +"       ,Estado               " 
                     +"       ,FechaCreacion        " 
                     +"       ,FechaModifica        " 
                     +" FROM  Score        ";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new ScoreDTO( rs.getInt(1)            // IdScore
                                    ,rs.getInt(2)           // IdJugador            
                                    ,rs.getInt(3)           // Puntaje
                                    ,rs.getString(4)        // Estado
                                    ,rs.getString(5)        // FechaCrea
                                    ,rs.getString(6));      // FechaMod
                                   
                lst.add(dto);
            }
        } 
        catch (SQLException e) {
            throw new GameException(e.getMessage(), getClass().getName(),"readAll()");
        }
        return lst;
    }

    @Override
    public boolean create(ScoreDTO entity) throws Exception {
        String query = " INSERT INTO Score (IdJugador,Puntaje) VALUES (?,?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdJugador());
            pstmt.setInt(2, entity.getPuntaje());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new GameException(e.getMessage(), getClass().getName(),"create()");  
        }
    }

    @Override
    public boolean update(ScoreDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE Score SET IdJugador = ?, Puntaje = ?, FechaModifica = ? , Estado = ? WHERE IdScore = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdJugador());
            pstmt.setInt(2, entity.getPuntaje());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setString(4, entity.getEstado());
            pstmt.setInt(5, entity.getIdScore());
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

    public Integer getRecord (int idJugador) throws Exception {
        Integer maxScore = null;
        String query = "SELECT MAX(Puntaje) AS PuntajeRecord FROM Score WHERE IdJugador = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idJugador);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                maxScore = rs.getInt("PuntajeRecord");
            }
        } catch (SQLException e) {
            throw new GameException(e.getMessage(), getClass().getName(),"getRecord()");
        }
        return maxScore;
    }
    
}