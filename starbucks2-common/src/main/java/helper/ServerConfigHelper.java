/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.Properties;

/**
 *
 * @author syle
 */
public class ServerConfigHelper {
    public static int getPort(){
        try{
            final int port = Integer.parseInt(System.getenv("PORT"));
            return port;
        } catch(Exception e){}

        return 8202;
    }

    public static String getHost(){
        return "localhost";
    }


    public static Properties getDatabaseConnectionProperties(){
        // get properties from string...
        String username = "";
        String password = "";
        String url = "";

        username = System.getenv("DB_USERNAME");
        password = System.getenv("DB_PASSWORD");
        url = System.getenv("DB_URL");

        Properties properties = new Properties();
        if(username != null && !username.isEmpty()){
            properties.setProperty("username", username);
        }

        if(password != null && !password.isEmpty()){
            properties.setProperty("password", password);
        }

        if(url != null && !url.isEmpty()){
            properties.setProperty("url", url);
        }

        return properties;
    }
}
