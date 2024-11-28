package rutgers.pizzeria.androidapp.models.orders;

import rutgers.pizzeria.androidapp.models.pizza.Pizza;
import java.util.ArrayList;

/**
 * Order class creates a unique order number for each instance of Order created and holds a list of pizzas.
 * @author Miguel Nino Adalla
 */
public class Order {
    private static int orderCounter = 1; //generates unique order number
    private int number;
    private ArrayList<Pizza> pizzas;
    private static final double TAX_RATE = 0.06625;  // Sales tax rate for New Jersey

    /**
     * Constructor to initialize the order with empty pizza list and a unique number
     */
    public Order(){
        this.pizzas = new ArrayList<>();
        this.number = orderCounter++;
    }


    /**
     * Add a pizza to the order
     * @param pizza that's added to order
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Removes a pizza from the order
     * @param pizza that's removed from order
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /**
     * Clears the pizza list from the order
     */
    public void clearPizzas() {
        pizzas.clear();
    }

    /**
     * Calculates the subtotal of the order
     * Adds the prices of all pizzas in the order w/o tax
     * @return subtotal of order
     */
    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (Pizza pizza : pizzas) {
            subtotal += pizza.price();
        }
        return subtotal;
    }

    /**
     * Calculates the sales tax based on the subtotal of the order
     * @return Sales Tax
     */
    public double calculateSalesTax() {
        double subtotal = calculateSubtotal();
        return subtotal * TAX_RATE;
    }

    /**
     * Calculates the total price of the order with sales tax
     * @return order total price
     */
    public double calculateTotal() {
        double subtotal = calculateSubtotal();
        double salestax = calculateSalesTax();
        return subtotal + salestax;
    }

    /**
     * Getter for the order number
     * @return order number
     */
    public int getOrderNumber() {
        return number;
    }

    /**
     * Retrieves the list of pizzas in the order
     * @return the list of pizzas
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }
}
