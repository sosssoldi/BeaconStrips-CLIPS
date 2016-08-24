/**
 * @file SearchNewStepActivity.java
 * @date 2016-07-18
 * @version 1.00
 * @author Viviana Alessio
 * Si occupa di gestire l'activity per la ricerca di un nuovo beacon
 */

package beaconstrips.clips.client.viewcontroller.games;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kontakt.sdk.android.ble.configuration.ActivityCheckConfiguration;
import com.kontakt.sdk.android.ble.configuration.ScanPeriod;
import com.kontakt.sdk.android.ble.configuration.scan.ScanMode;
import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.device.BeaconRegion;
import com.kontakt.sdk.android.ble.filter.ibeacon.IBeaconFilters;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.ProximityManagerContract;
import com.kontakt.sdk.android.ble.manager.listeners.IBeaconListener;
import com.kontakt.sdk.android.ble.manager.listeners.ScanStatusListener;
import com.kontakt.sdk.android.ble.manager.listeners.simple.SimpleIBeaconListener;
import com.kontakt.sdk.android.ble.manager.listeners.simple.SimpleScanStatusListener;
import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.common.profile.IBeaconDevice;
import com.kontakt.sdk.android.common.profile.IBeaconRegion;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import beaconstrips.clips.R;
import beaconstrips.clips.client.viewcontroller.utility.MenuActivity;

public class SearchNewStepActivity extends MenuActivity {

    private ProximityManagerContract proximityManager;
    private Button startTestButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_new_step);
        startTestButton = (Button) findViewById(R.id.startTestButton);
        setButtons();
        startTestButton.setVisibility(View.INVISIBLE);

        /* TODO when proof beacon is found:
            TextView descriptionLabel = (TextView) findElementById(R.id.descriptionLabel);
            TextView hintLabel = (TextView) findElementById(R.id.hintLabel);
            Button startTestButton = Button findElementById(R.id.startTestButton);
            descriptionLabel.setVisibility(View.GONE);
            hintLabel.setVisibility(View.GONE);
         */
        KontaktSDK.initialize(this);
        proximityManager = new ProximityManager(this);
        configureProximityManager();
        configureListeners();
        configureSpaces();
        //configureFilters();
        //TODO add all algorithms for proximity
    }

    private void configureProximityManager() {
        proximityManager.configuration()
                .scanMode(ScanMode.LOW_LATENCY)
                .scanPeriod(ScanPeriod.create(TimeUnit.SECONDS.toMillis(3), TimeUnit.SECONDS.toMillis(7)))
                .activityCheckConfiguration(ActivityCheckConfiguration.MINIMAL)
                .monitoringEnabled(false);
    }

    private void configureListeners() {
        proximityManager.setIBeaconListener(createIBeaconListener());
        proximityManager.setScanStatusListener(createScanStatusListener());
    }

    private void configureSpaces() {
        IBeaconRegion region = new BeaconRegion.Builder()
                .setIdentifier("All my iBeacons")
                .setProximity(UUID.fromString("f7826da6-4fa2-4e98-8024-bc5b71e0893e"))
                .build();

        proximityManager.spaces().iBeaconRegion(region);
    }

    private void configureFilters() {
        proximityManager.filters().iBeaconFilter(IBeaconFilters.newDeviceNameFilter(""));
    }

    @Override
    protected void onStart() {
        super.onStart();
        startScanning();
    }

    @Override
    protected void onStop() {
        proximityManager.stopScanning();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        proximityManager.disconnect();
        proximityManager = null;
        super.onDestroy();
    }

    private void startScanning() {
        proximityManager.connect(new OnServiceReadyListener() {
            @Override
            public void onServiceReady() {
                proximityManager.startScanning();
            }
        });
    }

    private void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SearchNewStepActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private IBeaconListener createIBeaconListener() {
        return new SimpleIBeaconListener() {
            @Override
            public void onIBeaconDiscovered(IBeaconDevice ibeacon, IBeaconRegion region) {
                if (ibeacon.getUniqueId().equals("JySs")) { //qui va modificato il beacon da trovare!
                    Log.i("Sample", "IBeacon discovered! UniqueID: " + ibeacon.getUniqueId());
                    Log.i("Distance", "" + ibeacon.getProximity());
                    showToast("Hai trovato il beacon!");
                    startTestButton.setVisibility(View.VISIBLE);

                }
            }
        };
    }

    private ScanStatusListener createScanStatusListener() {
        return new SimpleScanStatusListener() {
            @Override
            public void onScanStart() {
                //showToast("Scanning started");
            }

            @Override
            public void onScanStop() {
                //showToast("Scanning stopped");
            }
        };
    }


    private void setButtons() {
        if (startTestButton != null) {
            startTestButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), MultipleChoiceQuizActivity.class);
                    startActivity(i);
                    //TODO add spinner for loading

                }
            });
        }
    }
}
