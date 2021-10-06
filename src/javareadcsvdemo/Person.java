
package javareadcsvdemo;

/**
 *
 * @author 30039802 Caspian Maclean
 * 
 * Person - stores a person's name and favourite colours.
 * 
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
