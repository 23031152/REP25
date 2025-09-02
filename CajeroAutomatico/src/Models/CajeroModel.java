
package Models;

import java.util.HashMap;
import java.util.Map;

public class CajeroModel {
    private Map<String,Cuenta> cuentas;
    private Cuenta cuentaActual;
    public CajeroModel(){
        cuentas=new HashMap<>();
        inicializarCuentas();
    }
    private void inicializarCuentas(){
        cuentas.put("12345", new Cuenta("12345","1111",5000,"Juan Perez"));
        cuentas.put("67890", new Cuenta("67890","0000",6525,"Maria Lopez"));
        cuentas.put("24680", new Cuenta("24680","2244",10840,"Jose Torres"));
    }
    public boolean autenticar(String numeroCuenta, String pin){
        Cuenta cuenta=cuentas.get(numeroCuenta);
        if(cuenta != null && cuenta.validarPin(pin)){
            this.cuentaActual=cuenta;
            return true;
        }
        return false;
    }
    public Cuenta getCuentaActual(){
        return this.cuentaActual;
    }
    public double consultarSaldo(){
        return this.cuentaActual != null ? cuentaActual.getSaldo():0;
    }
    public boolean realizarRetiro(double cantidad){
        return cuentaActual !=null && cuentaActual.retirar(cantidad);
    }
    public boolean realizarDeposito(double cantidad){
        if(cuentaActual != null && cantidad>0){
            cuentaActual.depositar(cantidad);
            return true;
        }
        return false;
    }
    public boolean cuentaExistente(String numeroCuenta){
        return cuentas.containsKey(numeroCuenta);
    }
    //Tarea el método para transferir
}
