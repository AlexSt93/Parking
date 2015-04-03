package Resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String PROPERTIES_FILE = "./src/resources/server.config";

    public static int PORT;
    public static String URL;
    public static String LOGIN;
    public static String PASSWORD;
    public static int NUMBER_OF_AREAS;
    public static int SIZE_OF_AREAS;
    public static int NUMBER_OF_ENTRIES;
    public static String[] ENTRY_POSITION;

    private static Config instance;

    private Config() {
    	URL="jdbc:mysql://localhost:3306/parking";
    		PORT=8080;
    		LOGIN="root";
    		PASSWORD="root";
    		NUMBER_OF_AREAS=4;
    		SIZE_OF_AREAS=24;
    		NUMBER_OF_ENTRIES=5;
    		ENTRY_POSITION= new String[1];
    		ENTRY_POSITION[0] = "A";
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public void initialize(String configPath) {
    	System.out.println(configPath);
        Properties properties = new Properties();
        FileInputStream propertiesFile = null;

        try {
            propertiesFile = new FileInputStream(configPath);
            properties.load(propertiesFile);

            PORT = Integer.parseInt(properties.getProperty("PORT"));
            URL = properties.getProperty("URL");
            LOGIN = properties.getProperty("LOGIN");
            PASSWORD = properties.getProperty("PASSWORD");
            NUMBER_OF_AREAS = Integer.parseInt(properties.getProperty("NUMBER_OF_AREAS"));
            SIZE_OF_AREAS = Integer.parseInt(properties.getProperty("SIZE_OF_AREAS"));
            NUMBER_OF_ENTRIES = Integer.parseInt(properties.getProperty("NUMBER_OF_ENTRIES"));
            ENTRY_POSITION = properties.getProperty("ENTRY_POSITION").split(",");
            
        } catch (FileNotFoundException ex) {
            System.err.println("Properties config file not found");
        } catch (IOException ex) {
            System.err.println("Error while reading file");
        } finally {
            try {
                propertiesFile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
