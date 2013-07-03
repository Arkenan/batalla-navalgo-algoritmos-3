package utilidades;

import java.io.File;
import java.util.ArrayList;

public class UtilidadesXML {
    public static ArrayList<String> obtenerNombres(String dirCarpeta) {
        ArrayList<String> listaNombres = new ArrayList<String>();
        // habría que ver de manejar errores aca.
        File folder = new File(dirCarpeta);
        System.out.println(folder.getName());
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            File archivoActual = listOfFiles[i];
            if (archivoActual.isFile()) {
                String extension = archivoActual.getName().split("\\.")[1];
                if (extension.equals("xml")) {
                    listaNombres.add(archivoActual.getName().split("\\.")[0]);
                }

            }
        }

        return listaNombres;

    }
}
