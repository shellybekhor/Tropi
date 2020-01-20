package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Plant;
import shellybekhor.tropi.ui.login.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.AccessToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * This activity is the main screen of the app to which we enter first.
 * It holds the To-Do list of the plants, an option to add a new plant
 * and the daily Tropy-Tip.
 */
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "shellybekhor.tropi.extra.USERID";
    public static final int PLANT_REQUEST = 0;
    ArrayList<Plant> plants = new ArrayList<>();
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Plant newPlant = (Plant) getIntent().getSerializableExtra(AddNewPlant.EXTRA_PLANT);
            plants.add(newPlant);
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        connectUser();
        getPlantsDatabase();
    }

    private void connectUser() {
        if (!checkIfUserLoggedInFacebook() && mAuth.getCurrentUser() == null){
            launchLoginActivity();
        }
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void getPlantsDatabase() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUserId = currentUser.getUid();
            mDatabase.child(currentUserId);

            DatabaseReference userDB = FirebaseDatabase.getInstance().getReference(currentUserId);
            userDB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (String cat: Plant.CATEGORIES){
                        if (dataSnapshot.hasChild(cat)){
                            for (DataSnapshot ds: dataSnapshot.getChildren()){
//                                int icon = ds.getValue(Integer.class);
                            }
                        }
                    }

                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        plants.add(ds.getValue(Plant.class));
                    }
                    System.out.println(plants);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }
    }

    private boolean checkIfUserLoggedInFacebook(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && !accessToken.isExpired();
    }

    /**
     * Takes us to the add new plant activity.
     */
    public void addNewPlantButtonClicking(View view) {
        Intent intent = new Intent(this, ChooseCategoryActivity.class);
        intent.putExtra(EXTRA_USER_ID, currentUserId);
        startActivityForResult(intent, PLANT_REQUEST);
    }

    public void goToMyPlants(View view) {
        Intent intent = new Intent(this, MyPlantsActivity.class);
        intent.putExtra(EXTRA_USER_ID, currentUserId);
        startActivity(intent);
    }

    public void signOut(View view){
        mAuth.signOut();
        launchLoginActivity();
    }

    @Override
    public void onResume(){
        super.onResume();
        getPlantsDatabase();
    }

}
