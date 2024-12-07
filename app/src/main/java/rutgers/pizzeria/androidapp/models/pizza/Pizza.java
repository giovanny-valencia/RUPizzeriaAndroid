package rutgers.pizzeria.androidapp.models.pizza;

import java.util.ArrayList;

/**
 * Pizza class is an abstract class that holds contents of a pizza including
 * a list of toppings, the type of crust, and the size of the pizza
 * @author Miguel Nino Adalla
 */
public abstract class Pizza {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * Abstract method for price calculation
     * Overridden in the pizza subclasses
     */
    public abstract double price();

    /**
     * Pizza class constructor to instantiate the contents of the pizza
     * @param toppings
     * @param crust
     * @param size
     */
    Pizza(ArrayList<Topping> toppings, Crust crust, Size size){
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
    }

    /**
     * Getter method to retrieve the toppings
     * @return reference of the toppings arraylist
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Setter method to set the pizza's toppings
     * @param toppings of the pizza
     */
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * Getter method for the pizza's crust
     * @return crust of the pizza
     */
    public Crust getCrust(){
        return crust;
    }

    /**
     * Getter method to retrieve the pizza's size
     * @return size of the pizza
     */
    public Size getSize(){
        return size;
    }

    /**
     * Setter method to set the pizza's size
     * @param size of pizza
     */
    public void setSize(Size size){
        this.size = size;
    }

    /**
     * toString method of the Pizza class
     * @return String format of Pizza class
     */
    public String toString() {
        return "Pizza: " + toppings.toString() + size.toString();
    }
}
