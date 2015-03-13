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
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
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
    @FXML
    private Rectangle topPoint;
    @FXML
    private Rectangle botPoint;
    @FXML
    private AnchorPane viewPanel;

    private Parking parking;
    private ObjectOutputStream out;
    private String entryPoint;
    private List<Rectangle> placeBoxes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        placeBoxes = new ArrayList<>();
        viewPanel.setDisable(true);
        parking = Client.getParking();
        out = Client.getOut();
        while (parking==null){
            parking = Client.getParking();
        }
        viewPanel.setDisable(false);
        
    }

    public void showParking() {
        System.out.println("Parking: ");
        ArrayList<String> areas = parking.getAreaList().get(entryPoint);
        for (String a : areas) {
            ArrayList<Place> places = parking.getPlaces().get(a);
            Rectangle areaBox=null;
            if (a.equals("A")) {
                areaBox = AreaA;
            } else if (a.equals("B")) {
                areaBox = AreaB;
            } else if (a.equals("C")) {
                areaBox = AreaC;
            }
            for (Place p : places) {
                int position = p.getPlacePosition();
                double areaPositionX = areaBox.getX();
                double areaPositionY = areaBox.getY();
                Rectangle box = new Rectangle(areaPositionX + (position * 10), areaPositionY);
                box.setWidth(p.getPlaceSize() * 10);
                placeBoxes.add(box);
            }
            viewPanel.getChildren().addAll(placeBoxes);
        }

    }

    @FXML
    void selectEntryPoint(MouseEvent event) {
        if (event.getTarget().equals(topPoint)) {
            entryPoint = "top";
            botPoint.setVisible(false);
        } else if (event.getTarget().equals(botPoint)) {
            entryPoint = "bot";
            topPoint.setVisible(false);
        }
        //viewPanel.setDisable(false);
        showParking();
    }

}
