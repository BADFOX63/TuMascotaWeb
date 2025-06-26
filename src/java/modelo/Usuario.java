package modelo;

public class Usuario {
    private String nombre;
    private String email;
    private String telefono;
    private String password;

    public Usuario(String nombre, String email, String telefono, String password) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public String getContraseña() { return password; }

    
    public void setContraseña(String password) {
        this.password = password;
    }
}
