package rutgers.pizzeria.androidapp.models.pizza;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Meatzza pizza with a fixed set of toppings.
 * This class extends the Pizza class and includes specific toppings associated
 * with a Meatzza pizza. The price is calculated based on the pizza size.
 * The toppings for the Meatzza pizza include sausage, pepperoni, beef, and ham.
 * The default size is set to MEDIUM.
 *
 * @author Giovanny and Miguel Nino Adalla
 */
public class Meatzza extends Pizza {
    private static final ArrayList<Topping> toppings = new ArrayList<>(
            Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF, Topping.HAM)
    );

    /**
     * Constructs a Meatzza pizza with a specified crust and default size of MEDIUM.
     *
     * @param crust the crust type for this Meatzza pizza
     */
    public Meatzza(Crust crust){
        super(toppings, crust, Size.MEDIUM);
    }

    /**
     * Calculates the price of the Meatzza pizza based on its size.
     *
     * @return the total price of the Meatzza pizza
    */
    @Override
    public double price(){
        return switch (getSize()) {
            case SMALL -> 17.99 ;
            case MEDIUM -> 19.99;
            case LARGE -> 21.99;
        };
    }

    /**
     * Overridden toString method
     * @return Meatzza pizza information
     */
    @Override
    public String toString() {
        String s = "";
        if(getCrust() == Crust.STUFFED) {
            s = String.format("Meatzza(Chicago Style-%s), %s, %s, $%.2f", getCrust().name(), toppings.toString(), getSize().name(), price());
        }
        else if(getCrust() == Crust.HAND_TOSSED) {
            s = String.format("Meatzza(NYStyle-%s), %s, %s, $%.2f,", getCrust().name(), toppings.toString(), getSize().name(), price());
        }
        return s;
    }
}
