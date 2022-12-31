import java.io.FileReader;
import java.io.BufferedReader;

public class EjecutaWAV {
    public static void main(String[] args) throws IllegalArgumentException, NullPointerException {
        // Se maneja lectura de archivo con parametros para enviarlo a GeneraWAV
        try {
            FileReader fr = new FileReader("Prueba.txt");
            BufferedReader br = new BufferedReader(fr);
            String name = br.readLine();
            int iFrecuenciaMuestreo = Integer.parseInt(br.readLine());
            int numCanales = Integer.parseInt(br.readLine());
            int armonico = Integer.parseInt(br.readLine());
            int iTiempo = Integer.parseInt(br.readLine());
            br.close();
            GeneraWAV generador = new GeneraWAV(numCanales);
            generador.Escribe(name, iTiempo, iFrecuenciaMuestreo, armonico);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        // Se establece valor de armonico de 326767
    }
}
