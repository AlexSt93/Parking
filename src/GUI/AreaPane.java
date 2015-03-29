/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import javafx.scene.layout.Pane;

/**
 *
 * @author Александр
 */
public class AreaPane extends Pane{
    private String name;
    public AreaPane(String name){
        this.name = name;
        this.setPrefSize(640, 125);        
        this.setStyle("-fx-background-color: #d2e9ff");
        this.setLayoutX(45);        
    }
    @Override
    public boolean equals(Object obj){
        boolean res = false;
        try{
        AreaPane ap = (AreaPane)obj;  
        if (ap.getName().equals(this.name)){
            res = true;
        }
        }catch(Exception ex){
            
        }
        return res;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
