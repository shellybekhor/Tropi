package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Plant;
import shellybekhor.tropi.Plants.Succulent;
import shellybekhor.tropi.Plants.Special;
import shellybekhor.tropi.Plants.Spices;
import shellybekhor.tropi.Plants.Tropic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddNewPlant extends AppCompatActivity {
    private static final String LOG_TAG = AddNewPlant.class.getSimpleName();
    public static final String EXTRA_PLANT = "shellybekhor.tropi.extra.PLANT";
    int currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_plant);
        Intent intent = getIntent();
        int category = intent.getIntExtra(DefineCategory.EXTRA_CATEGORY, 0);
        currentCategory = category;
        Log.d(LOG_TAG, String.valueOf(category));
    }

    public void plantDone(View view){
        EditText locationTextView = findViewById(R.id.locationText);
        String location = locationTextView.getText().toString();
        EditText nicknameTextView = findViewById(R.id.nicknameText);
        String nickname = nicknameTextView .getText().toString();
        EditText kindTextView = findViewById(R.id.kindText);
        String kind = kindTextView.getText().toString();

        Plant newPlant;
        switch (currentCategory){
            case Succulent.CATEGORY:
                newPlant = new Succulent(location, kind, nickname);
                break;
            case Tropic.CATEGORY:
                newPlant = new Tropic(location, kind, nickname);
                break;
            case Spices.CATEGORY:
                newPlant = new Spices(location, kind, nickname);
                break;
            case Special.CATEGORY:
                newPlant = new Special(location, kind, nickname);
                break;
            default:
                newPlant = null;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_PLANT, newPlant);
        startActivityForResult(intent, MainActivity.PLANT_REQUEST);
    }
}
