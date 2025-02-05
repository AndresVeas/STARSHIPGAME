package DataAcces.DTO;

public class JugadorDTO {
    private Integer IdJugador;
    private String  Nombre;
    private String  Apellido;
    private String  Contrasena;
    private Integer PuntajeMax;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModifica;

    public JugadorDTO() {}

    public JugadorDTO(String nombre, String apellido) {
        Nombre = nombre;
        Apellido = apellido;
    }

    public JugadorDTO(Integer idJugador, String nombre, String apellido, String contrasena, Integer puntajeMax, String estado, String fechaCreacion,
                    String fechaModifica) {
        IdJugador = idJugador;
        Nombre = nombre;
        Apellido = apellido;
        Contrasena = contrasena;
        PuntajeMax = puntajeMax;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }

    public Integer getIdJugador() {
        return IdJugador;
    }
    public void setIdUsuario(Integer idJuagdor) {
        IdJugador = idJuagdor;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getApellido() {
        return Apellido;
    }
    public void setApellido(String apellido) {
        Apellido = apellido;
    }
    public String getContrasena() {
        return Contrasena;
    }
    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }
    public Integer getPuntajeMax() {
        return PuntajeMax;
    }
    public void setPuntajeMax(Integer puntajeMax) {
        PuntajeMax = puntajeMax;
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
    public void setFechaModifica(String fechaModificacion) {
        FechaModifica = fechaModificacion;
    }

    public String toString(){
        return getClass().getName()
        + "\n IdJugador         : "+ getIdJugador           ()    
        + "\n Nombre            : "+ getNombre              ()    
        + "\n Apellido          : "+ getApellido            ()  
        + "\n Contrasena        : "+ getContrasena          ()
        + "\n PuntajeMax        : "+ getPuntajeMax          ()  
        + "\n Estado            : "+ getEstado              ()  
        + "\n FechaCreacion     : "+ getFechaCreacion       ()
        + "\n FechaModificacion : "+ getFechaModifica       ();
    }

}