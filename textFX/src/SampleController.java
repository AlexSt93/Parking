/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SampleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tf1;
    @FXML
    private Circle cir1;
    @FXML
    private Polygon p1;
    private Point basePoint;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        basePoint = new Point(100, 100);
        p1= new Polygon();
//        but1.(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent t) {
//                cir1.setRadius(Double.parseDouble((tf1.getPromptText())));
//            }
//
//        });

        tf1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int n = 3;
                cir1.setRadius(Double.parseDouble((tf1.getText())));
                p1.getPoints().addAll(calcVert(n));
            }
        });
    }

    private Double[] calcVert(int n) {
        Double[] res = new Double[]{
            //        100.0,100.0,
            //        200.0, 200.0,
            //        100.0, 400.0,
            //        0.0,200.0
            0.0, 0.0,
            20.0, 10.0,
            10.0, 20.0
        };
        return res;
    }
//    private Double[] calcVert(int n) {
//        Double[] result = new Double[n*2];
//        result[0] = 0.0+basePoint.getX();
//        result[1] = -50+basePoint.getY();
//        for (int i = 2; i < n*2; i+=2) {
//            result[i] = basePoint.getX() + (result[i - 2] + Math.cos(Math.PI / n));
//            result[i+1] = basePoint.getY() + (result[i - 3] + Math.sin(Math.PI / n));
//        }
//        return result;
//    }

}
