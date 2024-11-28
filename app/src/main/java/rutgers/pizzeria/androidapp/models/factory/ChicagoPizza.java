package rutgers.pizzeria.androidapp.models.factory;

import rutgers.pizzeria.androidapp.models.pizza.BBQChicken;
import rutgers.pizzeria.androidapp.models.pizza.BuildYourOwn;
import rutgers.pizzeria.androidapp.models.pizza.Crust;
import rutgers.pizzeria.androidapp.models.pizza.Deluxe;
import rutgers.pizzeria.androidapp.models.pizza.Meatzza;
import rutgers.pizzeria.androidapp.models.pizza.Pizza;

/**
 * Factory class for creating Chicago-style pizzas.
 * This class implements the PizzaFactory interface and provides methods to create
 * specific types of pizzas with Chicago-style crusts.
 *<p>
 * Each method in this class returns a different type of pizza with the appropriate
 * Chicago-style crust configuration.
 *
 * @author Giovanny
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Creates a Chicago-style Deluxe pizza with a deep-dish crust.
     *
     * @return a new Deluxe pizza with a deep-dish crust
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.DEEP_DISH);
    }

    /**
     * Creates a Chicago-style Meatzza pizza with a stuffed crust.
     *
     * @return a new Meatzza pizza with a stuffed crust
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.STUFFED);
    }

    /**
     * Creates a Chicago-style BBQ Chicken pizza with a pan crust.
     *
     * @return a new BBQChicken pizza with a pan crust
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.PAN);
    }

    /**
     * Creates a Chicago-style Build Your Own pizza with a pan crust.
     * This pizza starts with no toppings and allows the user to add their own toppings.
     *
     * @return a new BuildYourOwn pizza with a pan crust
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.PAN);
    }
}
