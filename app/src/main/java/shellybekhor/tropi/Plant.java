package shellybekhor.tropi;

/**
 * This class is the plant abstract class.
 * Each plant is defined by its a category and treatment.
 */
public abstract class Plant {

    int _category;
    float _wateringPerWeek;
    float _litersPerWatering;
    String _houseLocation;

    Plant(int category, float wateringPerWeek, float litersPerWatering, String houseLocation)
    {
        _category = category;
        _wateringPerWeek = wateringPerWeek;
        _litersPerWatering = litersPerWatering;
        _houseLocation = houseLocation;
    }
}
