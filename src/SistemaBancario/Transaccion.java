package SistemaBancario;

import java.util.Objects;

public class Transaccion {

    private static int numeroTransaccion;
    private int idTransaccion;
    private String tipoTransaccion;
    private int montoTransaccion;
    private int fechaTransaccion;
    private int cuentaOrigen;
    private int cuentaDestino;

    public Transaccion() {}

    public Transaccion(String tipoTransaccion, int montoTransaccion, int fechaTransaccion, int cuentaOrigen, int cuentaDestino) {
        this();
        this.tipoTransaccion = tipoTransaccion;
        this.montoTransaccion = montoTransaccion;
        this.fechaTransaccion = fechaTransaccion;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public int getIdTransaccion() {
        return this.idTransaccion;
    }

    public String getTipoTransaccion() {
        return this.tipoTransaccion;
    }

    public int getMontoTransaccion() {
        return this.montoTransaccion;
    }

    public int getFechaTransaccion() {
        return this.fechaTransaccion;
    }

    public int getCuentaOrigen() {
        return this.cuentaOrigen;
    }

    public int getCuentaDestino() {
        return this.cuentaDestino;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public void setMontoTransaccion(int montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public void setFechaTransaccion(int fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public void setCuentaOrigen(int cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public void setCuentaDestino(int cuentaDestino) {
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
                ", fechaTransaccion=" + this.fechaTransaccion +
                ", cuentaOrigen=" + this.cuentaOrigen +
                ", cuentaDestino=" + this.cuentaDestino +
                '}';
    }
}
