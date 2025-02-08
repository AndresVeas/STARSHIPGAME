package DataAcces.DAO;

import DataAcces.DTO.LoginDTO;
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

public class LoginDAO extends SQLiteDataHelper implements IDAO <LoginDTO> {
    @Override
    public LoginDTO readBy(Integer id) throws Exception {
        LoginDTO dto = new LoginDTO();
        String query =" SELECT IdLogin     " 
                     +"       ,IdJugador            " 
                     +"       ,Estado               " 
                     +"       ,FechaCreacion        " 
                     +"       ,FechaModifica        " 
                     +" FROM  LoginUs        "
                     +" WHERE IdLogin =    " + id.toString() ;
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new LoginDTO( rs.getInt(1)            // IdLogin
                                    ,rs.getInt(2)           // IdJugador
                                    ,rs.getString(3)        // Estado
                                    ,rs.getString(4)        // FechaCreacion
                                    ,rs.getString(5));      // FechaMod
            }

        } 
        catch (SQLException e) {
            throw e;
        }
        return dto;
    }

    @Override
    public List<LoginDTO> readAll() throws Exception {
        LoginDTO dto;
        List<LoginDTO> lst = new ArrayList<>();
        String query =" SELECT IdLogin     " 
                     +"       ,IdJugador            " 
                     +"       ,Estado               " 
                     +"       ,FechaCreacion        " 
                     +"       ,FechaModifica        " 
                     +" FROM  LoginUs        ";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new LoginDTO( rs.getInt(1)            // IdLogin
                                    ,rs.getInt(2)           // IdJugador
                                    ,rs.getString(3)        // Estado
                                    ,rs.getString(4)        // FechaCrea
                                    ,rs.getString(5));      // FechaMod
                                   
                lst.add(dto);
            }
        } 
        catch (SQLException e) {
            throw e;
        }
        return lst;
    }

    @Override
    public boolean create(LoginDTO entity) throws Exception {
        String query = " INSERT INTO LoginUs (IdJugador,Estado) VALUES (?,?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdJugador());
            pstmt.setString(2, entity.getEstado());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e;  
        }
    }

    @Override
    public boolean update(LoginDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE LoginUs SET IdJugador = ?, FechaModifica = ? , Estado = ? WHERE IdLogin = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdJugador());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setString(3, entity.getEstado());
            pstmt.setInt(4, entity.getIdLogin());
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
        String query =" SELECT COUNT(*) TotalReg FROM LoginUs";
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