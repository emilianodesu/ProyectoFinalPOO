import java.io.File;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class GeneraWAV {

    private short numCanales;

    public GeneraWAV(short numCanales) {
        this.numCanales = numCanales;
    }

    public void Escribe(String name, int iTiempo, int iFrecuenciaMuestreo, int armonico) throws IOException {
        try {

            File archivo = new File(name);
            archivo.createNewFile();
            DataOutputStream salida = new DataOutputStream(new FileOutputStream(name));

            // Estructura

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

            double angulo = (armonico * Math.PI * bytesShort) / iFrecuenciaMuestreo;
            int amplitud = 32000; //?????
            int muestra;

            for (int i = 0; i < iNumMuestras; i++) {
                muestra = (int) Math.floor(amplitud * Math.sin(angulo * i));
                salida.write(convertEndianndessInt(muestra), 0, bytesInt);
            }
            salida.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static byte[] convertEndianndessShort(short valor) {
        byte[] resultado;
        byte b0 = (byte) (valor & 0xFF);
        byte b1 = (byte) ((valor >> 8) & 0xFF);
        resultado = new byte[] { b0, b1 };
        return resultado;
    }

    private static byte[] convertEndianndessInt(long valor) {
        byte[] resultado;
        byte b0 = (byte) (valor & 0xFF);
        byte b1 = (byte) ((valor >> 8) & 0xFF);
        byte b2 = (byte) ((valor >> 16) & 0xFF);
        byte b3 = (byte) ((valor >> 24) & 0xFF);
        resultado = new byte[] { b0, b1, b2, b3 };
        return resultado;
    }
}