package rutgers.pizzeria.androidapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

//import androidx.activity.EdgeToEdge;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;


import java.util.ArrayList;
import java.util.Collections;

import rutgers.pizzeria.androidapp.models.pizza.Pizza;

public class ViewCart extends AppCompatActivity implements AdapterView.OnItemClickListener  {


    private ListView lv_pizza;

    private TextView tv_orderNum; //might use to R.getIDs

    private Button remove_button, clear_button, placeOrder_button;

    private EditText te_subTotal, te_salesTax, te_orderTotal;
    ObservableArrayList<Pizza> obl_pizzas;

    ArrayList<Pizza> pizzas; //get pizzas from activity 2
    ArrayAdapter<Pizza> items;


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
        //EdgeToEdge.enable(this);
        setContentView(R.layout.view_cart);
        lv_pizza = findViewById(R.id.listView);
        obl_pizzas = new ObservableArrayList<>();
        //pizzas = ord
        //Collections.addAll(obl_pizzas, pizzas);
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

    }
}



