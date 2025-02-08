package BusinessLogic;

import java.util.List;

import DataAcces.DAO.JugadorDAO;
import DataAcces.DTO.JugadorDTO;

public class JugadorBL {
    private JugadorDTO Jugador;       // cache
    private static JugadorDAO jDAO = new JugadorDAO();
    
    public JugadorBL(){}
    
    public static List<JugadorDTO> getAll() throws Exception{
        return jDAO.readAll();
    }

    public JugadorDTO getByIdJugador(int idJugador) throws Exception{
        Jugador = jDAO.readBy(idJugador);
        return Jugador;
    }
    public boolean create(JugadorDTO JugadorDTO) throws Exception{   
        return jDAO.create(JugadorDTO);
    }
    public boolean update(JugadorDTO JugadorDTO) throws Exception{
        return jDAO.update(JugadorDTO);
    }

    public boolean search (String Nickname) throws Exception{
        return jDAO.userExists(Nickname);
    }

    public boolean delete(int idJugador) throws Exception{
        return jDAO.delete(idJugador);
    }
    public Integer getMaxRow() throws Exception{
        return jDAO.getMaxRow();
    }
}
