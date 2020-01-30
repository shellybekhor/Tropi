package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;

import shellybekhor.tropi.Plants.Spices;
import shellybekhor.tropi.Plants.Succulent;
import shellybekhor.tropi.Plants.Tropic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseIconCategorizedPlant extends AppCompatActivity {

    private DatabaseReference mDatabase;
    int currentCategory;
    String currentCatName;
    String currentUserId;
    public static final String EXTRA_ICON = "shellybekhor.tropi.extra.ICON";
    int[] succulentsIcons = {R.drawable.ic_cactus1, R.drawable.ic_cactus2, R.drawable.ic_cactus3,
            R.drawable.ic_succulent1, R.drawable.ic_succulent2,
            R.drawable.ic_sansivera};
    int[] spicesIcons = {R.drawable.ic_basil, R.drawable.ic_cherry_tomato, R.drawable.ic_mint,
            R.drawable.ic_oregano, R.drawable.ic_lettuce, R.drawable.ic_avocado};
    int[] tropicalsIcons = {R.drawable.ic_boston_fern, R.drawable.ic_pink_syngonyum,
            R.drawable.ic_birdsnest, R.drawable.ic_ornata, R.drawable.ic_pothos,
            R.drawable.ic_banana, R.drawable.ic_rubber_piccus, R.drawable.ic_monstera};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_icon_categorized_plant);
        Intent intent = getIntent();
        int category = intent.getIntExtra(ChooseCategoryActivity.EXTRA_CATEGORY, 0);
        currentUserId = intent.getStringExtra(MainActivity.EXTRA_USER_ID);
        currentCategory = category;
        setCatagory();

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void setCatagory() {
        TextView text = findViewById(R.id.CateogoryName);

        switch (currentCategory) {
            case Succulent.CATEGORY:
                text.setText(R.string.Succulent);
                currentCatName = getResources().getString(R.string.Succulent);
                defineIcons(succulentsIcons);
                break;
            case Tropic.CATEGORY:
                text.setText(R.string.Tropic);
                currentCatName = getResources().getString(R.string.Tropic);
                defineIcons(tropicalsIcons);
                break;
            case Spices.CATEGORY:
                text.setText(R.string.Spice);
                currentCatName = getResources().getString(R.string.Spice);
                defineIcons(spicesIcons);
                break;
        }
    }

    private void defineIcons(int[] icons) {
        LinearLayout iconsScroll = findViewById(R.id.layoutScrollIcons);
        for (int i : icons) {
            ImageView singleIcon = new ImageView(this);
            singleIcon.setBackgroundResource(i);
            singleIcon.setTag(i);
            singleIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int icon = (int) v.getTag();
                    addPlantToDB(icon, currentCatName);
                    launchPlantInfo(icon);
                }
            });
            iconsScroll.addView(singleIcon);
        }
    }

    private void addPlantToDB(int chosenIcon, String categoryName) {
        DatabaseReference categoryRef = mDatabase.child(currentUserId).child(categoryName);
        categoryRef.push().setValue(chosenIcon);
    }

    public void launchPlantInfo(int icon) {
        Intent intent = new Intent(this, PlantInfoActivity.class);
        intent.putExtra(ChooseCategoryActivity.EXTRA_CATEGORY, currentCategory);
        intent.putExtra(MainActivity.EXTRA_USER_ID, currentUserId);
        intent.putExtra(EXTRA_ICON, icon);
        startActivityForResult(intent, 1);
    }

    public void launchMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
