package shellybekhor.tropi.Plants;
import java.io.Serializable;
import java.util.Calendar;

/**
 * This class is the plant abstract class.
 * Each plant is defined by its a category and treatment.
 */
@SuppressWarnings("serial")
public class Plant implements Serializable {


    // Static members //
    public static final String[] CATEGORIES = {"Succulent", "Tropic", "Spice"};
    public static boolean isWateredToday;

     // Plant's data member //
    private int category;
    private int daysBetweenWatering;
    private int glassesPerWatering;
    private static Calendar lastWatering;
    private Calendar birthDate;


    /**
     * Plant constructor.
     * @param category The plant category
     * @param wateringPerWeek How many time to water in a week
     * @param glassesPerWatering How many glasses of water should be given per watering
     */
    public Plant(int category, int wateringPerWeek, int glassesPerWatering)
    {
        this.category = category;
        daysBetweenWatering = wateringPerWeek;
        this.glassesPerWatering = glassesPerWatering;
        birthDate = Calendar.getInstance();
        lastWatering = null;
        isWateredToday = false;
    }

    /**
     * @return The date of last watering
     */
    public static Calendar getLastWatering() {
        return lastWatering;
    }

    /**
     * Set the last watering
     * @param date The last watering date
     */
    public void setLastWatering(Calendar date) {
        lastWatering = date;
    }

    /**
     * @return The plant category
     */
    public int getCategory() {
        return category;
    }

    /**
     * Sets the Category to the given one
     * @param category The given category
     */
    public void setCategory(int category) {
        this.category = category;
    }

    /**
     * @return How many days should pass between 2 waterings
     */
    public int getDaysBetweenWatering() {
        return daysBetweenWatering;
    }

    /**
     * Sets the days between 2 watering to the given number
\     */
    public void setDaysBetweenWatering(int _wateringPerWeek) {
        this.daysBetweenWatering = _wateringPerWeek;
    }

    /**
     * @return The plant's number of glasses per watering
     */
    public int getGlassesPerWatering() {
        return glassesPerWatering;
    }

    /**
     * Sets the glasees per watering to the given number
     */
    public void setGlassesPerWatering(int glassesPerWatering) {
        this.glassesPerWatering = glassesPerWatering;
    }

    /**
     * @return A boolean value indicating if the plant was watered today
     */
    public static boolean isWateredToday() {
        return isWateredToday;
    }

    /**
     * Sets the isWateredToday boolean flag
     */
    public static void setWateredToday(boolean wateredToday) {
        isWateredToday = wateredToday;
    }

//    public Calendar getBirthDate() {
//        return birthDate;
//    }
//
//
//    public void setBirthDate(Calendar birthDate) {
//        this.birthDate = birthDate;
//    }
}
