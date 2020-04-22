package entities;

import java.io.Serializable;

public class Guest implements Serializable {

    private static final long serialVersionUID = 7L;
    /*id of the Guest-can be passport number or drivers license number*/
    private String id;

    /*name of the Guest*/
    private String name;

    /*credit card number of the Guest*/
    private String creditCard;

    /*address of the Guest*/
    private String address;

    /*country of the Guest*/
    private String country;

    /*gender of Guest-can be male, female or others*/
    private String gender;

    /*nationality of the Guest*/
    private String nationality;

    /*contact number of the Guest- can be home number of mobile number*/
    private String contactNumber;


    /*constructor of Guest class*/
    public Guest(String id, String name, String creditCard, String address, String country, String gender, String nationality, String contactNumber) {
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
    public Guest() {};

    /* to get the identity of the Guest */
    public String getId()
    {
        return id;
    }

    /* to get the name of the Guest */
    public String getName()
    {
        return name;
    }

    /* to get the credit card number of the Guest */
    public String getCreditCard()
    {
        return creditCard;
    }

    /* to get the address of the Guest */
    public String getAddress()
    {
        return address;
    }

    /* to get the country of the Guest */
    public String getCountry()
    {
        return country;
    }

    /* to get the gender of the Guest */
    public String getGender()
    {
        return gender;
    }

    /* to get the nationality of the Guest */
    public String getNationality()
    {
        return nationality;
    }

    /* to get the contact number of the Guest */
    public String getContact()
    {
        return contactNumber;
    }

    /* to set the id of the Guest */
    public void setId(String id)
    {
        this.id = id;
    }


    /* to set the name of the Guest */
    public void setName(String name)
    {
        this.name = name;
    }

    /* to set the address of the Guest */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /* to set the contact number of the Guest */
    public void setContact(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    /* to set the credit card number of the Guest */
    public void setCreditCard(String creditCard){
        this.creditCard = creditCard;
    }

    /* to set the country of the Guest */
    public void setCountry(String country)
    {
        this.country = country;
    }

    /* to set the nationality of the Guest */
    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    /* to set the gender of the Guest */
    public void setGender(String gender)
    {
        this.gender = gender;
    }


    /*to return the information about the Guest*/
    public String print(){
        return "The details of the Guest are as follows: \n"+ "\nIdentity: "+id + "\nName: "+name+ "\nGender: "+gender+ "\nContact Number: " + contactNumber+
                "\nAddress: "+address+ "\nCountry: "+country+ "\nNationality: " + nationality+ "\nCredit Card Number: "+ creditCard + "\n\n";
    }//end of string function

}//end of class


