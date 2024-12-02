package rutgers.pizzeria.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreationActivity extends AppCompatActivity {

    String selectedPizza = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        Intent intent = getIntent();
        selectedPizza = intent.getStringExtra("pizzaSelected");
        TextView textViewSelectedPizza = findViewById(R.id.textViewSelectedPizza);
        textViewSelectedPizza.setText(selectedPizza);
    }

    //just returns to main activity
    public void onCancel(View view){
        finish();
    }

}