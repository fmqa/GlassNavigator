package de.f4.htwberlin.ps.nav.model;

import java.math.BigDecimal;

/**
 * Created by fadimk on 19.01.15.
 */
public class Stop {
    private long stop_id;
    private String stop_name;
    private double stop_lat;
    private double stop_lon;
    private int location_type;

    public long getStopId() {
        return stop_id;
    }

    public void setStopId(long stopId) {
        stop_id = stopId;
    }

    public String getStopName() {
        return stop_name;
    }

    public void setStopName(String stopName) {
        stop_name = stopName;
    }

    public double getStopLat() {
        return stop_lat;
    }

    public void setStop_lat(double stopLat) {
        stop_lat = stop_lat;
    }

    public double getStopLon() {
        return stop_lon;
    }

    public void setStopLon(double stopLon) {
        stop_lon = stopLon;
    }

    public int getLocationType() {
        return location_type;
    }

    public void setLocationType(int locationType) {
        location_type = location_type;
    }
}
