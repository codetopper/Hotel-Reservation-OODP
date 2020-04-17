package guest_entity;
import java.util.*;

public class guestManager {
	
/*defining an arraylist of guest class*/	
public static ArrayList<guest>arrayListOfGuest;

/*default constructor*/
public guestManager()
{
		arrayListOfGuest=new ArrayList<guest>();
	}
	
/*declaring the attributes*/
String name;
String identity;
String contact;
String add;
String card;
String gender;
int genderOption;
String country;
String nationality;
String invalid = "0123456789+-*/()[]{}";


/*creating guest details*/	
/*We take the input from the user for the corresponding fields*/
/*if the input is empty, we output "sorry input not entered"*/
/*if the input is invalid, we output "invalid input"*/
/*else, we set the value of the input using guest object g*/
public void createGuest()
{
	/*to get input*/
	Scanner sc = new Scanner(System.in);
	
	/*guest object*/
	guest g = new guest();
	
	
	/*takes input for the identity*/
	System.out.println("Please enter your identity!");
	identity=sc.nextLine();
	
	
	for(guest guest: arrayListOfGuest) {
		if(guest.getId() == identity) {
			System.out.println("Already exists");
			createGuest();
		}
	}
	if(identity.isEmpty())
	{
		System.out.println("Sorry! No identity is entered");
	}
	else
	{
		g.setId(identity);
	}
	
	/*takes input for the name*/
	System.out.println("Please enter your name!");
	name=sc.nextLine();
	
	if(name.isEmpty())
	{
		System.out.println("Sorry! No name is entered");
	}
	else if(name.contains(invalid))
	{
		System.out.println("Invalid name.");
	}
	else
	{
		g.setName(name);
	}
	
	/*takes input for the address*/
	System.out.println("Please enter your address!");
	add=sc.nextLine();
	
	if(add.isEmpty())
	{
		System.out.println("Sorry! No address is entered");
	}
	
	else
	{
		g.setAddress(add);
	}
	
	/*takes input for the contact number*/
	System.out.println("Please enter your contact number-mobile number or home number!");
	contact=sc.nextLine();
	
	if(contact.isEmpty())
	{
		System.out.println("Sorry! No number is entered");
	}
	else
	{
		g.setContact(contact);
	}
	
	/*takes input for the credit card number*/
	System.out.println("Please enter your credit card number!");
	card=sc.nextLine();
	
	if(card.isEmpty())
	{
		System.out.println("Sorry! No credit card number is entered");
	}
	else
	{
		g.setCreditCard(card);
	}
	
	/*takes input for the country*/
	System.out.println("Please enter your country!");
	country=sc.nextLine();
	
	if(country.isEmpty())
	{
		System.out.println("Sorry! No country is entered");
	}
	else if(country.contains(invalid))
	{
		System.out.println("Invalid country.");
	}
	else
	{
		g.setCountry(country);
	}
	
	/*takes input for the nationality*/
	System.out.println("Please enter your nationality!");
	nationality=sc.nextLine();
	
	if(nationality.isEmpty())
	{
		System.out.println("Sorry! No nationality is entered");
	}
	else if(nationality.contains(invalid))
	{
		System.out.println("Invalid nationality.");
	}
	else
	{
		g.setNationality(nationality);
	}
	
	/*takes input for the gender*/
	System.out.println("Please select gender-(1)male (2)female (3)others!");
	genderOption =Integer.parseInt(sc.nextLine());
	
	if(genderOption==1)
	{
		g.setGender("Male");
	}
	else if (genderOption==2)
	{
		g.setGender("Female");
	}
	else if(genderOption==3)
	{
		g.setGender("Others");
	}
	else
	{
		System.out.println("Invalid gender");
	}
	
	/*adding the details of the guest to the arraylist*/
	arrayListOfGuest.add(g);
	
		
}//end of createGuest
	

/*updating guest details*/
public void updateGuest()
{	
	
	guest g = new guest();
	Scanner sc = new Scanner(System.in);
	System.out.println("Please enter your identity");
	String givenId = sc.nextLine();
	for(guest guest: arrayListOfGuest) {
		if(givenId.equals(guest.getId())) {
			System.out.println("Please select the option you want to update");
			System.out.println("1.name");
			System.out.println("2.country");
			System.out.println("3.address");
			System.out.println("4.nationality");
			System.out.println("5.contact number");
			System.out.println("6.gender");
			System.out.println("7.credit card number");
			System.out.println("8.identity");
			g = guest;
			break;
		} 		
	}

	
	int choice;
	choice=Integer.parseInt(sc.nextLine());
	switch(choice)
	{
	/*update name*/
	case 1:
		System.out.println("Previous name: "+g.getName());
		System.out.println("Please enter new name");
		name=sc.nextLine();
		
		if(name.isEmpty())
		{
			System.out.println("Sorry! No name is entered");
		}
		else if(name.equals(g.getName()))
		{
			System.out.println("Previous name and new name are the same");
		}
		else
		{
			g.setName(name);
		}
		break;
		
		/*update country*/
	case 2:
		System.out.println("Previous country: "+g.getCountry());
		System.out.println("Please enter new country");
		
		String country=sc.nextLine();
		System.out.println("The country is being printed " + country);
		
		if(country.isEmpty())
		{
			System.out.println("Sorry! No country is entered");
		}
		else if(country.equals(g.getCountry()))
		{
			System.out.println("Previous country and new country are the same");
		}
		else
		{
			g.setCountry(country);
		}
		break;
		
		/*update address*/
	case 3:
		System.out.println("Previous address: "+g.getAddress());
		System.out.println("Please enter new address");
		add=sc.nextLine();
		
		if(add.isEmpty())
		{
			System.out.println("Sorry! No address is entered");
		}
		else if(add.equals(g.getAddress()))
		{
			System.out.println("Previous address and new address is the same");
		}
		else
		{
			g.setAddress(add);
		}
		break;
		
		/*update nationality*/
	case 4:
		System.out.println("Previous nationality: "+g.getNationality());
		System.out.println("Please enter new nationality");
		nationality=sc.nextLine();
		
		if(nationality.isEmpty())
		{
			System.out.println("Sorry! No nationality is entered");
		}
		else if(nationality.equals(g.getNationality()))
		{
			System.out.println("Previous nationality and new nationality is the same");
		}
		else
		{
			g.setNationality(nationality);
		}
		break;
		
		/*update contact number*/
		case 5:
			System.out.println("Previous contact number "+g.getContact());
			System.out.println("Please enter new contact number");
			contact=sc.nextLine();
			
			if(contact.isEmpty())
			{
				System.out.println("Sorry! No contact number is entered");
			}
			else if(contact.equals(g.getContact()))
			{
				System.out.println("Previous contact number and new contact numbers is the same");
			}
			else
			{
				g.setContact(contact);
			}
			break;
			
			/*update gender*/
		case 6:
			System.out.println("Previous gender: "+g.getGender());
			System.out.println("Please enter new gender");
			gender=sc.nextLine();
			
			if(gender.isEmpty())
			{
				System.out.println("Sorry! No gender is entered");
			}
			else if(gender.equals(g.getGender()))
			{
				System.out.println("Previous gender and new gender is the same");
			}
			else
			{
				g.setGender(gender);
			}
			break;
			
			/*update credit card number*/
		case 7:
			System.out.println("Previous credit card number "+g.getCreditCard());
			System.out.println("Please enter new credit card number");
			card=sc.nextLine();
			
			if(card.isEmpty())
			{
				System.out.println("Sorry! No credit card number is entered");
			}
			else if(card.equals(g.getCreditCard()))
			{
				System.out.println("Previous credit card number and new credit card number is the same");
			}
			else
			{
				g.setCreditCard(card);
			}
			break;
			
			/*update identity*/
		case 8:
			System.out.println("Previous identity number: "+g.getId());
			System.out.println("Please enter new identity");
			identity=sc.nextLine();
			
			if(identity.isEmpty())
			{
				System.out.println("Sorry! No identity is entered");
			}
			else if(identity.equals(g.getId()))
			{
				System.out.println("Previous name and new identities are the same");
			}
			else
			{
				g.setId(identity);
			}
			break;
		
	}
	
}//end of class

/*if the identity is found, we print 'found' and then print the guest details*/
/*if identity is not found,we print 'not found'*/
/*searching guest details*/
public void searchGuest(String identity) {
	
	for (guest guest: arrayListOfGuest)
	{
		if(identity.equals(guest.getId())) 
		{
			System.out.print("Found\n");
			guest.print();
			return;
		}
		
	}
		System.out.print("Not found!");
}
	
}
