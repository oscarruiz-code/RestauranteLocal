package org.example.restaurante.entity;

import jakarta.persistence.*;

/**
 * Entidad que representa el detalle de un pedido en la base de datos.
 *
 * @autor oscarruiz-code
 */
@Entity
@Table(name = "DetallePedidos")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detallePedido")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Column(name = "producto_id")
    private int productoId;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio")
    private double precio;

    @Column(name = "subtotal")
    private double subtotal;

    public DetallePedido() {}

    /**
     * Constructor con los detalles de un pedido.
     *
     * @param pedido El pedido al que pertenece este detalle.
     * @param productoId El ID del producto.
     * @param cantidad La cantidad del producto.
     * @param precio El precio del producto.
     */
    public DetallePedido(Pedido pedido, int productoId, int cantidad, double precio) {
        this.pedido = pedido;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = cantidad * precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = this.cantidad * this.precio; // Recalculamos el subtotal al cambiar la cantidad
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
        this.subtotal = this.cantidad * this.precio; // Recalculamos el subtotal al cambiar el precio
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
