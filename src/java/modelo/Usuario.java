package modelo;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String password;
    private int idRol;

    // Constructor sin ID (para cuando se registra un nuevo usuario)
    public Usuario(String nombre, String email, String telefono, String password) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }

    // Constructor con ID y Rol (para cuando se obtiene desde la base de datos)
    public Usuario(int id, String nombre, String email, String telefono, String password, int idRol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.idRol = idRol;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public String getContraseña() { return password; }
    public int getIdRol() { return idRol; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setContraseña(String password) { this.password = password; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
