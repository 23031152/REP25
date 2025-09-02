
package Views;

import java.util.Scanner;

public class CajeroView {
    private Scanner scanner;
    public CajeroView(){
        scanner=new Scanner(System.in);
    }
    
    public void mostrarBienvenida(){
        System.out.println("====================================================");
        System.out.println("Bienvenido al cajero automatico dek banco del bajio");
        System.out.println("====================================================");
    }
    public String solicitarNumeroCuenta(){
        System.out.println("INgresa tu numero de cuenta: ");
        return scanner.nextLine();
    }
    public String solicitarPin(){
        System.out.println("Ingresa tu PIN: ");
        return scanner.nextLine();
    }
    public void mostrarMenuPrincipal(String titular){
        System.out.println("====================================================");
        System.out.println("Bienvenido "+titular);
        System.out.println("====================================================");
        System.out.println("1.- Consultar saldo ");
        System.out.println("2.- Retirar ");
        System.out.println("3.- Depositar ");
        //Definir las opciones faltantes
        System.out.println("9.- Salir");
    }
    public int leerOpcion(){
        try{
            return Integer.parseInt(scanner.nextLine());
        } catch(NumberFormatException e){
            return -1;
        }
    }
    public void mostrarSaldo(double saldo){
        System.out.println("====================================================");
        System.out.println("Tu saldo actual es: $"+saldo);
        System.out.println("====================================================");
    }
    public double solicitarCantidad(String operacion){
        System.out.println("Ingresa la cantidad a "+operacion+": ");
        try{
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e){
            return -1;
        }
    }
    public void mostrarMensaje(String mensaje){
        System.out.println("==================="+mensaje);
    }
    //Tarea personalizar mensajes de error y de exito
    //Tarea metodo para salir cerrar el scanner
}
