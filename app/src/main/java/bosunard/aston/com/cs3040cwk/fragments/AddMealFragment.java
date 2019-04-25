package bosunard.aston.com.cs3040cwk.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import bosunard.aston.com.cs3040cwk.R;

public class AddMealFragment extends Fragment {

    private static final String TAG = "AddMealFragment";

    private onAddMealFragmentInteractionListener mListener;


    public AddMealFragment(){
        //required empty constructor
        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    public static AddMealFragment newInstance(){

        Bundle args = new Bundle();
        // args.putSerializable("");
        AddMealFragment fragment = new AddMealFragment();
        fragment.setArguments(args);

        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meals_form_fragment, container, false);

        EditText restaurantName = view.findViewById(R.id.meal_restaurant_name);
        EditText mealName = view.findViewById(R.id.meal_name);
        EditText mealDescription = view.findViewById(R.id.meal_descrtiption);
        EditText mealRating = view.findViewById(R.id.meal_rating);


        Button addMeal = view.findViewById(R.id.add_meal_button);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof  onAddMealFragmentInteractionListener){
            mListener = (onAddMealFragmentInteractionListener) context;
        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentListInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;

    }


    public interface onAddMealFragmentInteractionListener{

    }
}
