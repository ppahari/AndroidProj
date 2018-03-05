package com.example.realnapster.contactapp;

/**
 * Created by Realnapster on 3/2/2018.
 */

public class ContactData {
    String name;
    String number;
//    String error;
    public ContactData(String name,String number) {
        this.name=name;
        this.number=number;
    }
//
//    public String getError() {
//        return error;
//    }
//
//    public void setError(String error) {
//        this.error = error;
//    }

//    public ContactData(String error) {
//        this.error=error;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
