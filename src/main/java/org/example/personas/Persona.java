package org.example.personas;

public class Persona {
    // nombre, apellido, edad, genero, sueldoHora, cargo
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private Double sueldoHora;
    private String cargo;

    public Persona() {
    }

    public Persona(String nombre, String apellido, int edad, String genero, Double sueldoHora, String cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.sueldoHora = sueldoHora;
        this.cargo = cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getSueldoHora() {
        return sueldoHora;
    }

    public void setSueldoHora(Double sueldoHora) {
        this.sueldoHora = sueldoHora;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
