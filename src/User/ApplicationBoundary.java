package User;

import java.util.Scanner;

public class ApplicationBoundary {

    Scanner sc = new Scanner(System.in);

    public void enterInterface() {
        int choice = -1;
        while (choice != 5) { //to be changed
            System.out.println("Welcome, please select an option:\n");
            System.out.println("1.\tFind vacant rooms.");
            System.out.println("2.\tView room status report.");
            System.out.println("5.\tQuit");//to be changed

            choice = getIntegerOnly("Option: ", 1, 5);//to be changed
            //tbc
        }
    }

    protected int getIntegerOnly(String message, int low_inclusive, int high_inclusive) {
        int choice;
        System.out.println(message);
        try
        {
            choice = sc.nextInt();
            if (choice<low_inclusive || choice > high_inclusive)
            {
                System.out.printf("Out of bounds. Please enter an integer from %d to %d.", low_inclusive, high_inclusive);
                choice = getIntegerOnly(message, low_inclusive, high_inclusive);
            }
        }
        catch(Exception e)
        {
            System.out.printf("Only integers are accepted. Please enter an integer from %d to %d.", low_inclusive, high_inclusive);
            sc.next();
            choice = getIntegerOnly(message, low_inclusive, high_inclusive);
        }
        return choice;
    }
}

