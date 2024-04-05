package edu.arep.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Esta clase proporciona métodos para realizar conexiones HTTP GET.
 */
public class HttpConnectionExample {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static int rr = 1;

    /**
     * Realiza una solicitud GET a un servidor HTTP especificado.
     *
     * @param PORT  El puerto en el que se encuentra el servidor.
     * @param path  La ruta del recurso al que se realizará la solicitud.
     * @param query La cadena de consulta que se adjuntará a la URL.
     * @return La respuesta del servidor como una cadena.
     * @throws IOException Si ocurre un error de E/S durante la conexión.
     */
    public static String Invoke(String PORT, String path, String query) throws IOException{
        String GET_URL;
        if(rr == 1){
            GET_URL = "http://ec2-107-22-103-142.compute-1.amazonaws.com:";
            rr = 2;
        }else{
            GET_URL = "http://ec2-54-204-126-36.compute-1.amazonaws.com:";
            rr = 1;
        }

        GET_URL = GET_URL + PORT + path + query;
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();

        } else {
            return "GET request not worked";
        }


    }

}