package bosunard.aston.com.cs3040cwk.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bosunard.aston.com.cs3040cwk.R;
import bosunard.aston.com.cs3040cwk.dummy.Bookings;
import bosunard.aston.com.cs3040cwk.fragments.BookingListFragment;

public class BookingsListAdapter extends RecyclerView.Adapter<BookingsListAdapter.ViewHolder> {

    private static final String TAG = "BookingsActivity";

//    private ArrayList<String>  mBookingNames  = new ArrayList<>();
//    private ArrayList<Date> mBookingDate = new ArrayList<>();
//    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private Context mContext;
    private List<Bookings> bookingsList;


    private BookingListFragment.onBookingListFragmentInteractionListener mListener;


    public BookingsListAdapter(Context mContext,BookingListFragment.onBookingListFragmentInteractionListener mListener,List<Bookings> bookingsList) {

        this.mContext = mContext;
        this.mListener = mListener;
        this.bookingsList = bookingsList;
//        this.mBookingNames = mBookingNames;
//        this.mBookingDate = mBookingDate;
//        this.mImageUrls = mImageUrls;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG,"onCreateViewHolder: called.");

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookings_list_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.bookingName.setText(bookingsList.get(i).getName());
        viewHolder.bookingDate.setText(bookingsList.get(i).getDate());


    }

    @Override
    public int getItemCount() {
        return bookingsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View bookingContainer;
        ImageView bookingImage;
        TextView bookingName;
        TextView bookingDate;

        //TextView resDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookingContainer = itemView;

            //bookingContainer = itemView.findViewById(R.id.booking_container);
            bookingImage = itemView.findViewById(R.id.booking_image);
            bookingName = itemView.findViewById(R.id.booking_title);
            bookingDate = itemView.findViewById(R.id.booking_date);


        }
    }
}
