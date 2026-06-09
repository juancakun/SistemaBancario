package SistemaBancario;

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
            case 6 -> depositarDinero();
            case 7 -> retirarDinero();
            case 8 -> transferirDinero();
            case 9 -> consultarSaldo();
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
            var iniciarSaldo = consola.nextBoolean();
            consola.nextLine();
            var saldo = 0.0;
            if(iniciarSaldo) {
                System.out.print("Con cuánto saldo desea iniciar la cuenta: ");
                saldo = consola.nextDouble();
                consola.nextLine();
                ClientesExistentes.crearCuentaCliente(ClientesExistentes.buscarCliente(idCliente), saldo);
            } else {
                ClientesExistentes.crearCuentaCliente(ClientesExistentes.buscarCliente(idCliente), saldo);
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
        System.out.print("De que cliente desea mostrar todos sus cuentas");
        var idCliente = Integer.parseInt(consola.nextLine());
        Cliente cliente =  ClientesExistentes.buscarCliente(idCliente);
        ClientesExistentes.mostrarTodasCuentas(cliente);
    }

    private static void depositarDinero(){

    }

    private static void retirarDinero(){

    }

    private static void transferirDinero(){

    }

    private static void consultarSaldo(){

    }

    private static void verHistorialTransacciones(){

    }

}
