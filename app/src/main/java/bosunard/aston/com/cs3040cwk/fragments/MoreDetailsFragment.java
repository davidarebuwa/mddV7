package bosunard.aston.com.cs3040cwk.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import bosunard.aston.com.cs3040cwk.R;
import bosunard.aston.com.cs3040cwk.models.GooglePlace;
import bosunard.aston.com.cs3040cwk.models.GooglePlacesUtility;
import bosunard.aston.com.cs3040cwk.models.PlaceDetail;

public class MoreDetailsFragment  extends Fragment {

    private PlaceDetail placeDetail;
   // private PlaceInfo placeInfo;
    private GooglePlace place;

    private onMoreDetailsFragmentInteractionListener mListener;

    private TextView phoneNumber;

    public MoreDetailsFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        place = (GooglePlace) getArguments().getSerializable("PLACE");
        this.setHasOptionsMenu(true);
        getActivity().setTitle(place.getName());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_place_details_fragment, container, false);



        Button addButton = (Button) view.findViewById(R.id.add_booking_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddBookingFragment nextFrag= new AddBookingFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;



    }

    @Override
    public void onResume() {
        super.onResume();

        String placesKey = getActivity().getResources().getString(R.string.google_places_API_key);
        String placesRequest = "https://maps.googleapis.com/maps/api/place/details/json?" +
                "key=" + placesKey + "&reference=" + place.getReference();
        MoreDetailsReadFeed detailTask = new MoreDetailsReadFeed();
        detailTask.execute(placesRequest);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof  onMoreDetailsFragmentInteractionListener){
            mListener = (onMoreDetailsFragmentInteractionListener) context;
        }else{

            throw new RuntimeException(context.toString() + "must implement onMoreDetailsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    public static MoreDetailsFragment newInstance(GooglePlace place){

        Bundle args = new Bundle();
        args.putSerializable("PLACE",place);
        MoreDetailsFragment fragment = new MoreDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private class MoreDetailsReadFeed extends AsyncTask<String, Void, PlaceDetail>{

        private final ProgressDialog dialog = new ProgressDialog((getActivity()));

        @Override
        protected PlaceDetail doInBackground(String... strings) {

            try{

                String input = GooglePlacesUtility.readGooglePlaces(strings[0], getActivity().getResources().getString(R.string.referer));
                Gson gson = new Gson();
                PlaceDetail place = gson.fromJson(input, PlaceDetail.class);
               // Log.i(MainActivity.TAG, "Place found is " + place.toString());
                return place;
            } catch (Exception e) {
                e.printStackTrace();
              //  Log.i(MainActivity.TAG, e.getMessage());
                return null;


            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Getting more details...");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(PlaceDetail placeDetail) {
            place = placeDetail.getResult();
            fillInLayout(place);
            this.dialog.dismiss();;
        }
    }

    private void fillInLayout(final GooglePlace place){

        // title element has name and types
        TextView title = (TextView)getView().findViewById(R.id.restaurant_name);
        title.setText(place.getName());
        //Log.i(MainActivity.TAG, "Setting title to: " + title.getText());
        //address
        TextView address = (TextView)getView().findViewById(R.id.restaurant_address);
        address.setText(place.getFormatted_address() + " ");

        phoneNumber = getView().findViewById(R.id.restaurant_phoneNumber);
        phoneNumber.setText(place.getFormatted_phone_number());

        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialPhoneNumber(place.getFormatted_phone_number());

            }
        });
       // Log.i(RssItemActivity.TAG, "Setting address to: " + address.getText());
        //vicinity
        //TextView vicinity = (TextView)getView().findViewById(R.id.vicinity);
       // vicinity.setText(place.getVicinity());
      //  Log.i(RssItemActivity.TAG, "Setting vicinity to: " + vicinity.getText());
        //price_level
        TextView priceLevel = (TextView) getView().findViewById(R.id.restaurant_price_level);
      //  priceLevel.setText(place.getPriceLevel()); //-seems to keep breaking MoreDetailsFragment


        //rating
        TextView rating = (TextView)getView().findViewById(R.id.restaurant_rating);
        //rating.setText((int) place.getRating());


       // Log.i(RssItemActivity.TAG, "Setting rating to: " + reviews.getText());
    }

    public interface onMoreDetailsFragmentInteractionListener{

        void showDetails(GooglePlace place);
    }

    private void dialPhoneNumber(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}
