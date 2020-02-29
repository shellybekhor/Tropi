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
 * The activity enables to find plant's category if not originaly known,
 * by answering a few identification questions
 */
public class FindCategoryActivity extends AppCompatActivity {

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
    /**
     * The user id.
     */
    public String currentUserId;

    /**
     * Series of actions happening in the creation of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_category);
        Intent intent = getIntent();
        currentUserId = intent.getStringExtra(MainActivity.EXTRA_USER_ID);
    }

    /**
     * Leading to the next step of creating a plant in case of 'YES' click
     */
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

    /**
     * Leading to the next step in case of 'NO' click
     */
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

    /**
     * Launching the create plant activity after choosing its category
     */
    private void launchCreatePlant(int category){
        Intent intent = new Intent(this, ChooseIconCategorizedPlant.class);
        intent.putExtra(ChooseCategoryActivity.EXTRA_CATEGORY, category);
        intent.putExtra(MainActivity.EXTRA_USER_ID, currentUserId);
        startActivityForResult(intent, 1);
    }

    /**
     * Launching main activity
     */
    public void launchMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
