package data;

import android.location.Location;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by Enrico on 01/08/2016.
 */
public class Utility {

   public static GregorianCalendar stringToGregorianCalendar(String data, String field){
      JSONObject tmp = new JSONObject();
      try{
         tmp = new JSONObject(data);
      }
      catch(JSONException e){

      }

      GregorianCalendar ret = new GregorianCalendar();
      try {
         ret.setTime(new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss.SSS'Z'", Locale.ITALIAN).parse(tmp.getString(field)));
      }
      catch(Exception e){

      }

      return ret;
   }

   public static int calculateTotalScore(List<ProofResult> proofsResults){
      Iterator<ProofResult> it = proofsResults.iterator();
      int ret = 0;

      while(it.hasNext()){
         ret += it.next().score;
      }

      return ret;
   }

   private static float distanceToBuilding(Location user, Building b){
      Location building = new Location("");
      building.setLongitude(b.longitude);
      building.setLatitude(b.latitude);

      return user.distanceTo(building);
   }

   private static Building[] insertBuilding(Building[] buildings, Building b, int index){
      System.arraycopy(buildings, index,buildings, index+1, (buildings.length-(index+1)));
      buildings[index] = b;
      return buildings;
   }

   public static Building[] addNearestBuilding(Building b, Building[] buildings,double latitude, double longitude){
      Location userPosition = new Location("");
      userPosition.setLatitude(latitude);
      userPosition.setLongitude(longitude);

      Location buildingPosition = new Location("");
      buildingPosition.setLatitude(b.latitude);
      buildingPosition.setLongitude(b.longitude);

      float distance = userPosition.distanceTo(buildingPosition);

      for(int i=0; i<buildings.length; ++i){
         if(distance <= distanceToBuilding(userPosition, buildings[i])){
            buildings = insertBuilding(buildings, b, i);
         }
      }


      return buildings;
   }
}