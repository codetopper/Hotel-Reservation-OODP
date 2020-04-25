package guest;
import java.util.*;

public class GuestControl {
	
	GuestDAO dao = new GuestDAO();

	/*declaring the attributes*/
	String invalid = "0123456789+-*/()[]{}";

	/*creating Guest details*/
	/*We take the input from the user for the corresponding fields*/
	/*if the input is empty, we output "sorry input not entered"*/
	/*if the input is invalid, we output "invalid input"*/
	/*else, we set the value of the input using Guest object g*/
	public String createGuest(String identity, String name, String addr, String contact, String card, String country, String nationality, int genderOption) {

		//validation
		if (genderOption != 1 && genderOption != 2 && genderOption != 3)
		{
			return "Invalid gender.  Guest is not created. Please enter 1, 2 or 3.";
		}
		if(name.contains(invalid))
		{
			return "Invalid name. Guest is not created. Please use alphabets only.";
		}
		if(country.contains(invalid))
		{
			return "Invalid country. Guest is not created. Please use alphabets only.";
		}
		if(nationality.contains(invalid))
		{
			return "Invalid nationality. Guest is not created. Please use alphabets only.";
		}

		ArrayList<Guest> guests = dao.getAllItem();
		Guest g = new Guest();

		for(Guest guest: guests) {
			if(guest.getId() == identity) {
				return "Guest already exists";
			}
		}

		g.setId(identity);
		g.setName(name);
		g.setAddress(addr);
		g.setContact(contact);
		g.setCreditCard(card);
		g.setCountry(country);
		g.setNationality(nationality);
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

		/*adding the details of the Guest to the arraylist*/
		dao.add(g);
		return "Guest is created!";

	}//end of createGuest


	/*updating Guest details*/
	public String updateGuest(String givenId, int choice, String input)
	{
		Guest g = dao.getItemById(givenId);
		switch(choice)
		{
			/*update name*/
			case 1:

				if(input.isEmpty())
				{
					return "Sorry! No name is entered";
				}
				else if(input.equals(g.getName()))
				{
					return "Previous name and new name are the same";
				}
				else
				{
					g.setName(input);
					break;
				}

			/*update country*/
			case 2:

				if(input.isEmpty())
				{
					return "Sorry! No country is entered";
				}
				else if(input.equals(g.getCountry()))
				{
					return "Previous country and new country are the same";
				}
				else
				{
					g.setCountry(input);
					break;
				}

			/*update address*/
			case 3:

				if(input.isEmpty())
				{
					return "Sorry! No address is entered";
				}
				else if(input.equals(g.getAddress()))
				{
					return "Previous address and new address is the same";
				}
				else
				{
					g.setAddress(input);
					break;
				}

			/*update nationality*/
			case 4:

				if(input.isEmpty())
				{
					return "Sorry! No nationality is entered";
				}
				else if(input.equals(g.getNationality()))
				{
					return "Previous nationality and new nationality is the same";
				}
				else
				{
					g.setNationality(input);
					break;
				}

			/*update contact number*/
			case 5:

				if(input.isEmpty())
				{
					return "Sorry! No contact number is entered";
				}
				else if(input.equals(g.getContact()))
				{
					return "Previous contact number and new contact numbers is the same";
				}
				else
				{
					g.setContact(input);
					break;
				}

				/*update gender*/
			case 6:

				if(input.isEmpty())
				{
					return "Sorry! No gender is entered";
				}
				else if(input.equals(g.getGender()))
				{
					return "Previous gender and new gender is the same";
				}
				else
				{
					g.setGender(input);
					break;
				}

				/*update credit card number*/
			case 7:

				if(input.isEmpty())
				{
					return "Sorry! No credit card number is entered";
				}
				else if(input.equals(g.getCreditCard()))
				{
					return "Previous credit card number and new credit card number is the same";
				}
				else
				{
					g.setCreditCard(input);
					break;
				}

				/*update identity*/
			case 8:

				if(input.isEmpty())
				{
					return "Sorry! No identity is entered";
				}
				else if(input.equals(g.getId()))
				{
					return "Previous name and new identities are the same";
				}
				else
				{
					g.setId(input);
					break;
				}
		}
		dao.update(g);
		return "Guest information is updated!";
	}//end of class

	/*if the identity is found, we print 'found' and then print the Guest details*/
	/*if identity is not found,we print 'not found'*/
	/*searching Guest details*/
	public String searchGuest(String identity) {

		ArrayList<Guest> guests = dao.getAllItem();

		for (Guest guest: guests)
		{
			if(identity.equals(guest.getId()))
			{
				System.out.print("Guest found!\n");
				return guest.print();
			}
		}
			return "Guest not found!\n\n";
	}
}