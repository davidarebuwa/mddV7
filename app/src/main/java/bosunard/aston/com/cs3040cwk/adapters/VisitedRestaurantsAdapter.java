package bosunard.aston.com.cs3040cwk.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bosunard.aston.com.cs3040cwk.R;
import bosunard.aston.com.cs3040cwk.dummy.Restaurant;
import bosunard.aston.com.cs3040cwk.fragments.VisitedRestaurantFragment;

public class VisitedRestaurantsAdapter extends RecyclerView.Adapter<VisitedRestaurantsAdapter.VisitedViewHolder> {

    private Context mContext;
    private List<Restaurant> mData;

    String[] options = {"View", "Delete", "Rebook","Add Meal"};

    private VisitedRestaurantFragment.onVisitedRestaurantFragmentListInteractionListener mListener;

    public VisitedRestaurantsAdapter(Context mContext, List<Restaurant> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public VisitedRestaurantsAdapter.VisitedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_record_item,viewGroup,false);

        return new VisitedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VisitedRestaurantsAdapter.VisitedViewHolder visitedViewHolder, int i) {

        visitedViewHolder.resName.setText(mData.get(i).getName());
        visitedViewHolder.resThumbnail.setImageResource(mData.get(i).getImage());

        visitedViewHolder.moreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Options");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on option[which]

                        if ("View".equals(options[which])){
                            Toast.makeText(mContext,"more details", Toast.LENGTH_SHORT).show();
                        }
                        else if ("Delete".equals(options[which])){
                            Toast.makeText(mContext,"deleting..", Toast.LENGTH_SHORT).show();
                        }
                        else if ("Rebook".equals(options[which])){
                            Toast.makeText(mContext,"rebooking..", Toast.LENGTH_SHORT).show();
                        }
                        else if ("Add Meal".equals(options[which])){
                            Toast.makeText(mContext,"Adding new meal.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class VisitedViewHolder extends RecyclerView.ViewHolder {

        public Restaurant restaurant;
        View view;
        ImageView resThumbnail;
        TextView resName;
        ImageView moreOptions;

        public VisitedViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            resName = itemView.findViewById(R.id.item_name);
            resThumbnail = itemView.findViewById(R.id.item_image);
            moreOptions = itemView.findViewById(R.id.more_options_button);
        }
    }
}
