package servicio;

import modelo.*;
import java.util.ArrayList;

public class EmpleadoServicio {

    private ArrayList<Empleado> empleados = new ArrayList<>();

    public boolean cedulaExiste(String cedula) {
        for (Empleado e : empleados) {
            if (e.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void mostrarEmpleados() {

        if (empleados.isEmpty()) {
            System.out.println("No existen registros.");
            return;
        }

        for (Empleado e : empleados) {
            e.mostrarInformacion();
        }
    }

    public Empleado buscarPorCedula(String cedula) {

        for (Empleado e : empleados) {
            if (e.getCedula().equals(cedula)) {
                return e;
            }
        }

        return null;
    }

    public boolean eliminarEmpleado(String cedula) {

        Empleado empleado = buscarPorCedula(cedula);

        if (empleado != null) {
            empleados.remove(empleado);
            return true;
        }

        return false;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}
