package beaconstrips.clips.data.datamanager;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import beaconstrips.clips.data.Beacon;
import beaconstrips.clips.data.Path;
import beaconstrips.clips.data.Proof;
import beaconstrips.clips.data.Proximity;
import beaconstrips.clips.data.Step;
import beaconstrips.clips.urlrequest.AbstractUrlRequestListener;
import beaconstrips.clips.urlrequest.RequestMaker;
import beaconstrips.clips.urlrequest.ServerError;

/**
 * Created by andrea on 27/07/16.
 */
public class PathDataRequest extends DataManager<Path> {
   int pathID;

   public PathDataRequest(Context cx, int pathID, AbstractDataManagerListener<Path> listener) {
      super(cx, DataManager.CachePolicy.AlwaysReplaceLocal, listener);
      this.pathID = pathID;
      execute();
   }

   protected Path parseFromLocal() {
      return new DBHandler(cx).readPath(pathID);
   }

   protected void getRemoteData(AbstractUrlRequestListener listener) {
      RequestMaker.getPath(cx, pathID, listener);
   }

   protected Path parseFromUrlRequest(JSONObject response) {
      try {
         ArrayList<Step> steps = new ArrayList<>();
         JSONArray arraySteps = response.getJSONArray("steps");
         for(int i=0; i<arraySteps.length(); i++) {
            JSONObject step = arraySteps.getJSONObject(i);
            JSONObject beaconObject = step.getJSONObject("stopBeacon");
            Beacon beacon = new Beacon(beaconObject.getInt("id"), beaconObject.getString("UUID"), beaconObject.getInt("major"), beaconObject.getInt("minor"));
            ArrayList<Proximity> proximities = new ArrayList<>();
            JSONArray arrayProximities = step.getJSONArray("proximities");
            for(int j=0; j<arrayProximities.length(); j++) {
               JSONObject proximityObject = arrayProximities.getJSONObject(j);
               JSONObject proximityBeaconObject = proximityObject.getJSONObject("beacon");
               Beacon proximityBeacon = new Beacon(proximityBeaconObject.getInt("id"), proximityBeaconObject.getString("UUID"), proximityBeaconObject.getInt("major"), proximityBeaconObject.getInt("minor"));
               proximities.add(new Proximity(proximityBeacon, proximityObject.getDouble("percentage"), proximityObject.getString("textToDisplay")));
            }
            JSONObject proofObject = step.getJSONObject("proof");
            Proof proof = new Proof(proofObject.getInt("id"), proofObject.getString("title"), proofObject.getString("instructions"), proofObject.getJSONObject("algorithmData"), proofObject.getJSONObject("testData"));
            steps.add(new Step(beacon, proximities, proof));
         }
         return new Path(response.getInt("id"), response.getString("startingMessage"), response.getString("rewardMessage"), steps);
      } catch(JSONException e) {
         listener.onError(new ServerError(1002, "Error on parsing the response JSON after the execution of Path request", "")); //per sicurezza, per evitare inconsistenze. L'errore 1002 indica un errore in fase di parsing della risposta;
         return null;
      }
   }

   protected void updateLocalData(Path data){
      new DBHandler(cx).updatePath(data);
   }
}
