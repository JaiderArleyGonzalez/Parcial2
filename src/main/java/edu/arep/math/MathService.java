package edu.arep.math;
import edu.arep.proxy.HttpConnectionExample;

import static java.util.Arrays.copyOfRange;
import static spark.Spark.*;
import java.util.*;
public class MathService {
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
                return binarySearch(lista, value, 0, lista.length);
            }

        });
    }
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
        return "{\"operation\":\"linearSearch\",\"inputlist\":\""+ list+"\", \"value\":\"13\",\"output\":\""+ output +"\"}";
    }

    public static String binarySearch(String[] lista, String value, int inicio, int fin){
        String list = "";
        if (list == ""){
            for(int i = 0; i < lista.length; i++){
                if(i != lista.length - 1){
                    list += lista[i] +",";
                }else {
                    list += lista[i];
                }
            }
        }


        int medio = (fin - inicio) / 2;
        if(lista[medio].equals(value)){
            String output = "" + medio;
            return "{\"operation\":\"binarySearch\",\"inputlist\":\""+ list+"\", \"value\":\"13\",\"output\":\""+ output +"\"}";
        }
        if(Integer.parseInt(lista[medio]) > Integer.parseInt(value)){
            binarySearch(lista, value, medio, lista.length);
        }else{

            binarySearch(lista, value, 0, medio);
        }
        return "{\"operation\":\"binarySearch\",\"inputlist\":\""+ list+"\", \"value\":\"13\",\"output\":\"-1\"}";
    }
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000;
    }
}
