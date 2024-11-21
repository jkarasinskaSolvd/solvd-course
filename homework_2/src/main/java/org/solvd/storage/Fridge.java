package org.solvd.storage;

import org.solvd.TemperatureChangable;
import org.solvd.product.Category;
import org.solvd.product.StorageMethod;

import java.security.InvalidParameterException;

public class Fridge extends StoragePlace implements TemperatureChangable {
    private Double fridgeTemperatureInCelsius;
    private static final Double MAXTEMP = 8.0;
    private static final Double MINTEMP = 0.0;

    public Fridge() {
        super();
        fridgeTemperatureInCelsius = null;
        storageMethod = StorageMethod.FRIDGE;
    }

    public Fridge(String name, Category category, Double temperatureInCelcius) throws InvalidParameterException {
        super(name, category);
        changeTemperature(temperatureInCelcius);
        storageMethod = StorageMethod.FRIDGE;
    }

    public Double getFridgeTemperatureInCelsius() {
        return fridgeTemperatureInCelsius;
    }

    private void setFridgeTemperatureInCelsius(Double fridgeTemperatureInCelsius) {
        this.fridgeTemperatureInCelsius = fridgeTemperatureInCelsius;
    }

    @Override
    public void changeTemperature(Double temperature) {
        if(temperature < Fridge.MINTEMP || temperature > Fridge.MAXTEMP){
            throw new InvalidParameterException("temperatureInCelcius must be between min and max temperature");
        }
        this.setFridgeTemperatureInCelsius(temperature);
    }

}
