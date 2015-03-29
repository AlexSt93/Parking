/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Александр
 */
public class EntryBox extends Rectangle {

    String name;
    private ViewController vCntrl;

    public EntryBox(String name, ViewController vCntrl) {
        this.name = name;
        this.vCntrl = vCntrl;

        this.setHeight(125);
        this.setWidth(10);
        this.setFill(Color.web("#cea975"));
        //this.setStyle("-fx-background-color: #cea975;");
        this.setLayoutX(15);
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                selectEntryPoint(t);
            }
        });
    }

    private void selectEntryPoint(MouseEvent event) {
        if (event.getTarget().equals(this)) {
            for (EntryBox box : vCntrl.getEntryBoxes()) {
                if (!box.equals(this)) {
                    box.setVisible(false);
                    this.setDisable(true);
                }
            }
        }
        vCntrl.getSmallCar().setDisable(false);
        vCntrl.getBigCar().setDisable(false);
        vCntrl.setEntryPoint(this.name);
        vCntrl.showParking();

    }

}
