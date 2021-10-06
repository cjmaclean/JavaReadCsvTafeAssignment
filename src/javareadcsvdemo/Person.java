/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javareadcsvdemo;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author 30039802
 */
public class Person {

    private String name;
    private String favouriteColour;
    private String secondFavouriteColour;

    public Person(String name, String favouriteColour, String secondfavouriteColour) {
        this.name = name;
        this.favouriteColour = favouriteColour;
        this.secondFavouriteColour = secondfavouriteColour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavouriteColour() {
        return favouriteColour;
    }

    public void setFavouriteColour(String favouriteColour) {
        this.favouriteColour = favouriteColour;
    }

    public String getSecondFavouriteColour() {
        return secondFavouriteColour;
    }

    public void setSecondFavouriteColour(String secondFavouriteColour) {
        this.secondFavouriteColour = secondFavouriteColour;
    }

}
