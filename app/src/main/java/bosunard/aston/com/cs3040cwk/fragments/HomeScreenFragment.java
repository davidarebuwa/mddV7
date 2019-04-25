package bosunard.aston.com.cs3040cwk.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import bosunard.aston.com.cs3040cwk.R;
import bosunard.aston.com.cs3040cwk.adapters.RecyclerViewAdapter;
import bosunard.aston.com.cs3040cwk.dummy.Home;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeScreenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeScreenFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

   // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    public static final String TAG = "HomeScreenFragment";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();

    private OnFragmentInteractionListener mListener;
   // private List<GooglePlace> nearby;

    private double latitude = 52.485867;
    private double longitude = -1.890161;

    private RecyclerView recomRecyclerView;
    private RecyclerView topPickRecyclerView;

    private ArrayList<Home> homeArrayList;

    public HomeScreenFragment() {
        // Required empty public constructor

       // nearby = new ArrayList<>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeScreenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeScreenFragment newInstance(String param1, String param2) {
        HomeScreenFragment fragment = new HomeScreenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private void fillHome(){
       // Log.d(TAG,"intImageBitmaps: preparing bitmaps");

      homeArrayList = new ArrayList<>();
      homeArrayList.add(new Home("The Alchemist",R.drawable.splash6));
      homeArrayList.add(new Home("Gusto",R.drawable.splash2));
      homeArrayList.add(new Home("Fumo",R.drawable.res_image_3));
      homeArrayList.add(new Home("GBK",R.drawable.res_image_4));
      homeArrayList.add(new Home("Blue Nile",R.drawable.res_image_5));
      homeArrayList.add(new Home("Dominos",R.drawable.res_image_6));
      homeArrayList.add(new Home("Nandos",R.drawable.res_image_7));
      homeArrayList.add(new Home("Byron",R.drawable.res_image1_1));
      homeArrayList.add(new Home("Gosta Green",R.drawable.res_image1_2));
      homeArrayList.add(new Home("Pizza Hut",R.drawable.res_image_8));
      homeArrayList.add(new Home("McDonalds",R.drawable.res_image1_5));


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

//        String placesKey = getResources().getString(R.string.google_places_API_key);
//        //String type = URLEncoder.encode("food");
//        String placesRequest = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" +
//                latitude + "," + longitude + "&radius=500&type=restaurant&key=" + placesKey;
//        HomeScreenFragment.PlacesReadFeed process = new HomeScreenFragment.PlacesReadFeed();
//        process.execute(new String[]{placesRequest});
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        //fillHome();

        //find recyclerview
         recomRecyclerView = (RecyclerView) view.findViewById(R.id.home_recyclerView);
         topPickRecyclerView = (RecyclerView) view.findViewById(R.id.sub_recyclerView) ;


        //itemsList.add(Home.createHomeList(5));


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager subLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

        recomRecyclerView.setLayoutManager(layoutManager);

        topPickRecyclerView.setLayoutManager(subLayoutManager);


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(),homeArrayList);
        recomRecyclerView.setAdapter(adapter);

        topPickRecyclerView.setAdapter(adapter);



        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

//        void showDetails(GooglePlace pLace);
    }

//    protected void reportBack(GooglePlaceList nearby) {
//
//        this.nearby.addAll(nearby.getResults());
//
//        // tell adapter we have data.
//        Log.i(TAG, "We got " + nearby.getResults().size() + " places.");
//        recomRecyclerView.getAdapter().notifyDataSetChanged();
//        topPickRecyclerView.getAdapter().notifyDataSetChanged();
//    }

    //----------------------------------Google Places API------------------------------//

//    private class PlacesReadFeed extends AsyncTask<String,Void,GooglePlaceList>{
//
//        @Override
//        protected GooglePlaceList doInBackground(String... urls) {
//            try {
//                String referer = null;
//                //dialog.setMessage("Fetching Places Data");
//                if (urls.length == 1) {
//                    referer = null;
//                } else {
//                    referer = urls[1];
//                }
//                String input = GooglePlacesUtility.readGooglePlaces(urls[0], referer);
//                Gson gson = new Gson();
//                GooglePlaceList places = gson.fromJson(input, GooglePlaceList.class);
//                Log.i("PLACES_EXAMPLE", "Number of places found is " + places.getResults().size());
//                return places;
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.i("PLACES_EXAMPLE", e.getMessage());
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            Log.i(TAG, "Getting nearby places...");
//        }
//
//        @Override
//        protected void onPostExecute(GooglePlaceList places) {
//            Log.i(TAG, "Got places...");
//            reportBack(places);
//        }
   // }
}
