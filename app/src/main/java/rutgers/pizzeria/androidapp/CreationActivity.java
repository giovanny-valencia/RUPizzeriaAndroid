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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import rutgers.pizzeria.androidapp.models.pizza.Pizza;
import rutgers.pizzeria.androidapp.models.pizza.Topping;
import rutgers.pizzeria.androidapp.models.pizza.Size;



import rutgers.pizzeria.androidapp.recyclerview.ToppingModel;
import rutgers.pizzeria.androidapp.recyclerview.ToppingAdapter;

import rutgers.pizzeria.androidapp.models.factory.PizzaFactory;
import rutgers.pizzeria.androidapp.models.factory.NYPizza;
import rutgers.pizzeria.androidapp.models.factory.ChicagoPizza;

import rutgers.pizzeria.androidapp.models.pizza.BuildYourOwn;
import rutgers.pizzeria.androidapp.models.pizza.BBQChicken;
import rutgers.pizzeria.androidapp.models.pizza.Deluxe;
import rutgers.pizzeria.androidapp.models.pizza.Meatzza;

public class CreationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String CHICAGO_CRUST = "Chicago";
    private static final String NEW_YORK_CRUST = "New York";
    public static final String BYO = "Build Your Own";
    public static final String BBQ_CHICKEN = "BBQ Chicken";
    public static final String DELUXE = "Deluxe";
    public static final String MEATZZA = "Meatzza";
    private static final String SMALL = "SMALL";

    private static final String MEDIUM = "MEDIUM";

    private static final String LARGE = "LARGE";

    private Spinner spinnerCrustStylePizza;

    private Spinner spinnerSizePizza;

    private String selectedPizza = null;

    private String styleCrust = NEW_YORK_CRUST;

    private final HashMap<String, Integer> crustImageMap = new HashMap<>();

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

        ToppingAdapter adapter = new ToppingAdapter(this, toppings);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //For specialty pizzas, assign the fixed toppings
        assignToppings();
    }

    private void generatePizzaImageHash() {

        switch (selectedPizza) {
            case BYO -> {
                crustImageMap.put(CHICAGO_CRUST, R.drawable.pizza_chicken_bbq_pan); //pan
                crustImageMap.put(NEW_YORK_CRUST, R.drawable.pizza_meatzza_hand_tossed); //hand-tossed
            }
            case BBQ_CHICKEN -> {
                crustImageMap.put(CHICAGO_CRUST, R.drawable.pizza_chicken_bbq_pan); //pan
                crustImageMap.put(NEW_YORK_CRUST, R.drawable.pizza_bbq_chicken_thin_crust); //thin
            }
            case DELUXE -> {
                crustImageMap.put(CHICAGO_CRUST, R.drawable.pizza_deluxe_chicago_deep_dish); //deep dish
                crustImageMap.put(NEW_YORK_CRUST, R.drawable.pizza_brooklyn); //brooklyn
            }
            case MEATZZA -> {
                crustImageMap.put(CHICAGO_CRUST, R.drawable.pizza_meatzza_stuffed); //stuffed
                crustImageMap.put(NEW_YORK_CRUST, R.drawable.pizza_meatzza_hand_tossed); //hand-tossed
            }
        }
    }

    private void assignToppings() {
        if (!selectedPizza.equals(BYO)) {
            PizzaFactory tempPf = new NYPizza();
            Pizza tempPizza;

            // Assign the appropriate pizza
            tempPizza = switch (selectedPizza) {
                case BBQ_CHICKEN -> tempPf.createBBQChicken();
                case DELUXE -> tempPf.createDeluxe();
                case MEATZZA -> tempPf.createMeatzza();
                default -> null;
            };

            // Safeguard against unexpected cases
            if (tempPizza == null) {
                System.err.println("Error: Selected pizza is invalid.");
                return;
            }

            // Convert tempTopping to a Set for efficient lookups
            Set<String> tempToppingSet = tempPizza.getToppings().stream()
                    .map(topping -> topping.name().replace("_", " ").toUpperCase())
                    .collect(Collectors.toSet());

            // Assign and mark toppings
            for (ToppingModel topping : toppings) {
                topping.setEditable(false); // Specialty pizzas are not editable

                if (tempToppingSet.contains(topping.getName().toUpperCase())) {
                    topping.setChecked(true); // Mark the matching toppings as selected
                }
            }
        }
    }


    private void displayPizzaImage(String crust){
        ImageView imagePizza = findViewById(R.id.imagePizza);
        Integer img = crustImageMap.get(crust);

        if (img != null){
            imagePizza.setImageResource(img);
        }
    }

    /**
     * Creates a {@link Pizza} object based on the selected type and factory.
     * <p>
     * This method uses the Abstract Factory pattern to generate the pizza with predefined
     * or user-selected configurations. For "Build Your Own" pizzas, toppings are retrieved
     * dynamically from the user's selections.
     * </p>
     *
     * @param pizzaSelection The selected pizza type (e.g., Deluxe, BBQ Chicken).
     * @param pizzaFactory The factory to use for creating the pizza.
     * @return A {@link Pizza} object, or null if creation fails.
     */
    private Pizza makePizza(String pizzaSelection, PizzaFactory pizzaFactory) {
        Pizza pizza;

        switch (pizzaSelection) {
            case BYO -> {
                // Logic for building your own pizza
                pizza = pizzaFactory.createBuildYourOwn();

                // get and set toppings, if null return
               // ArrayList<Topping> toppings = getToppings();
                //if (toppings == null) return null;

                //pizza.setToppings(toppings);
            }
            case DELUXE -> {
                // Logic for Deluxe pizza
                pizza = pizzaFactory.createDeluxe();
            }
            case BBQ_CHICKEN -> {
                // Logic for BBQ Chicken pizza
                pizza = pizzaFactory.createBBQChicken();
            }
            case MEATZZA -> {
                // Logic for Meatzza pizza
                pizza = pizzaFactory.createMeatzza();
            }
            default -> { // this shouldn't happen, potential error handle?
                return null;
            }
        }
        return pizza;
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

    public void addToOrder(View view){

        //make pizza base
        PizzaFactory pizzaFactory = switch (spinnerCrustStylePizza.getSelectedItem().toString()) {
            case CHICAGO_CRUST -> new ChicagoPizza();
            default -> new NYPizza();
        };


        Pizza pizza = makePizza(selectedPizza, pizzaFactory);
        if (pizza==null) return;

        String sizeString = spinnerSizePizza.getSelectedItem().toString().toUpperCase();
        Size size = switch (sizeString) {
            case "SMALL" -> Size.SMALL;
            case "LARGE" -> Size.LARGE;
            default -> Size.MEDIUM;
        };

        pizza.setSize(size);

        ShareResource shareResource = ShareResource.getInstance();

        shareResource.getOrder().addPizza(pizza);

        toppings.clear();
        finish();
    }

    //just returns to main activity
    public void onCancel(View view){
        toppings.clear();
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