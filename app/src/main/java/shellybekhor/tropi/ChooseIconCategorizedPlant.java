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

    // todo - add an horizontal scroll
}
