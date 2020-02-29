package shellybekhor.tropi.Plants;

//import java.util.Calendar;
//import java.util.Date;

/**
 * This is the Spices plants class.
 */
public class Spices extends Plant {

    /**
     * The Spices members defining the category and it's treatment
     */
    public static final int CATEGORY = 2;
    private static final int WATER_EVERY_X_DAYS = 2;
    private static final int GLASSES_PER_WATERING = 2;

    /**
     * Spices constructor.
     */
    public Spices() {
        super(CATEGORY, WATER_EVERY_X_DAYS, GLASSES_PER_WATERING);
    }

}
