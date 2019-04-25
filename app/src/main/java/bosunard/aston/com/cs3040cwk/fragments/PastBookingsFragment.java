package bosunard.aston.com.cs3040cwk.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import bosunard.aston.com.cs3040cwk.DatabaseHelper;
import bosunard.aston.com.cs3040cwk.adapters.BookingsListAdapter;
import bosunard.aston.com.cs3040cwk.R;
import bosunard.aston.com.cs3040cwk.dummy.Bookings;

public class PastBookingsFragment extends Fragment {

//    private ArrayList<String> mBookingNames = new ArrayList<>();
//    private ArrayList<Date> mBookingDates = new ArrayList<>();
//    private ArrayList<Integer> mImageUrls = new ArrayList<>();

//
//    private DatabaseHelper dbHelper;
//    private Cursor cursor;
//    private SimpleCursorAdapter cursorAdapter;

    private List<Bookings> bookingsList;

    private BookingListFragment.onBookingListFragmentInteractionListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.past_bookings_fragment,container,false);

//        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
//
//        Cursor cursor = dbHelper.getAllBookings();
//
//
//        String[] from = new String []{DatabaseHelper.bookingName,
//                DatabaseHelper.bookingDate,DatabaseHelper.bookingTime,DatabaseHelper.bookingGuests};
//        int [] to = new int[] {R.id.booking_title,R.id.booking_date,R.id.booking_time,R.id.booking_guests};
//
//
//
//        cursorAdapter = new SimpleCursorAdapter(getContext(),R.layout.bookings_list_item,cursor,from,to);



        //startManagingCursor(c);

//        String [] from=new String []{DatabaseHelper.bookingName,DatabaseHelper.bookingDate,DatabaseHelper.bookingTime,DatabaseHelper.bookingGuests,DatabaseHelper.bookingPhoneNo};
//        int [] to=new int [] {R.id.colName,R.id.colPhone,R.id.colDept};
//        SimpleCursorAdapter sca=new SimpleCursorAdapter(this,R.layout.gridrow,c,from,to);
//        grid.setAdapter(sca);


        fillBookingsList();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.past_bookings_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);

        BookingsListAdapter adapter = new BookingsListAdapter(this.getContext(),mListener,bookingsList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public static PastBookingsFragment newInstance() {
        PastBookingsFragment fragment = new PastBookingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof BookingListFragment.onBookingListFragmentInteractionListener) {
            mListener = (BookingListFragment.onBookingListFragmentInteractionListener) context;
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


    public void fillBookingsList(){

        bookingsList = new ArrayList<>();
        bookingsList.add(new Bookings(1,"Gaucho","07453895293","davidarebuwa@gmail.com","10 Feb 2019"));
    }
}
