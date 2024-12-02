package rutgers.pizzeria.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Link to the blank layout
        setContentView(R.layout.activity_main);
    }

    public void handlePizzaClick(View view) {
        ImageButton imgBtn = (ImageButton) view;
        String tag = (String) imgBtn.getTag(); // Read the tag
        Toast.makeText(this, "Clicked: " + tag, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, CreationActivity.class);

        intent.putExtra("pizzaSelected",tag);

        startActivity(intent);
    }
}
