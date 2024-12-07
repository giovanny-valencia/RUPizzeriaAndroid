package rutgers.pizzeria.androidapp.recyclerview;

import android.content.Context;
import java.util.ArrayList;

import rutgers.pizzeria.androidapp.R;
import rutgers.pizzeria.androidapp.models.pizza.Topping;

public class ToppingModel {
    private final String name;
    private final int imageResId;
    private boolean isChecked;
    private boolean isEditable;



    // Static list to hold all toppings
    private static final ArrayList<ToppingModel> toppings = new ArrayList<>();

    // images
    private static final int[] img = {
            R.drawable.icon_topping_bacon, R.drawable.icon_topping_bbq_chicken, R.drawable.icon_topping_beef,
            R.drawable.icon_topping_cheddar, R.drawable.icon_topping_garlic, R.drawable.icon_topping_green_pepper,
            R.drawable.icon_topping_ham, R.drawable.icon_topping_mushroom, R.drawable.icon_topping_onions,
            R.drawable.icon_topping_pepperoni, R.drawable.icon_topping_pineapple, R.drawable.icon_topping_provolone,
            R.drawable.icon_topping_sausage
    };

    private ToppingModel(String name, int imageResId, boolean isChecked, boolean isEditable) {
        this.name = name;
        this.imageResId = imageResId;
        this.isChecked = isChecked;
        this.isEditable = isEditable;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public boolean isChecked(){
        return isChecked;
    }

    public void setChecked(boolean checked){
        isChecked = checked;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    // Set up toppings with a context to access resources

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
    // called by main activity to get and pass creation the toppings

    public static ArrayList<ToppingModel> getToppings(Context context) {
        if (toppings.isEmpty()){
            setUpToppings(context);
        }

        return toppings;
    }

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
