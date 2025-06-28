package modelo;

public class Cita {
    private int id;
    private String fecha;
    private String hora;
    private String estado;
    private int idMascota;
    private int idUsuario;

    private String nombreMascota;
    private String raza;
    private String especie;
    private String nombreUsuario;
    private String emailUsuario;
    private String telefonoUsuario;
    private String motivoCancelacion;

    // Constructor corto para obtenerCitasPorUsuario
    public Cita(String fecha, String hora, String estado, int idMascota) {
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.idMascota = idMascota;
    }

    // Constructor completo para obtenerTodasLasCitas (con motivoCancelacion)
    public Cita(int id, String fecha, String hora, String estado, int idMascota, int idUsuario,
                String nombreMascota, String raza, String especie,
                String nombreUsuario, String emailUsuario, String telefonoUsuario,
                String motivoCancelacion) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.idMascota = idMascota;
        this.idUsuario = idUsuario;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
        this.especie = especie;
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.motivoCancelacion = motivoCancelacion;
    }

    // Getters
    public int getId() { return id; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getEstado() { return estado; }
    public int getIdMascota() { return idMascota; }
    public int getIdUsuario() { return idUsuario; }
    public String getNombreMascota() { return nombreMascota; }
    public String getRaza() { return raza; }
    public String getEspecie() { return especie; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getEmailUsuario() { return emailUsuario; }
    public String getTelefonoUsuario() { return telefonoUsuario; }
    public String getMotivoCancelacion() { return motivoCancelacion; }
}
