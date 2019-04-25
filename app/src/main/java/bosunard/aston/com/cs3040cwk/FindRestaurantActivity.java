package bosunard.aston.com.cs3040cwk;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.URLEncoder;
import java.util.List;


import bosunard.aston.com.cs3040cwk.models.GooglePlaceList;
import bosunard.aston.com.cs3040cwk.models.GooglePlacesUtility;
import bosunard.aston.com.cs3040cwk.models.PlaceInfo;

public class FindRestaurantActivity extends AppCompatActivity {

    private static final String TAG ="FindRestaurantActivity";

    private GooglePlaceList nearby;
    private ListView mResultsList;
    private String placesKey;

    /* Location = Birmingham */

    private double latitude = 52.4862;
    private double longitude =  1.8904;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_find_restaurant);

        mResultsList = (ListView) findViewById(R.id.result_list);


        nearby = (GooglePlaceList) this.getIntent().getExtras().getSerializable("PLACE");;

        placesKey = this.getResources().getString(R.string.google_places_API_key);
        if(placesKey.equals("AIzaSyCBbDvVEieq0od4WmIcLiulzvIyccPsDl8")){

            Toast.makeText(this, "You haven't entered your Google Places Key into the strings file.  Dont forget to set a referer too.", Toast.LENGTH_LONG).show();
        }else {
            String type = URLEncoder.encode("food");
            String placesRequest = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" +
                    latitude + "," + longitude + "&radius=500&key=" + placesKey;

            PlacesReadFeed process = new PlacesReadFeed();
            process.execute(new String[] {placesRequest});
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void reportBack(GooglePlaceList nearby) {
        if (this.nearby == null) {
            this.nearby = nearby;

        } else {
           // this.nearby.getResults().addAll(nearby.getResults());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.layout_result_item, this.nearby.getPlaceNames());
        mResultsList.setAdapter(adapter);
    }



    private class PlacesReadFeed extends AsyncTask<String, Void, GooglePlaceList>{
        private final ProgressDialog dialog = new ProgressDialog(FindRestaurantActivity.this);
        private static final String TAG = "PlacesReadFeed";

        @Override
        protected GooglePlaceList doInBackground(String... urls) {

            try {
                String referer = null;
                //dialog.setMessage("Fetching Places Data");
                if (urls.length == 1) {
                    referer = null;
                } else {
                    referer = urls[1];
                }
                String input = GooglePlacesUtility.readGooglePlaces(urls[0], referer);
                Gson gson = new Gson();
                GooglePlaceList places = gson.fromJson(input, GooglePlaceList.class);
                Log.i("TAG", "Number of places found : " + places.getResults().size());
                return places;
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("TAG", e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPreExecute() {

            this.dialog.setMessage("Getting nearby places...");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(GooglePlaceList googlePlaceList) {

            this.dialog.dismiss();
            reportBack(googlePlaceList);
        }
    }


    private void fillInLayout(PlaceInfo place) {

        // title element has name and types
        TextView title = (TextView)findViewById(R.id.result_name);
        title.setText(place.getName());
        Log.i(TAG, "Setting title to: " + title.getText());

        //rating
        TextView reviews = (TextView) findViewById(R.id.restaurant_rating);
        Log.i(TAG, "Setting rating to: " + reviews.getText());

        //address
        //phone number
        //priceLevel

     /**
        //address
        //TextView address = (TextView) findViewById(R.id.address);
        address.setText(place.getFormatted_address() + " " + place.getFormatted_phone_number());
        Log.i(TAG, "Setting address to: " + address.getText());
        //vicinity
        TextView vicinity = (TextView) findViewById(R.id.vicinity);
        vicinity.setText(place.getVicinity());
        Log.i(TAG, "Setting vicinity to: " + vicinity.getText());
       ; */
    }
}
