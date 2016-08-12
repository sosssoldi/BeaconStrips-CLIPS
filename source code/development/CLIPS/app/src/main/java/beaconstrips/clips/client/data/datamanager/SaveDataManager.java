package beaconstrips.clips.client.data.datamanager;

import android.content.Context;

import beaconstrips.clips.client.urlrequest.ServerError;

/**
 * Created by andrea on 20/07/16.
 */
public abstract class SaveDataManager {
   protected final AbstractDataManagerListener<Boolean> listener;
   protected final Context cx;

   public SaveDataManager(Context cx, AbstractDataManagerListener<Boolean> listener) {
      this.cx = cx;
      this.listener = listener;
   }

   abstract void execute();

   protected void returnResponse() {
         listener.onResponse(true);
   } //non viene passato niente perché già la chiamata di questo metodo indica che l'operazione ha avuto esito positivo

   protected void returnError(ServerError error) {
      listener.onError(error);
   }
}
