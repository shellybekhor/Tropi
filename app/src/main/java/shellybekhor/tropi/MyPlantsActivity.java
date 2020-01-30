package shellybekhor.tropi;

import androidx.appcompat.app.AppCompatActivity;
import shellybekhor.tropi.Plants.Plant;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


// https://www.youtube.com/watch?v=hl0AcuplFwE

public class MyPlantsActivity extends AppCompatActivity {

    String currentUserId;
    int newPlant = -1;
    public static final String EXTRA_NP = "shellybekhor.tropi.extra.NEW_PLANT";
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
        newPlant = intent.getIntExtra(EXTRA_NP, -1);
        readIconsFromDB();
    }

    private void addIcons(int shelfId, ArrayList<Integer> icons){
        LinearLayout shelf = findViewById(shelvesIDs[shelfId]);
        int newIcon = 0;
        if (newPlant == shelfId){
            newIcon = icons.remove(icons.size()-1);
        }
        for (int icon: icons) {
            shelf.addView(createImageView(icon, false));
        }
        if (newPlant == shelfId){
            shelf.addView(createImageView(newIcon, true));
        }
    }

    private View createImageView(int resource, boolean isNew){
        View view = new View(this);
        if (isNew) {
            TextView text = new TextView(this);
            text.setText("new!\n\n\n\n\n\n");
            view = text;
        }
        view.setBackgroundResource(resource);
        return view;
    }

    private void readIconsFromDB(){
        DatabaseReference userDB = FirebaseDatabase.getInstance().getReference(currentUserId);
        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (int i = 0; i < Plant.CATEGORIES.length; i++){
                    if (dataSnapshot.hasChild(Plant.CATEGORIES[i])){
                        for (DataSnapshot ds: dataSnapshot.child(Plant.CATEGORIES[i]).getChildren()){
                            if (ds.getKey().equals(MainActivity.TIMESTAMP)) continue;
                            totalIcons[i].add(ds.getValue(Integer.class));
                        }
                        addIcons(i, totalIcons[i]);
                    }
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void infoPopUp(View view){
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.info_popup, null);
        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        TextView textView = popupWindow.getContentView().findViewById(R.id.popupText);
        setInfoText(view.getId(), textView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(20);
        }
        // show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    private void setInfoText(int viewId, TextView text) {
        switch(viewId){
            case R.id.info_succulent:
                text.setText(R.string.SucculentInfo);
                break;
            case R.id.info_spices:
                text.setText(R.string.SpiceInfo);
                break;
            case R.id.info_tropical:
                text.setText(R.string.TropicInfo);
                break;
        }
    }
}
