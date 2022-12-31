import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class GeneraWAV {

    private short numCanales;

    public GeneraWAV(short numCanales) {
        this.numCanales = numCanales;
    }

    public void Escribe(String name, int iTiempo, int iFrecuenciaMuestreo, int armonico) throws IOException{
        try {
            File archivo = new File(name);
            archivo.createNewFile();
            FileWriter fw = new FileWriter(name);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            // Escritura
            salida.println(numCanales);
            // ...
            salida.close();
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}