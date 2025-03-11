package org.example.restaurante.entity;

import jakarta.persistence.*;

/**
 * Entidad que representa un producto en la base de datos.
 *
 * @autor oscarruiz-code
 */
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "precio")
    private double precio;

    @Column(name = "disponibilidad")
    private boolean disponibilidad;

    @Column(name = "stock") // Nuevo campo
    private Integer stock;

    /**
     * Constructor con los detalles del producto.
     *
     * @param nombre El nombre del producto.
     * @param categoria La categoría del producto.
     * @param precio El precio del producto.
     * @param disponibilidad La disponibilidad del producto.
     */
    public Producto(String nombre, String categoria, double precio, boolean disponibilidad,Integer stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.stock = stock;
    }

    public Producto() {}

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
