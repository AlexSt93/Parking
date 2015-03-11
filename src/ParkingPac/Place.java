package ParkingPac;




import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Александр
 */
public class Place implements Serializable {
    private int id;
    private int status; // 0 - free, 1 - reserve
    private String areaName;
    private int areaRange;
    private int placePosition;
    private int placeSize;
    private int parking_id;
    public Place(int id, int status, String areaName, int areaRange,  int placeSize, int placePosition, int parking_id){
        this.id = id;
        this.status = status;
        this.areaName = areaName;
        
        this.areaRange = areaRange;
        this.placeSize = placeSize;
        this.placePosition = placePosition;
        this.parking_id = parking_id;
        
    }
    @Override
    public String toString(){
        String res = "Place: "+this.areaName+" "+this.placePosition;
        return res;
    }
    public Place(String areaName){
        this.id = 0;
        this.areaName = areaName;
    }
    public Place(){
        this.id = 0;
        
    }
    public Place(String areaName, int placePosition){
        this.areaName = areaName;
        this.placePosition = placePosition;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName the areaName to set
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * @return the areaRange
     */
    public int getAreaRange() {
        return areaRange;
    }

    /**
     * @param areaRange the areaRange to set
     */
    public void setAreaRange(int areaRange) {
        this.areaRange = areaRange;
    }

    /**
     * @return the placePosition
     */
    public int getPlacePosition() {
        return placePosition;
    }

    /**
     * @param placePosition the placePosition to set
     */
    public void setPlacePosition(int placePosition) {
        this.placePosition = placePosition;
    }

    /**
     * @return the placeSize
     */
    public int getPlaceSize() {
        return placeSize;
    }

    /**
     * @param placeSize the placeSize to set
     */
    public void setPlaceSize(int placeSize) {
        this.placeSize = placeSize;
    }

    /**
     * @return the parking_id
     */
    public int getParking_id() {
        return parking_id;
    }

    /**
     * @param parking_id the parking_id to set
     */
    public void setParking_id(int parking_id) {
        this.parking_id = parking_id;
    }


}
