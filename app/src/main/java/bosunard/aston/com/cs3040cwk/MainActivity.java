package bosunard.aston.com.cs3040cwk;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bosunard.aston.com.cs3040cwk.fragments.AddBookingFragment;
import bosunard.aston.com.cs3040cwk.fragments.AddMealFragment;
import bosunard.aston.com.cs3040cwk.fragments.BookingListFragment;
import bosunard.aston.com.cs3040cwk.fragments.FindRestaurantFragment;
import bosunard.aston.com.cs3040cwk.fragments.HomeScreenFragment;
import bosunard.aston.com.cs3040cwk.fragments.MealListFragment;
import bosunard.aston.com.cs3040cwk.fragments.MoreDetailsFragment;
import bosunard.aston.com.cs3040cwk.fragments.VisitedRestaurantFragment;
import bosunard.aston.com.cs3040cwk.models.GooglePlace;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    HomeScreenFragment.OnFragmentInteractionListener,
                    FindRestaurantFragment.onFragmentListInteractionListener,
                    MoreDetailsFragment.onMoreDetailsFragmentInteractionListener,
                    AddBookingFragment.onAddBookingFragmentInteractionListener,
                    BookingListFragment.onBookingListFragmentInteractionListener,
                    VisitedRestaurantFragment.onVisitedRestaurantFragmentListInteractionListener,
                    MealListFragment.onMealListFragmentInteractionListener,
                    AddMealFragment.onAddMealFragmentInteractionListener
                    {

    private Toolbar toolbar;
    //private ArrayList<String> mNames = new ArrayList<>();
    //private ArrayList<String> mImageUrls = new ArrayList<>();
    private static final String TAG = "MainActivity";

    //CarouselView topCarouselView;
    //CarouselView bottomCarouselView;

   // private int[] recommendedImages = new int[] {R.drawable.image_2, R.drawable.splash2, R.drawable.image_3};
    //private int[] topPickImages = new int[] {R.drawable.image_4, R.drawable.splash6, R.drawable.splash3};

    // permissions request code
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermissions();

    }

    /**
     * Checks the dynamically controlled permissions and requests missing permissions from end user.
     */
    protected void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();
        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
                    grantResults);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        Log.e("DBA", "Required permission '" + permissions[index]
                                + "' not granted, exiting");
                        finish();
                        return;
                    }
                }
                // all permissions were granted
                initialize();
                break;
        }
    }

    /**
     //Checking google services version

     **/
    public boolean checkServices() {

        Log.i("DBA", "checking Google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //user can make app requests
            Log.i("DBA", "Google play services initialized");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.i("DBA", "error occured but we can fix it");
           //  Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available, ERROR_DIALOG_REQUEST);
           //  dialog.show();

        } else {
            Toast.makeText(this, "Map request cannot be made", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void initialize() {
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setTitle(R.string.title_home_screen_view);
        addFragment(new HomeScreenFragment());


//        if(checkServices()){
//            init();
//        }
        //getImages();



     //   topCarouselView =  findViewById(R.id.carouselView);
        //topCarouselView.setImageListener(topImageListener);


       // topCarouselView.setImageListener(new ImageListener() {
         //   @Override
           // public void setImageForPosition(int position, ImageView imageView) {
             //   imageView.setImageResource(recommendedImages[position]);
       //     }
        //});

        //topCarouselView.setPageCount(recommendedImages.length);

        //bottomCarouselView = findViewById(R.id.carouselView2);
        //bottomCarouselView.setImageListener(bottomImageListener);

       // bottomCarouselView.setImageListener(new ImageListener() {
        //    @Override
          //  public void setImageForPosition(int position, ImageView imageView) {
              //  imageView.setImageResource(topPickImages[position]);
         //   }
      //  });
     //   bottomCarouselView.setPageCount(recommendedImages.length);

    }

    public void init(){

//       // Button buttonMap = (Button) findViewById(R.id.btn_Map);
//        buttonMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(MainActivity.this,MapActivity.class);
//                startActivity(intent);
//            }
//        });
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home_screen_go) {
            //Go to home screen
            toolbar.setTitle(R.string.title_home_screen_view);
            addFragment(new HomeScreenFragment());
        } else if (id == R.id.find_restaurant) {
            // Handle the find restaurant action
            // construct the listener, pass "this" (ie this activity)
            // as the class to be informed when we get results
            //SearchResultListener listener = new SearchResultListener(this);
            // construct a request for places to "eat-drink" and pass
            // the listener to the request.
            // MakeSearchRequest req = new MakeSearchRequest("eat-drink", listener);
            // trigger the search request
            //req.doSearch();


            toolbar.setTitle(R.string.find_restaurant_title);
            addFragment(new FindRestaurantFragment());

        } else  if (id == R.id.nav_meal){
        //Handle the meals action
        toolbar.setTitle(R.string.meals_title);
        addFragment(new MealListFragment());

    }
         else if (id == R.id.nav_view_bookings) {
            //Handle the view bookings action
            toolbar.setTitle(R.string.view_bookings_title);
            addFragment(new BookingListFragment());
        }
        else if(id == R.id.nav_visted){

             toolbar.setTitle(R.string.visit_restaurant_title);
             addFragment(new VisitedRestaurantFragment());

        }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }



    private void addFragment(Fragment newFragment) {
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragmentContainer, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }



    @Override
    public void showDetails(GooglePlace place) {

        MoreDetailsFragment fragment = MoreDetailsFragment.newInstance(place);
        addFragment(fragment);
    }

    @Override
    public void addMeal() {


                AddMealFragment addMealFragment = AddMealFragment.newInstance();
                addFragment(addMealFragment);

    }

    @Override
    public void addBooking() {


                AddBookingFragment addBookingFragment = AddBookingFragment.newInstance();
                addFragment(addBookingFragment);


    }

    @Override
    public void reBookRestaurant() {

        AddBookingFragment fragment = AddBookingFragment.newInstance();
        addFragment(fragment);

    }




    // private void initRecyclerView(){

     //   Log.d(TAG,"initRecyclerView: init recyclerview");


    //}
}
