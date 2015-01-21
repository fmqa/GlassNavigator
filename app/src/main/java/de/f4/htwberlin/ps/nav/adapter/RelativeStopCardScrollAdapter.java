package de.f4.htwberlin.ps.nav.adapter;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;

import java.util.Arrays;
import java.util.List;

import de.f4.htwberlin.ps.nav.R;
import de.f4.htwberlin.ps.nav.model.RelativeStop;

/**
 * Created by fadimk on 21.01.15.
 */
public class RelativeStopCardScrollAdapter extends CardScrollAdapter {
    public RelativeStopCardScrollAdapter(Context context, Location referenceLoc, List<? extends RelativeStop> stops) {
        this.context = context;
        this.referenceLoc = referenceLoc;
        this.stops = stops;
    }

    public RelativeStopCardScrollAdapter(Context context, Location referenceLoc, float heading, RelativeStop[] stops) {
        this(context, referenceLoc, Arrays.asList(stops));
    }

    @Override
    public int getCount() {
        return stops.size();
    }

    @Override
    public RelativeStop getItem(int i) {
        return stops.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CardBuilder cardBuilder = new CardBuilder(context, CardBuilder.Layout.COLUMNS);

        if (stops.get(i).getStopName().length() > 25) {
            cardBuilder.setText(stops.get(i).getStopName().substring(0, 25));
        } else {
            cardBuilder.setText(stops.get(i).getStopName());
        }

        if (stops.get(i).getDistanceInKm() > 1.0) {
            cardBuilder.setFootnote(Math.round(stops.get(i).getDistanceInKm()) + " km");
        } else {
            cardBuilder.setFootnote(Math.round(stops.get(i).getDistanceInKm() * 1000) + " m");
        }

        cardBuilder.setAttributionIcon(R.drawable.ic_navcube);

        if (stops.get(i).getStopName().startsWith("S ")) {
            cardBuilder.setIcon(R.drawable.ic_sbahnstop);
        } else if (stops.get(i).getStopName().startsWith("U ")) {
            cardBuilder.setIcon(R.drawable.ic_ubahnstop);
        } else {
            cardBuilder.setIcon(R.drawable.ic_busstop);
        }
        return cardBuilder.getView(view, viewGroup);
    }

    @Override
    public int getPosition(Object o) {
        return stops.indexOf(o);
    }

    private final Context context;
    private final Location referenceLoc;
    private final List<? extends RelativeStop> stops;
}
