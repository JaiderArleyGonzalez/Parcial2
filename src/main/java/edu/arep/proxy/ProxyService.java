package edu.arep.proxy;
import static spark.Spark.*;

/**
 * Esta clase actúa como un proxy para los servicios de búsqueda lineal y búsqueda binaria.
 * Se comunica con el servicio MathService para realizar las búsquedas.
 */
public class ProxyService {
    public static String PORT = "36000";

    /**
     * Método principal para iniciar el servidor proxy.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String... args){
        port(getPort());

        staticFiles.location("/public");
        get("hello", (req,res) -> "Hello Docker!");
        get("/linearsearch", (req, res) -> {
            String lista = req.queryParams("list");
            String value = req.queryParams("value");
            String query = "?list="+lista+"&value="+value;
            return HttpConnectionExample.Invoke(PORT, "/linearsearch", query);
        });
        get("/binarysearch", (req, res) -> {
            String lista = req.queryParams("list");
            String value = req.queryParams("value");
            String query = "?list="+lista+"&value="+value;
            return HttpConnectionExample.Invoke(PORT, "/binarysearch", query);
        });
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
        return 8080;
    }
}
