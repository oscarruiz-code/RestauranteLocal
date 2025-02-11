package org.example.restaurante.manejador;

import org.example.restaurante.entity.Cliente;
import org.example.restaurante.repository.ClienteRepository;

import java.util.List;

public class ManejadorCliente {

    private ClienteRepository clienteRepository;

    public ManejadorCliente() {
        clienteRepository = new ClienteRepository();
    }

    public void ingresarCliente(String nombre, String direccion, String telefono) {
        Cliente cliente = new Cliente(nombre, direccion, telefono);
        clienteRepository.guardarCliente(cliente);
    }

    public void actualizarCliente(int id, String nombre, String direccion, String telefono) {
        Cliente cliente = clienteRepository.obtenerClientePorId(id);
        if (cliente != null) {
            cliente.setNombre(nombre);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            clienteRepository.guardarCliente(cliente);
        }
    }

    public void eliminarCliente(int id) {
        clienteRepository.eliminarCliente(id);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.obtenerTodosLosClientes();
    }

    public List<Cliente> buscarClientesPorNombre(String nombre) {
        return clienteRepository.buscarClientesPorNombre(nombre);
    }

    public void cerrar() {
        clienteRepository.cerrar();
    }
}
