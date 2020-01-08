package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Plant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * This activity is the main screen of the app to which we enter first.
 * It holds the To-Do list of the plants, an option to add a new plant
 * and the daily Tropy-Tip.
 */
public class MainActivity extends AppCompatActivity {

    public static final int PLANT_REQUEST = 0;
    ArrayList<Plant> plants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Plant newPlant = (Plant) getIntent().getSerializableExtra(AddNewPlant.EXTRA_PLANT);
            plants.add(newPlant);
//            TextView t = findViewById(R.id.tropyTipText);
//            t.setText(String.valueOf(plants.size()));
        }
    }

    /**
     * Takes us to the add new plant activity.
     */
    public void addNewPlantButtonClicking(View view) {
        Intent intent = new Intent(this, DefineCategory.class);
        intent.putExtra(EXTRA_MESSAGE, "");
        startActivityForResult(intent, PLANT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLANT_REQUEST) {
            if (resultCode == RESULT_OK) {
                Plant newPlant = (Plant) data.getSerializableExtra(AddNewPlant.EXTRA_PLANT);
                plants.add(newPlant);
//                TextView t = findViewById(R.id.tropyTipText);
//                t.setText(plants.size());
            }
        }
    }




}
