package rutgers.pizzeria.androidapp.models.pizza;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a BBQ Chicken pizza with a fixed set of toppings.
 * This class extends the Pizza class and includes specific toppings associated
 * with a BBQ Chicken pizza. The price is calculated based on the pizza size.
 * The toppings for BBQ Chicken pizza include BBQ chicken, green pepper, provolone, and cheddar.
 * The default size is set to MEDIUM.
 *
 * @author Giovanny and Miguel
 */
public class BBQChicken extends Pizza{
    private static final ArrayList<Topping> toppings = new ArrayList<>(
            Arrays.asList(Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER, Topping.PROVOLONE, Topping.CHEDDAR)
    );

    /**
     * Constructs a BBQ Chicken pizza with a specified crust and default size of MEDIUM.
     *
     * @param crust the crust type for this BBQ Chicken pizza
     */
    public BBQChicken(Crust crust){
        super(toppings, crust, Size.MEDIUM);
    }

    /**
     * Calculates the price of the BBQ Chicken pizza based on its size.
     *
     * @return the total price of the BBQ Chicken pizza
     */
    @Override
    public double price(){
        return switch (getSize()) {
            case SMALL -> 14.99;
            case MEDIUM -> 16.99;
            case LARGE -> 19.99;
        };
    }

    /**
     * Overridden toString method
     * @return BBQ Chicken pizza information
     */
    @Override
    public String toString() {
        String s = "";
        if(getCrust() == Crust.PAN) {
            s = String.format("BBQ Chicken(Chicago Style-%s), %s, %s, $%.2f", getCrust().name(), toppings.toString(), getSize().name(), price());
        }
        else if(getCrust() == Crust.THIN) {
            s = String.format("BBQ Chicken(NYStyle-%s), %s, %s, $%.2f,", getCrust().name(), toppings.toString(), getSize().name(), price());
        }
        return s;
    }
}
