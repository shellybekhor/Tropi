package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;

import shellybekhor.tropi.Plants.Succulent;
import shellybekhor.tropi.Plants.Special;
import shellybekhor.tropi.Plants.Spices;
import shellybekhor.tropi.Plants.Tropic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class DefineCategory extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "shellybekhor.tropi.extra.CATEGORY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_category);
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
        Intent intent = new Intent(this, AddNewPlant.class);
        intent.putExtra(EXTRA_MESSAGE, category);
        startActivityForResult(intent, 1);

    }

    public void launchFindCategory() {
        Intent intent = new Intent(this, FindCategory.class);
        startActivityForResult(intent, 1);
    }

}
