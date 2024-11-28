package rutgers.pizzeria.androidapp.models.pizza;

import java.util.ArrayList;

/**
 * Represents a customizable "Build Your Own" pizza that allows a user to add their own toppings.
 * This class calculates the price based on the base price for the size and an additional cost per topping.
 * Maximum number of toppings is limited to 7.
 *
 * @author Giovanny
 */
public class BuildYourOwn extends Pizza {
    private static final double COST_PER_TOPPING = 1.69;

    /**
     * Constructs a Build Your Own pizza with a specified crust and a default size of MEDIUM.
     *
     * @param crust the crust type for this pizza
     */
   public BuildYourOwn(Crust crust) {
        super(new ArrayList<>(), crust, Size.MEDIUM);
    }

    /**
     * Calculates the price of the pizza based on its size and the number of toppings.
     *
     * @return the total price of the pizza
     */
    @Override
    public double price() {
        double basePrice = switch (getSize()) {
            case SMALL -> 8.99;
            case MEDIUM -> 10.99;
            case LARGE -> 12.99;
        };

        return basePrice + (getToppings().size() * COST_PER_TOPPING);
    }

    /**
     * Overridden toString method
     *
     * @return Build Your Own pizza information
     */
    @Override
    public String toString() {
        String s = "";
        if(getCrust() == Crust.PAN) {
            s = String.format("Build Your Own (Chicago Style-%s), %s, %s, $%.2f", getCrust().name(), getToppings().toString(), getSize().name(), price());
        }
        else if(getCrust() == Crust.HAND_TOSSED) {
            s = String.format("Build Your Own (NYStyle-%s), %s, %s, $%.2f", getCrust().name(), getSize().toString(), getSize().name(), price());
        }
        return s;
    }
}
