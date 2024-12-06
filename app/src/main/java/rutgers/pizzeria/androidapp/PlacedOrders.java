package rutgers.pizzeria.androidapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import java.util.ArrayList;
import rutgers.pizzeria.androidapp.models.orders.Order;
import rutgers.pizzeria.androidapp.models.pizza.Pizza;

/**
 * PlacedOrders is the activity class contains the backend of placed_orders.xml(user interface)
 * This manages the data of placed orders stored in central control(ShareResource) holding
 * each order's information including the total amount($) and pizzas.
 * Allows user to view the orders placed and the feature to cancel an order.
 * @author Miguel Nino Adalla
 */
public class PlacedOrders extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ShareResource resource = ShareResource.getInstance();

    private ListView listView;
    private Spinner sp_orderNum;
    private EditText orderTotal;

    ObservableArrayList<Pizza> obl_pizzas;
    ArrayList<Pizza> pizzas;
    ArrayList<Integer> orderNums = new ArrayList<>();;
    ArrayList<Order> orderList;
    ArrayAdapter<Pizza> items;
    ArrayAdapter<Integer> adapter; //adapter for spinner
    Order order;
    int indexOfCurrentOrder;

    /**
     * This method runs whenever the placed order activity is opened/visited
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placed_orders);

        //order = resource.getOrder(); //get order from singleton
        orderList = resource.getPlacedOrdersList();

        sp_orderNum = findViewById(R.id.sp_orderNum);
        orderTotal = findViewById(R.id.orderTotal);
        listView = findViewById(R.id.listView2);

        //populate the orderNum arraylist
        for(Order order: orderList)
            orderNums.add(order.getOrderNumber());

        if(orderList.isEmpty())
            Toast.makeText(this, "There are no placed orders", Toast.LENGTH_SHORT).show();

        //Create ArrayAdapter for the Spinner
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNums);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_orderNum.setAdapter(adapter); //sets the items in the spinner

        // Set the listener for selection
        sp_orderNum.setOnItemSelectedListener(this);
    }

    /**
     * This event Handler manages the listview and displays the content aligned with spinner item(order number)
     * such as the order's list of pizzas and the total price.
     * On default, this event Handler displays first item on the spinner object, which is the first element
     * on the list of placed orders.
     *
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //get selected item
        String selectedItem = parent.getItemAtPosition(position).toString();
        int selectedOrderNumber = Integer.parseInt(selectedItem); // Parse as order number

        // Find the corresponding order by matching the order number
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderNumber() == selectedOrderNumber) {
                indexOfCurrentOrder = i; // Get the index of the matched order
                break;
            }
        }

        // display order details
        order = orderList.get(indexOfCurrentOrder);
        pizzas = order.getPizzas();
        orderTotal.setText(String.format("$%.2f", order.calculateTotal()));

        obl_pizzas = new ObservableArrayList<>();
        obl_pizzas.addAll(pizzas);
        items = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, obl_pizzas);
        listView.setAdapter(items); //set the adapter of the ListView to the source

        //Show a Toast with the selected Item
        Toast.makeText(this, "Selected Order Number: " + selectedItem, Toast.LENGTH_SHORT).show();
    }

    /**
     * This event Handler runs when nothing is selected on the spinner object
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "No Item Selected", Toast.LENGTH_SHORT).show();
    }

    /**
     * Removes the current order from the list of placed orders when the Cancel button is clicked.
     *
     * @param view the view within the cancelOrder button that was clicked
     */
    public void cancelOrder(View view){
        if(!orderList.isEmpty()){

            orderList.remove(indexOfCurrentOrder);
            orderNums.remove(indexOfCurrentOrder);
            obl_pizzas.clear();

            sp_orderNum.setAdapter(adapter); //update the order numbers

            //Clear UI if the list is empty
            if(orderList.isEmpty()) {
                obl_pizzas.clear();
                items.notifyDataSetChanged();
                orderTotal.setText("");
                Toast.makeText(this, "No orders placed", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Message"); // Title of the alert
            alert.setMessage("There are no placed orders"); // Warning message

            // Set the positive button
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Action for OK button
                    dialog.dismiss();
                }
            });
            // Create and show the alert
            AlertDialog dialog = alert.create();
            dialog.show();
        }
    }
}