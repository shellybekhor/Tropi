package shellybekhor.tropi;

public class Task {

    public String _categoryName;
    public int _glassesPerWatering;
    public String _taskText;
    public String _taskCategory;
//    public float _wateringEveryXDays;


    public Task(String categoryName, int glassesPerWatering) {
        _categoryName = categoryName;
        _glassesPerWatering = glassesPerWatering;
        _taskText = String.format("Watering with %d glasses", _glassesPerWatering);
    }

}
