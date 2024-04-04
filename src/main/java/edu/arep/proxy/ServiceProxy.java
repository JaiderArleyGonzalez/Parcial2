package edu.arep.proxy;
import static spark.Spark.*;
public class ServiceProxy {
    public static String PORT = "36000";
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

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}
