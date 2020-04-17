package guest;

import java.util.Scanner;

public class guestBoundary {
	
	public static void main(String args[])
	{
		
		guestControl gm= new guestControl();
		Scanner sc = new Scanner(System.in);
		int x = -1;
		
		
		while(x !=10) {
			System.out.println("Enter choice\n 1.create\n 2.update\n 3.search");
			x= Integer.parseInt(sc.nextLine());
		/*to create guest details*/
		if(x==1)
		{
			System.out.println("Create guest details");
			gm.createGuest();
		}
		/*to update guest details*/
		if(x==2)
		{
			System.out.println("Update guest details");
			gm.updateGuest();
		}
		/*to search guest details*/
		if(x==3)
		{
			System.out.println("Update guest details");
			String id;
			System.out.print("Please enter your identity number");
			id=sc.nextLine();
			gm.searchGuest(id);
		}
		}
		
		sc.close();
	}

	
}
