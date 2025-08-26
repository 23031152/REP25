import java.util.Scanner;

public class Practica1Cajero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cajero cajero = new Cajero();
        Usuario usuarioActual = null;
        int intentos = 0;

        System.out.println("=== Bienvenido al Cajero ===");

        while (intentos < 3 && usuarioActual == null) {
            System.out.print("Ingrese su PIN: ");
            String pin = scanner.nextLine();
            usuarioActual = cajero.login(pin);
            if (usuarioActual == null) {
                System.out.println("PIN incorrecto.");
                intentos++;
            }
        }

        if (usuarioActual == null) {
            System.out.println("Demasiados intentos fallidos. Adiós.");
            return;
        }
        System.out.println("Bienvenido, " + usuarioActual.getNombre());
        boolean salir = false;

        while (!salir) {
            System.out.println("\n1. Ver saldo");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Su saldo es: $" + usuarioActual.getSaldo());
                    break;
                case 2:
                    System.out.print("Ingrese cantidad a retirar: ");
                    double retiro = scanner.nextDouble();
                    if (usuarioActual.retirar(retiro)) {
                        System.out.println("Retiro exitoso. Nuevo saldo: $" + usuarioActual.getSaldo());
                    } else {
                        System.out.println("Fondos insuficientes.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese cantidad a depositar: ");
                    double deposito = scanner.nextDouble();
                    usuarioActual.depositar(deposito);
                    System.out.println("Depósito exitoso. Nuevo saldo: $" + usuarioActual.getSaldo());
                    break;
                case 4:
                    salir = true;
                    System.out.println("Gracias por usar el cajero.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
