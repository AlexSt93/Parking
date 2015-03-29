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
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

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
    private ViewController vContrl;
    private ObjectInputStream in;
    private Parking parking;

    public ServerAnswThread(Socket socket, ViewController vContrl) throws IOException {
        this.socket = socket;
        this.vContrl = vContrl;
        this.in = new ObjectInputStream(Client.getSocket().getInputStream());
        this.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object obj = in.readObject();
                if (obj != null) {
                    if (obj instanceof Parking) {
                        parking = (Parking) obj;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                vContrl.setParking(parking);
                                Object obj = vContrl.getEntryPoint();
                                vContrl.initializeParking();

                            }
                        });

                    } else if (obj instanceof Place) {
                        final Place place = (Place) obj;

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                vContrl.getPlaceScreen().setText(place.toString());
                                vContrl.getPlaceScreen().setAlignment(Pos.CENTER);

                                System.out.println(place.toString());
                            }
                        });
                        vContrl.showParking();
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
