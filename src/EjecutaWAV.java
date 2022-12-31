import java.io.FileReader;
import java.io.BufferedReader;

public class EjecutaWAV {
    public static void main(String[] args) throws IllegalArgumentException, NullPointerException {
        // Se maneja lectura de archivo con parametros para enviarlo a GeneraWAV
        try {
            FileReader fr = new FileReader("Prueba.txt");
            BufferedReader br = new BufferedReader(fr);

            String name = br.readLine();
            if (name.equals("")) {
                br.close();
                throw new NullPointerException("Valor nulo para el nombre del archivo.");
            }

            String iFrecuenciaMuestreoString = br.readLine();
            if (iFrecuenciaMuestreoString.equals("")) {
                br.close();
                throw new NullPointerException("Valor nulo para la frecuencia de muestreo.");
            }
            for (int i = 0; i < iFrecuenciaMuestreoString.length(); i++) {
                if (!Character.isDigit(iFrecuenciaMuestreoString.charAt(i))) {
                    br.close();
                    throw new IllegalArgumentException("Argumento no permitido para la frecuencia de muestreo.");
                }
            }
            int iFrecuenciaMuestreo = Integer.parseInt(iFrecuenciaMuestreoString);

            String numCanalesString = br.readLine();
            if (numCanalesString.equals("")) {
                br.close();
                throw new NullPointerException("Valor nulo para el numero de canales.");
            }
            for (int i = 0; i < numCanalesString.length(); i++) {
                if (!Character.isDigit(numCanalesString.charAt(i))) {
                    br.close();
                    throw new IllegalArgumentException("Argumento no permitido para el numero de canales.");
                }
            }
            short numCanales = Short.parseShort(numCanalesString);

            String armonicoString = br.readLine();
            if (armonicoString.equals("")) {
                br.close();
                throw new NullPointerException("Valor nulo para la frecuencia del armonico.");
            }
            for (int i = 0; i < armonicoString.length(); i++) {
                if (!Character.isDigit(armonicoString.charAt(i))) {
                    br.close();
                    throw new IllegalArgumentException("Argumento no permitido para la frecuencia del armonico.");
                }
            }
            int armonico = Integer.parseInt(armonicoString);

            String iTiempoString = br.readLine();
            if (iTiempoString == null) {
                br.close();
                throw new NullPointerException("Valor nulo para la duracion del sonido.");
            }
            for (int i = 0; i < iTiempoString.length(); i++) {
                if (!Character.isDigit(iTiempoString.charAt(i))) {
                    br.close();
                    throw new IllegalArgumentException("Argumento no permitido para la duracion del sonido.");
                }
            }
            int iTiempo = Integer.parseInt(iTiempoString);
            br.close();
            GeneraWAV generador = new GeneraWAV(numCanales);
            generador.Escribe(name, iTiempo, iFrecuenciaMuestreo, armonico);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Se establece valor de armonico de 326767
    }
}
