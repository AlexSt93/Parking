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
public class LeaveCommand implements ParkingCommand, Serializable{

    private Place place;
    private Parking parking;

    public LeaveCommand(Place place, Parking parking) {
        this.place = place;
        this.parking = parking;
    }

    @Override
    public Place execute() {
        System.out.println("LeaveCommand.EXECUTE");
        Place foundPlace = new Place();
        foundPlace = parking.carIsLeaving(place);
        return foundPlace;
    }

}
