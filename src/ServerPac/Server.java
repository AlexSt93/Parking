package ServerPac;

import Resources.Config;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class Server {

    static public DBConnection con;
    private static final int idPark = 1;
    private static UserList list = new UserList();
    private static Config config;

    public static void main(String[] args) throws IOException, SQLException {
        new Server(args);
    }
    public Server(String[] args) throws SQLException {
        this.config = Config.getInstance();
        if (args.length>0){
        	config.initialize(args[0]);
        }
        
        this.con =  DBConnection.getInsance();
        
        try {
            ServerSocket socketListener = new ServerSocket(Config.PORT);
            while (true) {
                Socket client = null;
                client = socketListener.accept();                
                System.out.println("Client " + client.toString() + "connected");
                new ClientThread(client);
            }
        } catch (SocketException e) {
            System.err.println("Socket exception");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("I/O exception");
            e.printStackTrace();
        }
        
    }

    public synchronized static UserList getUserList() {
        return list;
    }    
}
