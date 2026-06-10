package SistemaBancario;

import java.time.LocalDate;
import java.util.Objects;

public class Transaccion {

    private static int numeroTransaccion;
    private final int idTransaccion;
    private String tipoTransaccion;
    private double montoTransaccion;
    private double montoAnterior;
    private double montoPosterior;
    private LocalDate fechaTransaccion;
    private CuentaBancaria cuentaOrigen;
    private CuentaBancaria cuentaDestino;

    public Transaccion() {
        this.idTransaccion = ++numeroTransaccion ;
    }

    public Transaccion(int tipoTransaccion, double montoTransaccion, double montoAnterior, double montoPosterior,LocalDate fechaTransaccion, CuentaBancaria cliente, CuentaBancaria cliente2) {
        this();
        switch (tipoTransaccion) {
            case 1 -> this.tipoTransaccion = "Deposito";
            case 2 -> this.tipoTransaccion = "Retiro";
            case 3 -> this.tipoTransaccion = "Transferencia";
        }
        this.montoAnterior = montoAnterior;
        this.montoPosterior = montoPosterior;
        this.montoTransaccion = montoTransaccion;
        this.fechaTransaccion = fechaTransaccion;
        if(cliente != null && cliente2 != null) {
            this.cuentaOrigen = cliente;
            this.cuentaDestino = cliente2;
        }

    }

    public int getIdTransaccion() {
        return this.idTransaccion;
    }

    public String getTipoTransaccion() {
        return this.tipoTransaccion;
    }

    public double getMontoTransaccion() {
        return this.montoTransaccion;
    }

    public LocalDate getFechaTransaccion() {
        return this.fechaTransaccion;
    }

    public CuentaBancaria getCuentaOrigen() {
        return this.cuentaOrigen;
    }

    public CuentaBancaria getCuentaDestino() {
        return this.cuentaDestino;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public void setMontoTransaccion(double montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public void setFechaTransaccion(LocalDate fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public void setCuentaOrigen(CuentaBancaria cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public void setCuentaDestino(CuentaBancaria cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaccion that = (Transaccion) o;
        return idTransaccion == that.idTransaccion && montoTransaccion == that.montoTransaccion && fechaTransaccion == that.fechaTransaccion && cuentaOrigen == that.cuentaOrigen && cuentaDestino == that.cuentaDestino && Objects.equals(tipoTransaccion, that.tipoTransaccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransaccion, tipoTransaccion, montoTransaccion, fechaTransaccion, cuentaOrigen, cuentaDestino);
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "idTransaccion=" + this.idTransaccion +
                ", tipoTransaccion='" + this.tipoTransaccion + '\'' +
                ", montoTransaccion=" + this.montoTransaccion +
                ", saldo anterior=" + this.montoAnterior +
                ", saldo actualizado=" + this.montoPosterior +
                ", fechaTransaccion=" + this.fechaTransaccion +
                ", cuentaOrigen=" + this.cuentaOrigen +
                ", cuentaDestino=" + this.cuentaDestino +
                '}';
    }
}
