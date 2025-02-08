package DataAcces.DTO;

public class ScoreDTO {

    private Integer IdScore;
    private Integer IdJugador;
    private Integer Puntaje;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModifica;

    public ScoreDTO(){}

    public ScoreDTO(Integer IdJugador, Integer Puntaje) {
        this.IdJugador = IdJugador;
        this.Puntaje = Puntaje;
    }

    public ScoreDTO(Integer idScore, Integer idJugador, Integer puntaje, String estado, String fechaCreacion,
            String fechaModifica) {
        IdScore = idScore;
        IdJugador = idJugador;
        Puntaje = puntaje;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }

    public Integer getIdScore() {
        return IdScore;
    }

    public void setIdScore(Integer idScore) {
        IdScore = idScore;
    }

    public Integer getIdJugador() {
        return IdJugador;
    }

    public void setIdJugador(Integer idJugador) {
        IdJugador = idJugador;
    }

    public Integer getPuntaje() {
        return Puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        Puntaje = puntaje;
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
        + "\n IdScore           : "+ getIdScore             ()    
        + "\n IdJugador         : "+ getIdJugador           ()    
        + "\n Puntaje           : "+ getPuntaje             ()  
        + "\n Estado            : "+ getEstado              ()  
        + "\n FechaCreacion     : "+ getFechaCreacion       ()
        + "\n FechaModificacion : "+ getFechaModifica       ();
    }
    
}
