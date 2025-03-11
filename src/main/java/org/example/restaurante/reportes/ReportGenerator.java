package org.example.restaurante.reportes;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.example.restaurante.entity.Cliente;
import org.example.restaurante.entity.Pedido;
import org.example.restaurante.entity.Producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase responsable de generar informes utilizando JasperReports.
 * Proporciona métodos para generar informes de clientes, productos con bajo stock,
 * pedidos en preparación y tickets de pedidos.
 *
 * @author oscarruiz-code
 */
public class ReportGenerator {

    /**
     * Genera un informe de clientes a partir de una lista de objetos {@link Cliente}.
     *
     * @param clientes La lista de clientes que se incluirán en el informe.
     */
    public void generateClientesReport(List<Cliente> clientes) {
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/clientes.jasper"));
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clientes);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera un informe de productos con bajo stock a partir de una lista de objetos {@link Producto}.
     *
     * @param productos La lista de productos que se incluirán en el informe.
     */
    public void generateProductosBajoStockReport(List<Producto> productos) {
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/productos_bajo_stock.jasper"));
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productos);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera un informe de pedidos en preparación a partir de una lista de objetos {@link Pedido}.
     *
     * @param pedidos La lista de pedidos que se incluirán en el informe.
     */
    public void generatePedidosPreparacionReport(List<Pedido> pedidos) {
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/pedidos_preparacion.jasper"));
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pedidos);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera un ticket de pedido a partir de un objeto {@link Pedido}.
     * Incluye detalles como el nombre del cliente, la fecha, la hora y los productos del pedido.
     *
     * @param pedido El pedido para el cual se generará el ticket.
     */
    public void generateTicketPedidoReport(Pedido pedido) {
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/ticket_pedido.jasper"));
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pedido.getDetalles());

            // Parámetros para el informe
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("clienteNombre", pedido.getCliente().getNombre());
            parameters.put("fecha", pedido.getFecha());
            parameters.put("hora", pedido.getHora());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}