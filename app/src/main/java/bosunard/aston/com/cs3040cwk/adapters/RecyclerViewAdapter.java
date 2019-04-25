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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bosunard.aston.com.cs3040cwk.R;
import bosunard.aston.com.cs3040cwk.dummy.Home;
import bosunard.aston.com.cs3040cwk.fragments.HomeScreenFragment;
import bosunard.aston.com.cs3040cwk.models.GooglePlace;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private Context mContext;
    //private List<GooglePlace> nearby;
    private List<Home> homeList;

    private HomeScreenFragment.OnFragmentInteractionListener mListener;

   // public RecyclerViewAdapter(Context context, List<GooglePlace> nearby,ArrayList<Integer> mImageUrls,HomeScreenFragment.OnFragmentInteractionListener mListener) {
        public RecyclerViewAdapter(Context context,List<Home> homeList){
       // this.mImageUrls = mImageUrls;
       // this.nearby = nearby;
        this.mContext = context;
        //this.mListener = mListener;
            this.homeList = homeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       Log.d(TAG,"onCreateViewHolder: called.");

       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup,false);


       return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {


            int[] images ={
                    R.drawable.res_image1_0,
                    R.drawable.res_image,
                    R.drawable.res_image1_1,
                    R.drawable.res_image1_2,
                    R.drawable.res_image1_3,
                    R.drawable.res_image1_5,
                    R.drawable.res_image_2,
                    R.drawable.res_image_3,
                    R.drawable.res_image_4,
                    R.drawable.res_image_6

            };
        Random random = new Random();

        Log.d(TAG,"onBindViewHolder: called");
      //  viewHolder.place = nearby.get(i);
      //  viewHolder.name.setText(viewHolder.place.getName());
        viewHolder.name.setText(homeList.get(i).getResName());
        viewHolder.image.setImageResource(random.nextInt(images.length));

//        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Intent intent = new Intent(resContext,MoreDetailActivity.class);
//
//                if(mListener != null){
//
//                    mListener.showDetails(viewHolder.place);
//                }
//            }
//        });

//        Glide.with(mContext)
//                .asBitmap()
//                .load(mImageUrls.get(i))
//                .into(viewHolder.image);


      //  viewHolder.name.setText(mNames.get(i));
      //  viewHolder.image.setOnClickListener(new View.OnClickListener(){

           // @Override
          //  public void onClick(View v) {
            //    Log.d(TAG,"onClick: clicked image: " + mNames.get(i));
           //     Toast.makeText(mContext,mNames.get(i),Toast.LENGTH_SHORT).show();
         //   }
       // });

    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public GooglePlace place;
        View mView;
        ImageView image;
        TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
        }
    }



}
