package rutgers.pizzeria.androidapp.models.factory;

import rutgers.pizzeria.androidapp.models.pizza.BBQChicken;
import rutgers.pizzeria.androidapp.models.pizza.BuildYourOwn;
import rutgers.pizzeria.androidapp.models.pizza.Crust;
import rutgers.pizzeria.androidapp.models.pizza.Deluxe;
import rutgers.pizzeria.androidapp.models.pizza.Meatzza;
import rutgers.pizzeria.androidapp.models.pizza.Pizza;

/**
 * Factory class for creating New York-style pizzas.
 * This class implements the PizzaFactory interface and provides methods to create
 * specific types of pizzas with New York-style crusts.
 *<p>
 * Each method in this class returns a different type of pizza with the appropriate
 * New York-style crust configuration.
 *
 * @author Giovanny
 */
public class NYPizza implements PizzaFactory {

    /**
     * Creates a New York-style Deluxe pizza with a Brooklyn crust.
     *
     * @return a new Deluxe pizza with a Brooklyn crust
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.BROOKLYN);
    }

    /**
     * Creates a New York-style Meatzza pizza with a hand-tossed crust.
     *
     * @return a new Meatzza pizza with a hand-tossed crust
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.HAND_TOSSED);
    }

    /**
     * Creates a New York-style BBQ Chicken pizza with a thin crust.
     *
     * @return a new BBQChicken pizza with a thin crust
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.THIN);
    }

    /**
     * Creates a New York-style Build Your Own pizza with a hand-tossed crust.
     * This pizza starts with no toppings and allows the user to add their own toppings.
     *
     * @return a new BuildYourOwn pizza with a hand-tossed crust
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.HAND_TOSSED);
    }
}
