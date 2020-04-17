package guest;
import java.util.*;

public class guest {

		/*id of the guest-can be passport number or drivers license number*/
		private String id;
		
		/*name of the guest*/
		private String name;
		
		/*credit card number of the guest*/
		private String creditCard;
		
		/*address of the guest*/
		private String address;
		
		/*country of the guest*/
		private String country;
		
		/*gender of guest-can be male, female or others*/
		private String gender;

		/*nationality of the guest*/
		private String nationality;
		
		/*contact number of the guest- can be home number of mobile number*/
		private String contactNumber;

		
	    /*constructor of guest class*/
		public guest(String id, String name, String creditCard, String address, String country, String gender, String nationality, String contactNumber) {		
			this.id = id;
			this.name = name;
			this.creditCard = creditCard;
			this.address = address;
			this.country = country;
			this.gender = gender;
			this.nationality = nationality;
			this.contactNumber = contactNumber;	
		}
		
		/*default constructor*/
		public guest() {};
		
		/* to get the identity of the guest */
		public String getId() 
		{
			return id;
			}	
		
		/* to get the name of the guest */
		public String getName()
		{
			return name;
			}
		
		/* to get the credit card number of the guest */
		public String getCreditCard()
		{
			return creditCard;
			}
		
		/* to get the address of the guest */
		public String getAddress() 
		{
			return address;
			}
		
		/* to get the country of the guest */
		public String getCountry() 
		{
			return country;
			}
		
		/* to get the gender of the guest */
		public String getGender() 
		{
			return gender; 
			}
		
		/* to get the nationality of the guest */
		public String getNationality()
		{ 
			return nationality; 
			}
		
		/* to get the contact number of the guest */
		public String getContact() 
		{ 
			return contactNumber;
		}
		
		/* to set the id of the guest */
		public void setId(String id)
		{
			this.id = id;
		}

		
		/* to set the name of the guest */
		public void setName(String name)
		{
			this.name = name;
		}

		/* to set the address of the guest */
		public void setAddress(String address)
		{
			this.address = address;
		}
		
		/* to set the contact number of the guest */
		public void setContact(String contactNumber)
		{
			this.contactNumber = contactNumber;
		}

		/* to set the credit card number of the guest */
		public void setCreditCard(String creditCard){
			this.creditCard = creditCard;
		}
		
		/* to set the country of the guest */
		public void setCountry(String country)
		{
			this.country = country;
		}
		
		/* to set the nationality of the guest */
		public void setNationality(String nationality)
		{
			this.nationality = nationality;
		}

		/* to set the gender of the guest */
		public void setGender(String gender)
		{
			this.gender = gender;
		}

		
		/*to print the information about the guest*/
		public void print(){
			System.out.println("The details of the guest are as follows: \n");
			System.out.println("Identity: "+id);
			System.out.println("Name: "+name);
			System.out.println("Gender: "+gender);
			System.out.println("Contact Number: " + contactNumber);
			System.out.println("Address: "+address);
			System.out.println("Country: "+country);
			System.out.println("Nationality: " + nationality);
			System.out.println("CreditCard Number: "+ creditCard);	
		}//end of void function
		
	}//end of class

