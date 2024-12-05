package rutgers.pizzeria.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class CreationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String CHICAGO_CRUST = "Chicago";
    private static final String NEW_YORK_CRUST = "New York";
    private Spinner spinnerCrustStylePizza;

    private Spinner spinnerSizePizza;

    private String selectedPizza = null;

    private String styleCrust = NEW_YORK_CRUST;

    private HashMap<String, Integer> crustImageMap = new HashMap<>();

    private ArrayList<ToppingModel> toppings;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String intentKey = "pizzaSelected";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        Intent intent = getIntent();
        selectedPizza = intent.getStringExtra(intentKey);
        TextView textViewSelectedPizza = findViewById(R.id.textViewSelectedPizza);
        textViewSelectedPizza.setText(selectedPizza);

        setUpSpinners();

        generatePizzaImageHash();

        toppings = ToppingModel.getToppings(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        Topping_RecyclerViewAdapter adapter = new Topping_RecyclerViewAdapter(this, toppings);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < toppings.size(); i++){
            System.out.println(toppings.get(i).getName() + " - " + toppings.get(i).getImageResId());
        }

    }

    private void generatePizzaImageHash() {
        String byo = "Build Your Own";
        String bbqChicken = "BBQ Chicken";
        String deluxe = "Deluxe";
        String meatzza = "Meatzza";

        if (selectedPizza.equals(byo)){
            crustImageMap.put(CHICAGO_CRUST, R.drawable.pizza_chicken_bbq_pan); //pan
            crustImageMap.put(NEW_YORK_CRUST, R.drawable.pizza_meatzza_hand_tossed); //hand-tossed
        }
        else if (selectedPizza.equals(bbqChicken)) {
            crustImageMap.put(CHICAGO_CRUST, R.drawable.pizza_chicken_bbq_pan); //pan
            crustImageMap.put(NEW_YORK_CRUST, R.drawable.pizza_bbq_chicken_thin_crust); //thin
        }
        else if (selectedPizza.equals(deluxe)) {
            crustImageMap.put(CHICAGO_CRUST, R.drawable.pizza_deluxe_chicago_deep_dish); //deep dish
            crustImageMap.put(NEW_YORK_CRUST, R.drawable.pizza_brooklyn); //brooklyn
        }
        else if (selectedPizza.equals(meatzza)) {
            crustImageMap.put(CHICAGO_CRUST, R.drawable.pizza_meatzza_stuffed); //stuffed
            crustImageMap.put(NEW_YORK_CRUST, R.drawable.pizza_meatzza_hand_tossed); //hand-tossed
        }
    }

    private void displayPizzaImage(String crust){
        ImageView imagePizza = findViewById(R.id.imagePizza);
        Integer img = crustImageMap.get(crust);

        System.out.println(img);

        if (img != null){
            imagePizza.setImageResource(img);
        }
    }

    private void setUpSpinners() {
        int mediumPizzaSize = 1;

        spinnerCrustStylePizza = findViewById(R.id.spinnerCrustStylePizza);
        spinnerSizePizza = findViewById(R.id.spinnerSizePizza);

        ArrayAdapter<CharSequence> adapterCrust = ArrayAdapter.createFromResource(this, R.array.crust, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(this, R.array.size, android.R.layout.simple_spinner_item);

        adapterCrust.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCrustStylePizza.setAdapter(adapterCrust);
        spinnerSizePizza.setAdapter(adapterSize);

        spinnerCrustStylePizza.setOnItemSelectedListener(this);
        spinnerSizePizza.setOnItemSelectedListener(this);

        spinnerSizePizza.post(()-> spinnerSizePizza.setSelection(mediumPizzaSize));
    }

    //just returns to main activity
    public void onCancel(View view){
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected = adapterView.getItemAtPosition(i).toString();

        Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT).show(); // for debugging, remove this

        //only attempt to change the pizza display image if the adapter is linked to crust spinner
        if (adapterView.getId() == R.id.spinnerCrustStylePizza){
            displayPizzaImage(spinnerCrustStylePizza.getSelectedItem().toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //do nothing
    }
}