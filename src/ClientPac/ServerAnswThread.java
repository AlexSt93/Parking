package ClientPac;

import GUI.*;
import ParkingPac.Parking;
import ParkingPac.Place;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class ServerAnswThread extends Thread {

    private Socket socket;

    public ServerAnswThread(Socket socket) {
        this.socket = socket;
        this.start();
    }

    @Override
    public void run() {
        ObjectInputStream in = Client.getIn();

        try {
            while (true) {
                Object obj = in.readObject();
                if (obj != null) {
                    if (obj instanceof Parking) {
                        Parking parking = (Parking) obj;
                        Client.setParking(parking);
                        //Application.launch(View.class);
//                        if (Client.getView()== null) {
//                            Client.setView(GUI.View.getInstance());
                            //Client.getView().launch(View.class);
                            //Application.launch(View.class);
                            //Client.setUserThread(new UserThread());
                        //}
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
                ex.printStackTrace();
            }
        }
    }
}