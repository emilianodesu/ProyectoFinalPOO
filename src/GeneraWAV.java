public class GeneraWAV {

    private short numCanales;

    public GeneraWAV(short numCanales) {
        this.numCanales = numCanales;
    }

    public void Escribe(String name, int iTiempo, int iFrecuenciaMuestreo, int armonico) {
        System.out.println(numCanales);
        System.out.println(name);
        System.out.println(iTiempo);
        System.out.println(iFrecuenciaMuestreo);
        System.out.println(armonico);
    }
}