package edu.arep.math;
import static spark.Spark.*;

/**
 * Esta clase proporciona servicios matemáticos, incluyendo búsqueda lineal y búsqueda binaria.
 */
public class MathService {

    /**
     * Método principal para iniciar el servidor.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String... args){
        port(getPort());
        staticFiles.location("/public");
        get("/linearsearch", (req, res) -> {
            String[] lista = req.queryParams("list").split(",");
            String value = req.queryParams("value");
            if(lista == null || value == null ){
                return "ingrese correctamente los parámetros";
            }else{
                return linearSearch(lista, value);
            }

        });
        get("/binarysearch", (req, res) -> {
            String[] lista = req.queryParams("list").split(",");
            String value = req.queryParams("value");
            if(lista == null || value == null ){
                return "ingrese correctamente los parámetros";
            }else{
                return binarySearch(lista, value, 0, lista.length - 1);
            }

        });
    }

    /**
     * Realiza una búsqueda lineal en la lista dada.
     *
     * @param lista La lista en la que se realizará la búsqueda.
     * @param value El valor que se está buscando.
     * @return Una cadena JSON que contiene el resultado de la búsqueda.
     */
    public static String linearSearch(String[] lista, String value){
        String list = "";
        String output = "-1";
        for(int i = 0; i < lista.length; i++){
            if(i != lista.length - 1){
                list += lista[i] +",";
            }else{
                list += lista[i];
            }
            if(lista[i].equals(value)){
                output = ""+i;
            }
        }
        return "{\"operation\":\"linearSearch\",\"inputlist\":\""+ list+"\", \"value\":\""+value+"\",\"output\":\""+ output +"\"}";
    }

    /**
     * Realiza una búsqueda binaria en la lista dada.
     *
     * @param lista  La lista en la que se realizará la búsqueda.
     * @param value  El valor que se está buscando.
     * @param inicio El índice inicial para la búsqueda.
     * @param fin    El índice final para la búsqueda.
     * @return Una cadena JSON que contiene el resultado de la búsqueda.
     */
    public static String binarySearch(String[] lista, String value, int inicio, int fin) {
        String list = "";
        for (int i = 0; i < lista.length; i++) {
            if (i != lista.length - 1) {
                list += lista[i] + ",";
            } else {
                list += lista[i];
            }
        }

        if (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;

            if (Integer.parseInt(lista[medio]) == Integer.parseInt(value)) {
                String output = "" + medio;
                return "{\"operation\":\"binarySearch\",\"inputlist\":\"" + list + "\", \"value\":\"" + value + "\",\"output\":\"" + output + "\"}";
            }

            if (Integer.parseInt(lista[medio]) < Integer.parseInt(value)) {
                return binarySearch(lista, value, medio + 1, fin);
            } else {
                return binarySearch(lista, value, inicio, medio - 1);
            }
        }

        return "{\"operation\":\"binarySearch\",\"inputlist\":\"" + list + "\", \"value\":\"" + value + "\",\"output\":\"-1\"}";
    }

    /**
     * Obtiene el puerto del entorno o utiliza un puerto predeterminado si no está configurado.
     *
     * @return El puerto para iniciar el servidor.
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000;
    }
}
