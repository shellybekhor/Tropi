package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;

import shellybekhor.tropi.Plants.Succulent;
import shellybekhor.tropi.Plants.Spices;
import shellybekhor.tropi.Plants.Tropic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseCategoryActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "shellybekhor.tropi.extra.CATEGORY";
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        Intent intent = getIntent();
        currentUserId = intent.getStringExtra(MainActivity.EXTRA_USER_ID);
    }

    public void createSucculent(View view){
        launchCreatePlant(Succulent.CATEGORY);
    }

    public void createSpices(View view){
        launchCreatePlant(Spices.CATEGORY);
    }

    public void createTropic(View view){
        launchCreatePlant(Tropic.CATEGORY);
    }

    public void findCategory(View view) { launchFindCategory(); }

    public void launchCreatePlant(int category){
        Intent intent = new Intent(this, ChooseIconCategorizedPlant.class);
        intent.putExtra(EXTRA_CATEGORY, category);
        intent.putExtra(MainActivity.EXTRA_USER_ID, currentUserId);
        startActivityForResult(intent, 1);
    }

    public void launchFindCategory() {
        Intent intent = new Intent(this, FindCategoryActivity.class);
        intent.putExtra(MainActivity.EXTRA_USER_ID, currentUserId);
        startActivityForResult(intent, 1);
    }

    public void launchMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
