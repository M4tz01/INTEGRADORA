package app;

import modelo.*;
import servicio.EmpleadoServicio;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EmpleadoServicio servicio = new EmpleadoServicio();

        int opcion = 0;

        do {

            System.out.println("===== CLINICA SALUD TOTAL =====");
            System.out.println("1. Registrar medico");
            System.out.println("2. Registrar administrativo");
            System.out.println("3. Mostrar empleados");
            System.out.println("4. Buscar por cedula");
            System.out.println("5. Eliminar registro");
            System.out.println("6. Salir");

            try {

                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {

                    case 1:

                        System.out.print("Cedula: ");
                        String cedula = sc.nextLine();

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Edad: ");
                        int edad = Integer.parseInt(sc.nextLine());

                        System.out.print("Telefono: ");
                        String telefono = sc.nextLine();

                        System.out.print("Correo: ");
                        String correo = sc.nextLine();

                        System.out.print("Especialidad: ");
                        String especialidad = sc.nextLine();

                        System.out.print("Pacientes atendidos: ");
                        int pacientes = Integer.parseInt(sc.nextLine());

                        System.out.print("Valor consulta: ");
                        double valorConsulta = Double.parseDouble(sc.nextLine());

                        Medico medico = new Medico(
                                cedula,
                                nombre,
                                edad,
                                telefono,
                                correo,
                                especialidad,
                                pacientes,
                                valorConsulta
                        );

                        servicio.agregarEmpleado(medico);

                        System.out.println("Medico registrado");
                        break;

                    case 2:

                        System.out.print("Cedula: ");
                        String cedulaA = sc.nextLine();

                        System.out.print("Nombre: ");
                        String nombreA = sc.nextLine();

                        System.out.print("Edad: ");
                        int edadA = Integer.parseInt(sc.nextLine());

                        System.out.print("Telefono: ");
                        String telefonoA = sc.nextLine();

                        System.out.print("Correo: ");
                        String correoA = sc.nextLine();

                        System.out.print("Departamento: ");
                        String departamento = sc.nextLine();

                        System.out.print("Horas trabajadas: ");
                        int horas = Integer.parseInt(sc.nextLine());

                        System.out.print("Valor hora: ");
                        double valorHora = Double.parseDouble(sc.nextLine());

                        Administrativo admin = new Administrativo(
                                cedulaA,
                                nombreA,
                                edadA,
                                telefonoA,
                                correoA,
                                departamento,
                                horas,
                                valorHora
                        );

                        servicio.agregarEmpleado(admin);

                        System.out.println("Administrativo registrado");
                        break;

                    case 3:

                        servicio.mostrarEmpleados();
                        break;

                    case 4:

                        System.out.print("Ingrese cedula: ");
                        String buscar = sc.nextLine();

                        Empleado encontrado = servicio.buscarPorCedula(buscar);

                        if (encontrado != null) {
                            encontrado.mostrarInformacion();
                        } else {
                            System.out.println("Registro no encontrado");
                        }

                        break;

                    case 5:

                        System.out.print("Cedula a eliminar: ");
                        String eliminar = sc.nextLine();

                        servicio.eliminarEmpleado(eliminar);

                        break;

                    case 6:

                        System.out.println("Saliendo...");
                        break;

                    default:

                        System.out.println("Opcion invalida");
                }

            } catch (Exception e) {

                System.out.println("Error en datos ingresados");
            }

        } while (opcion != 6);
    }
}