package shellybekhor.tropi.Plants;

//import java.util.Calendar;
//import java.util.Date;

/**
 * This is the Succulent plants class.
 */
public class Succulent extends Plant {

    /**
     * The Succulent members defining the category and it's treatment
     */
    public static final int CATEGORY = 0;
    private static final int WATER_EVERY_X_DAYS = 7;
    private static final int GLASSES_PER_WATERING = 1;

    /**
     * Succulent constructor.
     */
    public Succulent() {
        super(CATEGORY, WATER_EVERY_X_DAYS, GLASSES_PER_WATERING);
    }
}
