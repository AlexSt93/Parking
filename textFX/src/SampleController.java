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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SampleController implements Initializable {
    
    private static Point basePoint = new Point(100,100) ;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tf1;
    @FXML
    private AnchorPane viewPanel;
    @FXML
    private Polygon p1;
    @FXML
    private Circle cir1;
    
    private Polygon p;
    private Line l;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        p = new Poly(calcVertices(basePoint, 4, 80),basePoint);
        p.setStroke(Color.BLACK);
        p.setFill(Color.WHITE);
        viewPanel.getChildren().add(p);
        l = new Line(50, 100, 160, 100);
        viewPanel.getChildren().add(l);
        System.out.println(p.intersects(l.getBoundsInLocal()));
        
      
        
    }
    public double[] calcVertices(Point basePoint,int countPoints, int size){
        double[] vertices = new double[countPoints*2];
        Point start = new Point (0,size);
        
        double angle = 2* Math.PI/countPoints;
        
        int j = 0;
        for (int i=0; i<countPoints; i++){
            double X = Math.cos(angle*i) * start.getX() - Math.sin(angle*i) * start.getY();
            double Y = Math.cos(angle*i) * start.getY() - Math.sin(angle*i) * start.getX();
            vertices[j++] = X+basePoint.getX();
            vertices[j++] = Y+basePoint.getY();
        }
        return vertices;
    }

    

}
