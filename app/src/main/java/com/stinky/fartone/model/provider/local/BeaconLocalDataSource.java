package com.stinky.fartone.model.provider.local;

import android.support.annotation.NonNull;

import com.stinky.fartone.model.dto.Beacon;
import com.stinky.fartone.model.dto.BeaconsConfiguration;
import com.stinky.fartone.model.provider.BeaconDataSource;
import com.stinky.fartone.util.logger.Logger;
import com.stinky.fartone.util.logger.LoggerImpl;

import io.reactivex.Observable;

/**
 * Created by Acer on 2017-04-12.
 */

public class BeaconLocalDataSource implements BeaconDataSource {
    private final Logger logger = LoggerImpl.getInstance(getClass().getSimpleName());

    @Override
    public Observable<BeaconsConfiguration> getBeaconsConfiguration() {
        return null;
    }

    @Override
    public Observable<Beacon> getBeacon(@NonNull String beaconId) {
        return null;
    }
}
