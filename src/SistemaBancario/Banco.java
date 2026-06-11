package SistemaBancario;

import java.util.Objects;
import java.util.Scanner;

public class Banco {
    public static void main(String[] args) {
        bancoPrincipal();
    }

    public static void bancoPrincipal(){
        var salir = false;
        var consola = new Scanner(System.in);
        System.out.println("*** Las Chivas bank ***");
        ClientesExistentes.mostrarCliente();
        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola);
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static int mostrarMenu(Scanner sc){
        System.out.print("""
                \nMenu:
                1. Registrar cliente
                2. Crear cuenta bancaria
                3. Mostrar clientes
                4. Mostrar todas las cuentas
                5. Mostrar cuentas de cliente especifico
                6. Depositar dinero
                7. Retirar dinero
                8. Transferir dinero
                9. Consultar saldo
                10. Ver historial de transacciones
                11. Salir
                Elige una opción:\s""");
        return Integer.parseInt(sc.nextLine());
    }

    public static boolean ejecutarOpciones(Integer opcion, Scanner consola){
        var salir = false;
        switch(opcion){
            case 1 -> registrarCliente(consola);
            case 2 -> crearCuenta(consola);
            case 3 -> mostrarClientes();
            case 4 -> mostrarCuentas();
            case 5 -> mostrarTodasCuentas(consola);
            case 6 -> depositarDinero(consola);
            case 7 -> retirarDinero(consola);
            case 8 -> transferirDinero(consola);
            case 9 -> consultarSaldo(consola);
            case 10 -> verHistorialTransacciones();
            case 11 -> salir = true;
            default -> System.out.println("Esa no es una opción valida");
        }
        return salir;
    }

    private static void registrarCliente(Scanner consola){
        System.out.println("Registrar Cliente");
        System.out.print("Nombre: ");
        var nombre = consola.nextLine();
        System.out.print("Apellido: ");
        var apellido = consola.nextLine();
        System.out.print("Email: ");
        var email = consola.nextLine();
        System.out.print("Telefono: ");
        var telefono = consola.nextLine();
        Cliente cliente = new Cliente(nombre, apellido, email, telefono);
        ClientesExistentes.agregarCliente(cliente);
    }

    private static void crearCuenta(Scanner consola){
        System.out.print("Digite el cliente al que quiere crear una cuenta: ");
        var idCliente = Integer.parseInt(consola.nextLine());

        if(ClientesExistentes.buscarClienteId(idCliente)){
            System.out.print("Desea ingresar saldo a la cuenta?true/false: ");
            var iniciarSaldo = Boolean.parseBoolean(consola.nextLine());
            var saldo = 0.0;
            if(iniciarSaldo) {

                do{
                    System.out.print("Con cuánto saldo desea iniciar la cuenta: ");
                    saldo = Double.parseDouble(consola.nextLine());
                    if(saldo < 0)
                        System.out.println("El saldo inicial no puede ser negativo");
                }while(saldo < 0);

                ClientesExistentes.crearCuentaCliente(Objects.requireNonNull(ClientesExistentes.buscarCliente(idCliente)), saldo);

            } else {
                ClientesExistentes.crearCuentaCliente(Objects.requireNonNull(ClientesExistentes.buscarCliente(idCliente)), saldo);
                System.out.println("Cuenta creada");
            }
        } else
            System.out.println("Cliente no existe");
    }

    private static void mostrarClientes(){
        ClientesExistentes.mostrarCliente();
    }

    private static void mostrarCuentas(){
        ClientesExistentes.mostrarCuentas();
    }

    private static void mostrarTodasCuentas(Scanner consola){
        System.out.print("De que cliente desea mostrar todos sus cuentas: ");
        var idCliente = Integer.parseInt(consola.nextLine());
        Cliente cliente =  ClientesExistentes.buscarCliente(idCliente);
        ClientesExistentes.mostrarTodasCuentas(cliente);
    }

