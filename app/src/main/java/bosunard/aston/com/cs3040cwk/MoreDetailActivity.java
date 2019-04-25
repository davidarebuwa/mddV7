package bosunard.aston.com.cs3040cwk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import bosunard.aston.com.cs3040cwk.fragments.MoreDetailsFragment;
import bosunard.aston.com.cs3040cwk.models.GooglePlace;

public class MoreDetailActivity extends SingleFragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {

        GooglePlace place = (GooglePlace)getIntent().getSerializableExtra("PLACE");

        return MoreDetailsFragment.newInstance(place);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent i = NavUtils.getParentActivityIntent(this);
                if(i != null){

                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    NavUtils.navigateUpTo(this,i);
                    return true;

                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
