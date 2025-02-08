package DataAcces.DTO;

public class JugadorDTO {
    private Integer IdJugador;
    private String  Nickname;
    private String  Clave;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModifica;

    public JugadorDTO() {}

    public JugadorDTO(String nickname, String clave) {
        Nickname = nickname;
        Clave = clave;
    }

    public JugadorDTO(Integer idJugador, String nickname, String clave, String estado, String fechaCreacion,
            String fechaModifica) {
        IdJugador = idJugador;
        Nickname = nickname;
        Clave = clave;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }

    public Integer getIdJugador() {
        return IdJugador;
    }

    public void setIdJugador(Integer idJugador) {
        IdJugador = idJugador;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
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
        + "\n IdJugador         : "+ getIdJugador           ()    
        + "\n Nickname          : "+ getNickname            ()    
        + "\n Clave             : "+ getClave               ()
        + "\n Estado            : "+ getEstado              ()  
        + "\n FechaCreacion     : "+ getFechaCreacion       ()
        + "\n FechaModificacion : "+ getFechaModifica       ();
    }

}