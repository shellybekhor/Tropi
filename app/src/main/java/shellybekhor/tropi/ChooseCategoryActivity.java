package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;

import shellybekhor.tropi.Plants.Succulent;
import shellybekhor.tropi.Plants.Spices;
import shellybekhor.tropi.Plants.Tropic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The activity enables to choose a plant's category
 */
public class ChooseCategoryActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "shellybekhor.tropi.extra.CATEGORY";
    String currentUserId;

    /**
     * Series of actions happening in the creation of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        Intent intent = getIntent();
        currentUserId = intent.getStringExtra(MainActivity.EXTRA_USER_ID);
    }

    /**
     * Creating a Succulent plant
     */
    public void createSucculent(View view){
        launchCreatePlant(Succulent.CATEGORY);
    }

    /**
     * Creating a Spice plant
     */
    public void createSpices(View view){
        launchCreatePlant(Spices.CATEGORY);
    }

    /**
     * Creating a Tropic plant
     */
    public void createTropic(View view){
        launchCreatePlant(Tropic.CATEGORY);
    }

    /**
     * Starting the find category activity in case the plant category is not known
     */
    public void findCategory(View view) { launchFindCategory(); }

    /**
     * Lanching the create plant process
     */
    public void launchCreatePlant(int category){
        Intent intent = new Intent(this, ChooseIconCategorizedPlant.class);
        intent.putExtra(EXTRA_CATEGORY, category);
        intent.putExtra(MainActivity.EXTRA_USER_ID, currentUserId);
        startActivityForResult(intent, 1);
    }

    /**
     * Launching the find category activity in case the plant category is not known
     */
    public void launchFindCategory() {
        Intent intent = new Intent(this, FindCategoryActivity.class);
        intent.putExtra(MainActivity.EXTRA_USER_ID, currentUserId);
        startActivityForResult(intent, 1);
    }

    /**
     * Launching the main activity
     */
    public void launchMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
