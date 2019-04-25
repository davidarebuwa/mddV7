package bosunard.aston.com.cs3040cwk.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bosunard.aston.com.cs3040cwk.adapters.FindRestaurantAdapter;
import bosunard.aston.com.cs3040cwk.MapActivity;
import bosunard.aston.com.cs3040cwk.adapters.PlaceAutocompleteAdapter;
import bosunard.aston.com.cs3040cwk.R;
import bosunard.aston.com.cs3040cwk.models.GooglePlace;
import bosunard.aston.com.cs3040cwk.models.GooglePlaceList;
import bosunard.aston.com.cs3040cwk.models.GooglePlacesUtility;


public class FindRestaurantFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private final String TAG = "FindRestaurantFragment";

    //private static final String ARG_PARAM1 = "param1" ;
    //private static final String ARG_PARAM2 = "param2";
    /* Location is Aston University */

    private double latitude = 52.485867;
    private double longitude = -1.890161;

    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    private ImageView placeImage;
    //private GooglePlace place;

    private GeoDataClient geoDataClient;

    private GoogleApiClient mGoogleApiClient;

    private List<PlacePhotoMetadata> photoMetadataList;
    private List<GooglePlace> nearby;
    private ArrayList<Bitmap> bitmapArray = new ArrayList<>();

    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-45, -128), new LatLng(50, 146));

    private PlaceAutocompleteAdapter mPlaceAutoCompleteAdapter;

    private AutoCompleteTextView mSearchText;

    private onFragmentListInteractionListener mListener;

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

//    private PlaceInfo mPlaceInfo;
//    private PlaceAutocompleteAdapter mPlaceAutoCompleteAdapter;
//    private GoogleApiClient mGoogleApiClient;


    //    private ArrayList<String> mNames = new ArrayList<>();
