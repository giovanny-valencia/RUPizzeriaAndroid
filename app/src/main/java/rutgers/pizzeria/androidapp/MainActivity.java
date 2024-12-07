package rutgers.pizzeria.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * MainActivity serves as the entry point of the application.
 * <p>
 * It provides the main menu interface where users can select a pizza type,
 * view the cart, or check previously placed orders. Each action triggers navigation
 * to the corresponding activity.
 * </p>
 *
 * @author Giovanny
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Initializes the activity and sets the main layout.
     * <p>
     * This method is called when the activity is first created. It links the activity
     * to the `activity_main` layout.
     * </p>
     *
     * @param savedInstanceState The saved state of the activity, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Link to the blank layout
        setContentView(R.layout.activity_main);
    }

    /**
     * Handles click events on pizza selection buttons.
     * <p>
     * Reads the tag of the clicked {@link ImageButton} to determine the selected pizza type
     * and navigates to {@link CreationActivity} to configure the selected pizza.
     * </p>
     *
     * @param view The view that was clicked (e.g., an ImageButton with a tag).
     */
    public void handlePizzaClick(View view) {
        ImageButton imgBtn = (ImageButton) view;
        String tag = (String) imgBtn.getTag(); // Read the tag
        Toast.makeText(this, "Clicked: " + tag, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, CreationActivity.class);
        intent.putExtra("pizzaSelected",tag);
        startActivity(intent);
    }

    /**
     * Navigates to the cart view.
     * <p>
     * This method starts the {@link ViewCart} activity, where users can view
     * and manage the current order.
     * </p>
     *
     * @param view The view that triggered this action (e.g., a button).
     */
    public void viewCart(View view) {
        Intent intent = new Intent(this, ViewCart.class);
        startActivity(intent);
    }

    /**
     * Navigates to the placed orders view.
     * <p>
     * This method starts the {@link PlacedOrders} activity, where users can review
     * a list of all previously placed orders.
     * </p>
     *
     * @param view The view that triggered this action (e.g., a button).
     */
    public void viewPlacedOrders(View view) {
        Intent intent = new Intent(this, PlacedOrders.class);
        startActivity(intent);
    }
}
