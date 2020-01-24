package shellybekhor.tropi.Plants;

import java.util.Calendar;
import java.util.Date;

public class Tropic extends Plant {
    public static final int CATEGORY = 1;
    private static final int WATER_EVERY_X_DAYS = 3;
    private static final int GLASSES_PER_WATERING = 3;
    private static Calendar lastWatering;


    public Tropic() {
        super(CATEGORY, WATER_EVERY_X_DAYS, GLASSES_PER_WATERING);
    }

}
