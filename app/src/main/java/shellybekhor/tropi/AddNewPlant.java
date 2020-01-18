package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Plant;
import shellybekhor.tropi.Plants.Succulent;
import shellybekhor.tropi.Plants.Spices;
import shellybekhor.tropi.Plants.Tropic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewPlant extends AppCompatActivity {
    private static final String LOG_TAG = AddNewPlant.class.getSimpleName();
    public static final String EXTRA_PLANT = "shellybekhor.tropi.extra.PLANT";
    int currentCategory;
    String currentUserId;
    private DatabaseReference mDatabase;

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
//            case Special.CATEGORY:
//                newPlant = new Special(location, kind, nickname);
//                break;
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
