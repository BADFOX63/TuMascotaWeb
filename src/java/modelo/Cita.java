package modelo;

public class Cita {
    private String fecha;
    private String hora;
    private String estado;
    private int idMascota;

    public Cita(String fecha, String hora, String estado, int idMascota) {
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.idMascota = idMascota;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    public int getIdMascota() {
        return idMascota;
    }
}
