/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apisvideo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Eli
 */
public class PeticionesHttp {

    private final String USER_AGENT = "Mozilla/5.0";

    public ArrayList<String> metodoGet(String servicio, String busca) throws Exception {
        ArrayList<String> respuesta_array = new ArrayList<>();
        String url;
        if (servicio.equalsIgnoreCase("Youtube")) {
           // busca = busca.concat(" 360") ;
          //  System.out.println(busca);
            url = "https://www.googleapis.com/youtube/v3/search?q=" + busca +"&part=snippet&maxResults=10&search_sort=video_avg_rating&regionCode=MX&key=AIzaSyCR5In4DZaTP6IEZQ0r1JceuvluJRzQNLE";
        } else {
            // DailyMotion
            url = "https://api.dailymotion.com/videos?360_degree=1&search=" + busca;
        }
        // Conexion
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //Metodo GET
        con.setRequestMethod("GET");
        //add request header
      //  con.setRequestProperty("User-Agent", USER_AGENT);
      //  con.setRequestProperty("Accept", "application/json");
        int codigo_respuesta = con.getResponseCode();
        System.out.println("Enviando 'GET' a : " + url);
        System.out.println("Codigo de respuesta : " + codigo_respuesta);
        StringBuilder respuesta;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String linea_salida;
            respuesta = new StringBuilder();
            while ((linea_salida = in.readLine()) != null) {
                respuesta.append(linea_salida);
                respuesta_array.add(linea_salida);
            }
        }
        //Respuesta
        
        System.out.println("Respuesta"+respuesta.toString());
        return respuesta_array;
    }

    public static void main(String[] args) throws Exception {
        PeticionesHttp obj = new PeticionesHttp();
        System.out.println("Respuesta del metodo "+obj.metodoGet("youtube", "China"));
    }
}
