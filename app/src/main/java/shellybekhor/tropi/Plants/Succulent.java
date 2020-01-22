package shellybekhor.tropi.Plants;

import java.util.Calendar;
import java.util.Date;

public class Succulent extends Plant {
    public static final int CATEGORY = 0;
    public static final int WATER_EVERY_X_DAYS = 7;
    public static final int GLASSES_PER_WATERING = 1;
    private static Calendar lastWatering;


    public Succulent(String houseLocation, String kind, String nickname) {
        super(CATEGORY, WATER_EVERY_X_DAYS, GLASSES_PER_WATERING, houseLocation, kind, nickname);
        lastWatering = Calendar.getInstance();

    }

    public static void setLastWatering(Calendar lastWatering) {
        Succulent.lastWatering = lastWatering;
    }

    public static Calendar getLastWatering() {
        return lastWatering;
    }
}
