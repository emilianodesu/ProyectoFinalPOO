import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class EscucharWAV {
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
    }
}
