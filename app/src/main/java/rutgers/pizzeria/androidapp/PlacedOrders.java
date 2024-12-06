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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placed_orders);

        //order = resource.getOrder(); //get order from singleton
        orderList = resource.getPlacedOrdersList();

        sp_orderNum = findViewById(R.id.sp_orderNum);
        orderTotal = findViewById(R.id.orderTotal);
        listView = findViewById(R.id.listView2);

        //have to be called again when an order is removed
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

    //when an item is chosen
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //get selected item
        String selectedItem = parent.getItemAtPosition(position).toString();

        indexOfCurrentOrder = Integer.parseInt(selectedItem)-1; //subtract one to get the index
        System.out.println("The index of the current number is:" + indexOfCurrentOrder);

        //Show a Toast with the selected Item
        Toast.makeText(this, "Selected Order Number: " + selectedItem, Toast.LENGTH_SHORT).show();

        order = orderList.get(indexOfCurrentOrder);

        pizzas = order.getPizzas();
        orderTotal.setText(String.format("$%.2f", order.calculateTotal()));

        obl_pizzas = new ObservableArrayList<>();
        items = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, obl_pizzas);

        obl_pizzas.addAll(pizzas);
        listView.setAdapter(items); //set the adapter of the ListView to the source
    }

    //default
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //empty
    }

    public void cancelOrder(View view){
        //change values of orderNum, orderNums arraylist is connected to adapter
        if(!orderList.isEmpty()){

            orderList.remove(indexOfCurrentOrder);
            orderNums.remove(indexOfCurrentOrder);
            obl_pizzas.clear();

            /*for(Order order: orderList)
                orderNums.add(order.getOrderNumber());
            */
            sp_orderNum.setAdapter(adapter); //change the orderNums

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