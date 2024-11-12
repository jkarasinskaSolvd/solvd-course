package org.solvd.storage;

import org.solvd.product.Category;
import org.solvd.product.StorageMethod;

import java.security.InvalidParameterException;

public class Refrigerator extends StoragePlace {
    private Double refrigeratorTemperatureInCelsius;

    public Refrigerator() {
        super();
        refrigeratorTemperatureInCelsius = null;
        storageMethod = StorageMethod.REFRIGERATOR;
    }

    public Refrigerator(String name, Category category, Double temperatureInCelcius) throws InvalidParameterException {
        super(name, category);
        if (temperatureInCelcius < -30 || temperatureInCelcius > -18) {
            throw new InvalidParameterException("temperatureInCelcius must be between -30 and -18");
        } else {
            this.refrigeratorTemperatureInCelsius = temperatureInCelcius;
        }
        storageMethod = StorageMethod.REFRIGERATOR;
    }

    public Double getRefrigeratorTemperatureInCelsius() {
        return refrigeratorTemperatureInCelsius;
    }

    public void setRefrigeratorTemperatureInCelsius(Double refrigeratorTemperatureInCelsius) {
        if (refrigeratorTemperatureInCelsius < -30 || refrigeratorTemperatureInCelsius > -18) {
            throw new InvalidParameterException("temperatureInCelcius must be between -30 and -18");
        } else {
            this.refrigeratorTemperatureInCelsius = refrigeratorTemperatureInCelsius;
        }
    }
}
