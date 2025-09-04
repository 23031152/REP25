
package Views;

import Controllers.CajeroController;
import Models.CajeroModel;
/**
 * Clase principal que inicia el sistema del cajero automático.
 */
public class CajeroAutomatico {
    public static void main(String[] args) {
        CajeroModel model = new CajeroModel();
        CajeroView view = new CajeroView();
        CajeroController controller = new CajeroController(model,view);
        controller.iniciarSistema();    
    }
}
