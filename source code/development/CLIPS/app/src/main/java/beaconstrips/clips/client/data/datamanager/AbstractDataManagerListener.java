package beaconstrips.clips.client.data.datamanager;

import beaconstrips.clips.client.urlrequest.ServerError;

/**
 * Created by andrea on 19/07/16.
 */

//la classe astratta da cui derivano i listener del DataManager, onResponse e onError gestiscono le risposte rispettivamente quando viene ottenuta la risposta e quando viene ricevuto un errore
public interface AbstractDataManagerListener<ModelObject> {
   void onResponse(ModelObject response);

   void onError(ServerError error);
}
