package br.com.jmsstudio.agenda.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;

import br.com.jmsstudio.agenda.R;
import br.com.jmsstudio.agenda.fragment.MapsFragment;
import br.com.jmsstudio.agenda.helper.LocatorHelper;

public class MapsActivity extends AppCompatActivity {

    private final int LOCATION_PERMISSIONS_CODE = 1;
    private MapsFragment mapsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PermissionChecker.PERMISSION_GRANTED) ||
                    (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PermissionChecker.PERMISSION_GRANTED)) {

                String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSIONS_CODE);
            } else {
                startMapsFragment();
            }
        } else {
            startMapsFragment();
            new LocatorHelper(this, this.mapsFragment);
        }
    }

    private void startMapsFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ftx = fragmentManager.beginTransaction();

        mapsFragment = new MapsFragment();

        ftx.replace(R.id.frame_maps, mapsFragment);

        ftx.commit();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSIONS_CODE) {
            boolean allPermissionsGranted = grantResults.length > 0;

            for (int result: grantResults) {
                allPermissionsGranted = result == PackageManager.PERMISSION_GRANTED && allPermissionsGranted;
            }

            if (allPermissionsGranted) {
                startMapsFragment();
                new LocatorHelper(this, this.mapsFragment);
            }
        }
    }
}
