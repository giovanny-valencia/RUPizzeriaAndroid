package rutgers.pizzeria.androidapp;

import java.util.ArrayList;

import rutgers.pizzeria.androidapp.models.factory.ChicagoPizza;
import rutgers.pizzeria.androidapp.models.factory.PizzaFactory;
import rutgers.pizzeria.androidapp.models.orders.Order;
import rutgers.pizzeria.androidapp.models.pizza.Pizza;

/**
 * ShareResource is a singleton design pattern for centralized control
 * This class holds the Order object and a list of placed orders, and it contains
 * public methods to access its instance variables.
 * @author Miguel Nino Adalla
 */
public final class ShareResource {
    private static ShareResource resource;
    private Order order;
    private ArrayList<Order> placedOrders;

    /**
     * private constructor to prevent other classes to create an instance of ShareResource
     */
    private ShareResource(){
        order = new Order();
        placedOrders = new ArrayList<Order>();
        createPizzas();
    }

    public void createPizzas(){
        PizzaFactory pizzaFactory = new ChicagoPizza();
        Pizza pizza = pizzaFactory.createDeluxe();
        Pizza pizza2 = pizzaFactory.createBBQChicken();
        Pizza pizza3 = pizzaFactory.createMeatzza();
        Pizza pizza4 = pizzaFactory.createDeluxe();
        Pizza pizza5 = pizzaFactory.createDeluxe();
        Pizza pizza6 = pizzaFactory.createDeluxe();
        Pizza pizza7 = pizzaFactory.createDeluxe();

        order.addPizza(pizza);
        order.addPizza(pizza2);
        order.addPizza(pizza3);
        order.addPizza(pizza4);
        order.addPizza(pizza5);
        order.addPizza(pizza6);
        order.addPizza(pizza7);
    }

    /**
     * If the instance is not created yet, create one, otherwise return the instance (lazy approach.)
     * The synchronized keyword is essential to avoid problems in multi-threaded programs.
     *
     * @return the reference of the only instance of this class
     */
    public static synchronized ShareResource getInstance() {
        if (resource == null)
            resource = new ShareResource();
        return resource;
    }

    /**
     * Getter method for the list of placed orders.
     *
     * @return the list of placed orders.
     */
    public ArrayList<Order> getPlacedOrdersList(){
        return placedOrders;
    }

    /**
     * Getter method for the current order.
     *
     * @return the current order.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Updates the current order reference with a new order.
     *
     * @param order the new order to set.
     */
    public void createNewOrder(Order order) {
        this.order = order;
    }

}
