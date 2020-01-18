package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


// https://www.youtube.com/watch?v=hl0AcuplFwE

public class MyPlantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plants);

        LinearLayout socculentShelf = findViewById(R.id.SocculentShelf);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0 ; i < 3; i++) {
            View view =inflater.inflate(R.layout.socculent_item, socculentShelf, false);
            TextView textView = view.findViewById(R.id.socculentName);
            textView.setText("item" + i);

            ImageView imageView = view.findViewById(R.id.socculentImage);
            imageView.setImageResource(R.mipmap.ic_launcher);

            socculentShelf.addView(view);
        }
    }

    public void launchMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
