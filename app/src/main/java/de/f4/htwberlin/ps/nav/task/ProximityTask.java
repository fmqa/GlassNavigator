package de.f4.htwberlin.ps.nav.task;

import android.location.Location;
import android.os.AsyncTask;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import de.f4.htwberlin.ps.nav.listener.ProximityListener;
import de.f4.htwberlin.ps.nav.model.RelativeStop;

/**
 * Created by fadimk on 19.01.15.
 */
public class ProximityTask extends AsyncTask<Void, Void, RelativeStop[]> {
    public ProximityTask(String url, double distance, Location location, ProximityListener listener) {
        this.url = url;
        this.distance = distance;
        this.location = location;
        this.listener = listener;
    }

    @Override
    protected RelativeStop[] doInBackground(Void... objects) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        RelativeStop[] response = restTemplate.getForObject(url, RelativeStop[].class,
                distance, location.getLatitude(), location.getLongitude());
        return response;
    }

    @Override
    protected void onPostExecute(RelativeStop[] o) {
        super.onPostExecute(o);
        listener.postProximityRequest(o);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.preProximityRequest();
    }

    private final String url;
    private final double distance;
    private final Location location;
    private final ProximityListener listener;
}
