package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Plant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


// https://www.youtube.com/watch?v=hl0AcuplFwE

public class MyPlantsActivity extends AppCompatActivity {

    String currentUserId;
    private final int[] shelvesIDs = {R.id.SocculentShelf, R.id.tropicShelf, R.id.spiceShelf};
    private ArrayList<Integer> succulentIcons = new ArrayList<>();
    private ArrayList<Integer> tropicIcons = new ArrayList<>();
    private ArrayList<Integer> spicesIcons = new ArrayList<>();
    private ArrayList[] totalIcons = {succulentIcons, tropicIcons, spicesIcons};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plants);

        Intent intent = getIntent();
        currentUserId = intent.getStringExtra(MainActivity.EXTRA_USER_ID);
        readIconsFromDB();
    }

    private void addIcons(int shelfId, ArrayList<Integer> icons){
        LinearLayout shelf = findViewById(shelfId);
        for (int icon: icons) {
            shelf.addView(createImageView(icon));
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
                boolean noPlants = true;
                for (int i = 0; i < Plant.CATEGORIES.length; i++){
                    if (dataSnapshot.hasChild(Plant.CATEGORIES[i])){
                        noPlants = false;
                        for (DataSnapshot ds: dataSnapshot.child(Plant.CATEGORIES[i]).getChildren()){
                            totalIcons[i].add(ds.getValue(Integer.class));
                        }
                        addIcons(shelvesIDs[i], totalIcons[i]);
                    }
                }
                if(noPlants){
                    setNoPlants();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void launchMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 1);
    }

    public void addNewPlantButtonClicking(View view) {
        Intent intent = new Intent(this, ChooseCategoryActivity.class);
        intent.putExtra(MainActivity.EXTRA_USER_ID, currentUserId);
        startActivityForResult(intent, MainActivity.PLANT_REQUEST);
    }

    private void setNoPlants(){
        Button addNewPlant = findViewById(R.id.addPlantButton2);
        addNewPlant.setVisibility(View.VISIBLE);
        //TODO: set backgrounds and text "all of this can be yours"
    }
}
