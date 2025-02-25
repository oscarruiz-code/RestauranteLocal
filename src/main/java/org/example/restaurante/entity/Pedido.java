package org.example.restaurante.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un pedido en la base de datos.
 *
 * @autor oscarruiz-code
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "hora")
    private String hora;

    @Column(name = "total")
    private Double total;

    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetallePedido> detalles;

    public Pedido() {
        this.detalles = new ArrayList<>();
    }

    /**
     * Constructor con los detalles del pedido.
     *
     * @param cliente El cliente que realiza el pedido.
     * @param fecha La fecha del pedido.
     * @param hora La hora del pedido.
     * @param estado El estado del pedido.
     */
    public Pedido(Cliente cliente, String fecha, String hora, String estado) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.detalles = new ArrayList<>();
    }

    /**
     * Calcula el total del pedido sumando los subtotales de los detalles.
     */
    public void calcularTotal() {
        this.total = detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    /**
     * Obtiene el nombre del cliente asociado al pedido.
     *
     * @return El nombre del cliente.
     */
    public String getClienteNombre() {
        return cliente.getNombre();
    }

    /**
     * Obtiene los nombres de los productos asociados al pedido.
     *
     * @return Una cadena con los nombres de los productos.
     */
    public String getProductoNombre() {
        if (detalles == null || detalles.isEmpty()) {
            return "Sin productos";
        }
        StringBuilder nombres = new StringBuilder();
        for (DetallePedido detalle : detalles) {
            if (nombres.length() > 0) {
                nombres.append(", ");
            }
            nombres.append(detalle.getProducto().getNombre());
        }
        return nombres.toString();
    }

}
