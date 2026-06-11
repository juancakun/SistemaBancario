package SistemaBancario;

import java.util.Objects;

public class CuentaBancaria {

    private final int numeroCuenta;
    private static int contadorCuentas;
    private Cliente titular;
    private double saldo;
    private boolean activa=true;

    public CuentaBancaria() {
        this.numeroCuenta = ++contadorCuentas;
    }

    public CuentaBancaria(Cliente cliente,double saldo) {
        this();
        if(cliente != null)
            this.titular = cliente;
        else
            throw new IllegalArgumentException("Cliente no existe");

        if (saldo>=0)
            this.saldo=saldo;
        else
            throw  new IllegalArgumentException("El saldo inicial no puede ser negativo");
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public Cliente getTitular() {
        return this.titular;
    }

    public boolean isActiva() {
        return this.activa;
    }

    public void depositar(double saldo) {
        if(saldo>0) {
            System.out.println("El saldo depositado es: " + saldo);
            this.saldo += saldo;
            System.out.println("El nuevo saldo es: " + this.saldo);
        }
        else
            throw new IllegalArgumentException("El saldo a depositar no puede ser negativo o igual a cero.");
    }

    public void retirar(double saldo) {
        if(saldo<=this.saldo) {
            this.saldo -= saldo;
            System.out.println("El saldo retirado es: " + saldo);
            System.out.println("El nuevo saldo es: " + this.saldo);
        }
        else
            throw new IllegalArgumentException("El dinero a retirar no puede ser mayor al disponible.");
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancaria that = (CuentaBancaria) o;
        return numeroCuenta == that.numeroCuenta && saldo == that.saldo && activa == that.activa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCuenta, saldo, activa);
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "numeroCuenta=" + this.numeroCuenta +
                ", titular=" + this.titular +
                ", saldo=" + this.saldo +
                ", activa=" + this.activa +
                '}';
    }
}
