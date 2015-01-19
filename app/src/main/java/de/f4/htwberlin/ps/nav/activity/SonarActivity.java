package de.f4.htwberlin.ps.nav.activity;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.List;

import de.f4.htwberlin.ps.nav.R;
import de.f4.htwberlin.ps.nav.listener.ProximityListener;
import de.f4.htwberlin.ps.nav.model.RelativeStop;
import de.f4.htwberlin.ps.nav.task.ProximityTask;
import de.f4.htwberlin.ps.nav.util.Geo;

/**
 * Created by fadimk on 18.01.15.
 */
public class SonarActivity extends Activity implements LocationListener, ProximityListener {
    @Override
    protected void onCreate(Bundle bundle) {
        lastKnownLocation = Geo.getLastLocation(this);
        if (lastKnownLocation != null) {
            doSonar();
        } else {
            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            for (String p : locationManager.getProviders(true)) {
                locationManager.requestLocationUpdates(p, 0, 0, this);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (lastKnownLocation == null) {
            lastKnownLocation = Geo.getLastLocation(this);
        }

        if (lastKnownLocation == null) {
            lastKnownLocation = location;
        }

        locationManager.removeUpdates(this);

        doSonar();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }

    @Override
    public void preProximityRequest() {
    }

    @Override
    public void postProximityRequest(RelativeStop[] stops) {
        // Display Stops here
    }

    private void doSonar() {
        if (task != null && task.getStatus() != AsyncTask.Status.FINISHED) {
            task.cancel(true);
        }
        task = new ProximityTask(getString(R.string.sonar_url), distance, lastKnownLocation, this);
        task.execute();
    }

    private LocationManager locationManager;
    private Location lastKnownLocation;
    private ProximityTask task;
    private double distance = 1;
}
