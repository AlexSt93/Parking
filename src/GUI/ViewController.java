package GUI;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ParkingPac.Parking;
import ParkingPac.Place;
import ClientPac.Client;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Александр
 */
public class ViewController implements Initializable {
    @FXML
    private Rectangle AreaA;
    @FXML
    private Rectangle AreaB;
    @FXML
    private Rectangle AreaC;
    private Parking parking;
    private ObjectOutputStream out;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        out = Client.getOut();
        ArrayList<Place> places = parking.getPlaces().get("A");
        for (Place p : places){
            int position = p.getPlacePosition();
            double areaPositionX = AreaA.getLayoutX();
            double areaPositionY = AreaA.getLayoutY();
            Rectangle box = new Rectangle(areaPositionX + position, areaPositionY);
            box.setWidth(p.getPlaceSize()*10);
        }
    }    
    
}
