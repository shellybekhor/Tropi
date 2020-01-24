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
    public static final String SUCCULENT = "Succulent";
    public static final String TROPIC = "Tropic";
    public static final String SPICES = "Spice";
    public static final String WELL_DONE = "Well done!";
    public static final int PLANT_REQUEST = 0;
    public static final int TIP_WIDTH = 170;
    public static final int REQUEST_CODE = 1;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String currentUserId;
    boolean tipOpen = false;
    boolean noPlants = true;


    // TROPY TIPS //
    public static final String TIP_1 = "You can water with a sprinkler!";
    public static final String TIP_2 = "Clean your plants from dust, " +
                                        "it makes them happier";
    public static final String TIP_3 = "Don't forget to cut the dead leaves";
    public static final String TIP_4 = "Take the plants to the sun for a few minutes, they love it";
    public static final String TIP_5 = "You can change some twigs with your friends";
    public static final String TIP_6 = "Use distilled water for once in a while";
    public static final String TIP_7 = "Take your tropic plants with you to the shower, " +
                                        "they love the steams";
    public static final String TIP_8 = "Use big pots for your plants";
    public static final String TIP_9 = "If your plants only grow upwards and not to the sides " +
                                        "they need more sun!";
    public static final String TIP_10 = "If you see pests on the plant, take them off and spray" +
                                        "with water and soap";


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
        currentTasks.put(SUCCULENT, 0);
        currentTasks.put(TROPIC, 0);
        currentTasks.put(SPICES, 0);
        initWateredToday();
        connectUser();
        getPlantsDatabase();
        addListenerOnTasks();
    }

    private void initWateredToday() {
        Succulent succulent = new Succulent();
        updatePlantWateredToday(succulent);
        Tropic tropic = new Tropic();
        updatePlantWateredToday(tropic);
        Spices spices = new Spices();
        updatePlantWateredToday(spices);
    }

    private void updatePlantWateredToday(Plant plant) {
        if (plant.isWateredToday() &&
                plant.getLastWatering() != Calendar.getInstance()) {
            plant.setWateredToday(false);
        }
    }


    private void connectUser() {
        if (!checkIfUserLoggedInFacebook() && mAuth.getCurrentUser() == null){
            launchLoginActivity();
        }
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
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
                            WELL_DONE, Toast.LENGTH_LONG).show();
                     v.setVisibility(View.INVISIBLE);
                    final TextView text = findViewById(R.id.taskTextSucculent);
                    text.setVisibility(View.INVISIBLE);
                    Succulent succulent = new Succulent();
                    succulent.setWateredToday(true);
                    currentTasks.put(SUCCULENT, 0);
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
                            WELL_DONE, Toast.LENGTH_LONG).show();
                    v.setVisibility(View.INVISIBLE);
                    final TextView text = findViewById(R.id.taskTextTropic);
                    text.setVisibility(View.INVISIBLE);
                    Tropic tropic = new Tropic();
                    tropic.setWateredToday(true);
                    currentTasks.put(TROPIC, 0);
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
                            WELL_DONE, Toast.LENGTH_LONG).show();
                    v.setVisibility(View.INVISIBLE);
                    final TextView text = findViewById(R.id.taskTextSpices);
                    text.setVisibility(View.INVISIBLE);
                    Spices spice = new Spices();
                    spice.setWateredToday(true);
                    currentTasks.put(SPICES, 0);
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

    private Plant getCategorizedPlant(String categoryName) {
        Plant categorizedPlant = null;
        switch (categoryName)
        {
            case SUCCULENT:
                categorizedPlant = new Succulent();
                break;
            case TROPIC:
                categorizedPlant = new Tropic();
                break;
            case SPICES:
               categorizedPlant = new Spices();
                break;
        }
        return categorizedPlant;
    }

    private void setTask(String categoryName) {

        // if there's already a task  in this category do nothing
        if (currentTasks.get(categoryName) == 1) { return; }

        // if today is the day - create task and add it
        Plant plant = getCategorizedPlant(categoryName);
        Calendar todayDate = Calendar.getInstance();
        if (plant.getLastWatering() == null) {
            plant.setLastWatering(todayDate);
        }

        // check date
        long diff = todayDate.getTimeInMillis() -
                plant.getLastWatering().getTimeInMillis();
        float daysDiff = (float) diff / (24 * 60 * 60 * 1000);
        if (((daysDiff >= plant.getDaysBetweenWatering()) || (daysDiff == 0)) // todo remove 2nd part
                && (!plant.isWateredToday())) {
            Task task = new Task(categoryName, plant.getGlassesPerWatering());
            addTaskToScroll(task);
            plant.setLastWatering(Calendar.getInstance());
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
            View cb = findViewById(R.id.checkboxTropic);
            cb.setVisibility(View.VISIBLE);
            TextView text = findViewById(R.id.taskTextTropic);
            text.setText(task._taskText);
        }
        else {
            toDoList.setVisibility(View.VISIBLE);
            View cb = findViewById(R.id.checkboxSpices);
            cb.setVisibility(View.VISIBLE);
            TextView text = findViewById(R.id.taskTextSpices);
            text.setText(task._taskText);
        }
    }

    private void setEmptyCheckList() {
        ScrollView toDoList = findViewById(R.id.plantsToDoList);
        toDoList.setBackgroundResource(R.drawable.ic_nothing_to_do_text); // todo- add free day
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
        Intent intent;
        if (noPlants) {
            intent = new Intent(this, NoPlantsActivity.class);
        }
        else {
            intent = new Intent(this, MyPlantsActivity.class);
        }
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
        else {
            params.width += TIP_WIDTH;
            text.setText(randomTip());
            tipOpen = true;
        }
        view.setLayoutParams(params);
    }

    private String randomTip(){
        Random r = new Random();
        String[] tips = {TIP_1, TIP_2, TIP_3, TIP_4, TIP_5, TIP_6, TIP_7, TIP_8, TIP_9, TIP_10};
        int i = r.nextInt(tips.length);
        return tips[i];
    }
}
