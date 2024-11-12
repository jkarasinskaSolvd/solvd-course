package org.solvd.storage;

import org.solvd.product.Category;
import org.solvd.product.StorageMethod;

import java.security.InvalidParameterException;

public class Fridge extends StoragePlace{
    private Double fridgeTemperatureInCelsius;

    public Fridge() {
        super();
        fridgeTemperatureInCelsius = null;
        storageMethod = StorageMethod.FRIDGE;
    }

    public Fridge(String name, Category category, Double temperatureInCelcius) throws InvalidParameterException {
        super(name, category);
        if(temperatureInCelcius < 0 || temperatureInCelcius > 8){
            throw new InvalidParameterException("temperatureInCelcius must be between 0 and 8");
        } else {
            this.fridgeTemperatureInCelsius = temperatureInCelcius;
        }
        storageMethod = StorageMethod.FRIDGE;
    }

    public Double getFridgeTemperatureInCelsius() {
        return fridgeTemperatureInCelsius;
    }

    public void setFridgeTemperatureInCelsius(Double fridgeTemperatureInCelsius) {
        if(fridgeTemperatureInCelsius < 0 || fridgeTemperatureInCelsius > 8){
            throw new InvalidParameterException("temperatureInCelcius must be between 0 and 8");
        } else {
            this.fridgeTemperatureInCelsius = fridgeTemperatureInCelsius;
        }
    }
}
