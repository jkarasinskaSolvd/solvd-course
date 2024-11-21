package org.solvd.storage;

import org.solvd.TemperatureChangable;
import org.solvd.product.Category;
import org.solvd.product.StorageMethod;

import java.security.InvalidParameterException;

public class Refrigerator extends StoragePlace implements TemperatureChangable {
    private Double refrigeratorTemperatureInCelsius;
    private static final Double MAXTEMP = -18.0;
    private static final Double MINTEMP = -30.0;

    public Refrigerator() {
        super();
        refrigeratorTemperatureInCelsius = null;
        storageMethod = StorageMethod.REFRIGERATOR;
    }

    public Refrigerator(String name, Category category, Double temperatureInCelcius) throws InvalidParameterException {
        super(name, category);
        changeTemperature(temperatureInCelcius);
        storageMethod = StorageMethod.REFRIGERATOR;
    }

    public Double getRefrigeratorTemperatureInCelsius() {
        return refrigeratorTemperatureInCelsius;
    }

    private void setRefrigeratorTemperatureInCelsius(Double refrigeratorTemperatureInCelsius) {
        this.refrigeratorTemperatureInCelsius = refrigeratorTemperatureInCelsius;
    }

    @Override
    public void changeTemperature(Double temperature) throws InvalidParameterException {
        if(temperature < Refrigerator.MINTEMP || temperature > Refrigerator.MAXTEMP){
            throw new InvalidParameterException("temperatureInCelcius must be between min and max temperature");
        }
        this.setRefrigeratorTemperatureInCelsius(temperature);
    }
}
