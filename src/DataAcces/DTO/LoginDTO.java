package DataAcces.DTO;

public class LoginDTO {

    private Integer IdLogin;
    private Integer IdJugador;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModifica;

    public LoginDTO(){}

    public LoginDTO(Integer idJugador) {
        IdJugador = idJugador;
    }

    public LoginDTO(Integer idLogin, Integer idJugador, String estado, String fechaCreacion,
            String fechaModifica) {
        IdLogin = idLogin;
        IdJugador = idJugador;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }

    public Integer getIdLogin() {
        return IdLogin;
    }

    public void setIdLogin(Integer idLogin) {
        IdLogin = idLogin;
    }

    public Integer getIdJugador() {
        return IdJugador;
    }

    public void setIdJugador(Integer idJugador) {
        IdJugador = idJugador;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public String getFechaModifica() {
        return FechaModifica;
    }

    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }

    public String toString(){
        return getClass().getName()
        + "\n IdLogin           : "+ getIdLogin             ()    
        + "\n IdJugador         : "+ getIdJugador           ()    
        + "\n Estado            : "+ getEstado              ()  
        + "\n FechaCreacion     : "+ getFechaCreacion       ()
        + "\n FechaModificacion : "+ getFechaModifica       ();
    }
    
}
