package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ClientPac.Client;
import ClientPac.ServerAnswThread;
import ParkingPac.Parking;
import ParkingPac.ParkingCommand;
import ParkingPac.Place;
import ParkingPac.SearchCommand;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import static javafx.scene.paint.Color.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Александр
 */
public class ViewController implements Initializable {

    /**
     * @return the parking
     */
    @FXML
    private Button bigCar;
    @FXML
    private Button smallCar;
    @FXML
    private Label placeScreen;
    @FXML
    private ScrollPane scrollPane;
   

    @FXML
    private AnchorPane viewPane;

    private Parking parking;
    private ObjectOutputStream out;
    //private String entryPoint;
    //private List<Rectangle> placeBoxes;
    private ObservableList<Pane> placeBoxes = FXCollections.observableArrayList();
    private ObservableList<AreaPane> areaPanes = FXCollections.observableArrayList();
    private ObservableList<EntryBox> entryBoxes = FXCollections.observableArrayList();
    private Thread serverAnswThread;
    private String entryPoint;
    private static ViewController instance;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getSmallCar().setDisable(true);
            getBigCar().setDisable(true);
            scrollPane.setContent(viewPane);
            instance = this;
            entryPoint = null;
            out = new ObjectOutputStream(Client.getSocket().getOutputStream());
            serverAnswThread = new ServerAnswThread(Client.getSocket(), instance);

            //viewPanel.setDisable(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void initializeParking() {

        if (areaPanes.isEmpty() && entryBoxes.isEmpty()) {
            int i = 0;
            double sizeViewPane = 0;
            int sizeOfAreas = parking.getSizeOfAreas()*27;
            for (String str : parking.getAreas()) {
                //System.out.println(parking.getSizeOfAreas()*27);
                AreaPane areaPane = new AreaPane(str);
                areaPane.setLayoutY(90 + 160 * i++);
                areaPane.setPrefWidth(sizeOfAreas);
                sizeViewPane = areaPane.getLayoutY();
                Label areaLabel = new Label(str);
                areaLabel.setLayoutX(areaPane.getLayoutX()+areaPane.getPrefWidth()+20);
                areaLabel.setLayoutY(areaPane.getLayoutY()+40);
                areaLabel.setPrefHeight(40);
                areaLabel.setPrefWidth(40);
                areaLabel.setTextAlignment(TextAlignment.CENTER);
                areaLabel.setFont(Font.font(25));
                areaLabel.setTextFill(Paint.valueOf("#4a7aa4"));
                viewPane.getChildren().add(areaLabel);
                areaPanes.add(areaPane);

            }
            for (String str : parking.getEntryPosition()) {
                EntryBox entryBox = new EntryBox(str, this);
                entryBox.setLayoutY(areaPanes.get(areaPanes.indexOf(new AreaPane(str))).getLayoutY());
                entryBoxes.add(entryBox);

            }
            viewPane.getChildren().addAll(areaPanes);
            viewPane.getChildren().addAll(entryBoxes);
            viewPane.setPrefHeight(sizeViewPane+250);
            viewPane.setPrefWidth(sizeOfAreas+90);
            
        }
        
        if (entryPoint != null) {
            showParking();
        }

    }

    public void showParking() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                //viewPane.getChildren().add(new Rectangle(100, 100));        
                viewPane.getChildren().removeAll(placeBoxes);
                placeBoxes.clear();
                //ArrayList<String> areas = getParking().getAreaPositions().get(entryPoint);

                for (AreaPane apane : areaPanes) {
                    ArrayList<Place> places = getParking().getPlaces().get(apane.getName());
                    if (places == null) {
                        continue;
                    }
                    double areaPositionX = apane.getLayoutX() + 20;
                    double areaPositionY = apane.getLayoutY() + 20;
                    for (Place p : places) {
                        Pane carBox = new CarBox(instance, p, areaPositionX, areaPositionY);
                        //Rectangle box = new Rectangle(20 + areaPositionX + (25 * position), areaPositionY + 20, p.getPlaceSize() * 20, 80);

                        placeBoxes.add(carBox);
                    }

                }
                viewPane.getChildren().addAll(placeBoxes);

            }
        });
    }

    @FXML
    private void newCar(ActionEvent event) throws IOException {
        int carSize = 0;
        if (event.getTarget().equals(getBigCar())) {
            carSize = 3;
        } else if (event.getTarget().equals(getSmallCar())) {
            carSize = 2;
        }
        ParkingCommand search = new SearchCommand(carSize, entryPoint, getParking());
        getOut().writeObject(search);
        getOut().flush();
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public String getEntryPoint() {
        return entryPoint;
    }

    /**
     * @return the placeScreen
     */
    public Label getPlaceScreen() {
        return placeScreen;
    }

    /**
     * @param placeScreen the placeScreen to set
     */
    public void setPlaceScreen(Label placeScreen) {
        this.placeScreen = placeScreen;
    }

    public Parking getParking() {
        return parking;
    }

    /**
     * @return the out
     */
    public ObjectOutputStream getOut() {
        return out;
    }

    /**
     * @return the areaPanes
     */
    public ObservableList<AreaPane> getAreaPanes() {
        return areaPanes;
    }

    /**
     * @return the entryBoxes
     */
    public ObservableList<EntryBox> getEntryBoxes() {
        return entryBoxes;
    }

    void setEntryPoint(String name) {
        this.entryPoint = name;
    }

    /**
     * @return the bigCar
     */
    public Button getBigCar() {
        return bigCar;
    }

    /**
     * @return the smallCar
     */
    public Button getSmallCar() {
        return smallCar;
    }
}
