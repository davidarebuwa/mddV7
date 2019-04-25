package bosunard.aston.com.cs3040cwk.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import bosunard.aston.com.cs3040cwk.DatabaseHelper;
import bosunard.aston.com.cs3040cwk.R;
import bosunard.aston.com.cs3040cwk.adapters.SectionsPageAdapter;

public class BookingListFragment extends Fragment {

    //DatabaseHelper mDatabaseHelper;
//    private long returnValue;
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;



    private onBookingListFragmentInteractionListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_booking, container, false);


        mSectionsPageAdapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);




//        FloatingActionButton fab = view.findViewById(R.id.add_booking_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Creating new booking", Snackbar.LENGTH_LONG)
//                        .show();
//
//                mListener.addBooking();
//            }
//        });

        return view;
    }

    private void setupViewPager(ViewPager viewPager){


        this.mViewPager = viewPager;

        SectionsPageAdapter adapter = new SectionsPageAdapter((getActivity().getSupportFragmentManager()));
        adapter.addFragment(new UpcomingBookingsFragment(), "UPCOMING");
        adapter.addFragment(new PastBookingsFragment(),"PAST");

    }

    public interface  onBookingListFragmentInteractionListener{

        void addBooking();



    }
}
