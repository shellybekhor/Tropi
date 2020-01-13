package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ChooseIconCategorizedPlant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_icon_categorized_plant);
        TextView text = findViewById(R.id.CateogoryName);
//        text.setText(savedInstanceState.get()); // todo add the category name
    }

    int[] succulentsIcons = {R.drawable.ic_cactus1, R.drawable.ic_cactus2, R.drawable.ic_cactus3,
            R.drawable.ic_succulent1, R.drawable.ic_succulent2, R.drawable.ic_senecio,
            R.drawable.ic_sansiveria};
    int[] spicesIcons = {R.drawable.ic_basil, R.drawable.ic_cherry_tomato, R.drawable.ic_mint,
            R.drawable.ic_oregano, R.drawable.ic_lettuce};
    int[] tropicalsIcons = {R.drawable.ic_boston_fern, R.drawable.ic_syngonium,
            R.drawable.ic_birdsnest, R.drawable.ic_ornata, R.drawable.ic_pothos};

    // todo - add an horizontal scroll
}
