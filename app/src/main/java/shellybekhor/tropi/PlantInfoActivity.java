package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Spices;
import shellybekhor.tropi.Plants.Succulent;
import shellybekhor.tropi.Plants.Tropic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * This activity shows info on the categorized plant after adding it
 */
public class PlantInfoActivity extends AppCompatActivity {

    // class members //
    int currentCategory;
    String currentUserId;

    /**
     * Series of actions happening in the creation of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);

        Intent intent = getIntent();
        currentCategory = intent.getIntExtra(ChooseCategoryActivity.EXTRA_CATEGORY, 0);
        currentUserId = intent.getStringExtra(MainActivity.EXTRA_USER_ID);
        int iconIndex = intent.getIntExtra(ChooseIconCategorizedPlant .EXTRA_ICON, 0);
        editInfoText();
    }

    /**
     * Setting the correct text by category
     */
    private void editInfoText(){
        TextView info = findViewById(R.id.infoText);
        switch (currentCategory){
            case Succulent.CATEGORY:
                info.setText(R.string.SucculentInfo);
                break;
            case Tropic.CATEGORY:
                info.setText(R.string.TropicInfo);
                break;
            case Spices.CATEGORY:
                info.setText(R.string.SpiceInfo);
                break;
        }
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
     * Launch the My Plants activity
     */
    public void launchMyPlants(View view){
        Intent intent = new Intent(this, MyPlantsActivity.class);
        intent.putExtra(MainActivity.EXTRA_USER_ID, currentUserId);
        intent.putExtra(MyPlantsActivity.EXTRA_NP, currentCategory);
        startActivity(intent);
    }

}
