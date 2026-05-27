package servicio;

import modelo.*;
import java.util.ArrayList;

public class EmpleadoServicio {

    private ArrayList<Empleado> empleados = new ArrayList<>();

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void mostrarEmpleados() {

        for (Empleado e : empleados) {
            e.mostrarInformacion();
            System.out.println("----------------");
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

    public void eliminarEmpleado(String cedula) {

        Empleado e = buscarPorCedula(cedula);

        if (e != null) {
            empleados.remove(e);
            System.out.println("Registro eliminado");
        } else {
            System.out.println("Registro no encontrado");
        }
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}
