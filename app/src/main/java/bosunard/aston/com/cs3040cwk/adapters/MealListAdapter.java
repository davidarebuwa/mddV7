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
import bosunard.aston.com.cs3040cwk.dummy.Meal;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MealViewHolder> {

    private Context mContext;
    private List<Meal> mealList;

    String[] options = {"Edit" , "Delete"};

    public MealListAdapter(Context mContext, List<Meal> mealList) {
        this.mContext = mContext;
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public MealListAdapter.MealViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_record_item,viewGroup,false);

        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealListAdapter.MealViewHolder mealViewHolder, int i) {

     //   int[] images ={
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

        mealViewHolder.mealName.setText(mealList.get(i).getName());
        //mealViewHolder.mealThumbnail.setImageResource(mealList.get(i).getImage());

        mealViewHolder.moreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Options");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on option[which]

                        if ("Edit".equals(options[which])){
                            Toast.makeText(mContext,"editing meal", Toast.LENGTH_SHORT).show();
                        }
                        else if ("Delete".equals(options[which])){
                            Toast.makeText(mContext,"deleting..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
            }
        });

        //mealViewHolder.mealThumbnail.setImageResource(random.nextInt(images.length));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView mealThumbnail;
        TextView mealName;
        ImageView moreOptions;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            mealThumbnail = itemView.findViewById(R.id.item_image);
            mealName = itemView.findViewById(R.id.item_name);
            moreOptions = itemView.findViewById(R.id.more_options_button);
        }
    }
}
