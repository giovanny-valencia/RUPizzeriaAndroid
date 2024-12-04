package rutgers.pizzeria.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ToppingModel> toppings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Link to the blank layout
        setContentView(R.layout.activity_main);

        //create the topping layout models since this only needs to happen once on app startup
    }

    public void handlePizzaClick(View view) {
        ImageButton imgBtn = (ImageButton) view;
        String tag = (String) imgBtn.getTag(); // Read the tag
        Toast.makeText(this, "Clicked: " + tag, Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(this, CreationActivity.class);

        intent.putExtra("pizzaSelected",tag);

        startActivity(intent);
    }

    public void viewCart(View view) {
        ImageButton imgBtn = (ImageButton) view;
        String tag = (String) imgBtn.getTag(); // Read the tag
        //Toast.makeText(this, "Clicked: " + tag, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ViewCart.class);

        //intent.putExtra("pizzaSelected",tag);

        startActivity(intent);
    }

    public void viewPlacedOrders(View view) {
        ImageButton imgBtn = (ImageButton) view;
        String tag = (String) imgBtn.getTag(); // Read the tag
        //Toast.makeText(this, "Clicked: " + tag, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, PlacedOrders.class);

        //intent.putExtra("pizzaSelected",tag);

        startActivity(intent);
    }
}
