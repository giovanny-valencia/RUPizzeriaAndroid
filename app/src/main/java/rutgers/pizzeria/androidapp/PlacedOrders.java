package rutgers.pizzeria.androidapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.ObservableArrayList;

import java.util.ArrayList;

import rutgers.pizzeria.androidapp.models.orders.Order;
import rutgers.pizzeria.androidapp.models.pizza.Pizza;

public class PlacedOrders extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ShareResource resource = ShareResource.getInstance();

    private ListView listview;
    private Spinner sp_orderNum;
    //private TextView lb_orderNum;
    private EditText orderTotal;

    ObservableArrayList<Pizza> obl_pizzas;
    ArrayList<Pizza> pizzas;
    ArrayList<Integer> orderNums = new ArrayList<>();;
    ArrayList<Order> orderList;
    ArrayAdapter<Pizza> items;
    ArrayAdapter<Integer> adapter; //adapter for spinner
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placed_orders);

        //order = resource.getOrder(); //get order from singleton
        orderList = resource.getPlacedOrdersList();

        sp_orderNum = findViewById(R.id.sp_orderNum);
        orderTotal = findViewById(R.id.orderTotal);
        listview = findViewById(R.id.listView2);

        //Create a dynamic list of items
        //orderNums = new ArrayList<>();

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

        //Show a Toast with the selected Item
        Toast.makeText(this, "Selected Order Number: " + selectedItem, Toast.LENGTH_SHORT).show();
        //display the list of pizzas here

        order = orderList.get(Integer.parseInt(selectedItem));
        pizzas = order.getPizzas();
        orderTotal.setText(String.format("$%.2f", order.calculateTotal()));
    }

    //default dapat
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Set default value (e.g., second item in the list)
        int defaultPosition = 0; // Index of the default item (0-based index)
        sp_orderNum.setSelection(defaultPosition);
        Toast.makeText(this, "Order Number 1: ", Toast.LENGTH_SHORT).show();
    }

    public void cancelOrder(String item){
        //change values of orderNum, orderNums arraylist is connected to adapter
        for(Order order: orderList)
            orderNums.add(order.getOrderNumber());

        sp_orderNum.setAdapter(adapter); //change the orderNums
    }
}