package modelo;

public class Medico extends Empleado {

    private String especialidad;
    private int numeroPacientesAtendidos;
    private double valorConsulta;

    public Medico(String cedula, String nombre, int edad, String telefono,
                  String correo, String especialidad,
                  int numeroPacientesAtendidos, double valorConsulta) {

        super(cedula, nombre, edad, telefono, correo);

        this.especialidad = especialidad;
        this.numeroPacientesAtendidos = numeroPacientesAtendidos;
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
        System.out.println("Pacientes: " + numeroPacientesAtendidos);
        System.out.println("Valor consulta: " + valorConsulta);
        System.out.println("Pago: " + calcularPago());
    }
}