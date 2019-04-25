package bosunard.aston.com.cs3040cwk.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;

import bosunard.aston.com.cs3040cwk.MapActivity;
import bosunard.aston.com.cs3040cwk.R;
import bosunard.aston.com.cs3040cwk.adapters.VisitedRestaurantsAdapter;
import bosunard.aston.com.cs3040cwk.dummy.Restaurant;

public class VisitedRestaurantFragment extends Fragment {

    private static final String TAG = "VisitedRestaurantFragment";

    private RecyclerView visitedRestaurantsList;

    private onVisitedRestaurantFragmentListInteractionListener mListener;

    private ArrayList<Restaurant> vistedList;

    public VisitedRestaurantFragment(){
        //required empty constructor
    }

    // TODO: Rename and change types and number of parameters
    public static VisitedRestaurantFragment newInstance() {
        VisitedRestaurantFragment fragment = new VisitedRestaurantFragment();
        Bundle args = new Bundle();
        // add parameters??
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.visited_restaurants_fragment, container, false);

        fillVisitedList();

        visitedRestaurantsList = view.findViewById(R.id.visited_recycler_list);
        VisitedRestaurantsAdapter adapter = new VisitedRestaurantsAdapter(getContext(),vistedList);
        visitedRestaurantsList.setLayoutManager(new GridLayoutManager(getContext(),3));

        visitedRestaurantsList.setAdapter(adapter);


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

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof  onVisitedRestaurantFragmentListInteractionListener){
            mListener = (onVisitedRestaurantFragmentListInteractionListener) context;
        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentListInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    private void fillVisitedList(){

        vistedList = new ArrayList<>();
        vistedList.add(new Restaurant("Gaucho","B5 LMW","Chinese","Restaurant",R.drawable.splash1,true));
        vistedList.add(new Restaurant("Tortilla","B5 LMW","Burrito","Restaurant",R.drawable.splash2,true));
        vistedList.add(new Restaurant("Las Iguanas","B5 LMW","Brazillian","Restaurant",R.drawable.splash3,true));
        vistedList.add(new Restaurant("Nandos","B5 LMW","Chinese","Restaurant",R.drawable.res_image1_1,true));
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



    public interface onVisitedRestaurantFragmentListInteractionListener{

        void reBookRestaurant();

    }
}
