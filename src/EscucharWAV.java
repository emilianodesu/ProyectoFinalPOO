import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Esta clase sirve para escuchar un sonido de un archivo <i>.wav</i> de 10 segundos.
 * El código fue proporcionado por el profesor. Para generar el archivo <i>.wav</i> se
 * tiene que ejecutar primero la clase <i>EjecutaWAV</i>.
 * 
 * @see EjecutaWAV
 */
public class EscucharWAV {
    /**
     * Para ejecutar se utiliza el siguiente comando en terminal (desde la raíz del proyecto):
     * <ul>
     * <li><i>java -cp bin EscucharWAV Prueba.wav</i></li>
     * </ul>
     * 
     * <p>
     * El argumento <i>Prueba.wav</i> puede variar, dependiendo del nombre que se eligió para el
     * archivo.
     * 
     * @param args Command-line arguments. <i>args[0]</i> debe corresponder al nombre del archivo <i>.wav</i> que se va a leer.
     */
    public static void main(String[] args) {
        try {
            Clip sonido = AudioSystem.getClip();
            File a = new File(args[0]);
            sonido.open(AudioSystem.getAudioInputStream(a));
            sonido.start();
            System.out.println("Reproduciendo 10s de sonido...");
            Thread.sleep(10000);
            sonido.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    } // Cierre del método
} // Cierre de la clase
