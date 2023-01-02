import java.io.File;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

/**
 * Esta clase es utilizada desde la clase principal del proyecto, <i>EjecutaWAV</i>.
 * Contiene los métodos necesarios para crear y escribir en un archivo con
 * extensión <i>.wav</i>.
 * 
 * @author Chong Hernández Samuel
 * @author López Campillo Francisco Manuel
 * @author Mendoza Hernández Carlos Emiliano
 * @author Roa Díaz Vanessa
 * 
 * @see EjecutaWAV
 */
public class GeneraWAV {
    // Atributo
    /**
     * Representa el número de canales de los audios generados con esta instancia.
     * Por convención en esta práctica, este método puede ser omitido si se
     * considera únicamente un solo canal (mono), sin embargo se implementó para
     * hacer posible la codificación de audio de más de un canal.
     */
    private short numCanales;

    // Constructor
    /**
     * Crea una instancia de la clase <i>GeneraWAV</i>, la cual contiene el método
     * <i>Escribe()</i> para crear y escribir en un archivo con extensión <i>.wav</i>.
     * 
     * @param numCanales Número de canales que tendrán los audios generados con esta
     *                   instancia. Mono = 1, Stereo = 2, etc.
     */
    public GeneraWAV(short numCanales) {
        this.numCanales = numCanales;
    } // Cierre del constructor

    /**
     * Este método se invoca desde la clase principal del proyecto, <i>EjecutaWAV</i>. En
     * este método se crea un archivo con el nombre especificado (<i>Prueba.wav</i>),
     * se genera el encabezado del archivo <i>.wav</i>, se genera una señal senoidal con
     * los parámetros especificados, y se escriben los bytes en el archivo <i>.wav</i>. El
     * archivo <i>.wav</i> es creado en la ruta principal del proyecto.
     * <p>
     * Utiliza los métodos internos <i>convertEndiannesShort()</i> y <i>convertEndiannessInt()</i>
     * para convertir los
     * ipos de datos <i>short</i> e <i>int</i> a arreglos de bytes en little endian.
     * 
     * @param name                Nombre del archivo a crear.
     * @param iTiempo             Segundos de duración del sonido a generar.
     * @param iFrecuenciaMuestreo Frecuencia del muestreo.
     * @param armonico            Frecuencia de la señal a generar.
     * @throws IOException Si existen problemas en la creación del archivo o en su
     *                     escritura.
     * 
     * @see <a href=
     *      "https://www.fatalerrors.org/a/detailed-explanation-of-wav-file-format.html">Estructura
     *      de un archivo WAVE</a>
     */
    public void Escribe(String name, int iTiempo, int iFrecuenciaMuestreo, int armonico) throws IOException {
        try {

            // Creación del archivo
            File archivo = new File(name);
            archivo.createNewFile();
            DataOutputStream salida = new DataOutputStream(new FileOutputStream(name));

            // Variables para el archivo
            final short bytesInt = 4;
            final short bytesShort = 2;
            final short restodebytes = 36;
            final String Riff = "RIFF";
            final String Wave = "WAVEfmt ";
            final String Data = "data";

            long Formato = 16;
            short Bytes_m = (short) (numCanales * Formato / 8);
            short Bits_m = (short) Formato;
            int iNumMuestras = iTiempo * iFrecuenciaMuestreo;
            long Tamanio = iNumMuestras * Bytes_m + restodebytes;

            short PCM = 1;
            int F_muestreo = (int) (iFrecuenciaMuestreo * numCanales * Formato / 8);
            int BytesArchivo = iNumMuestras * numCanales * Bytes_m;

            // Escritura
            // Header chunk
            salida.writeBytes(Riff);
            salida.write(convertEndianndessInt((int) Tamanio), 0, bytesInt);
            salida.writeBytes(Wave);

            // Format chunk
            salida.write(convertEndianndessInt((int) Formato), 0, bytesInt);
            salida.write(convertEndianndessShort(PCM), 0, bytesShort);
            salida.write(convertEndianndessShort(numCanales), 0, bytesShort);
            salida.write(convertEndianndessInt(iFrecuenciaMuestreo), 0, bytesInt);
            salida.write(convertEndianndessInt(F_muestreo), 0, bytesInt);
            salida.write(convertEndianndessShort(Bytes_m), 0, bytesShort);
            salida.write(convertEndianndessShort(Bits_m), 0, bytesShort);

            // Data chunk
            salida.writeBytes(Data);
            salida.write(convertEndianndessInt(BytesArchivo), 0, bytesInt);

            // Generando señal senoidal
            double angulo = (armonico * Math.PI * bytesShort) / iFrecuenciaMuestreo;
            int amplitud = 10000; // ?????
            int muestra;

            for (int i = 0; i < iNumMuestras; i++) {
                muestra = (int) Math.floor(amplitud * Math.sin(angulo * i));
                salida.write(convertEndianndessInt(muestra), 0, bytesInt);
            }
            salida.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    } // Cierre del método

    /**
     * Método interno que convierte un tipo de dato <i>short</i> de big endian a little
     * endian.
     * 
     * @param valor Valor de tipo <i>short</i> a convertir.
     * @return Cadena de bytes invertidos a little endian.
     */
    private static byte[] convertEndianndessShort(short valor) {
        byte[] resultado;
        byte b0 = (byte) (valor & 0xFF);
        byte b1 = (byte) ((valor >> 8) & 0xFF);
        resultado = new byte[] { b0, b1 };
        return resultado;
    } // Cierre del método

    /**
     * Método interno que convierte un tipo de dato <i>int</i> de big endian a little
     * endian.
     * 
     * @param valor Valor de tipo <i>int</i> a convertir.
     * @return Cadena de bytes invertidos a little endian.
     */
    private static byte[] convertEndianndessInt(long valor) {
        byte[] resultado;
        byte b0 = (byte) (valor & 0xFF);
        byte b1 = (byte) ((valor >> 8) & 0xFF);
        byte b2 = (byte) ((valor >> 16) & 0xFF);
        byte b3 = (byte) ((valor >> 24) & 0xFF);
        resultado = new byte[] { b0, b1, b2, b3 };
        return resultado;
    } // Cierre del método
} // Cierre de la clase