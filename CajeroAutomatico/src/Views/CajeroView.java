
package Views;

import java.util.Scanner;
import java.util.function.Function;
/**
 * Vista del cajero automático. Se encarga de interactuar con el usuario
 * mediante la consola, mostrando menús, solicitando datos y mostrando mensajes.
 */
public class CajeroView {
    private Scanner scanner;
    /**
     * Constructor de la vista. Inicializa el Scanner para leer entradas del usuario.
     */
    public CajeroView(){
        scanner=new Scanner(System.in);
    }
    /**
     * Método génerico para solicitar datos al usuario y convertirlos al tipo indicado.
     * @param <T> Tipo de dato a devolver
     * @param mensaje Texto a mostrar al usuario
     * @param parser Función para convertir el String ingresado al tipo deseado
     * @param Valor convertido del tipo T, o null si ocurre un error
     */
    public <T> T solicitarDato(String mensaje, Function<String, T>parser){
        System.out.println(mensaje);
        String input=scanner.nextLine();
        try{
            return parser.apply(input);
        } catch(Exception e){
            return null;
        }
    }
    /**
     * Muestra un mensaje de bienvenida al usuario.
     */
    public void mostrarBienvenida(){
        System.out.println("====================================================");
        System.out.println("Bienvenido al cajero automatico dek banco del bajio");
        System.out.println("====================================================");
    }
    /**
     * Solicita al usuario su número de cuenta.
     * @return El número de cuenta ingresado por el usuario.
     */
    public String solicitarNumeroCuenta(){
        return solicitarDato("Ingresa tu numero de cuenta: ", s -> s);
    }
    /**
     * Solicita al usuario su PIN.
     * @return El PIN ingresado por el usuario.
     */
    public String solicitarPin(){
        return solicitarDato("Ingresa tu PIN: ", s -> s);
    }
     /**
     * Muestra el menú principal con las opciones disponibles para el usuario.
     * @param titular Nombre del titular de la cuenta activa.
     */
    public void mostrarMenuPrincipal(String titular){
        System.out.println("====================================================");
        System.out.println("Bienvenido "+titular);
        System.out.println("====================================================");
        System.out.println("1.- Consultar saldo ");
        System.out.println("2.- Retirar ");
        System.out.println("3.- Depositar ");
        System.out.println("4.- Transferir");
        System.out.println("5.-Cambiar PIN");
        System.out.println("9.- Salir");
    }
    /**
     * Lee la opción ingresada por el usuario en el menú.
     * @return Número de opción elegido, o -1 si la entrada no es válida.
     */
    public int leerOpcion(){
        Integer opcion=solicitarDato("Elige una opción: ", Integer::parseInt);
        return (opcion != null) ? opcion : -1;
    }
    /**
     * Muestra el saldo actual de la cuenta activa.
     * @param saldo Saldo a mostrar
     */
    public void mostrarSaldo(double saldo){
        System.out.println("====================================================");
        System.out.println("Tu saldo actual es: $"+saldo);
        System.out.println("====================================================");
    }
    /**
     * Solicita al usuario una cantidad para realizar operaciones como retiro, depósito o transferencia.
     * @param operacion Nombre de la operación que se va a realizar.
     * @return Cantidad ingresada por el usuario, o -1 si la entrada no es válida.
     */
    public double solicitarCantidad(String operacion){
        Double cantidad=solicitarDato("Ingresa la cantidad a "+operacion +": ", Double::parseDouble);
        return(cantidad != null) ? cantidad : -1;
    }
    /**
     * Muestra un mensaje genérico al usuario (acepta cualquier tipo de objeto).
     * @param <T> Tipo del objeto a mostrar
     * @param mensaje Mensaje u objeto a mostrar
     */
    public <T> void mostrarMensaje(T mensaje) {
        System.out.println("===================" + mensaje);
    }
     /**
     * Solicita al usuario el número de cuenta destino para realizar una transferencia.
     * @return Número de cuenta destino ingresado.
     */
    public String solicitarCuentaDestino(){
        return solicitarDato("Ingresa el número de cuenta destino: ", s -> s);
    }
     /**
     * Solicita al usuario un nuevo PIN para cambiarlo.
     * @return Nuevo PIN ingresado por el usuario.
     */
    public String solicitarNuevoPin(){
        return solicitarDato("Ingresa tu nuevo PIN: ", s -> s);
    }
     /**
     * Cierra el Scanner utilizado para leer entradas del usuario.
     * Debe llamarse al finalizar el programa para liberar recursos.
     */
    public void cerrar(){
        scanner.close();
    }
}
