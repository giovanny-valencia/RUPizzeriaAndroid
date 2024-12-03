package rutgers.pizzeria.androidapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;


import java.util.ArrayList;
import java.util.Collections;

import rutgers.pizzeria.androidapp.models.factory.ChicagoPizza;
import rutgers.pizzeria.androidapp.models.factory.PizzaFactory;
import rutgers.pizzeria.androidapp.models.orders.Order;
import rutgers.pizzeria.androidapp.models.pizza.Pizza;

public class ViewCart extends AppCompatActivity implements AdapterView.OnItemClickListener  {

    ShareResource resource = ShareResource.getInstance();
    private ListView lv_pizza;

    private TextView tv_orderNum; //might use to R.getIDs

    private Button remove_button, clear_button, placeOrder_button;

    private EditText te_subTotal, te_salesTax, te_orderTotal;
    ObservableArrayList<Pizza> obl_pizzas;

    ArrayList<Pizza> pizzas; //get pizzas from activity 2
    ArrayAdapter<Pizza> items;

    Order order;

    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) { //creates this everytime this activity is opened
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_cart);

        //instantiate variables
        obl_pizzas = new ObservableArrayList<>(); //initialize observableList
        //create an adapter for the ListView and set the data source to the ObservableList
        items = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, obl_pizzas);

        //get global data
        order = resource.getOrder();
        pizzas = resource.getOrder().getPizzas(); //gets the pizzas in the order

        //test-purposes
        getIDs();

        tv_orderNum.setText("Order# " + order.getOrderNumber());

        //Set the price amounts
        setPriceValue();

        obl_pizzas.addAll(pizzas);

        lv_pizza.setAdapter(items); //set the adapter of the ListView to the source
        lv_pizza.setOnItemClickListener(this); //add a listener to the ListView
    }

    private void setPriceValue(){
        te_subTotal.setText(String.format("$%.2f", order.calculateSubtotal()));
        te_salesTax.setText(String.format("$%.2f", order.calculateSalesTax()));
        te_orderTotal.setText(String.format("$%.2f", order.calculateTotal()));
    }

    private void getIDs(){
        tv_orderNum = findViewById(R.id.tv_orderNum); //connect the textView object
        lv_pizza = findViewById(R.id.listView);
        te_subTotal = findViewById(R.id.te_subTotal);
        te_salesTax = findViewById(R.id.te_salesTax);
        te_orderTotal = findViewById(R.id.te_orderTotal);

    }

    /**
     * The event Handler for the onItemClick event on the ListView
     * @param parent The AdapterView where the click happened.
     * @param view The View within the AdapterView that was clicked (in this example is ListView)
     * @param position the index/position of the view that was clicked in the adapter.
     * @param id the raw id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Message");
        //alert.setMessage(parent.getAdapter().getItem(position).toString());
        alert.setMessage("Remove this item?");
        //anonymous inner class to handle the onClick event of YES or NO.
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                removePizza(view, position);
                Toast.makeText(getApplicationContext(), "you cliked YES, removed", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getApplicationContext(), "you clicked NO", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Removes a pizza when user clicked the Remove pizza button
     * Displays alert messages when user attempts to click button without
     * selecting a pizza or when the cart is empty
     */
    public void removePizza(View view, int i) {
        obl_pizzas.remove(i); //remove from the data source
        pizzas.remove(i);
        setPriceValue();
        items.notifyDataSetChanged(); //updates the listview
    }
    /**
     * Clears all the pizzas inside the cart when user clicked the Clear Cart button
     * Displays alert messages when user attempts to click button when the cart is already empty
     */
    public void clearCart(View view) {

    }

    /**
     * Allows user to place an order when the cart is not empty
     * Displays a message when user attempts to place an order while the cart is empty
     */
    public void placeOrder(View view) {

    }
}



