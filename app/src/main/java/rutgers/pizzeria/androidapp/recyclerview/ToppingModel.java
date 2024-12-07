package rutgers.pizzeria.androidapp.recyclerview;

import android.content.Context;
import java.util.ArrayList;

import rutgers.pizzeria.androidapp.R;
import rutgers.pizzeria.androidapp.models.pizza.Topping;

/**
 * ToppingModel represents a topping for a pizza, including its name, associated image resource,
 * and its state (selected or editable). It provides utility methods to manage and retrieve toppings.
 * <p>
 * This class is designed to work with the {@link ToppingAdapter} for displaying and managing toppings
 * in the pizza creation screen.
 * </p>
 *
 * <p>
 * Toppings are initialized statically and stored in a shared list to avoid redundant creation.
 * </p>
 *
 * @author Giovanny
 */
public class ToppingModel {
    private final String name;
    private final int imageResId;
    private boolean isChecked;
    private boolean isEditable;



    /**
     * Static list to store all topping models.
     */
    private static final ArrayList<ToppingModel> toppings = new ArrayList<>();

    /**
     * Array of image resource IDs corresponding to the available toppings.
     */
    private static final int[] img = {
            R.drawable.icon_topping_bacon, R.drawable.icon_topping_bbq_chicken, R.drawable.icon_topping_beef,
            R.drawable.icon_topping_cheddar, R.drawable.icon_topping_garlic, R.drawable.icon_topping_green_pepper,
            R.drawable.icon_topping_ham, R.drawable.icon_topping_mushroom, R.drawable.icon_topping_onions,
            R.drawable.icon_topping_pepperoni, R.drawable.icon_topping_pineapple, R.drawable.icon_topping_provolone,
            R.drawable.icon_topping_sausage
    };

    /**
     * Constructs a new {@link ToppingModel}.
     *
     * @param name The name of the topping.
     * @param imageResId The resource ID of the topping's image.
     * @param isChecked The initial selected state of the topping.
     * @param isEditable The initial editable state of the topping.
     */
    private ToppingModel(String name, int imageResId, boolean isChecked, boolean isEditable) {
        this.name = name;
        this.imageResId = imageResId;
        this.isChecked = isChecked;
        this.isEditable = isEditable;
    }

    /**
     * Returns the name of the topping.
     *
     * @return The topping name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the image resource ID for the topping.
     *
     * @return The image resource ID.
     */
    public int getImageResId() {
        return imageResId;
    }

    /**
     * Checks whether the topping is currently selected.
     *
     * @return {@code true} if the topping is selected, {@code false} otherwise.
     */
    public boolean isChecked(){
        return isChecked;
    }

    /**
     * Sets the selected state of the topping.
     *
     * @param checked {@code true} to mark the topping as selected, {@code false} to deselect it.
     */
    public void setChecked(boolean checked){
        isChecked = checked;
    }

    /**
     * Checks whether the topping is editable.
     *
     * @return {@code true} if the topping is editable, {@code false} otherwise.
     */

    public boolean isEditable() {
        return isEditable;
    }

    /**
     * Sets the editable state of the topping.
     *
     * @param editable {@code true} to make the topping editable, {@code false} to make it non-editable.
     */
    public void setEditable(boolean editable) {
        isEditable = editable;
    }


    /**
     * Initializes the toppings list with default values.
     * <p>
     * This method loads topping names from resources and associates each with an image and default states.
     * It ensures that the toppings list is initialized only once.
     * </p>
     *
     * @param context The application context for accessing resources.
     */
    private static void setUpToppings(Context context) {
        if (!toppings.isEmpty()) {
            return; // Prevent duplicate initialization
        }

        String[] names = context.getResources().getStringArray(R.array.toppings);

        for (int i = 0; i < names.length; i++){
            ToppingModel topping = new ToppingModel(names[i], img[i], false, true);
            toppings.add(topping);
        }
    }

    /**
     * Retrieves the list of toppings, initializing it if necessary.
     * <p>
     * This method ensures that the toppings list is populated before returning it.
     * </p>
     *
     * @param context The application context for accessing resources.
     * @return A list of {@link ToppingModel} objects.
     */
    public static ArrayList<ToppingModel> getToppings(Context context) {
        if (toppings.isEmpty()){
            setUpToppings(context);
        }

        return toppings;
    }

    /**
     * Converts the topping's name to the corresponding {@link Topping} enum value.
     *
     * @param name The name of the topping.
     * @return The matching {@link Topping} enum value.
     * @throws IllegalArgumentException if the name does not match any known topping.
     */
    public Topping getToppingEnum(String name) {
        return switch (name.toUpperCase()) {
            case "BACON" -> Topping.BACON;
            case "BBQ CHICKEN" -> Topping.BBQ_CHICKEN;
            case "BEEF" -> Topping.BEEF;
            case "CHEDDAR" -> Topping.CHEDDAR;
            case "GARLIC" -> Topping.GARLIC;
            case "GREEN PEPPER" -> Topping.GREEN_PEPPER;
            case "HAM" -> Topping.HAM;
            case "MUSHROOM" -> Topping.MUSHROOM;
            case "ONION" -> Topping.ONION;
            case "PEPPERONI" -> Topping.PEPPERONI;
            case "PINEAPPLE" -> Topping.PINEAPPLE;
            case "PROVOLONE" -> Topping.PROVOLONE;
            case "SAUSAGE" -> Topping.SAUSAGE;
            default -> throw new IllegalArgumentException("Unknown topping: " + name);
        };
    }


}
