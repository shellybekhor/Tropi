package shellybekhor.tropi.Plants;

import java.util.Calendar;
import java.util.Date;

public class Succulent extends Plant {
    public static final int CATEGORY = 0;
    private static final int WATER_EVERY_X_DAYS = 7;
    private static final int GLASSES_PER_WATERING = 1;
    private static Calendar lastWatering;


    public Succulent() {
        super(CATEGORY, WATER_EVERY_X_DAYS, GLASSES_PER_WATERING);
    }
}
