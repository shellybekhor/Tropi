package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Plant;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


// https://www.youtube.com/watch?v=hl0AcuplFwE

public class MyPlantsActivity extends AppCompatActivity {

    String currentUserId;
    private ArrayList<Integer> succulentIcons = new ArrayList<>();
    private ArrayList<Integer> tropicIcons = new ArrayList<>();
    private ArrayList<Integer> spicesIcons = new ArrayList<>();
    private ArrayList[] icons = {succulentIcons, tropicIcons, spicesIcons};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plants);

        Intent intent = getIntent();
        currentUserId = intent.getStringExtra(MainActivity.EXTRA_USER_ID);
        readIconsFromDB();
    }

    private void addSucculents(){
        LinearLayout socculentShelf = findViewById(R.id.SocculentShelf);
        for (int icon: succulentIcons) {
            socculentShelf.addView(createImageView(icon));
        }
    }

    private void addSpices(){
        LinearLayout succulentShelf = findViewById(R.id.spiceShelf);
        for (int icon: spicesIcons) {
            succulentShelf.addView(createImageView(icon));
        }
    }

    private void addTropic(){
        LinearLayout succulentShelf = findViewById(R.id.tropicShelf);
        for (int icon: tropicIcons) {
            succulentShelf.addView(createImageView(icon));
        }
    }

    private ImageView createImageView(int resource){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(resource);
        return imageView;
    }

    private void readIconsFromDB(){
        DatabaseReference userDB = FirebaseDatabase.getInstance().getReference(currentUserId);
        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (int i = 0; i < Plant.CATEGORIES.length; i++){
                    if (dataSnapshot.hasChild(Plant.CATEGORIES[i])){
                        for (DataSnapshot ds: dataSnapshot.child(Plant.CATEGORIES[i]).getChildren()){
                            icons[i].add(ds.getValue(Integer.class));
                        }
                    }
                }
                addSucculents();
                addSpices();
                addTropic();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void launchMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