//    private ArrayList<String> mCategories = new ArrayList<>();



    public FindRestaurantFragment() {
        // Required empty public constructor
        nearby = new ArrayList<>();
    }

    /*
    private void init() {
        Log.d(TAG, "init: initializing");

        mGoogleApiClient = new GoogleApiClient
                .Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(getActivity(), this)
                .build();

    }
*/

    // TODO: Rename and change types and number of parameters
    public static FindRestaurantFragment newInstance() {
        FindRestaurantFragment fragment = new FindRestaurantFragment();
        Bundle args = new Bundle();
        // add parameters??
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // get parameters back
        }
        //Sending Google PlacesAPI Request
        String placesKey = getResources().getString(R.string.google_places_API_key);
        //String type = URLEncoder.encode("food");
        String placesRequest = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" +
                latitude + "," + longitude + "&radius=500&type=restaurant&key=" + placesKey;
        PlacesReadFeed process = new PlacesReadFeed();
        process.execute(new String[]{placesRequest});


       // Places.initialize(getContext(), getContext().getString(R.string.google_places_API_key));
        // PlacesClient placesClient = Places.createClient(this);

        //getPhotoMetadata();



    }

    private void init(){

        Log.d(TAG, "init: initializing");
        mGoogleApiClient = new GoogleApiClient
                .Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(getActivity(), this)
                .build();


        mPlaceAutoCompleteAdapter = new PlaceAutocompleteAdapter(getContext(), mGoogleApiClient, LAT_LNG_BOUNDS, null);

        mSearchText.setAdapter(mPlaceAutoCompleteAdapter);

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER) {

                    //execute search method

                    geoLocate();
                }
                return false;
            }
        });


    }

    private void geoLocate() {

        Log.d(TAG, "geoLocate: geolocating");
        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(getContext());
        List<Address> list = new ArrayList<>();
        try {

            list = geocoder.getFromLocationName(searchString, 1);

        } catch (IOException e) {
            Log.e(TAG, "geoLocate : IOException: " + e.getMessage());
        }

        if (list.size() > 0) {

            Address address = list.get(0);

            Log.d(TAG, "geoLocate: found a location: " + address.toString());


            //Return search results ion recyclerView

            recyclerView.getAdapter().notifyDataSetChanged();


        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_restaurant, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.result_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        FindRestaurantAdapter adapter = new FindRestaurantAdapter(getContext(), nearby,mListener); //fill in parameters
        recyclerView.setAdapter(adapter);

        mSearchText = (AutoCompleteTextView) view.findViewById(R.id.input_search);


        placeImage = view.findViewById(R.id.restaurant_photo);

//        Button moreInfoButton = view.findViewById(R.id.select_restaurant_button);
//        moreInfoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getActivity(), MoreDetailActivity.class);
//                intent.putExtra("PLACE",nearby.get(0).toString());
//                startActivity(intent);
//
//            }
//        });

        if(checkServices()) {

            Button btnMap = (Button) view.findViewById(R.id.btnMapSwitch);
            btnMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), MapActivity.class);
                    startActivity(intent);

                }
            });
        }


                  geoDataClient = Places.getGeoDataClient(getContext(), null);
        return view;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //Checking google services version
    public boolean checkServices() {

        Log.i("DBA", "checking Google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext());

        if (available == ConnectionResult.SUCCESS) {
            //user can make app requests
            Log.i("DBA", "Google play services initialized");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.i("DBA", "error occured but we can fix it");
            //  Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available, ERROR_DIALOG_REQUEST);
            //  dialog.show();

        } else {
            Toast.makeText(getContext(), "Map request cannot be made", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    protected void reportBack(GooglePlaceList nearby) {

        this.nearby.addAll(nearby.getResults());

        // tell adapter we have data.
        Log.i(TAG, "We got " + nearby.getResults().size() + " places.");
        recyclerView.getAdapter().notifyDataSetChanged();
    }

//    private void getPhotoMetadata(String placeId){
//
//        final Task<PlacePhotoMetadataResponse> photoResponse =
//                geoDataClient.getPlacePhotos(placeId);
//
//        photoResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoMetadataResponse>() {
//            @Override
//            public void onComplete(@NonNull Task<PlacePhotoMetadataResponse> task) {
//
//                photoMetadataList = new ArrayList<>();
//                PlacePhotoMetadataResponse photos = task.getResult();
//                PlacePhotoMetadataBuffer placePhotoMetadataBuffer = photos.getPhotoMetadata();
//
//                Log.d(TAG,"number of photos" + placePhotoMetadataBuffer.getCount());
//
//                for(PlacePhotoMetadata placePhotoMetadata : placePhotoMetadataBuffer){
//
//                    //get first photo in the list
//                    photoMetadataList.add(placePhotoMetadataBuffer.get(0).freeze());
//
//                    //get attribution text
//                    CharSequence attribution = placePhotoMetadata.getAttributions();
//                }
//                placePhotoMetadataBuffer.release();
//                displayPhoto();
//            }
//        });
//
//    }
//
//    private void getPhoto(PlacePhotoMetadata photoMetadata){
//
//        Task<PlacePhotoResponse> photoResponseTask = geoDataClient.getPhoto(photoMetadata);
//        photoResponseTask.addOnCompleteListener(new OnCompleteListener<PlacePhotoResponse>() {
//            @Override
//            public void onComplete(@NonNull Task<PlacePhotoResponse> task) {
//
//                PlacePhotoResponse photo = task.getResult();
//                Bitmap photoBitmap = photo.getBitmap();
//
//                Log.d(TAG,"photo: "+ photo.toString());
//
//                placeImage.invalidate();
//                placeImage.setImageBitmap(photoBitmap);
//            }
//        });
//    }
//    private void displayPhoto(){
//        Log.d(TAG,"photo list size: " + photoMetadataList.size());
//        if(photoMetadataList.isEmpty()){
//            return;
//        }
//        getPhoto(photoMetadataList.get(0));
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof  onFragmentListInteractionListener){
            mListener = (onFragmentListInteractionListener) context;
        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentListInteractionListener");
        }
    }

    public interface onFragmentListInteractionListener{

        void showDetails(GooglePlace pLace);
    }


    //----------------------------------Google Places API------------------------------//

    private class PlacesReadFeed extends AsyncTask<String, Void, GooglePlaceList> {
        //private final ProgressDialog dialog = new ProgressDialog();

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
                Log.i("PLACES_EXAMPLE", "Number of places found is " + places.getResults().size());
                return places;
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("PLACES_EXAMPLE", e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            //this.dialog.setMessage("Getting nearby places...");
            //this.dialog.show();
            Log.i(TAG, "Getting nearby places...");
        }

        @Override
        protected void onPostExecute(GooglePlaceList places) {
            //this.dialog.dismiss();
            Log.i(TAG, "Got places...");
            reportBack(places);
        }


    }
}
