package app;

import hotel.Hotel;

public class HRPS {

    public static void main(String[] args) {

        //Load data
        DataController dataController = new DataController();
        Hotel hotel = dataController.loadHotel();
        //Load data

        // What is this doing?
        //reset data
        boolean reset = false;
        if(reset) {
            hotel = new Hotel();
        }
        //reset data

        //main application
        AppBoundary appBoundary = new AppBoundary();
        appBoundary.start(hotel);
        //main application

        //Save data
        dataController.serializeObject(hotel, "hotel.ser");
        //Save data
    }
}
