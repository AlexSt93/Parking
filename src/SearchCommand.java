
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
public class SearchCommand implements ParkingCommand,Serializable {

    private int carSize;
    private Parking parking;
    private String entryPoint;

    public SearchCommand(int carSize, String entryPoint, Parking parking) {
        this.carSize = carSize;
        this.parking = parking;
        this.entryPoint = entryPoint;
    }

    @Override
    public Place execute() {
        Place foundPlace = new Place();
        System.out.println("SearchCommand.EXECUTE");
        foundPlace = parking.searchPlace(carSize, entryPoint);
        return foundPlace;
    }

}
