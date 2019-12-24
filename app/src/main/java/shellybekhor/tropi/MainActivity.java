
package shellybekhor.tropi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * This activity is the main screen of the app to which we enter first.
 * It holds the To-Do list of the plants, an option to add a new plant
 * and the daily Tropy-Tip.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Takes us to the add new plant activity.
     */
    public void addNewPlantButtonClicking(View view)
    {

    }


}
