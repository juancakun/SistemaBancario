package SistemaBancario;

import java.util.Objects;

public class CuentaBancaria {

    private final int numeroCuenta;
    private static int contadorCuentas;
    private Cliente titular;
    private double saldo;
    private boolean activa=true;

    public CuentaBancaria(Cliente cliente) {
        this.numeroCuenta = ++contadorCuentas;
    }

    public CuentaBancaria(Cliente cliente,double saldo) {
        this(cliente);
        this.titular = cliente;
        this.saldo = saldo;
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

    public void setSaldo(int saldo) {
        this.saldo = saldo;
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
                "numeroCuenta=" + numeroCuenta +
                ", titular=" + titular +
                ", saldo=" + saldo +
                ", activa=" + activa +
                '}';
    }
}
