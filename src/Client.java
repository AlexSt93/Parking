
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
public class Client implements Runnable {

    private Socket socket;
    private Parking parking;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {

        try {

            Socket s = new Socket("localhost", Server.PORT);//CONNECT TO THE SERVER
            //s.setSoTimeout(10000);
            Client client = new Client(s);//START NEW CLIENT OBJECT
            Thread t = new Thread(client);//INITIATE NEW THREAD
            t.start();//START THREAD
        } catch (Exception noServer)//IF DIDNT CONNECT PRINT THAT THEY DIDNT
        {
            System.out.println("The server might not be up at this time.");
            System.out.println("Please try again later.");
        }

    }

    @Override
    public void run() {
        Thread getsObjects;
        getsObjects = new Thread(new Runnable() {

            @Override
            public void run() {

                ObjectInputStream in = null;
                try {
                    in = new ObjectInputStream(socket.getInputStream());
                    while (true) {

                        Object obj = in.readObject();
                        if (obj != null) {
                            if (obj instanceof Parking) {
                                parking = (Parking) obj;
                                if (!parking.isEmpty() == false) {
                                    System.out.println("Parking changed1");
                                }
                            } else if (obj instanceof Place) {
                                Place place = (Place) obj;

                                System.out.println(place.toString());
                            }
                        }

                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
        );
        getsObjects.start();

        Scanner inKey = new Scanner(System.in);
        String s;

        try {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            boolean flag = true;
            while (flag) {

                System.out.println("Enter number:");
                System.out.println("1 - Car coming");
                System.out.println("2 - Car is leaving");
                System.out.println("3 - Show parking");
                System.out.println("4 - Exit is coming");
                System.out.println("---------");
                int key = inKey.nextInt();
                inKey.reset();
                switch (key) {
                    case 1:
                        System.out.println("2/3?");

                        int carSize = inKey.nextInt();
                        if (carSize == 2 || carSize == 3) {
                            //out.println("search " + car);
                            ParkingCommand search = new SearchCommand(carSize, parking);
                            out.writeObject(search);
                            out.flush();
                        }
                        inKey.reset();
                        break;
                    case 2:
                        System.out.println("Place?");
                        String strPlace = inKey.next();
                        ParkingCommand leave = new LeaveCommand(new Place(strPlace.substring(0, 1), Integer.parseInt(strPlace.substring(1))), parking);
                        out.writeObject(leave);
                        out.flush();

                        inKey.reset();
                        break;
                    case 3:
                        System.out.println("Parking: ");
                        parking.showParking();
                        inKey.reset();
                        break;
                    case 4:
                        System.out.println("Exit");
                        inKey.reset();
                        flag = false;
                        break;

                }

            }
        } catch (IOException ex) {
            System.err.println("I/O exception");
            ex.printStackTrace();

            //} catch (ClassNotFoundException ex) {
            // ex.printStackTrace();
        } finally {
            try {
                socket.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

}
