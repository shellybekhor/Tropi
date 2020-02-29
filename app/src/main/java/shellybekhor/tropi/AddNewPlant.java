package shellybekhor.tropi;

// IMPORTS //
import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Plant;
import shellybekhor.tropi.Plants.Succulent;
import shellybekhor.tropi.Plants.Spices;
import shellybekhor.tropi.Plants.Tropic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Activity that adds a new plant
 */
public class AddNewPlant extends AppCompatActivity {

    // Members //
    public static final String EXTRA_PLANT = "shellybekhor.tropi.extra.PLANT";
    private static final String LOG_TAG = AddNewPlant.class.getSimpleName();
    public int currentCategory;
    public String currentUserId;
    private DatabaseReference mDatabase;

    /**
     * Series of actions happening in the creation of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_plant);
        Intent intent = getIntent();
        int category = intent.getIntExtra(ChooseCategoryActivity.EXTRA_CATEGORY, 0);
        currentUserId = intent.getStringExtra(MainActivity.EXTRA_USER_ID);
        currentCategory = category;
        Log.d(LOG_TAG, String.valueOf(category));
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Adding the new plant
     */
    public void plantDone(View view){
//        EditText locationTextView = findViewById(R.id.locationText);
//        String location = locationTextView.getText().toString();
//        EditText nicknameTextView = findViewById(R.id.nicknameText);
//        String nickname = nicknameTextView .getText().toString();
//        EditText kindTextView = findViewById(R.id.kindText);
//        String kind = kindTextView.getText().toString();

        Plant newPlant;
        switch (currentCategory){
            case Succulent.CATEGORY:
                newPlant = new Succulent();
                break;
            case Tropic.CATEGORY:
                newPlant = new Tropic();
                break;
            case Spices.CATEGORY:
                newPlant = new Spices();
                break;
            default:
                newPlant = null;
        }

        String key = mDatabase.child(currentUserId).push().getKey();
        mDatabase.child(currentUserId).child(key).setValue(newPlant);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_PLANT, newPlant);
        startActivityForResult(intent, 1);
    }
}
