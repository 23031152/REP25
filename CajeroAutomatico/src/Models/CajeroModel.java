
package Models;

import java.util.HashMap;
import java.util.Map;
/**
 * Modelo del cajero automático. Maneja cuentas y operaciones como autenticación,
 * depósito, retiro, transferencia, cambio de PIN y cierre de sesión.
 */
public class CajeroModel {
    private Map<String,Cuenta> cuentas;
    private Cuenta cuentaActual;
     /** Constructor: inicializa las cuentas disponibles */
    public CajeroModel(){
        cuentas=new HashMap<>();
        inicializarCuentas();
    }
     /** Inicializa algunas cuentas de ejemplo */
    private void inicializarCuentas(){
        cuentas.put("12345", new Cuenta("12345","1111",5000,"Juan Perez"));
        cuentas.put("67890", new Cuenta("67890","0000",6525,"Maria Lopez"));
        cuentas.put("24680", new Cuenta("24680","2244",10840,"Jose Torres"));
    }
    /**
     * Autentica una cuenta usando número y PIN.
     * @param numeroCuenta Número de la cuenta
     * @param pin PIN de la cuenta
     * @return true si la autenticación es correcta, false si no
     */
    public boolean autenticar(String numeroCuenta, String pin){
        Cuenta cuenta=cuentas.get(numeroCuenta);
        if(cuenta != null && cuenta.validarPin(pin)){
            this.cuentaActual=cuenta;
            return true;
        }
        return false;
    }
     /** @return Cuenta actualmente activa */
    public Cuenta getCuentaActual(){
        return this.cuentaActual;
    }
    /** @return Saldo de la cuenta activa */
    public double consultarSaldo(){
        return this.cuentaActual != null ? cuentaActual.getSaldo():0;
    }
      /** Retira una cantidad de la cuenta activa */
    public boolean realizarRetiro(double cantidad){
        return cuentaActual !=null && cuentaActual.retirar(cantidad);
    }
      /** Retira una cantidad de la cuenta activa */
    public boolean realizarDeposito(double cantidad){
        if(cuentaActual != null && cantidad>0){
            cuentaActual.depositar(cantidad);
            return true;
        }
        return false;
    }
     /** Verifica si existe una cuenta */
    public boolean cuentaExistente(String numeroCuenta){
        return cuentas.containsKey(numeroCuenta);
    }
     /** Transfiere a otra cuenta desde la cuenta activa */
     public boolean transferir(String numeroCuentaDestino, double cantidad){
        if(cuentaActual == null) return false;
        Cuenta destino = cuentas.get(numeroCuentaDestino);
        if(destino == null) return false;
        return cuentaActual.transferir(destino, cantidad);
    }
     /** Cambia el PIN de la cuenta activa */
    public boolean cambiarPin(String nuevoPin){
        return cuentaActual != null && cuentaActual.cambiarPin(nuevoPin);
    }
     /** Cierra la sesión de la cuenta activa */
    public void cerrarSesion(){
        if(cuentaActual != null){
            cuentaActual.cerrarSesion();
            cuentaActual = null;
        }
    }
}
