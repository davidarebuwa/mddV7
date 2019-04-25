package bosunard.aston.com.cs3040cwk;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bosunard.aston.com.cs3040cwk.adapters.SectionsPageAdapter;
import bosunard.aston.com.cs3040cwk.fragments.PastBookingsFragment;
import bosunard.aston.com.cs3040cwk.fragments.UpcomingBookingsFragment;

public class BookingActivity extends AppCompatActivity {


    //AddBookings Fragment
    DatabaseHelper mDatabaseHelper;
    private Button btnAdd;
    private EditText bookingName;
    private TextView displayDate;
    private TextView displayTime;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private TimePickerDialog.OnTimeSetListener mOnTimeSetListener;

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;


private static final String TAG = "BookingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

//        //Addbookings Fragment
//        bookingName = (EditText) findViewById(R.id.booking_name);
//        displayDate = (TextView) findViewById(R.id.booking_date);
//        btnAdd = (Button) findViewById(R.id.add_booking_button);
//        mDatabaseHelper = new DatabaseHelper(this);
//
//        displayDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                int year = calendar.get(Calendar.YEAR);
//                int month = calendar.get(Calendar.MONTH);
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(BookingActivity.this,
//                        android.R.style.Widget_Material_DatePicker,
//                        mOnDateSetListener,
//                        year,month,day);
//
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//            }
//        });
//        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                month = month + 1;
//
//                Log.d(TAG, "onDateSet: date: " + year + "/" + month + "/" + dayOfMonth);
//
//                String date = year + "/" + month + "/" + dayOfMonth;
//
//                displayDate.setText(date);
//            }
//        };
//
//        displayTime = (TextView) findViewById(R.id.booking_time);
//
//        displayTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Calendar calendar = Calendar.getInstance();
//                int hour = calendar.get(Calendar.HOUR);
//                int minute = calendar.get(Calendar.MINUTE);
//
//                TimePickerDialog timePickerDialog = new TimePickerDialog(BookingActivity.this,mOnTimeSetListener,
//                        hour,minute,true);
//                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                timePickerDialog.show();
//            }
//        });
//        mOnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//                Log.d(TAG, "onDateSet: date: " + hourOfDay + ":" + minute );
//
//                String time = hourOfDay + ":" + minute;
//
//                displayTime.setText(time);
//            }
//        };
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String newEntry = bookingName.getText().toString();
//                if(newEntry.length() != 0){
//
//                    addData(newEntry);
//                    bookingName.setText("");
//                }else{
//
//                    makeToast("Provide Restaurant Name!");
//                }
//            }
//        });




    }

    public void addData(String newEntry){

        //boolean insertData = mDatabaseHelper.addData(newEntry);

//        if(insertData){
//
//            makeToast("Booking adding sucessful");
//
//        }else{
//
//            makeToast("Uh-oh! Booking adding failed");
//        }

    }


    private void setupViewPager(ViewPager viewPager){


        SectionsPageAdapter adapter = new SectionsPageAdapter((getSupportFragmentManager()));
        adapter.addFragment(new UpcomingBookingsFragment(), "UPCOMING");
        adapter.addFragment(new PastBookingsFragment(),"PAST");

    }

    //Toast Message Maker
    private void makeToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }






}
