package BusinessLogic;

import java.util.List;

import DataAcces.DAO.ScoreDAO;
import DataAcces.DTO.ScoreDTO;

public class ScoreBL {
    private ScoreDTO Score;       // cache
    private static ScoreDAO sDAO = new ScoreDAO();
    
    public ScoreBL(){}
    
    public static List<ScoreDTO> getAll() throws Exception{
        return sDAO.readAll();
    }

    public ScoreDTO getByIdScore(int idScore) throws Exception{
        Score = sDAO.readBy(idScore);
        return Score;
    }
    public boolean create(ScoreDTO ScoreDTO) throws Exception{   
        return sDAO.create(ScoreDTO);
    }
    public boolean update(ScoreDTO ScoreDTO) throws Exception{
        return sDAO.update(ScoreDTO);
    }

    public boolean delete(int idScore) throws Exception{
        return sDAO.delete(idScore);
    }
}
