package org.solvd.storage;

import org.solvd.IChangeTemperature;
import org.solvd.exception.InvalidTemperatureException;
import org.solvd.exception.ObjectCreationFailureException;
import org.solvd.product.Category;
import org.solvd.product.StorageMethod;

public class Fridge extends StoragePlace implements IChangeTemperature {
    private Double fridgeTemperatureInCelsius;
    private static Double MAXTEMP = 8.0;
    private static Double MINTEMP = 0.0;

    public Fridge() {
        super();
        fridgeTemperatureInCelsius = null;
        storageMethod = StorageMethod.FRIDGE;
    }

    public Fridge(String name, Category category, Double temperatureInCelcius)
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
    public void changeTemperature(Double temperature) throws InvalidTemperatureException {
        if(temperature < Fridge.MINTEMP || temperature > Fridge.MAXTEMP){
            throw new InvalidTemperatureException();
        }
        this.setFridgeTemperatureInCelsius(temperature);
    }

}
