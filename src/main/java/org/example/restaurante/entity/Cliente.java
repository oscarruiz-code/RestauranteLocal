package org.example.restaurante.entity;

import jakarta.persistence.*;

/**
 * Entidad que representa un cliente en la base de datos.
 *
 * @autor oscarruiz-code
 */
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    public Cliente() {
    }

    /**
     * Constructor con nombre del cliente.
     *
     * @param nombre El nombre del cliente.
     */
    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Constructor con nombre, dirección y teléfono del cliente.
     *
     * @param nombre    El nombre del cliente.
     * @param direccion La dirección del cliente.
     * @param telefono  El teléfono del cliente.
     */
    public Cliente(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Override
    public String toString() {
        return nombre;

    }
}
