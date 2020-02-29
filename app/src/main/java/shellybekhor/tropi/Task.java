package shellybekhor.tropi;

/**
 * This class is representing a task of plants watering
 */
public class Task {

    // Class members //
    public String _categoryName;
    public int _glassesPerWatering;
    public String _taskText;
    public String _taskCategory;

    /**
     * The Task constructor
     */
    public Task(String categoryName, int glassesPerWatering) {
        _categoryName = categoryName;
        _glassesPerWatering = glassesPerWatering;
        _taskText = String.format("Water with %d glasses", _glassesPerWatering);
    }

}
