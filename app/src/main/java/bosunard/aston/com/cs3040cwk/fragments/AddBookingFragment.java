package bosunard.aston.com.cs3040cwk.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import bosunard.aston.com.cs3040cwk.DatabaseHelper;
import bosunard.aston.com.cs3040cwk.R;
import bosunard.aston.com.cs3040cwk.dummy.Bookings;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class AddBookingFragment extends Fragment {

    private static final String TAG = "AddBookingFragment";

//    DatabaseHelper mDatabaseHelper;
//   // private Button saveButton;
//   private EditText bookingName;
//    private EditText bookingPhoneNo;
//    private EditText bookingEmail;
    private TextView bookingDate;
   // private long returnValue;


    private onAddBookingFragmentInteractionListener mListener;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // returnValue = -1;

        //AddBookingFragment.getInstance().getBookingItems().add(new Bookings(1,"Marco Pierre White","07453895293","mmm@gmail.com","23-01-2019"));
    }

    public static AddBookingFragment newInstance(){

        Bundle args = new Bundle();
       // args.putSerializable("");
        AddBookingFragment fragment = new AddBookingFragment();
        fragment.setArguments(args);

        return fragment;

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bookings_form_fragment, container, false);

        EditText bookingName = view.findViewById(R.id.booking_name);

        EditText bookingPhoneNo = ((EditText) view.findViewById(R.id.booking_phoneNo));
        EditText bookingEmail = ((EditText) view.findViewById(R.id.booking_email));
         bookingDate = (TextView) view.findViewById(R.id.booking_date);


         bookingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Widget_Material_DatePicker,
                        mOnDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;

                Log.d(TAG, "onDateSet: date: " + year + "/" + month + "/" + dayOfMonth);

                String date = year + "/" + month + "/" + dayOfMonth;

                bookingDate.setText(date);
            }
        };

//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                returnValue = -1;
//
//
//                String name = bookingName.getText().toString();
//
//                int phone = Integer.parseInt((bookingPhoneNo.getText().toString()));
//
//                String email = bookingEmail.getText().toString();
//
//
//                Bookings booking = new Bookings(name, phone, email);
//                Log.i("DB_UPDATE", "Inserting: " + name);
//                returnValue = databaseHelper.AddBooking(booking);
//
//                if (returnValue < 0) {
//                    Toast.makeText(getContext(), "There was an error inserting the booking", Toast.LENGTH_LONG);
//                }
//
//
//                finish();
//            }
//
//        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof  onAddBookingFragmentInteractionListener){
            mListener = (onAddBookingFragmentInteractionListener) context;
        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentListInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener= null;
    }

    public interface onAddBookingFragmentInteractionListener{

    }

//    public void finish() {
//        Intent data = new Intent();
//        data.putExtra("returnKey1", returnValue);
//        if (returnValue < 0) {
//            getActivity().setResult(RESULT_CANCELED, data);
//            Log.i(TAG, "Cancelling");
//        } else {
//            getActivity().setResult(RESULT_OK, data);
//            Log.i(TAG, "OK");
//        }
//        getActivity().finish();
//    }
}
