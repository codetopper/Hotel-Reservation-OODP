public class Application {

    static DataController dataController;
    static Hotel hotel;

    public static void main (String[] args) {

        //Load data
        dataController = new DataController();
        hotel = dataController.loadHotel();
        //Load data

        //main application

        //main application

        //Save data
        dataController.serializeObject(hotel, "hotel.ser");
        //Save data
    }
}
