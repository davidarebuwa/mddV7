package bosunard.aston.com.cs3040cwk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by beaumoaj on 13/01/15.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment ();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_main);


        FragmentManager fm = this.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if(fragment == null){
            fragment = createFragment();
            fragment.setArguments(getIntent().getExtras());
            fm.beginTransaction().add(R.id.fragmentContainer,fragment).commit();
        }

    }
}
