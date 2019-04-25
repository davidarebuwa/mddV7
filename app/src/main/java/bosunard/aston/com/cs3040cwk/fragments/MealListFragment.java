package bosunard.aston.com.cs3040cwk.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bosunard.aston.com.cs3040cwk.adapters.MealListAdapter;
import bosunard.aston.com.cs3040cwk.R;
import bosunard.aston.com.cs3040cwk.dummy.Meal;

public class MealListFragment extends Fragment {

    private static final String TAG = "MealListFragment";

    private RecyclerView mealsList;

    private onMealListFragmentInteractionListener mListener;

    private ArrayList<Meal> meals;


    public MealListFragment(){
        //required empty constructor
    }

    public static MealListFragment newInstance() {
        MealListFragment fragment = new MealListFragment();
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

        View view = inflater.inflate(R.layout.meal_list_fragment, container, false);

        fillMealList();
        mealsList = view.findViewById(R.id.meals_recycler_list);
        MealListAdapter adapter = new MealListAdapter(getContext(),meals);
        mealsList.setLayoutManager(new GridLayoutManager(getContext(),3));
        mealsList.setAdapter(adapter);

       // FloatingActionButton fab = view.findViewById(R.id.add_meal_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Creating new meal", Snackbar.LENGTH_LONG)
//                        .show();
//
//                mListener.addMeal();
//            }
//        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof  onMealListFragmentInteractionListener){
            mListener = (onMealListFragmentInteractionListener) context;
        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentListInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void fillMealList(){

        meals = new ArrayList<>();
        meals.add(new Meal("Kale Salad","Italian",R.drawable.res_image_4,"Gaucho","Very Delicious!"));


    }

    public interface onMealListFragmentInteractionListener{

      void  addMeal();

    }
}
