package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Spices;
import shellybekhor.tropi.Plants.Succulent;
import shellybekhor.tropi.Plants.Tropic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FindCategoryActivity extends AppCompatActivity {

    String currentUserId;
    /**
     * Current category, initialized to -1.
     */
    private int category = -1;
    /**
     * This field is holding the phase of the category search:
     * 0 - The phase of asking the first question and before first answer
     * 1 - after first answer and before the second
     * 2 - after second answer and before the third
     */
    private int phase = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_category);
        Intent intent = getIntent();
        currentUserId = intent.getStringExtra(MainActivity.EXTRA_USER_ID);
    }

    public void yesButtonOnClick(View view) {
        if (phase == 0) {
            category = Succulent.CATEGORY;
        } else if (phase == 1) {
            category = Tropic.CATEGORY;
        } else if (phase == 2) {
            category = Spices.CATEGORY;
        }
        launchCreatePlant(category);
    }

    public void noButtonOnClick(View view) {
        if (phase == 0) {
            phase = 1;
            TextView question = findViewById(R.id.categoryQuestion);
            question.setText(R.string.categoryQuestion2);
        }
        else if (phase == 1) {
            phase = 2;
            TextView question = findViewById(R.id.categoryQuestion);
            question.setText(R.string.categoryQuestion3);
        }
        else if (phase == 2) {
            category = Tropic.CATEGORY;
            launchCreatePlant(category);
        }
    }

    private void launchCreatePlant(int category){
        Intent intent = new Intent(this, ChooseIconCategorizedPlant.class);
        intent.putExtra(ChooseCategoryActivity.EXTRA_CATEGORY, category);
        intent.putExtra(MainActivity.EXTRA_USER_ID, currentUserId);
        startActivityForResult(intent, 1);
    }


}
