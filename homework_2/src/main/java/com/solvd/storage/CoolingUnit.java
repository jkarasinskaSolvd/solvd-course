package com.solvd.storage;

import com.solvd.exception.InvalidTemperatureException;
import com.solvd.exception.ObjectCreationFailureException;
import com.solvd.product.Category;
import com.solvd.product.StorageMethod;

public class CoolingUnit extends StoragePlace {
    private Double fridgeTemperatureInCelsius;
    private static Double MAXTEMP = 8.0;
    private static Double MINTEMP = 0.0;

    public CoolingUnit() {
        super();
        fridgeTemperatureInCelsius = null;
        storageMethod = StorageMethod.FRIDGE;
    }

    public CoolingUnit(String name, Category category, Double temperatureInCelcius)
            throws ObjectCreationFailureException {
        super(name, category);
        try{
            changeTemperature(temperatureInCelcius);
        }catch(InvalidTemperatureException e){
            throw new ObjectCreationFailureException(e.getMessage());
        }

        storageMethod = StorageMethod.FRIDGE;
    }

    public Double getFridgeTemperatureInCelsius() {
        return fridgeTemperatureInCelsius;
    }

    public static Double getMAXTEMP() {
        return MAXTEMP;
    }

    public static void setMAXTEMP(Double MAXTEMP) {
        CoolingUnit.MAXTEMP = MAXTEMP;
    }

    public static Double getMINTEMP() {
        return MINTEMP;
    }

    public static void setMINTEMP(Double MINTEMP) {
        CoolingUnit.MINTEMP = MINTEMP;
    }

    private void setFridgeTemperatureInCelsius(Double fridgeTemperatureInCelsius) {
        this.fridgeTemperatureInCelsius = fridgeTemperatureInCelsius;
    }

    public void changeTemperature(Double temperature) throws InvalidTemperatureException {
        if(temperature < CoolingUnit.MINTEMP || temperature > CoolingUnit.MAXTEMP){
            throw new InvalidTemperatureException();
        }
        this.setFridgeTemperatureInCelsius(temperature);
    }

}
