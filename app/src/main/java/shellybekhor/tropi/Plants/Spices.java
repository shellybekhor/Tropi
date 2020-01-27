package shellybekhor.tropi.Plants;

import java.util.Calendar;
import java.util.Date;

public class Spices extends Plant {
    public static final int CATEGORY = 2;
    private static final int WATER_EVERY_X_DAYS = 2;
    private static final int GLASSES_PER_WATERING = 2;

    public Spices() {
        super(CATEGORY, WATER_EVERY_X_DAYS, GLASSES_PER_WATERING);
    }

}
