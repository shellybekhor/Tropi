package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Plant;
import shellybekhor.tropi.Plants.Spices;
import shellybekhor.tropi.Plants.Succulent;
import shellybekhor.tropi.Plants.Tropic;
import shellybekhor.tropi.ui.login.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;


/**
 * This activity is the main screen of the app to which we enter first.
 * It holds the To-Do list of the plants, an option to add a new plant
 * and the daily Tropy-Tip.
 */
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "shellybekhor.tropi.extra.USERID";
    public static final int PLANT_REQUEST = 0;
    public static final int TIP_WIDTH = 170;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String currentUserId;
    boolean tipOpen = false;
    /**
     * This hash map holds the different categories and an int representing
     * if they have a relevant task open (=1) or not(=0).
     */
    public static HashMap<String, Integer> currentTasks = new HashMap<String, Integer> ();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentTasks.put("Succulent", 0);
        currentTasks.put("Tropic", 0);
        currentTasks.put("Spice", 0);
        connectUser();
        getPlantsDatabase();
        addListenerOnTasks();
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
                    boolean noPlants = true;
                    for (String cat: Plant.CATEGORIES){
                        if (dataSnapshot.hasChild(cat)){
                            noPlants = false;
                            setTask(cat);
                        }
                    }
                    if (noPlants){
                        setEmptyCheckList();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }
    }

    private void addListenerOnTasks() {
        succulentCategoryListener(R.id.checkboxSucculent);
        tropicCategoryListener(R.id.checkboxTropic);
        spicesCategoryListener(R.id.checkboxSpices);
    }

    private void succulentCategoryListener(int checkBoxId) {
        final CheckBox checkBox = findViewById(checkBoxId);
        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(MainActivity.this,
                            "Well done!", Toast.LENGTH_LONG).show();
                     v.setVisibility(View.INVISIBLE);
                    final TextView text = findViewById(R.id.taskTextSucculent);
                    text.setVisibility(View.INVISIBLE);
                    if (isEmptyCheckList()) {
                        setEmptyCheckList();
                    }
                }
            }
        });
    }

    private void tropicCategoryListener(int checkBoxId) {
        final CheckBox checkBox = findViewById(checkBoxId);
        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(MainActivity.this,
                            "Well done!", Toast.LENGTH_LONG).show();
                    v.setVisibility(View.INVISIBLE);
                    final TextView text = findViewById(R.id.taskTextTropic);
                    text.setVisibility(View.INVISIBLE);
                    if (isEmptyCheckList()) {
                        setEmptyCheckList();
                    }
                }
            }
        });
    }

    private void spicesCategoryListener(int checkBoxId) {
        final CheckBox checkBox = findViewById(checkBoxId);
        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(MainActivity.this,
                            "Well done!", Toast.LENGTH_LONG).show();
                    v.setVisibility(View.INVISIBLE);
                    final TextView text = findViewById(R.id.taskTextSpices);
                    text.setVisibility(View.INVISIBLE);
                    if (isEmptyCheckList()) {
                        setEmptyCheckList();
                    }
                }
            }
        });
    }

    private boolean isEmptyCheckList() {
        View succulentCheckBox = findViewById(R.id.checkboxSucculent);
        View tropicCheckBox = findViewById(R.id.checkboxTropic);
        View spicesCheckBox = findViewById(R.id.checkboxSpices);

        return ((succulentCheckBox.getVisibility() == View.INVISIBLE) &&
                (tropicCheckBox.getVisibility() == View.INVISIBLE) &&
                (spicesCheckBox.getVisibility() == View.INVISIBLE));
    }


    private void setTask(String categoryName) {

        if (currentTasks.get(categoryName) == 1) { return; }
        int glassesOfWater;
        int daysBetweenWatering;
        Calendar lastWateingDate;
        switch (categoryName)
        {
            case "Succulent":
                glassesOfWater = Succulent.GLASSES_PER_WATERING;
                daysBetweenWatering = Succulent.WATER_EVERY_X_DAYS;
                lastWateingDate = Succulent.getLastWatering();
                break;
            case "Tropic":
                glassesOfWater = Tropic.GLASSES_PER_WATERING;
                daysBetweenWatering = Tropic.WATER_EVERY_X_DAYS;
                lastWateingDate = Tropic.getLastWatering();
                break;
            case "Spice":
                glassesOfWater = Spices.GLASSES_PER_WATERING;
                daysBetweenWatering = Spices.WATER_EVERY_X_DAYS;
                lastWateingDate = Spices.getLastWatering();
                break;
            default:
                glassesOfWater = Succulent.GLASSES_PER_WATERING;
                daysBetweenWatering = Tropic.WATER_EVERY_X_DAYS;
                lastWateingDate = Succulent.getLastWatering();
        }

        // if today is the day - create task and add it
        // todo - fix logic with dates.
        Calendar todayDate = Calendar.getInstance();
        if (lastWateingDate == null) {
            lastWateingDate = todayDate;
        }
        long diff = todayDate.getTimeInMillis() - lastWateingDate.getTimeInMillis();
        float daysDiff = (float) diff / (24 * 60 * 60 * 1000);
        if ((daysDiff >= daysBetweenWatering) || (daysDiff == 0)) {
            Task task = new Task(categoryName, glassesOfWater);
            addTaskToScroll(task);
            currentTasks.put(categoryName, 1);
        }
    }


    private void addTaskToScroll(Task task) {
        LinearLayout toDoList = findViewById(R.id.allTasksLinear);
        if (task._categoryName.equals(getResources().getString(R.string.Succulent))) {
            toDoList.setVisibility(View.VISIBLE);
            View cb = findViewById(R.id.checkboxSucculent);
            cb.setVisibility(View.VISIBLE);
            TextView text = findViewById(R.id.taskTextSucculent);
            text.setText(task._taskText);
        }
        else if (task._categoryName.equals(getResources().getString(R.string.Tropic))) {
            toDoList.setVisibility(View.VISIBLE);
            View cb = findViewById(R.id.checkboxSucculent);
            cb.setVisibility(View.VISIBLE);
            TextView text = findViewById(R.id.taskTextSucculent);
            text.setText(task._taskText);
        }
        else {
            toDoList.setVisibility(View.VISIBLE);
            View cb = findViewById(R.id.checkboxSucculent);
            cb.setVisibility(View.VISIBLE);
            TextView text = findViewById(R.id.taskTextSucculent);
            text.setText(task._taskText);
        }
    }

    private void setEmptyCheckList() {
        ScrollView toDoList = findViewById(R.id.plantsToDoList);
        toDoList.setBackgroundResource(R.drawable.ic_nothing_to_do_text);
        View tasks = findViewById(R.id.allTasksLinear);
        tasks.setVisibility(View.INVISIBLE);
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

    public void openTropiTip(View view){
        TextView text = (TextView) view;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (tipOpen) {
            params.width -= TIP_WIDTH;
            text.setText(R.string.tip_title);
            tipOpen = false;
        }
        else{
            params.width += TIP_WIDTH;
            text.setText(randomTip());
            tipOpen = true;
        }
        view.setLayoutParams(params);
    }

    private String randomTip(){
        Random r = new Random();
        String[] tips = {"You can water with a sprinkler!",
            "Sometimes clean your plants from dust, it makes them happier",
            "Don't forget to cut the dead leaves",
            "Take your plants to the sun for a few minutes sometimes",
            "You can change some twigs with your friends",
            "You can water with distilled water sometimes"};
        int i = r.nextInt(tips.length);
        return tips[i];
    }
}
