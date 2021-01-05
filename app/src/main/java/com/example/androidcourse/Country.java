package com.example.androidcourse;

public class Country {

    //Variables
    private String name;
    private int flag;
    private String countryDescription;
    private String currency;

    //This is an empty constructor
    public Country (){
        this.name = name;
        this.flag = flag;
        this.countryDescription = countryDescription;
        this.currency = currency;
    }

    //This is the constructor
    public Country( String name, int flag, String countryDescription, String currency){
        this.name = name;
        this.flag = flag;
        this.countryDescription = countryDescription;
        this.currency = currency;
    }

    //These methods are needed to get country names or country id that represent flag
    public String getCountryName() { return name;}
    public int getCountryFlag() { return flag;}
    public String getCountryDescription() {return countryDescription;}
    public String getCurrency() {return currency;}

    //These methods are needed to set the country name and flag
    public void setCountryName(String name){this.name = name;}
    public void setCountryFlag(int flag) {this.flag = flag;}
    public void setCountryDescription(String countryDescription) {this.countryDescription = countryDescription;}
    public void setCurrency(String currency) {this.currency = currency;}
}
