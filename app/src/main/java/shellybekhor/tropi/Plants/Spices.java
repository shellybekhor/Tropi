package shellybekhor.tropi.Plants;

import java.util.Calendar;
import java.util.Date;

public class Spices extends Plant {
    public static final int CATEGORY = 2;
    public static final int WATER_EVERY_X_DAYS = 2;
    public static final int GLASSES_PER_WATERING = 2;
    private static Calendar lastWatering;

    public Spices(String houseLocation, String kind, String nickname) {
        super(CATEGORY, WATER_EVERY_X_DAYS, GLASSES_PER_WATERING, houseLocation, kind, nickname);
        lastWatering = Calendar.getInstance();

    }

    public static Calendar getLastWatering() {
        return lastWatering;
    }

    public static void setLastWatering(Calendar lastWatering) {
        Spices.lastWatering = lastWatering;
    }
}
