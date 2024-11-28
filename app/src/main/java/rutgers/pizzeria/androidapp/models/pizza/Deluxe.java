package rutgers.pizzeria.androidapp.models.pizza;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Deluxe pizza with a fixed set of toppings.
 * This class extends the Pizza class and includes specific toppings associated
 * with a Deluxe pizza. The price is calculated based on the pizza size.
 * The toppings for the Deluxe pizza include sausage, pepperoni, green pepper, onion, and mushroom.
 * The default size is set to MEDIUM.
 *
 * @author Giovanny and Miguel Nino Adalla
 */
public class Deluxe extends Pizza {
    private static final ArrayList<Topping> toppings = new ArrayList<>(
            Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM)
    );

    /**
     * Constructs a Deluxe pizza with a specified crust and default size of MEDIUM.
     *
     * @param crust the crust type for this Deluxe pizza
     */
    public Deluxe(Crust crust){
        super(toppings, crust, Size.MEDIUM);
    }

    /**
     * Calculates the price of the Deluxe pizza based on its size.
     *
     * @return the total price of the Deluxe pizza
     */
    @Override
    public double price(){
        return switch (getSize()) {
            case SMALL -> 16.99;
            case MEDIUM -> 18.99;
            case LARGE -> 20.99;
        };
    }

    /**
     * Overridden toString method
     * @return Deluxe pizza information
     */
    @Override
    public String toString() {
        String s = "";
        if(getCrust() == Crust.DEEP_DISH) {
            s = String.format("Deluxe(Chicago Style-%s), %s, %s, $%.2f", getCrust().name(), toppings.toString(), getSize().name(), price());
        }
        else if(getCrust() == Crust.BROOKLYN) {
            s = String.format("Deluxe(NYStyle-%s), %s, %s, $%.2f,", getCrust().name(), toppings.toString(), getSize().name(), price());
        }
        return s;
    }
}
