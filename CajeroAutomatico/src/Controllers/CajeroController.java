package Controllers;

import Models.CajeroModel;
import Views.CajeroView;
/**
 * Controlador del cajero automático. Maneja la interacción
 * entre el usuario (vista) y las operaciones del modelo.
 */
public class CajeroController {
    private CajeroModel model;
    private CajeroView view;
    private boolean sistemaActivo;
     /**
     * Constructor del controlador.
     * @param model Modelo del cajero
     * @param view Vista del cajero
     */
    public CajeroController(CajeroModel model, CajeroView view){
        this.model=model;
        this.view=view;
        this.sistemaActivo=true;
    }
     /** Inicia el sistema del cajero */
    public void iniciarSistema(){
        view.mostrarBienvenida();
        while(sistemaActivo){
            if(autenticarUsuario()){
                ejecutarMenuPrincipal();
            } else{
                view.mostrarMensaje("Credenciales incorrectas");
            }
        }
        view.mostrarMensaje("Gracias por usar nuestro cajero");
    }
     /** Pide número de cuenta y PIN y autentica al usuario */
    private boolean autenticarUsuario(){
        String numeroCuenta=view.solicitarNumeroCuenta();
        String pin=view.solicitarPin();
        return model.autenticar(numeroCuenta,pin);
    }
    /** Ejecuta el menú principal mientras la sesión está activa */
    public void ejecutarMenuPrincipal(){
        boolean sessionActiva=true;
        while(sessionActiva){
            view.mostrarMenuPrincipal(model.getCuentaActual().getTitular());
            int opcion=view.leerOpcion();
            switch(opcion){
                case 1:
                    consultarSaldo();
                break;
                case 2:
                    realizarRetiro();
                break;
                case 3:
                    realizarDeposito();
                break;
                case 4:
                    realizarTransferencia();
                break;
                case 5:
                    cambiarPin();
                break;
                case 9:
                    cerrarSesion();
                    sessionActiva = false;
                break;
                default:
                    view.mostrarMensaje("Opción no válida, intenta de nuevo");
                break;
            }
        }
    }
      /** Muestra el saldo de la cuenta activa */
    public void consultarSaldo(){
        double saldo=model.consultarSaldo();
        view.mostrarSaldo(saldo);
    }
     /** Realiza un retiro de la cuenta activa */
    public void realizarRetiro(){
        double cantidad=view.solicitarCantidad("Retirar");
        if(cantidad<=0){
            view.mostrarMensaje("Error en la cantidad");
            return;
        }
        if(model.realizarRetiro(cantidad)){
            view.mostrarMensaje("Reitor exitoso de "+cantidad);
        } else{
            view.mostrarMensaje("Fondos insuficientes");
        }
    }
    /** Realiza un depósito en la cuenta activa */
    public void realizarDeposito(){
        double cantidad=view.solicitarCantidad("Deposito");
        if(cantidad <=0){
            view.mostrarMensaje("Error en la cantidad");
            return;
        }
        if(model.realizarDeposito(cantidad)){
            view.mostrarMensaje("Deposito exitoso por la cantidad "+cantidad);
        } else{
            view.mostrarMensaje("Error al procesar el deposito");
        }
    }
     /** Realiza una transferencia a otra cuenta */
    public void realizarTransferencia(){
        String cuentaDestino = view.solicitarCuentaDestino();
        double cantidad = view.solicitarCantidad("transferir");
        if(cantidad <= 0){
            view.mostrarMensaje("Error en la cantidad");
            return;
        }
        if(model.transferir(cuentaDestino, cantidad)){
            view.mostrarMensaje("Transferencia exitosa de $" + cantidad + " a la cuenta " + cuentaDestino);
        } else {
            view.mostrarMensaje("Error en la transferencia (cuenta inválida o saldo insuficiente)");
        }
    }
    /** Cambia el PIN de la cuenta activa */
    public void cambiarPin(){
        String nuevoPin = view.solicitarNuevoPin();
        if(model.cambiarPin(nuevoPin)){
            view.mostrarMensaje("PIN actualizado correctamente");
        } else {
            view.mostrarMensaje("Error al cambiar PIN, intente con otro valor");
        }
    }
     /** Cierra la sesión de la cuenta activa */
    public void cerrarSesion(){
        model.cerrarSesion();
        view.mostrarMensaje("Sesión cerrada correctamente");
    }
}
