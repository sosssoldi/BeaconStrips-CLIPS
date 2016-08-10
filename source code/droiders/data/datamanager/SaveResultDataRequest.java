package data.datamanager;

import android.content.Context;

import org.json.JSONObject;

import data.PathResult;
import urlrequest.AbstractUrlRequestListener;
import urlrequest.RequestMaker;
import urlrequest.ServerError;

/**
 * Created by andrea on 20/07/16.
 */
public class SaveResultDataRequest extends SaveDataManager { //andrà sicuramente modificata o questa classe o SaveDataManager, altrimenti in execute non finirà mai result
   private PathResult result;

   public SaveResultDataRequest(Context cx, PathResult result, AbstractDataManagerListener<Boolean> listener) {
      super(cx, listener);
      this.result = result;
      execute();
   }

   void execute() {
      RequestMaker.saveResult(cx, result, new AbstractUrlRequestListener() {
         public void onResponse(JSONObject response) { //se bisogna salvare in locale le operazioni si possono fare qui
            returnResponse();
         }

         public void onError(ServerError error) {
            returnError(error);
         }
      });
   }
}