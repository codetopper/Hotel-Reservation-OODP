package boundary_classes;

import control_classes.CheckOut;
import main.MainBoundary;

import java.util.Scanner;

public class CheckOutBoundary {

    Scanner sc = MainBoundary.scanner;
    CheckOut checkOut = new CheckOut();

    public void display() {
        int option = -1;

        while (!(option == 0)) {
            // display menu
            System.out.println("===== Check Out Menu");
            System.out.println("");
            System.out.println("0. Back to Main Menu");
            System.out.println("=====");

        }
    }
}
