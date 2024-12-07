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

/**
 * CreationActivity is responsible for managing the pizza creation screen in the Android application.
 * It allows users to select the type of pizza, crust style, size, and toppings,
 * and facilitates the addition of the configured pizza to the order.
 * <p>
 * This activity supports:
 * <ul>
 *     <li>Dynamic pizza image updates based on selected crust style.</li>
 *     <li>Predefined toppings for specialty pizzas (non-editable).</li>
 *     <li>Customizable toppings for "Build Your Own" pizzas.</li>
 *     <li>Integration with RecyclerView for displaying and selecting toppings.</li>
 *     <li>Use of spinners for size and crust style selection.</li>
 * </ul>
 * </p>
 *
 * @author Giovanny
 */
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
    private final HashMap<String, Integer> crustImageMap = new HashMap<>();

    private ArrayList<ToppingModel> toppingModels;

    ToppingAdapter adapter;

    /**
     * Initializes the activity, sets up UI components, and handles pizza configuration.
     * <p>
     * The method retrieves the selected pizza type from the Intent, sets up spinners,
     * generates images for pizza crusts, and configures the RecyclerView for toppings.
     * </p>
     *
     * @param savedInstanceState The saved state of the activity, if any.
     */
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

        toppingModels = ToppingModel.getToppings(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        adapter = new ToppingAdapter(this, toppingModels);


        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //For specialty pizzas, assign the fixed toppings
        assignToppings();
    }

    /**
     * Generates a mapping of crust styles to their respective pizza images.
     * <p>
     * This method assigns specific images for each combination of pizza type and crust style,
     * enabling dynamic updates in the UI.
     * </p>
     */
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


    /**
     * Assigns toppings for specialty pizzas and disables editing for these pizzas.
     * <p>
     * For "Build Your Own" pizzas, toppings remain editable and are not preselected.
     * This method ensures that specialty pizzas display their fixed toppings in the UI.
     * </p>
     */
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
                return;
            }

            // Convert tempTopping to a Set for efficient lookups
            Set<String> tempToppingSet = tempPizza.getToppings().stream()
                    .map(topping -> topping.name().replace("_", " ").toUpperCase())
                    .collect(Collectors.toSet());

            // Assign and mark toppings
            for (ToppingModel topping : toppingModels) {
                topping.setEditable(false); // Specialty pizzas are not editable

                if (tempToppingSet.contains(topping.getName().toUpperCase())) {
                    topping.setChecked(true); // Mark the matching toppings as selected
                }
            }
        }

    }


    /**
     * Displays the appropriate pizza image based on the selected crust style.
     *
     * @param crust The selected crust style (e.g., Chicago, New York).
     */
    private void displayPizzaImage(String crust){
        ImageView imagePizza = findViewById(R.id.imagePizza);
        Integer img = crustImageMap.get(crust);

        if (img != null){
            imagePizza.setImageResource(img);
        }
    }

    /**
     * Configures the spinners for crust style and size selection.
     * <p>
     * This method initializes the spinners with predefined options, sets the default
     * selection for size to "Medium," and assigns listeners for user interactions.
     * </p>
     */
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

    /**
     * Adds the configured pizza to the current order.
     * <p>
     * The method retrieves user selections for crust style, size, and toppings,
     * creates the pizza object using a factory pattern, and adds it to the shared order resource.
     * </p>
     *
     * @param view The view that triggered this action (e.g., a button).
     */
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
            case SMALL -> Size.SMALL;
            case LARGE -> Size.LARGE;
            default -> Size.MEDIUM;
        };

        pizza.setSize(size);

        ShareResource shareResource = ShareResource.getInstance();

        shareResource.getOrder().addPizza(pizza);

        toppingModels.clear();

        finish();
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

                ArrayList<Topping> toppings = adapter.getToppings();

                if (toppings.isEmpty()){
                    Toast.makeText(this, "Select at least 1 topping", Toast.LENGTH_SHORT).show();
                    return null;
                }

                pizza.setToppings(toppings);
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

    /**
     * Clears the toppings list and returns to the main activity.
     * <p>
     * This method is triggered when the user cancels the pizza creation process.
     * </p>
     *
     * @param view The view that triggered this action (e.g., a button).
     */
    public void onCancel(View view){
        toppingModels.clear();
        finish();
    }

    /**
     * Handles item selection events for spinners.
     * <p>
     * Updates the pizza display image when the crust style spinner selection changes.
     * </p>
     *
     * @param adapterView The adapter view where the selection occurred.
     * @param view The view within the adapter view that was clicked.
     * @param i The position of the selected item.
     * @param l The row id of the selected item.
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //only attempt to change the pizza display image if the adapter is linked to crust spinner
        if (adapterView.getId() == R.id.spinnerCrustStylePizza){
            displayPizzaImage(spinnerCrustStylePizza.getSelectedItem().toString());
        }
    }

    /**
     * Callback for when no item is selected in a spinner.
     * <p>
     * This method is required to implement the {@link AdapterView.OnItemSelectedListener} interface,
     * but it does not perform any actions.
     * </p>
     *
     * @param adapterView The adapter view where no selection was made.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //do nothing
    }
}