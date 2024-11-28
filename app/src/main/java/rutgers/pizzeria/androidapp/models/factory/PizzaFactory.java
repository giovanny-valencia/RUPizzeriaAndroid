package rutgers.pizzeria.androidapp.models.factory;

import rutgers.pizzeria.androidapp.models.pizza.Pizza;

/**
 * Interface for a pizza factory that provides methods to create various types of pizzas.
 * Implementing classes will define specific ways to create each pizza type.
 * The interface includes methods for creating a Deluxe, Meatzza, BBQ Chicken, and Build Your Own pizza.
 *
 * @author Giovanny
 */
public interface PizzaFactory {
    /**
     * Creates a Deluxe pizza.
     * @return a Pizza object representing a Deluxe pizza.
     */
    Pizza createDeluxe();

    /**
     * Creates a Meatzza pizza.
     * @return a Pizza object representing a Meatzza pizza.
     */
    Pizza createMeatzza();

    /**
     * Creates a BBQ Chicken pizza.
     * @return a Pizza object representing a BBQ Chicken pizza.
     */
    Pizza createBBQChicken();

    /**
     * Creates a Build Your Own pizza.
     * @return a Pizza object representing a Build Your Own pizza.
     */
    Pizza createBuildYourOwn();
}