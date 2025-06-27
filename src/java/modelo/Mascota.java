package modelo;

public class Mascota {
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private int idUsuario;

    // Constructor completo
    public Mascota(int id, String nombre, String especie, String raza, int idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.idUsuario = idUsuario;
    }

    // Constructor sin ID
    public Mascota(String nombre, String especie, String raza, int idUsuario) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.idUsuario = idUsuario;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public String getRaza() { return raza; }
    public int getIdUsuario() { return idUsuario; }

    public void setId(int id) { this.id = id; }
}
