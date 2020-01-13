package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Spices;
import shellybekhor.tropi.Plants.Succulent;
import shellybekhor.tropi.Plants.Tropic;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseIconCategorizedPlant extends AppCompatActivity {

    int currentCategory;
    String currentUserId;
    public static final String EXTRA_ICON = "shellybekhor.tropi.extra.ICON";
    int[] succulentsIcons = {R.drawable.ic_cactus1, R.drawable.ic_cactus2, R.drawable.ic_cactus3,
            R.drawable.ic_succulent1, R.drawable.ic_succulent2, R.drawable.ic_senecio,
            R.drawable.ic_sansiveria};
    int[] spicesIcons = {R.drawable.ic_basil, R.drawable.ic_cherry_tomato, R.drawable.ic_mint,
            R.drawable.ic_oregano, R.drawable.ic_lettuce};
    int[] tropicalsIcons = {R.drawable.ic_boston_fern, R.drawable.ic_syngonium,
            R.drawable.ic_birdsnest, R.drawable.ic_ornata, R.drawable.ic_pothos};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_icon_categorized_plant);
        Intent intent = getIntent();
        int category = intent.getIntExtra(DefineCategory.EXTRA_CATEGORY, 0);
        currentUserId = intent.getStringExtra(MainActivity.EXTRA_USER_ID);
        currentCategory = category;
        setCatagory();
    }

    private void setCatagory(){
        TextView text = findViewById(R.id.CateogoryName);

        switch (currentCategory){
            case Succulent.CATEGORY:
                text.setText("Succulent");
                defineIcons(succulentsIcons);
                break;
            case Tropic.CATEGORY:
                text.setText("Tropic");
                defineIcons(spicesIcons);
                break;
            case Spices.CATEGORY:
                text.setText("Spices");
                defineIcons(tropicalsIcons);
                break;
        }
    }

    private void defineIcons(int[] icons){
        LinearLayout iconsScroll = findViewById(R.id.layoutScroll);
        for (int i: icons){
            ImageView singleIcon = new ImageView(this);
            singleIcon.setBackgroundResource(i);
            iconsScroll.addView(singleIcon);
        }
    }

    public void launchPlantInfo(int icon){
        Intent intent = new Intent(this, PlantInfo.class);
        intent.putExtra(DefineCategory.EXTRA_CATEGORY, currentCategory);
        intent.putExtra(MainActivity.EXTRA_USER_ID, currentUserId);
        intent.putExtra(EXTRA_ICON, icon);
        startActivityForResult(intent, 1);
    }
}
