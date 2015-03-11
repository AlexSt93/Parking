import java.io.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Александр
 */
public class Client{

    /**
     * @return the entryPoint
     */
    public static String getEntryPoint() {
        return entryPoint;
    }

    /**
     * @param aEntryPoint the entryPoint to set
     */
    public static void setEntryPoint(String aEntryPoint) {
        entryPoint = aEntryPoint;
    }

    /**
     * @param aParking the parking to set
     */
    private Socket socket;
    private static Thread userThread;
    private static Thread serverAnswThread;
    private static Parking parking;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    private static String entryPoint;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.serverAnswThread = new ServerAnswThread(this.socket);
        this.entryPoint = null;
    }

    public static void main(String[] args) throws IOException {

        try {

            Socket s = new Socket("localhost", Config.PORT);//CONNECT TO THE SERVER
            //s.setSoTimeout(10000);
            Client client = new Client(s);//START NEW CLIENT OBJECT
            

        } catch (Exception noServer)//IF DIDNT CONNECT PRINT THAT THEY DIDNT
        {
            System.out.println("The server might not be up at this time.");
            System.out.println("Please try again later.");
        }finally{
            in.close();
            out.close();            
        }
        

    }

    /**
     * @return the parking
     */
    public synchronized static Parking getParking() {
        return parking;
    }

    public synchronized static void setParking(Parking aParking) {
        Client.parking = aParking;
    }

    /**
     * @return the userThread
     */
    public synchronized static Thread getUserThread() {
        return userThread;
    }

    /**
     * @param aUserThread the userThread to set
     */
    public synchronized static void setUserThread(Thread aUserThread) {
        Client.userThread = aUserThread;
    }

    /**
     * @return the serverAnswThread
     */
    public synchronized static Thread getServerAnswThread() {
        return serverAnswThread;
    }

    /**
     * @param aServerAnswThread the serverAnswThread to set
     */
    public synchronized static void setServerAnswThread(Thread aServerAnswThread) {
        Client.serverAnswThread = aServerAnswThread;
    }

    /**
     * @return the in
     */
    public synchronized static ObjectInputStream getIn() {
        return in;
    }

    /**
     * @param in the in to set
     */
    public static void setIn(ObjectInputStream in) {
        Client.in = in;
    }

    /**
     * @return the out
     */
    public static ObjectOutputStream getOut() {
        return out;
    }

    /**
     * @param out the out to set
     */
    public static void setOut(ObjectOutputStream out) {
        Client.out = out;
    }

    
}
