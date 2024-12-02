package rutgers.pizzeria.androidapp;

import java.util.ArrayList;

import rutgers.pizzeria.androidapp.models.orders.Order;
import rutgers.pizzeria.androidapp.models.pizza.Pizza;

public final class ShareResource {
    private static ShareResource resource;
    private Order order;
    private ArrayList<Order> placedOrders;

    //private ArrayList<Pizza> pizzas; //may not need this

    private ShareResource(){
        order = new Order();
        //pizzas = order.getPizzas();
        placedOrders = new ArrayList<Order>();
    }

    /**
     * If the instance is not created yet, create one, otherwise return the instance (lazy approach.)
     * The synchronized keyword is essential to avoid problems in multi-threaded programs.
     * @return the reference of the only instance of this class
     */
    public static synchronized ShareResource getInstance() {
        if (resource == null)
            resource = new ShareResource();
        return resource;
    }

    /**
     * Getter method for the list of placed orders.
     * @return the list of placed orders.
     */
    public ArrayList<Order> getPlacedOrdersList(){
        return placedOrders;
    }

    /**
     * Getter method for the current order.
     * @return the current order.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Updates the current order reference with a new order.
     * @param order the new order to set.
     */
    public void createNewOrder(Order order) {
        this.order = order;
    }

}
