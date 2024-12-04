package rutgers.pizzeria.androidapp;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class ToppingModel {
    private final String name;
    private final int imageResId;

    // Static list to hold all toppings
    private static final List<ToppingModel> toppings = new ArrayList<>();

    // images
    private static final int[] img = {
            R.drawable.icon_topping_bacon, R.drawable.icon_topping_bbq_chicken, R.drawable.icon_topping_beef,
            R.drawable.icon_topping_cheddar, R.drawable.icon_topping_garlic, R.drawable.icon_topping_green_pepper,
            R.drawable.icon_topping_ham, R.drawable.icon_topping_mushroom, R.drawable.icon_topping_onions,
            R.drawable.icon_topping_pepperoni, R.drawable.icon_topping_pineapple, R.drawable.icon_topping_provolone,
            R.drawable.icon_topping_sausage
    };

    private ToppingModel(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    // Set up toppings with a context to access resources
    private static void setUpToppings(Context context) {
        if (!toppings.isEmpty()) {
            return; // Prevent duplicate initialization
        }

        String[] names = context.getResources().getStringArray(R.array.toppings);

        for (int i = 0; i < names.length; i++){
            ToppingModel topping = new ToppingModel(names[i], img[i]);
            toppings.add(topping);
        }
    }

    // called by main activity to get and pass creation the toppings
    public List<ToppingModel> getToppings(Context context) {
        if (toppings.isEmpty()){
            setUpToppings(context);
        }

        return toppings;
    }
}
