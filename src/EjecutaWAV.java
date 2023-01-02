import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Clase principal del proyecto. Lee un archivo <i>.txt</i> con los parámetros del
 * sonido a generar. Invoca al método <i>Escribe()</i> de la clase <i>GeneraWAV</i>, y le
 * envía los parámetros para generar el archivo <i>.wav</i>.
 * 
 * @see GeneraWAV#Escribe(String, int, int, int)
 */
public class EjecutaWAV {
    /**
     * Para ejecutar se usa el siguiente comando en consola (desde la raíz del
     * proyecto):
     * <ul>
     * <li><i>java -cp bin EjecutaWAV prueba.txt</i></li>
     * </ul>
     * 
     * <p>
     * El argumento <i>prueba.txt</i> puede variar, dependiendo del nombre que se eligió para el
     * archivo. 
     * 
     * <p>
     * El archivo de entrada <i>.txt</i> debe estar ubicado en la raíz del
     * proyecto. El archivo <i>.wav</i> generado se guardará en la raíz del proyecto.
     * 
     * <p>
     * El archivo <i>.txt</i> tiene el siguiente formato, donde cada argumento viene en una
     * línea del archivo:
     * <ul>
     * <li><b>Nombre del archivo a generar:</b> debe ser un nombre de archivo válido y terminar
     * con la extensión <i>.wav</i>. Valor por defecto: <i>Prueba.wav</i></li>
     * <li><b>Frecuencia de muestro:</b> expresada en Hertz. Valor por defecto: <i>22050</i></li>
     * <li><b>Numero de canales:</b> Mono = 1, Stereo = 2, etc. Valor por defecto: <i>1</i></li>
     * <li><b>Armonico:</b> frecuencia que se desea escuchar en el canal, expresado en Hertz.
     * Valor por defecto: <i>440</i> (Nota <i>LA</i>)</li>
     * <li><b>Tiempo:</b> Duración del sonido, expresado en segundos. Valor por defecto: <i>10</i></li>
     * </ul>
     * @param args Command-line arguments. <i>args[0]</i> debe corresponder al nombre del archivo <i>.txt</i>
     * @throws IllegalArgumentException Arrojada si se recibe un argumento no
     *                                  permitido (no numérico, a excepción del
     *                                  nombre del archivo, que se asume será
     *                                  válido).
     * @throws NullPointerException     Arrojada si se recibe un valor nulo del
     *                                  archivo (una linea en blanco).
     */
    public static void main(String[] args) throws IllegalArgumentException, NullPointerException {
        // Se maneja lectura de archivo con parametros para enviarlo a GeneraWAV
        try {

            FileReader fr = new FileReader(args[0]);
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
    } // Cierre del método
} // Cierre de la clase
