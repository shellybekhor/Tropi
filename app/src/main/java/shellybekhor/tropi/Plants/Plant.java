package shellybekhor.tropi.Plants;

import java.io.Serializable;
import java.util.Calendar;

/**
 * This class is the plant abstract class.
 * Each plant is defined by its a category and treatment.
 */
@SuppressWarnings("serial")
public class Plant implements Serializable {

    public static final String[] CATEGORIES = {"Succulent", "Tropic", "Spice"};
    public static boolean isWateredToday;

    private int category;
    private int daysBetweenWatering;
    private int glassesPerWatering;
    private static Calendar lastWatering;
    private Calendar birthDate;


//    public Plant(){
//        lastWatering = Calendar.getInstance();
//    }

    public Plant(int category, int wateringPerWeek, int litersPerWatering)
    {
        this.category = category;
        daysBetweenWatering = wateringPerWeek;
        glassesPerWatering = litersPerWatering;
        birthDate = Calendar.getInstance();
        lastWatering = null;
        isWateredToday = false;
    }

    public static Calendar getLastWatering() {
        return lastWatering;
    }

    public void setLastWatering(Calendar date) {
        lastWatering = date;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getDaysBetweenWatering() {
        return daysBetweenWatering;
    }

    public void setDaysBetweenWatering(int _wateringPerWeek) {
        this.daysBetweenWatering = _wateringPerWeek;
    }

    public int getGlassesPerWatering() {
        return glassesPerWatering;
    }

    public void setGlassesPerWatering(int glassesPerWatering) {
        this.glassesPerWatering = glassesPerWatering;
    }

    public static boolean isWateredToday() {
        return isWateredToday;
    }

    public static void setWateredToday(boolean wateredToday) {
        isWateredToday = wateredToday;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }
}
