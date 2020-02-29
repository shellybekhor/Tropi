package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Showing the plants shelves when their still empty
 */
public class NoPlantsActivity extends AppCompatActivity {
    String currentUserId;

    /**
     * Series of actions happening in the creation of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_plants);
        Intent intent = getIntent();
        currentUserId = intent.getStringExtra(MainActivity.EXTRA_USER_ID);
    }

    /**
     * Launch the main activity
     */
    public void launchMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * Handeling the "add new plant" button's click
     */
    public void addNewPlantButtonClicking(View view) {
        Intent intent = new Intent(this, ChooseCategoryActivity.class);
        intent.putExtra(MainActivity.EXTRA_USER_ID, currentUserId);
        startActivityForResult(intent, MainActivity.PLANT_REQUEST);
    }
}
