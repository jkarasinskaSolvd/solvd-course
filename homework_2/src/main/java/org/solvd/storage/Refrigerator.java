package org.solvd.storage;

import org.solvd.IChangeTemperature;
import org.solvd.product.Category;
import org.solvd.product.StorageMethod;

import java.security.InvalidParameterException;

public class Refrigerator extends StoragePlace implements IChangeTemperature {
    private Double refrigeratorTemperatureInCelsius;
    private static Double MAXTEMP = -18.0;
    private static Double MINTEMP = -30.0;

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

    public static Double getMAXTEMP() {
        return MAXTEMP;
    }

    public static void setMAXTEMP(Double MAXTEMP) {
        Refrigerator.MAXTEMP = MAXTEMP;
    }

    public static Double getMINTEMP() {
        return MINTEMP;
    }

    public static void setMINTEMP(Double MINTEMP) {
        Refrigerator.MINTEMP = MINTEMP;
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
