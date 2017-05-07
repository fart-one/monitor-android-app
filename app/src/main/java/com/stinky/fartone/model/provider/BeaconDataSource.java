package com.stinky.fartone.model.provider;

import android.support.annotation.NonNull;

import com.stinky.fartone.model.dto.Beacon;
import com.stinky.fartone.model.dto.BeaconsConfiguration;

import io.reactivex.Observable;

/**
 * Created by Acer on 2017-04-12.
 */

public interface BeaconDataSource {

    Observable<BeaconsConfiguration> getBeaconsConfiguration();

    Observable<Beacon> getBeacon(@NonNull String beaconId);
}
