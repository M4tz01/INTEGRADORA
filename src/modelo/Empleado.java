package modelo;
    abstract class Empleado {
    private String cedula;
    private String nombre;
    private int edad;
    private String telefono;
    private String correo;

    public Empleado(String cedula, String nombre, int edad, String telefono, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void mostrarInformacion() {
        System.out.println("Cédula: " + cedula);
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Correo: " + correo);
    }

    public abstract double calcularPago();
}
```

        ---

        # src/modelo/Medico.java

```java
package modelo;

public class Medico extends Empleado {

    private String especialidad;
    private int numeroPacientesAtendidos;
    private double valorConsulta;

    public Medico(String cedula, String nombre, int edad, String telefono, String correo,
                  String especialidad, int numeroPacientesAtendidos, double valorConsulta) {

        super(cedula, nombre, edad, telefono, correo);
        this.especialidad = especialidad;
        this.numeroPacientesAtendidos = numeroPacientesAtendidos;
        this.valorConsulta = valorConsulta;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getNumeroPacientesAtendidos() {
        return numeroPacientesAtendidos;
    }

    public void setNumeroPacientesAtendidos(int numeroPacientesAtendidos) {
        this.numeroPacientesAtendidos = numeroPacientesAtendidos;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    @Override
    public double calcularPago() {
        return numeroPacientesAtendidos * valorConsulta;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Pacientes atendidos: " + numeroPacientesAtendidos);
        System.out.println("Valor consulta: " + valorConsulta);
        System.out.println("Pago: " + calcularPago());
        System.out.println("--------------------------");
    }
}
```

        ---

        # src/modelo/Administrativo.java

```java
package modelo;

public class Administrativo extends Empleado {

    private String departamento;
    private int horasTrabajadas;
    private double valorHora;

    public Administrativo(String cedula, String nombre, int edad, String telefono, String correo,
                          String departamento, int horasTrabajadas, double valorHora) {

        super(cedula, nombre, edad, telefono, correo);
        this.departamento = departamento;
        this.horasTrabajadas = horasTrabajadas;
        this.valorHora = valorHora;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public double calcularPago() {
        return horasTrabajadas * valorHora;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Departamento: " + departamento);
        System.out.println("Horas trabajadas: " + horasTrabajadas);
        System.out.println("Valor hora: " + valorHora);
        System.out.println("Pago: " + calcularPago());
        System.out.println("--------------------------");
    }
}
```

        ---

        # src/util/Validador.java

```java
package util;

public class Validador {

    public static boolean textoVacio(String texto) {
        return texto.trim().isEmpty();
    }

    public static boolean validarCorreo(String correo) {
        return correo.contains("@") && correo.contains(".");
    }

    public static boolean validarTelefono(String telefono) {
        return telefono.matches("\\d+");
    }
}
```

        ---

        # src/servicio/EmpleadoServicio.java

```java
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

    public void mostrarEstadisticas() {

        int totalMedicos = 0;
        int totalAdministrativos = 0;

        double pagoMedicos = 0;
        double pagoAdministrativos = 0;

        Empleado mayorIngreso = null;

        for (Empleado e : empleados) {

            if (e instanceof Medico) {
                totalMedicos++;
                pagoMedicos += e.calcularPago();
            }

            if (e instanceof Administrativo) {
                totalAdministrativos++;
                pagoAdministrativos += e.calcularPago();
            }

            if (mayorIngreso == null || e.calcularPago() > mayorIngreso.calcularPago()) {
                mayorIngreso = e;
            }
        }

        System.out.println("Total médicos: " + totalMedicos);
        System.out.println("Total administrativos: " + totalAdministrativos);
        System.out.println("Total empleados: " + empleados.size());
        System.out.println("Pago total médicos: " + pagoMedicos);
        System.out.println("Pago total administrativos: " + pagoAdministrativos);

        if (mayorIngreso != null) {
            System.out.println("Empleado con mayor ingreso: " + mayorIngreso.getNombre());
            System.out.println("Pago: " + mayorIngreso.calcularPago());
        }
    }
}
```

        ---

        # src/app/Main.java

```java
package app;

import modelo.*;
        import servicio.EmpleadoServicio;
import util.Validador;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmpleadoServicio servicio = new EmpleadoServicio();

        int opcion = 0;

        do {

            System.out.println("===== CLÍNICA SALUD TOTAL =====");
            System.out.println("1. Registrar médico");
            System.out.println("2. Registrar administrativo");
            System.out.println("3. Mostrar empleados");
            System.out.println("4. Buscar por cédula");
            System.out.println("5. Reemplazar información");
            System.out.println("6. Eliminar registro");
            System.out.println("7. Calcular pagos");
            System.out.println("8. Mostrar estadísticas");
            System.out.println("9. Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                if (opcion < 1 || opcion > 9) {
                    System.out.println("Error: opción inválida.");
                    continue;
                }

            } catch (Exception e) {
                System.out.println("Error: opción inválida.");
                continue;
            }

            switch (opcion) {

                case 1:

                    try {

                        System.out.print("Cédula: ");
                        String cedula = sc.nextLine();

                        if (servicio.cedulaExiste(cedula)) {
                            System.out.println("Cédula duplicada.");
                            break;
                        }

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        if (Validador.textoVacio(nombre)) {
                            System.out.println("Nombre vacío.");
                            break;
                        }

                        System.out.print("Edad: ");
                        int edad = Integer.parseInt(sc.nextLine());

                        if (edad <= 0 || edad > 150) {
                            System.out.println("Edad inválida.");
                            break;
                        }

                        System.out.print("Teléfono: ");
                        String telefono = sc.nextLine();

                        if (!Validador.validarTelefono(telefono)) {
                            System.out.println("Teléfono inválido.");
                            break;
                        }

                        System.out.print("Correo: ");
                        String correo = sc.nextLine();

                        if (!Validador.validarCorreo(correo)) {
                            System.out.println("Correo inválido.");
                            break;
                        }

                        System.out.print("Especialidad: ");
                        String especialidad = sc.nextLine();

                        if (Validador.textoVacio(especialidad)) {
                            System.out.println("Especialidad vacía.");
                            break;
                        }

                        System.out.print("Pacientes atendidos: ");
                        int pacientes = Integer.parseInt(sc.nextLine());

                        if (pacientes <= 0) {
                            System.out.println("Valor inválido.");
                            break;
                        }

                        System.out.print("Valor consulta: ");
                        double valorConsulta = Double.parseDouble(sc.nextLine());

                        if (valorConsulta <= 0) {
                            System.out.println("Valor inválido.");
                            break;
                        }

                        Medico medico = new Medico(cedula, nombre, edad, telefono, correo,
                                especialidad, pacientes, valorConsulta);

                        servicio.agregarEmpleado(medico);

                        System.out.println("Médico registrado correctamente.");

                    } catch (Exception e) {
                        System.out.println("Error en los datos ingresados.");
                    }

                    break;

                case 2:

                    try {

                        System.out.print("Cédula: ");
                        String cedula = sc.nextLine();

                        if (servicio.cedulaExiste(cedula)) {
                            System.out.println("Cédula duplicada.");
                            break;
                        }

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        if (Validador.textoVacio(nombre)) {
                            System.out.println("Nombre vacío.");
                            break;
                        }

                        System.out.print("Edad: ");
                        int edad = Integer.parseInt(sc.nextLine());

                        if (edad <= 0 || edad > 150) {
                            System.out.println("Edad inválida.");
                            break;
                        }

                        System.out.print("Teléfono: ");
                        String telefono = sc.nextLine();

                        if (!Validador.validarTelefono(telefono)) {
                            System.out.println("Teléfono inválido.");
                            break;
                        }

                        System.out.print("Correo: ");
                        String correo = sc.nextLine();

                        if (!Validador.validarCorreo(correo)) {
                            System.out.println("Correo inválido.");
                            break;
                        }

                        System.out.print("Departamento: ");
                        String departamento = sc.nextLine();

                        if (Validador.textoVacio(departamento)) {
                            System.out.println("Departamento vacío.");
                            break;
                        }

                        System.out.print("Horas trabajadas: ");
                        int horas = Integer.parseInt(sc.nextLine());

                        if (horas <= 0) {
                            System.out.println("Valor inválido.");
                            break;
                        }

                        System.out.print("Valor por hora: ");
                        double valorHora = Double.parseDouble(sc.nextLine());

                        if (valorHora <= 0) {
                            System.out.println("Valor inválido.");
                            break;
                        }

                        Administrativo administrativo = new Administrativo(cedula, nombre,
                                edad, telefono, correo, departamento, horas, valorHora);

                        servicio.agregarEmpleado(administrativo);

                        System.out.println("Administrativo registrado correctamente.");

                    } catch (Exception e) {
                        System.out.println("Error en los datos ingresados.");
                    }

                    break;

                case 3:
                    servicio.mostrarEmpleados();
                    break;

                case 4:

                    System.out.print("Ingrese cédula: ");
                    String cedulaBuscar = sc.nextLine();

                    Empleado encontrado = servicio.buscarPorCedula(cedulaBuscar);

                    if (encontrado != null) {
                        encontrado.mostrarInformacion();
                    } else {
                        System.out.println("Registro no encontrado.");
                    }

                    break;

                case 5:

                    System.out.print("Ingrese cédula a reemplazar: ");
                    String cedulaReemplazo = sc.nextLine();

                    Empleado emp = servicio.buscarPorCedula(cedulaReemplazo);

                    if (emp != null) {

                        System.out.print("Nuevo nombre: ");
                        emp.setNombre(sc.nextLine());

                        System.out.print("Nuevo correo: ");
                        emp.setCorreo(sc.nextLine());

                        System.out.println("Información actualizada.");

                    } else {
                        System.out.println("Registro no encontrado.");
                    }

                    break;

                case 6:

                    System.out.print("Ingrese cédula a eliminar: ");
                    String cedulaEliminar = sc.nextLine();

                    if (servicio.eliminarEmpleado(cedulaEliminar)) {
                        System.out.println("Registro eliminado.");
                    } else {
                        System.out.println("Registro no encontrado.");
                    }

                    break;

                case 7:

                    for (Empleado e : servicio.getEmpleados()) {
                        System.out.println(e.getNombre() + " -> Pago: " + e.calcularPago());
                    }

                    break;

                case 8:
                    servicio.mostrarEstadisticas();
                    break;

                case 9:
                    System.out.println("Saliendo del sistema...");
                    break;
            }

        } while (opcion != 9);
    }
}
```

        ---

        # Estructura de carpetas

```text
src/
        │
        ├── modelo/
        │   ├── Empleado.java
│   ├── Medico.java
│   └── Administrativo.java
│
        ├── servicio/
        │   └── EmpleadoServicio.java
│
        ├── util/
        │   └── Validador.java
│
        └── app/
        └── Main.java
```

