package shellybekhor.tropi.Plants;

public class Socculent extends Plant {
    public static final int CATEGORY = 0;
    private static final float WATERING_PER_WEEK = 0;
    private static final float LITERS_PER_WATERING = 0;


    public Socculent(String houseLocation, String kind, String nickname) {
        super(CATEGORY, WATERING_PER_WEEK, LITERS_PER_WATERING, houseLocation, kind, nickname);
    }

}
