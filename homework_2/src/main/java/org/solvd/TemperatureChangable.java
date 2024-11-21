package org.solvd;

import java.security.InvalidParameterException;

public interface TemperatureChangable {
    public void changeTemperature(Double temperature) throws InvalidParameterException;
}