    private static void depositarDinero(Scanner consola){
        System.out.println("Iniciando depositar dinero");
        System.out.print("A qué cliente desea ingresar el deposito?: ");
        var idCliente = Integer.parseInt(consola.nextLine());
        Cliente cliente = ClientesExistentes.buscarCliente(idCliente);

        ClientesExistentes.mostrarTodasCuentas(cliente);

        System.out.print("A que cuenta del cliente desea depositar dinero?: ");
        var idCuentaCliente = Integer.parseInt(consola.nextLine());
        CuentaBancaria cuentaBancaria = ClientesExistentes.seleccionarCuentaCliente(cliente, idCuentaCliente);

        var montoAnterior = cuentaBancaria.getSaldo();

        System.out.print("Cuánto dinero quiere depositar a la cuenta bancaria?");
        var saldo = Double.parseDouble(consola.nextLine());

        int tipoTransaccion = 1;

        assert cuentaBancaria != null;
        cuentaBancaria.depositar(saldo);
        var montoPosterior = cuentaBancaria.getSaldo();
        ClientesExistentes.historialTransaccion(tipoTransaccion, saldo, montoAnterior, montoPosterior,cuentaBancaria, cuentaBancaria);
    }

    private static void retirarDinero(Scanner consola){
        System.out.println("Iniciando retiro de dinero");
        System.out.print("A qué cliente desea retirar?: ");
        var idCliente = Integer.parseInt(consola.nextLine());
        Cliente cliente = ClientesExistentes.buscarCliente(idCliente);

        ClientesExistentes.mostrarTodasCuentas(cliente);

        System.out.print("A que cuenta del cliente desea retirar dinero?: ");
        var idCuentaCliente = Integer.parseInt(consola.nextLine());
        CuentaBancaria cuentaBancaria = ClientesExistentes.seleccionarCuentaCliente(cliente, idCuentaCliente);

        assert cuentaBancaria != null;
        var montoAnterior = cuentaBancaria.getSaldo();

        System.out.print("Cuánto dinero quiere retirar a la cuenta bancaria?");
        var saldo = Double.parseDouble(consola.nextLine());

        var tipoTransaccion = 2;

        assert cuentaBancaria != null;
        cuentaBancaria.retirar(saldo);
        var montoPosterior = cuentaBancaria.getSaldo();
        ClientesExistentes.historialTransaccion(tipoTransaccion, saldo, montoAnterior, montoPosterior, cuentaBancaria, cuentaBancaria);
    }

    private static void transferirDinero(Scanner consola){
        System.out.println("Iniciando transferir dinero");

        //Cliente 1

        System.out.print("Que cliente desea transferir dinero?: ");
        var idCuenta = Integer.parseInt(consola.nextLine());
        Cliente cliente = ClientesExistentes.buscarCliente(idCuenta);

        ClientesExistentes.mostrarTodasCuentas(cliente);

        System.out.print("Desde que cuenta del cliente desea transferir dinero?: ");
        var idCuentaCliente = Integer.parseInt(consola.nextLine());
        CuentaBancaria cuentaBancaria = ClientesExistentes.seleccionarCuentaCliente(cliente, idCuentaCliente);

        System.out.print("Cuánto dinero quiere transferir de la cuenta bancaria?");
        var saldo = Double.parseDouble(consola.nextLine());

        //Cliente 2

        System.out.print("Qué cliente desea que reciba el dinero?: ");
        var idCuenta2 = Integer.parseInt(consola.nextLine());
        Cliente cliente2 = ClientesExistentes.buscarCliente(idCuenta2);

        ClientesExistentes.mostrarTodasCuentas(cliente);

        System.out.print("A qué cuenta del cliente desea transferir dinero?: ");
        var idCuentaCliente2 = Integer.parseInt(consola.nextLine());
        CuentaBancaria cuentaBancaria2 = ClientesExistentes.seleccionarCuentaCliente(cliente2, idCuentaCliente2);

        var tipoTransaccion = 3;

        var montoAnterior = cuentaBancaria.getSaldo();

        cuentaBancaria.retirar(saldo);
        cuentaBancaria2.depositar(saldo);

        var montoPosterior = cuentaBancaria.getSaldo();

        ClientesExistentes.historialTransaccion(tipoTransaccion, saldo, montoAnterior, montoPosterior, cuentaBancaria, cuentaBancaria2);

    }

    private static void consultarSaldo(Scanner consola){
        System.out.println("Iniciando consultar saldo");
        System.out.print("Que cliente desea consultar saldo?: ");
        var idCliente = Integer.parseInt(consola.nextLine());
        Cliente cliente = ClientesExistentes.buscarCliente(idCliente);

        System.out.print("Qué cuenta desea consultar saldo?: ");
        var idCuentaCliente = Integer.parseInt(consola.nextLine());
        CuentaBancaria cuentaBancaria = ClientesExistentes.seleccionarCuentaCliente(cliente, idCuentaCliente);
    }

    private static void verHistorialTransacciones(){
        ClientesExistentes.mostrarTransacciones();
    }

}
