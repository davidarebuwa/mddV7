package bosunard.aston.com.cs3040cwk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import bosunard.aston.com.cs3040cwk.dummy.Bookings;
import bosunard.aston.com.cs3040cwk.dummy.Meal;
import bosunard.aston.com.cs3040cwk.dummy.Restaurant;

public class DatabaseHelper extends SQLiteOpenHelper {


        private static final String TAG = "DatabaseHelper";

        private static final String DBNAME = "myRestaurant";

        private static final String bookingsTable = "Bookings";
        private static final String bookingID = "BookingID";
        public static final String bookingName = "BookingName";
        public static final String bookingPhoneNo = "BookingPhoneNo";
        // public static final String bookingEmail = "BookingEmail";
        public static final String bookingDate = "BookingDate";
        public static final String bookingTime = "BookingTime";
        public static final String bookingGuests = "BookingGuests";

        private static final String mealTable = "Meal";
        private static final String mealID = "MealID";
        public static final String mealName = "MealName";
        public static final String mealRestaurant = "MealRestaurant";
        public static final String mealRating = "MealRating";
        public static final String mealComment = "MealComment";
        public static final String mealPhoto = "MealPhoto";

        private static final String visitedRestaurantsTable = "VisitedRestaurants";
        private static final String visitedID = "VisitedID";
        public static final String visitedName = "VisitedName";


        private static final String viewBookings = "ViewBookings";
        private static final String viewMeals = "ViewMeals";
        private static final String viewVisitedRestaurants = "ViewVisitedRestaurants";

        //private static final String COL1 = "ID";
        //private static final String COL2 = "name";
        //  private static final String COL3 = "booking_date";
        // private static final String COL4 = "booking_time";


        public DatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

//        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + COL2 +" TEXT)";
//        db.execSQL(createTable);

            //Meals table contsruction
            String sql = "CREATE TABLE " + mealTable + " (" + mealID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT , " + mealName + " TEXT, " + mealRestaurant +
                    " TEXT," + mealRating + " TEXT," + mealComment + " TEXT," + mealPhoto + " TEXT )";

            Log.i(TAG, sql);
            db.execSQL(sql);

            //Bookings table construction
            sql = "CREATE TABLE " + bookingsTable + "(" + bookingID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + bookingName
                    + " TEXT, " + bookingPhoneNo + " INTEGER, " + bookingDate + " TEXT," + bookingTime + " TEXT," + bookingGuests + " INTEGER)";

            Log.i(TAG, sql);
            db.execSQL(sql);

            //Visited Restaurants table
            sql = "CREATE TABLE " + visitedRestaurantsTable + "(" + visitedID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + visitedName
                    + " TEXT)";

            Log.i(TAG, sql);
            db.execSQL(sql);


            // create the view of bookings
            sql = "CREATE VIEW " + viewBookings + " AS SELECT " + bookingsTable + "."
                    + bookingID + " AS _id," + " " + bookingsTable + "." + bookingName
                    + "," + " " + bookingsTable + "." + bookingPhoneNo + "," + " "
                    + bookingsTable + "." + bookingDate + ","  + " " + bookingsTable + "." +
                    bookingTime + "," + " " + bookingsTable + "." + bookingGuests;

            Log.i("TAG", sql);
            db.execSQL(sql);

            // create the view of meals

            sql = "CREATE VIEW " + viewMeals + " AS SELECT " + mealTable + "."
                    + mealID + " AS _id," + " " + mealTable + "." + mealName
                    + "," + " " + mealTable + "." + mealRating + "," + " "
                    + mealTable + "." + mealRating + "," + " " + mealTable + "." + mealComment +
                    "," + " " + mealTable + "." + mealPhoto;

            Log.i("TAG", sql);
            db.execSQL(sql);

            //visited restaurants view

            sql = "CREATE VIEW " + viewVisitedRestaurants + " AS SELECT " + visitedRestaurantsTable + "."
                    + visitedID + " AS _id," + " " + visitedRestaurantsTable + "." + visitedName;


        }

        public Cursor getAllBookings() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM " + viewBookings, null);
            return cur;
        }

        public Cursor getAllMeals() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM " + viewMeals, null);
            return cur;
        }

        public Cursor getAllVisitedRestaurants() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM " + viewVisitedRestaurants, null);
            return cur;
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + bookingsTable);
            db.execSQL("DROP TABLE IF EXISTS " + mealTable);
            db.execSQL("DROP TABLE IF EXISTS " + visitedRestaurantsTable);

            db.execSQL("DROP VIEW IF EXISTS " + viewBookings);
            db.execSQL("DROP VIEW IF EXISTS " + viewMeals);
            db.execSQL("DROP VIEW IF EXISTS " + viewVisitedRestaurants);
            onCreate(db);
        }

        public long AddBooking(Bookings booking) {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(bookingName, booking.getName());
            cv.put(bookingDate, booking.getDate());
            cv.put(bookingPhoneNo, booking.getPhoneNumber());
            cv.put(bookingDate, booking.getDate());
            cv.put(bookingTime, booking.getTime());
            cv.put(bookingGuests, booking.getNoOfGuests());

            long i = db.insert(bookingsTable, bookingName, cv);
            db.close();
            return i;
        }

        public long AddMeal(Meal meal) {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(mealName, meal.getName());
            cv.put(mealRating, meal.getRating());
            cv.put(mealRestaurant, meal.getRestaurant());
            cv.put(mealComment, meal.getComment());
            cv.put(mealPhoto, meal.getImage());

            long i = db.insert(mealTable, mealName, cv);
            db.close();
            return i;
        }

        public long AddVisitedRestaurants(Restaurant restaurant) {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(visitedName, restaurant.getName());

            long i = db.insert(visitedRestaurantsTable, visitedName, cv);
            db.close();
            return i;

        }

        public int UpdateBookings(Bookings booking) {


            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(bookingName, booking.getName());
            cv.put(bookingDate, booking.getDate());
            cv.put(bookingTime, booking.getTime());
            cv.put(bookingPhoneNo, booking.getPhoneNumber());
            cv.put(bookingDate, booking.getDate());
            cv.put(bookingGuests, booking.getNoOfGuests());


            return db.update(bookingsTable, cv, bookingID + "=?",
                    new String[] { String.valueOf(booking.getId()) });

        }

        public int UpdateMeals(Meal meal) {


            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(mealName, meal.getName());
            cv.put(mealRating, meal.getRating());
            cv.put(mealRestaurant, meal.getRestaurant());
            cv.put(mealComment, meal.getComment());
            cv.put(mealPhoto, meal.getImage());


            return db.update(mealTable, cv, mealID + "=?",
                    new String[] { String.valueOf(meal.getId()) });

        }

        public void DeleteBookings(Bookings booking) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(bookingsTable, bookingID + "=?",
                    new String[] { String.valueOf(booking.getId()) });
            db.close();
        }

        public void DeleteMeal(Meal meal) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(mealTable, mealID + "=?",
                    new String[] { String.valueOf(meal.getId()) });
            db.close();
        }

    }
