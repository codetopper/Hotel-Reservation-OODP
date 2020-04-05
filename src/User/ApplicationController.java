package User;

import Hotel.Hotel;

public class ApplicationController {

    static DataController dataController;
    public static Hotel hotel;

    public static void main(String[] args) {

        //Load data
        dataController = new DataController();
        hotel = dataController.loadHotel();
        //Load data

        //reset data
        boolean reset = false;
        if(reset) {
            hotel = new Hotel();
        }
        //reset data

        //main application
        ApplicationBoundary applicationBoundary = new ApplicationBoundary();
        applicationBoundary.enterInterface();
        //main application

        //Save data
        dataController.serializeObject(hotel, "hotel.ser");
        //Save data
    }
}
