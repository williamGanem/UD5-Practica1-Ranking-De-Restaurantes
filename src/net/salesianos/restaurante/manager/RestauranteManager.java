package net.salesianos.restaurante.manager;

import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.salesianos.restaurante.Restaurante;

public class RestauranteManager {

    private ArrayList<Restaurante> restaurantes;

    public RestauranteManager() {
        restaurantes = new ArrayList<>();
    }

    public void añadirRestaurante() {
        JTextField nombreJField = new JTextField(10);
        JTextField localizacionJField = new JTextField(10);
        JTextField horarioJField = new JTextField(10);
        JTextField puntuacionJField = new JTextField(10);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Introduzca el nombre"));
        panel.add(nombreJField);
        panel.add(new JLabel("Introduzca la localizacion"));
        panel.add(localizacionJField);
        panel.add(new JLabel("Introduzca el horario"));
        panel.add(horarioJField);
        panel.add(new JLabel("Introduzca la puntuacion"));
        panel.add(puntuacionJField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Ingresar datos del Restaurante",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String nombre = nombreJField.getText();
            String localizacion = localizacionJField.getText();
            String horario = horarioJField.getText();
            float puntuacion;

            try {
                puntuacion = Float.parseFloat(puntuacionJField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un valor válido para la puntuación.");
                return;
            }

            Restaurante restaurante = new Restaurante(nombre, localizacion, horario, puntuacion);

            restaurantes.add(restaurante);
            JOptionPane.showMessageDialog(null, "Restaurante añadido exitosamente:\n" + restaurante.toString());
        }
    }

    public void comprobarRestaurantes() {
        if (restaurantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay restaurantes disponibles para modificar.");
            return;
        }
    }

    public void editarRestaurante() {

        comprobarRestaurantes();

        // Crear una lista de nombres de restaurantes para seleccionar
        String[] nombresRestaurantes = new String[restaurantes.size()];
        for (int i = 0; i < restaurantes.size(); i++) {
            nombresRestaurantes[i] = restaurantes.get(i).getNombre();
        }

        String nombreSeleccionado = "";
        do {
            try {

                nombreSeleccionado = (String) JOptionPane.showInputDialog(
                        null,
                        "Selecciona un restaurante para modificar:",
                        "Modificar Restaurante",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        nombresRestaurantes,
                        nombresRestaurantes[0]);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al seleccionar restaurante.");
                return;
            }

        } while (nombreSeleccionado == null);

        Restaurante restauranteSeleccionado = null;
        for (Restaurante restaurante : restaurantes) {
            if (restaurante.getNombre().equals(nombreSeleccionado)) {
                restauranteSeleccionado = restaurante;
                break;
            }
        }

        if (restauranteSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Restaurante no encontrado.");
            return;
        }

        JTextField nombreField = new JTextField(restauranteSeleccionado.getNombre(), 10);
        JTextField localizacionField = new JTextField(restauranteSeleccionado.getLocalizacion(), 10);
        JTextField horarioField = new JTextField(restauranteSeleccionado.getHorario(), 10);
        JTextField puntuacionField = new JTextField(Float.toString(restauranteSeleccionado.getPuntuacion()), 10);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Nombre del Restaurante:"));
        panel.add(nombreField);
        panel.add(new JLabel("Localización del Restaurante:"));
        panel.add(localizacionField);
        panel.add(new JLabel("Horario del Restaurante:"));
        panel.add(horarioField);
        panel.add(new JLabel("Puntuación del Restaurante:"));
        panel.add(puntuacionField);

        int result = 0;
        String nuevoNombre = "";
        String nuevaLocalizacion = "";
        String nuevoHorario = "";
        float nuevaPuntuacion = -1;
        do {
            if (result == JOptionPane.OK_OPTION) {
                result = JOptionPane.showConfirmDialog(null, panel, "Modificar datos del Restaurante",
                        JOptionPane.OK_CANCEL_OPTION);
                nuevoNombre = nombreField.getText();
                nuevaLocalizacion = localizacionField.getText();
                nuevoHorario = horarioField.getText();
                nuevaPuntuacion = -1;
            }

        } while (result == -1);

        try {
            nuevaPuntuacion = Float.parseFloat(puntuacionField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un valor válido para la puntuación.");
            return;
        }

        restauranteSeleccionado.setNombre(nuevoNombre);
        restauranteSeleccionado.setLocalizacion(nuevaLocalizacion);
        restauranteSeleccionado.setHorario(nuevoHorario);
        restauranteSeleccionado.setPuntuacion(nuevaPuntuacion);

        JOptionPane.showMessageDialog(null,
                "Restaurante modificado exitosamente:\n" + restauranteSeleccionado.toString());

    }

    public void listarRestaurantes() {
        comprobarRestaurantes();

        restaurantes.sort(Comparator.comparingDouble(Restaurante::getPuntuacion).reversed());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Lista de Restaurantes ordenada de mejor a peor:\n");
        for (Restaurante restaurante : restaurantes) {
            stringBuilder.append("Nombre: ").append(restaurante.getNombre()).append("\n");
            stringBuilder.append("Localización: ").append(restaurante.getLocalizacion()).append("\n");
            stringBuilder.append("Horario: ").append(restaurante.getHorario()).append("\n");
            stringBuilder.append("Puntuación: ").append(restaurante.getPuntuacion()).append("\n");
            stringBuilder.append("\n");
        }

        JOptionPane.showMessageDialog(null, stringBuilder.toString());
    }

    public void eliminarRestaurante() {

        if (restaurantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay restaurantes disponibles para eliminar.");
            return;
        }

        String[] nombresRestaurantes = new String[restaurantes.size()];
        for (int i = 0; i < restaurantes.size(); i++) {
            nombresRestaurantes[i] = restaurantes.get(i).getNombre();
        }

        String nombreSeleccionado = "";
        do {
            try {
                nombreSeleccionado = (String) JOptionPane.showInputDialog(
                        null,
                        "Selecciona un restaurante para eliminar:",
                        "Eliminar Restaurante",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        nombresRestaurantes,
                        nombresRestaurantes[0]);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al seleccionar restaurante.");
                return;
            }
        } while (nombreSeleccionado == null);

        int confirmResult = JOptionPane.showConfirmDialog(
                null,
                "¿Estás seguro de que deseas eliminar el restaurante " + nombreSeleccionado + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmResult == JOptionPane.YES_OPTION) {

            Restaurante restauranteAEliminar = null;
            for (Restaurante restaurante : restaurantes) {
                if (restaurante.getNombre().equals(nombreSeleccionado)) {
                    restauranteAEliminar = restaurante;
                    break;
                }
            }

            if (restauranteAEliminar != null) {
                restaurantes.remove(restauranteAEliminar);
                JOptionPane.showMessageDialog(null, "Restaurante eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Restaurante no encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Eliminación cancelada.");
        }
    }

}
