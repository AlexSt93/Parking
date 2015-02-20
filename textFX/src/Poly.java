
import java.awt.Point;
import javafx.scene.shape.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class Poly extends Polygon{
    private Point basePoint;
    
   
    public Poly(double[] vertices, Point basePoint){
        super(vertices);
        
        
    }
    
    private double[] calcVertices(Point basePoint, int countPoints, int size){
        double[] vertices = new double[countPoints*2];
        return vertices;
        
    }
    
}
