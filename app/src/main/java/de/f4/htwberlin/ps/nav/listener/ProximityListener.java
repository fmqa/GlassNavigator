package de.f4.htwberlin.ps.nav.listener;

import de.f4.htwberlin.ps.nav.model.RelativeStop;

/**
 * Created by fadimk on 19.01.15.
 */
public interface ProximityListener {
    void preProximityRequest();
    void postProximityRequest(RelativeStop[] stops);
}
