import javax.swing.JOptionPane;

import net.salesianos.menu.Menu;
import net.salesianos.restaurante.manager.RestauranteManager;

public class App {
    public static void main(String[] args) throws Exception {
        Menu menu = new Menu();
        String opcion;
        RestauranteManager restauranteManager = new RestauranteManager();
        do {

            do {
                opcion = JOptionPane.showInputDialog(menu.getMenu());
                if (opcion == null) {
                    int salir = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Confirmar salida",
                            JOptionPane.YES_NO_OPTION);
                    if (salir == JOptionPane.YES_OPTION) {
                        System.out.println("Saliendo...");
                        salir = -1;
                        break;
                    } else {
                        continue;
                    }
                }

            } while (opcion == null);

            switch (opcion.toUpperCase()) {
                case "1":
                    restauranteManager.añadirRestaurante();
                    break;
                case "2":
                    restauranteManager.editarRestaurante();
                    break;
                case "3":
                    restauranteManager.listarRestaurantes();
                    break;
                case "4":
                    restauranteManager.eliminarRestaurante();
                    break;
                case "Q":
                    System.out.println("Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "La opcion no es valida ");
                    break;
            }

        } while (!opcion.toUpperCase().equals("Q"));
        JOptionPane.showMessageDialog(null, "Saliendo... ");
    }
}
