package SistemaBancario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientesExistentes {

    private static final List<Cliente> cliente;
    private static final List<CuentaBancaria> cuentaBancaria;
    private static final List<Transaccion> transacciones ;


    //Bloque de tipo estatico de cuentas

    static{
        cliente = new ArrayList<>();
        cuentaBancaria = new ArrayList<>();
        transacciones = new ArrayList<>();
        cliente.add(new Cliente("Juan", "Carlos", "juancarlos.hdz.montero@gmail", "5561524910"));
        cliente.add(new Cliente("Ana", "Martínez", "ana.martinez@gmail.com", "5512345678"));
        cliente.add(new Cliente("Luis", "García", "luis.garcia@gmail.com", "5523456789"));
        cliente.add(new Cliente("María", "López", "maria.lopez@gmail.com", "5534567890"));
        cliente.add(new Cliente("Carlos", "Ramírez", "carlos.ramirez@gmail.com", "5545678901"));
        cliente.add(new Cliente("Sofía", "Hernández", "sofia.hernandez@gmail.com", "5556789012"));
    }

    public static void agregarCliente(Cliente c){
        cliente.add(c);
    }

    public static void eliminarCliente(int idCliente){
        for (Cliente c : cliente){
            if(c.getIdCliente() == idCliente){
                cliente.remove(c);
            }
        }
    }

    public static List<Cliente> getClientes(){
        return cliente;
    }

    public static void mostrarCliente(){
        var clientesExistentes = "";
        for(var cliente: cliente)
            clientesExistentes += cliente.toString() + "\n";
        System.out.println("--- Clientes del banco ---");
        System.out.println(clientesExistentes);
    }

    public static boolean buscarClienteId(int idCliente){
        for (var cliente : cliente) {
            if (cliente.getIdCliente() == idCliente) {
                return true;
            }
        }
        return false;
    }

    public static Cliente buscarCliente(int idCliente){
        for (var cliente : cliente) {
            if (cliente.getIdCliente() == idCliente)
                return cliente;
        }
        return null;
    }

    public static void crearCuentaCliente(Cliente cliente, double saldo){
        if(buscarClienteId(cliente.getIdCliente())){
            CuentaBancaria cb = new CuentaBancaria(cliente, saldo);
            cuentaBancaria.add(cb);
            System.out.println("Cuenta bancaria agregada");
        }else{
            System.out.println("El titular de la cuenta no existe");
        }
    }

    public static void mostrarCuentas(){
        var cuentasExistentes = "";
        for(var cuenta: cuentaBancaria)
            cuentasExistentes += cuenta.toString() + "\n";
        System.out.println("--- Cuentas del banco ---");
        System.out.println(cuentasExistentes);
    }

    public static void mostrarTodasCuentas(Cliente cliente){
        for(var cuenta: cuentaBancaria){
            if(cliente.getIdCliente() == cuenta.getTitular().getIdCliente()) {
                System.out.println(cuenta);
            }
        }
    }

    public static CuentaBancaria seleccionarCuentaCliente(Cliente cliente, int idCuentaCliente){
        for(var cuenta: cuentaBancaria) {
            if(cliente.getIdCliente() == cuenta.getTitular().getIdCliente()) {
                if (cuenta.getNumeroCuenta() == idCuentaCliente) {
                    System.out.println(cuenta);
                    return cuenta;
                }
            }
        }
        return null;
    }

    public static void historialTransaccion(int tipoTransaccion, double saldo, double montoAnterior , double montoPosterior , CuentaBancaria cuentaBancaria1, CuentaBancaria cuentaBancaria2){
        LocalDate fechaTransaccion = LocalDate.now();
        Transaccion transaccion = new Transaccion(tipoTransaccion, saldo, montoAnterior, montoPosterior,fechaTransaccion, cuentaBancaria1, cuentaBancaria2);
        transacciones.add(transaccion);
    }

    public static void mostrarTransacciones (){
        for(var transaccion: transacciones){
            System.out.println(transaccion);
        }
    }
}
