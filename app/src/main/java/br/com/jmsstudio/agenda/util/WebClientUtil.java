package br.com.jmsstudio.agenda.util;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by jms on 28/12/16.
 */
public class WebClientUtil {
    public static String sendJsonData(String jsonData) {
        String response = null;
        try {
            URL url = new URL("https://www.caelum.com.br/mobile");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");

            connection.setDoOutput(true);
            connection.setDoInput(true);

            //escreve no outputstream
            PrintStream saida = new PrintStream(connection.getOutputStream());
            saida.println(jsonData);

            //realiza conexao
            connection.connect();

            response = new Scanner(connection.getInputStream()).next();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
