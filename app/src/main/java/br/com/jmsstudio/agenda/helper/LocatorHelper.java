package br.com.jmsstudio.agenda.helper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import br.com.jmsstudio.agenda.fragment.MapsFragment;

/**
 * Created by jms on 27/01/17.
 */
public class LocatorHelper implements GoogleApiClient.ConnectionCallbacks, LocationListener {

    private GoogleApiClient googleApiClient;
    private Context context;
    private final MapsFragment mapsFragment;

    public LocatorHelper(Context context, MapsFragment mapsFragment) {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();

        this.context = context;
        this.mapsFragment = mapsFragment;
        googleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setSmallestDisplacement(100);//faz conexao com gps se mover ao menos 100 m
        locationRequest.setInterval(1000); // verifica por mudancas a cada 1 seg
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng coordenada = new LatLng(location.getLatitude(), location.getLongitude());
        this.mapsFragment.centerMap(coordenada);
    }
}
