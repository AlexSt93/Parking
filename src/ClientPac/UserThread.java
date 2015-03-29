package ClientPac;



import ParkingPac.SearchCommand;
import ParkingPac.ParkingCommand;
import ParkingPac.LeaveCommand;
import ParkingPac.Place;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import static javafx.application.Application.launch;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class UserThread extends Thread {

    public UserThread() {
        this.start();
    }

    @Override
    public void run() {
        Scanner inKey = new Scanner(System.in);
        String s;
        ObjectOutputStream out = Client.getOut();
        

        try {
            boolean flag = true;
            while (flag) {

                if (Client.getEntryPoint() == null) {
                    System.out.println("Input entry point (top/bot):");
                    String entryPoint = inKey.next();
                    Client.setEntryPoint(entryPoint);
                    inKey.reset();
                }
                System.out.println("Enter number:");
                System.out.println("1 - Car coming");
                System.out.println("2 - Car is leaving");
                System.out.println("3 - Show parking");
                System.out.println("4 - Exit");
                System.out.println("---------");
                int key = inKey.nextInt();
                inKey.reset();
                switch (key) {
                    case 1:
                        System.out.println("2/3?");

                        int carSize = inKey.nextInt();
                        if (carSize == 2 || carSize == 3) {
                            //out.println("search " + car);
                            ParkingCommand search = new SearchCommand(carSize, Client.getEntryPoint(), Client.getParking());
                            out.writeObject(search);
                            out.flush();
                        }
                        inKey.reset();
                        break;
                    case 2:
                        System.out.println("Place?");
                        String strPlace = inKey.next();
                        Place leavePlace = new Place(strPlace.substring(0, 1), Integer.parseInt(strPlace.substring(1)));
                        ParkingCommand leave = new LeaveCommand(leavePlace, Client.getParking());
                        out.writeObject(leave);
                        out.flush();
                        inKey.reset();
                        break;
                    case 3:
                        System.out.println("Parking: ");
                        //Client.getParking().showParking(Client.getEntryPoint());
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
        } finally {
            try {
                out.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }
}
