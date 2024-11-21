package org.solvd.storage;

import org.solvd.IChangeTemperature;
import org.solvd.product.Category;
import org.solvd.product.StorageMethod;

import java.security.InvalidParameterException;

public class Fridge extends StoragePlace implements IChangeTemperature {
    private Double fridgeTemperatureInCelsius;
    private static Double MAXTEMP = 8.0;
    private static Double MINTEMP = 0.0;

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

    public static Double getMAXTEMP() {
        return MAXTEMP;
    }

    public static void setMAXTEMP(Double MAXTEMP) {
        Fridge.MAXTEMP = MAXTEMP;
    }

    public static Double getMINTEMP() {
        return MINTEMP;
    }

    public static void setMINTEMP(Double MINTEMP) {
        Fridge.MINTEMP = MINTEMP;
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
