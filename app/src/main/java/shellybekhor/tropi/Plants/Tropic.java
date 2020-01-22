package shellybekhor.tropi.Plants;

import java.util.Calendar;
import java.util.Date;

public class Tropic extends Plant {
    public static final int CATEGORY = 1;
    public static final int WATER_EVERY_X_DAYS = 3;
    public static final int GLASSES_PER_WATERING = 3;
    private static Calendar lastWatering;



    public Tropic(String houseLocation, String kind, String nickname) {
        super(CATEGORY, WATER_EVERY_X_DAYS, GLASSES_PER_WATERING, houseLocation, kind, nickname);
        lastWatering = Calendar.getInstance();

    }

    public static void setLastWatering(Calendar lastWatering) {
        Tropic.lastWatering = lastWatering;
    }

    public static Calendar getLastWatering() {
        return lastWatering;
    }
}
