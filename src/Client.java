import java.io.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Александр
 */
public class Client {

    /**
     * @param aParking the parking to set
     */
    private static Socket socket;
    private static Thread userThread;
    private static Thread serverAnswThread;
    private static Parking parking;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        serverAnswThread = new ServerAnswThread(this.socket);
    }

    public static void main(String[] args) throws IOException {

        try {

            Socket s = new Socket("localhost", Server.PORT);//CONNECT TO THE SERVER
            //s.setSoTimeout(10000);
            Client client = new Client(s);//START NEW CLIENT OBJECT

        } catch (Exception noServer)//IF DIDNT CONNECT PRINT THAT THEY DIDNT
        {
            System.out.println("The server might not be up at this time.");
            System.out.println("Please try again later.");
        }

    }

    /**
     * @return the parking
     */
    public static synchronized Parking getParking() {
        return parking;
    }

    public static synchronized void setParking(Parking parking) {
        Client.parking = parking;
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
        userThread = aUserThread;
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
        serverAnswThread = aServerAnswThread;
    }

    /**
     * @return the in
     */
    public static synchronized ObjectInputStream getIn() {
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
