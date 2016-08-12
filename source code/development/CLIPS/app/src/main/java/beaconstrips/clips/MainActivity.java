package beaconstrips.clips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import beaconstrips.clips.client.viewcontroller.authentication.AccountActivity;
import beaconstrips.clips.client.viewcontroller.authentication.LoginActivity;
import beaconstrips.clips.client.viewcontroller.authentication.RegistrationActivity;
import beaconstrips.clips.client.viewcontroller.building.BuildingActivity;
import beaconstrips.clips.client.viewcontroller.building.BuildingSearchActivity;
import beaconstrips.clips.client.viewcontroller.games.ProofResultActivity;
import beaconstrips.clips.client.viewcontroller.games.SearchNewStepActivity;
import beaconstrips.clips.client.viewcontroller.savedresults.RankingActivity;
import beaconstrips.clips.client.viewcontroller.savedresults.ResultActivity;
import beaconstrips.clips.client.viewcontroller.savedresults.SavedResultsActivity;
import beaconstrips.clips.client.viewcontroller.utility.MenuActivity;

public class MainActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      /*beaconstrips.clips.client.data.datamanager.LoginManager.sharedManager(getApplicationContext()).logout(new beaconstrips.clips.client.data.datamanager.AbstractDataManagerListener<Boolean>() {
          public void onResponse(Boolean response) {
            System.out.println("Logout avvenuto con successo");
          }

          public void onError(beaconstrips.clips.client.urlrequest.ServerError error) {
             System.out.println("Errore in fase di logout");
          }
       });

       /*beaconstrips.clips.client.data.datamanager.LoginManager.sharedManager(getApplicationContext()).registration("cenze@gmail.com", "Cenze", "Cenze94", new beaconstrips.clips.client.data.datamanager.AbstractDataManagerListener<Boolean>(){
          public void onResponse(Boolean response) {
             System.out.println("Registrazione avvenuta con successo");
          }
          public void onError(beaconstrips.clips.client.urlrequest.ServerError error) {
             System.out.println(error.errorCode+ " "+error.debugMessage+" "+error.userMessage);
          }
       });*/

        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, SearchNewStepActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu_drawer, menu);
        return true;
    }

}
