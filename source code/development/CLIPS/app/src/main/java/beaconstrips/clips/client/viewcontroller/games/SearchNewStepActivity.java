package beaconstrips.clips.client.viewcontroller.games;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import beaconstrips.clips.R;
import beaconstrips.clips.client.viewcontroller.utility.MenuActivity;

public class SearchNewStepActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_new_step);
        /* TODO when proof beacon is found:
            TextView descriptionLabel = (TextView) findElementById(R.id.descriptionLabel);
            TextView hintLabel = (TextView) findElementById(R.id.hintLabel);
            Button startTestButton = Button findElementById(R.id.startTestButton);
            descriptionLabel.setVisibility(View.GONE);
            hintLabel.setVisibility(View.GONE);
            startTestButton.setVisibility(View.VISIBLE);
         */

        //TODO add all algorithms for proximity
    }
}