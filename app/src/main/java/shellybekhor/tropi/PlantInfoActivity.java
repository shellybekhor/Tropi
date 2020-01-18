package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Spices;
import shellybekhor.tropi.Plants.Succulent;
import shellybekhor.tropi.Plants.Tropic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlantInfoActivity extends AppCompatActivity {

    int currentCategory;
    String currentUserId;

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

    public void launchMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void launchMyPlants(View view){
        Intent intent = new Intent(this, MyPlantsActivity.class);
        startActivity(intent);
    }

}
