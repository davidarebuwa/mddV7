package bosunard.aston.com.cs3040cwk.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;
import java.util.Random;

import bosunard.aston.com.cs3040cwk.R;
import bosunard.aston.com.cs3040cwk.fragments.FindRestaurantFragment;
import bosunard.aston.com.cs3040cwk.models.GooglePlace;

public class FindRestaurantAdapter extends RecyclerView.Adapter<FindRestaurantAdapter.ViewHolder> {


    //private PlaceInfo mPlaceInfo;
    //private PlaceAutocompleteAdapter mPlaceAutoCompleteAdapter;
    //private GoogleApiClient mGoogleApiClient;

    private final String TAG = "FindRestaurantAdapter";

    //private ArrayList<String> mNames = new ArrayList<>();
    //private ArrayList<String> mCategory = new ArrayList<>();
    private  List<GooglePlace> nearby;

    private final FindRestaurantFragment.onFragmentListInteractionListener mListener;

    private Context resContext;

    private ImageView imageView;

    public FindRestaurantAdapter(Context context, List<GooglePlace> nearby,FindRestaurantFragment.onFragmentListInteractionListener mListener){
        this.resContext = context;
        //this.mNames = mNames;
        //this.mCategory = mCategory;
        this.nearby = nearby;
        this.mListener = mListener;
    }

    /*
    private void init() {
        Log.d(TAG, "init: initializing");

        mGoogleApiClient = new GoogleApiClient
                .Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

    }
    */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_result_item,viewGroup,false);


        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

//        int[] images ={
//                R.drawable.res_image1_0,
//                R.drawable.res_image,
//                R.drawable.res_image1_1,
//                R.drawable.res_image1_2,
//                R.drawable.res_image1_3,
//                R.drawable.res_image1_5,
//                R.drawable.res_image_2,
//                R.drawable.res_image_3,
//                R.drawable.res_image_4,
//                R.drawable.res_image_6
//
//        };
//        Random random = new Random();
//        viewHolder.resName.setText(nearby.get(position).getName());



        viewHolder.place = nearby.get(position);
        viewHolder.resName.setText(viewHolder.place.getName());
       // viewHolder.resPhoto.setImageResource(random.nextInt(images.length));
        // viewHolder.resCuisine.setText(nearby.get(position).);
        //viewHolder.resRating.setText( (int) viewHolder.place.getRating()); --app doesnt like this for some reason
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(resContext,MoreDetailActivity.class);

                if(mListener != null){

                    mListener.showDetails(viewHolder.place);
                }
            }
        });
       viewHolder.likeButton.setIconSizeDp(20);

       viewHolder.likeButton.setOnLikeListener(new OnLikeListener() {
           @Override
           public void liked(LikeButton likeButton) {

               Toast.makeText(resContext,"Added to Visited Restaurants!",Toast.LENGTH_SHORT);

           }

           @Override
           public void unLiked(LikeButton likeButton) {

           }
       });

     //

    }

    @Override
    public int getItemCount() {
        return nearby.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public GooglePlace place;
        View mView;
        ImageView resPhoto;
        TextView resName;
        //TextView resCuisine;
       // TextView resDistance;
        //TextView resRating;
        TextView priceLevel;

        TextView resPriceLevel;
        //Button moreInfoButton;


         LikeButton likeButton;

        //@BindView(R.id.heart_button)
        //LikeButton starButton;

        public ViewHolder(View view) {
            super(view);

            mView = view;

            resPhoto = view.findViewById(R.id.restaurant_photo);
            resName = view.findViewById(R.id.result_name);
           // resRating = view.findViewById(R.id.restaurant_rating);
            resPriceLevel = view.findViewById(R.id.restaurant_price_level);
          //  moreInfoButton = view.findViewById(R.id.more_info_button);


            //resDistance = view.findViewById(R.id.restaurant_distance);

            likeButton = view.findViewById(R.id.heart_button);


        }
    }

 }
