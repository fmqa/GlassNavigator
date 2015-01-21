package de.f4.htwberlin.ps.nav.activity;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.glass.widget.CardScrollView;

import java.util.List;

import de.f4.htwberlin.ps.nav.R;
import de.f4.htwberlin.ps.nav.adapter.RelativeStopCardScrollAdapter;
import de.f4.htwberlin.ps.nav.listener.ProximityListener;
import de.f4.htwberlin.ps.nav.model.RelativeStop;
import de.f4.htwberlin.ps.nav.task.ProximityTask;

/**
 * Created by fadimk on 18.01.15.
 */
public class SonarActivity extends Activity implements LocationListener, ProximityListener {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mCardScroller = new CardScrollView(this);
        mCardScroller.activate();
        setContentView(mCardScroller);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTracking();
        mCardScroller.activate();
    }

    @Override
    protected void onPause() {
        mCardScroller.deactivate();
        stopTracking();
        super.onPause();
    }

    private void startTracking() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.NO_REQUIREMENT);
        List<String> providers = locationManager.getProviders(criteria, true);
        for (String provider : providers) {
            locationManager.requestLocationUpdates(provider, 10000, 0, this);
        }
    }

    private void stopTracking() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        lastKnownLocation = location;
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
        mCardScroller.deactivate();
        mCardScroller.setAdapter(new RelativeStopCardScrollAdapter(this, lastKnownLocation, heading, stops));
        setContentView(mCardScroller);
        mCardScroller.activate();
    }

    private void doSonar() {
        if (task != null && task.getStatus() != AsyncTask.Status.FINISHED) {
            task.cancel(true);
        }

        task = new ProximityTask(getString(R.string.sonar_url), distance, lastKnownLocation, this);
        task.execute();
    }

    private CardScrollView mCardScroller;
    private LocationManager locationManager;
    private Location lastKnownLocation;
    private ProximityTask task;
    private float heading = Float.NaN;
    private double distance = 0.5;
}
