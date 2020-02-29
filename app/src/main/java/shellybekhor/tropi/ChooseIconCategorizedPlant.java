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

/**
 * This activity is enabling to choose the plant's icon for identification
 */
public class ChooseIconCategorizedPlant extends AppCompatActivity {

    // members //
    public static final String EXTRA_ICON = "shellybekhor.tropi.extra.ICON";
    private DatabaseReference mDatabase;
    public int currentCategory;
    public String currentCatName;
    public String currentUserId;

    // The different categories' icons //
    public int[] succulentsIcons = {R.drawable.ic_cactus1, R.drawable.ic_cactus2, R.drawable.ic_cactus3,
            R.drawable.ic_succulent1, R.drawable.ic_succulent2,
            R.drawable.ic_sansivera};
    public int[] spicesIcons = {R.drawable.ic_basil, R.drawable.ic_cherry_tomato, R.drawable.ic_mint,
            R.drawable.ic_oregano, R.drawable.ic_lettuce, R.drawable.ic_avocado};
    public int[] tropicalsIcons = {R.drawable.ic_boston_fern, R.drawable.ic_pink_syngonyum,
            R.drawable.ic_birdsnest, R.drawable.ic_ornata, R.drawable.ic_pothos,
            R.drawable.ic_banana, R.drawable.ic_rubber_piccus, R.drawable.ic_monstera};

    /**
     * Series of actions happening in the creation of the activity
     */
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

    /**
     * Setting the plant's category in order to create an icon
     */
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

    /**
     * Enabling the user to see the relevant icon in order to choose one
     * @param icons The relevant icons list
     */
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

    /**
     * Adding the new plant and icon to the DB under the user
     * @param chosenIcon The plant's icon
     * @param categoryName The plant's category
     */
    private void addPlantToDB(int chosenIcon, String categoryName) {
        DatabaseReference categoryRef = mDatabase.child(currentUserId).child(categoryName);
        categoryRef.push().setValue(chosenIcon);
    }

    /**
     * Launching the plants info activity
     */
    public void launchPlantInfo(int icon) {
        Intent intent = new Intent(this, PlantInfoActivity.class);
        intent.putExtra(ChooseCategoryActivity.EXTRA_CATEGORY, currentCategory);
        intent.putExtra(MainActivity.EXTRA_USER_ID, currentUserId);
        intent.putExtra(EXTRA_ICON, icon);
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
