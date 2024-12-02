package rutgers.pizzeria.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerCrustStylePizza;

    Spinner spinnerSizePizza;

    String selectedPizza = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        Intent intent = getIntent();
        selectedPizza = intent.getStringExtra("pizzaSelected");
        TextView textViewSelectedPizza = findViewById(R.id.textViewSelectedPizza);
        textViewSelectedPizza.setText(selectedPizza);

        spinnerCrustStylePizza = findViewById(R.id.spinnerCrustStylePizza);
        spinnerSizePizza = findViewById(R.id.spinnerSizePizza);

        ArrayAdapter<CharSequence> adapterCrust = ArrayAdapter.createFromResource(this, R.array.crust, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(this, R.array.size, android.R.layout.simple_spinner_item);

        adapterCrust.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCrustStylePizza.setAdapter(adapterCrust);
        spinnerCrustStylePizza.setOnItemSelectedListener(this);
        spinnerSizePizza.setAdapter(adapterSize);
        spinnerSizePizza.setOnItemSelectedListener(this);
    }

    //just returns to main activity
    public void onCancel(View view){
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}