package shellybekhor.tropi.Plants;

import java.io.Serializable;
/**
 * This class is the plant abstract class.
 * Each plant is defined by its a category and treatment.
 */
@SuppressWarnings("serial")
public abstract class Plant implements Serializable {

    int _category;
    float _wateringPerWeek;
    float _litersPerWatering;
    String _houseLocation;
    String _kind;
    String _nickname;

    Plant(int category, float wateringPerWeek, float litersPerWatering, String houseLocation,
          String kind, String nickname)
    {
        _category = category;
        _wateringPerWeek = wateringPerWeek;
        _litersPerWatering = litersPerWatering;
        _houseLocation = houseLocation;
        _kind = kind;
        _nickname = nickname;
    }
}
