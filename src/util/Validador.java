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