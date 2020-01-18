package shellybekhor.tropi.Plants;

import java.io.Serializable;

import shellybekhor.tropi.R;

/**
 * This class is the plant abstract class.
 * Each plant is defined by its a category and treatment.
 */
@SuppressWarnings("serial")
public class Plant implements Serializable {

    public int _category;
    public float _wateringPerWeek;
    public float _litersPerWatering;
    public String _houseLocation;
    public String _kind;
    public String _nickname;
    public static final String[] CATEGORIES = {"Succulent", "Tropic", "Spice"};


    public Plant(){

    }

    public int get_category() {
        return _category;
    }

    public void set_category(int _category) {
        this._category = _category;
    }

    public float get_wateringPerWeek() {
        return _wateringPerWeek;
    }

    public void set_wateringPerWeek(float _wateringPerWeek) {
        this._wateringPerWeek = _wateringPerWeek;
    }

    public float get_litersPerWatering() {
        return _litersPerWatering;
    }

    public void set_litersPerWatering(float _litersPerWatering) {
        this._litersPerWatering = _litersPerWatering;
    }

    public String get_houseLocation() {
        return _houseLocation;
    }

    public void set_houseLocation(String _houseLocation) {
        this._houseLocation = _houseLocation;
    }

    public String get_kind() {
        return _kind;
    }

    public void set_kind(String _kind) {
        this._kind = _kind;
    }

    public String get_nickname() {
        return _nickname;
    }

    public void set_nickname(String _nickname) {
        this._nickname = _nickname;
    }

    public Plant(int category, float wateringPerWeek, float litersPerWatering, String houseLocation,
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
