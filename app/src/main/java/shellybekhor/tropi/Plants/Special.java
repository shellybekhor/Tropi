/**
 * This class is another type of a plant that is not in use at the moment.
 */

package shellybekhor.tropi.Plants;

public class Special extends Plant {
    public static final int CATEGORY = 3;
    private static final float WATERING_PER_WEEK = 0;
    private static final float LITERS_PER_WATERING = 0;


    public Special(String houseLocation, String kind, String nickname) {
        super(CATEGORY, WATERING_PER_WEEK, LITERS_PER_WATERING, houseLocation, kind, nickname);
    }

}
