package BusinessLogic;

import java.util.List;

import DataAcces.DAO.LoginDAO;
import DataAcces.DTO.LoginDTO;

public class LoginBL {
    private LoginDTO Login;       // cache
    private static LoginDAO lDAO = new LoginDAO();
    
    public LoginBL(){}
    
    public static List<LoginDTO> getAll() throws Exception{
        return lDAO.readAll();
    }

    public LoginDTO getByIdLogin(int idLogin) throws Exception{
        Login = lDAO.readBy(idLogin);
        return Login;
    }
    public boolean create(LoginDTO LoginDTO) throws Exception{   
        return lDAO.create(LoginDTO);
    }
    public boolean update(LoginDTO LoginDTO) throws Exception{
        return lDAO.update(LoginDTO);
    }

    public boolean delete(int idLogin) throws Exception{
        return lDAO.delete(idLogin);
    }

    public boolean search (String Nickname) throws Exception{
        return lDAO.userExists(Nickname);
    }

    public boolean verifyPassword (String Nickname, String clave) throws Exception{
        return lDAO.verifyPassword(Nickname,clave);
    }

    public Integer getId (String nickname) throws Exception{
        return lDAO.getId(nickname);
    }
}
