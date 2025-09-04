
package Models;
/**
 * Representa una cuenta bancaria con número de cuenta, PIN, saldo y titular.
 * Permite operaciones como retiro, depósito, transferencia y cambio de PIN.
 */
public class Cuenta{
    private String numeroCuenta;
    private String pin;
    private double saldo;
    private String titular;
    /**
     * Constructor de la clase Cuenta.
     *
     * @param numCuenta   Número de la cuenta.
     * @param pin         PIN de la cuenta.
     * @param saldoInicial Saldo inicial de la cuenta.
     * @param titular     Nombre del titular de la cuenta.
     */   
    public Cuenta(String numCuenta, String pin, double saldoInicial, String titular){
        this.numeroCuenta=numeroCuenta;
        this.pin=pin;
        this.saldo=saldoInicial;
        this.titular=titular;
    }
    
    public String getNumeroCuenta(){ return numeroCuenta;}
    public String getPin(){ return pin;}
    public double getSaldo(){ return saldo;}
    public String getTitular(){ return titular;}
    /**
     * Valida si el PIN ingresado coincide con el PIN de la cuenta.
     * @param pinIngresado PIN a validar
     * @return true si coincide, false si no
     */
    public boolean validarPin(String pinIngresado){
        return this.pin.equals(pinIngresado);
    }
     /**
     * Retira una cantidad de la cuenta si hay suficiente saldo.
     * @param cantidad Cantidad a retirar
     * @return true si el retiro fue exitoso, false si no hay suficiente saldo
     */
    public boolean retirar(double cantidad){
        if(cantidad>0 && cantidad <=this.saldo){
            saldo-=cantidad;
            return true;
        }
        return false;
    }
    /**
     * Deposita una cantidad en la cuenta si es válida (>0)
     * @param cantidad Cantidad a depositar
     */
    public void depositar(double cantidad){
        if(cantidad>0){
            saldo+=cantidad;
        }
    }
    /**
     * Transfiere dinero a otra cuenta si hay saldo suficiente.
     * @param destino Cuenta destino
     * @param cantidad Cantidad a transferir
     * @return true si la transferencia fue exitosa, false si no
     */
    public boolean transferir(Cuenta destino, double cantidad){
        if(destino == null) return false;
        if(this.retirar(cantidad)){ 
            destino.depositar(cantidad);
            return true;
        }
        return false; 
    }
    /**
     * Cambia el PIN de la cuenta si el nuevo es válido y distinto al actual.
     * @param nuevoPin Nuevo PIN
     * @return true si se cambió correctamente, false si no
     */
    public boolean cambiarPin(String nuevoPin){
        if(nuevoPin != null && !nuevoPin.trim().isEmpty() && !nuevoPin.equals(this.pin)){
            this.pin = nuevoPin;
            return true;
        }
        return false;
    }
    /** Cierra sesión de la cuenta */
    public void cerrarSesion(){
        System.out.println("Sesión cerrada para la cuenta: " + numeroCuenta);
    }
}
