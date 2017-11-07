package com.flexfare.android.user.LocationProvider.interfaces;

import com.flexfare.android.user.LocationProvider.model.LocalGovernmentArea;
import com.flexfare.android.user.LocationProvider.model.State;

/**
 * Created by kodenerd on 11/6/17.
 */

public interface OnFilterInteractionListener {
    // TODO: Update argument type and name
    void onFiltersInteraction(State state, LocalGovernmentArea localGovernmentArea); /*TODO NOT SURE IF I NEED YOUS, int stateIndex, int lgaIndex)*/;
}