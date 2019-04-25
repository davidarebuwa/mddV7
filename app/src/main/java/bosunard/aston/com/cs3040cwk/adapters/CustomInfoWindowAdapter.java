package bosunard.aston.com.cs3040cwk.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import bosunard.aston.com.cs3040cwk.R;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;

    private CardView mCardView;


    public CustomInfoWindowAdapter(Context mContext) {

        this.mContext = mContext;

        mWindow = LayoutInflater.from(mContext).inflate(R.layout.custom_info_window,null);
    }

    private void renderWindowText(Marker marker,View view){

        mCardView = (CardView) view.findViewById(R.id.result_map_cv);

        String title = marker.getTitle();
        TextView tvTitle = (TextView) view.findViewById(R.id.result_map_title);

        if(!title.equals("")){
            tvTitle.setText(title);
        }

        String snippet = marker.getSnippet();
        TextView tvSnippet = (TextView) view.findViewById(R.id.result_map_snippet);

        if(!snippet.equals("")){
            tvSnippet.setText(snippet);
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        renderWindowText(marker,mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        renderWindowText(marker,mWindow);
        return mWindow;
    }
}
