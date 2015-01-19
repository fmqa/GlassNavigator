package de.f4.htwberlin.ps.nav.model;

import java.math.BigDecimal;

/**
 * Created by fadimk on 19.01.15.
 */
public class RelativeStop extends Stop {
    private double distance_in_km;

    public double getDistanceInKm() {
        return distance_in_km;
    }

    public void setDistanceInKm(double distanceInKm) {
        distance_in_km = distanceInKm;
    }
}
