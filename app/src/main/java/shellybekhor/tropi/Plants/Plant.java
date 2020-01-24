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
    private int category;
    private int daysBetweenWatering;
    private int glassesPerWatering;
    private Calendar lastWatering;
    private Calendar birthDate;
    private boolean wateredToday;


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
        wateredToday = false;
    }

    public Calendar getLastWatering() {
        return lastWatering;
    }

    public void setLastWatering(Calendar lastWatering) {
        this.lastWatering = lastWatering;
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

    public boolean isWateredToday() {
        return wateredToday;
    }

    public void setWateredToday(boolean wateredToday) {
        this.wateredToday = wateredToday;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }
}
