package bosunard.aston.com.cs3040cwk.dummy;

import java.util.ArrayList;
import java.util.Objects;

import bosunard.aston.com.cs3040cwk.R;

public class Home {

    private String resName;
    private int resImage;

    public Home(String resName, int resImage){

        this.resName = resName;
        this.resImage = resImage;
    }

    public String getResName(){return resName;}

    public int getResImage() {
        return resImage;
    }

    private static int resId = 0;

    public static ArrayList<Home> createHomeList(int numItems) {
        ArrayList<Home> homeItems = new ArrayList<Home>();

        for (int i = 1; i <= numItems; i++) {
            homeItems.add(new Home("A" , R.drawable.image_2));
        }

        return homeItems;
    }
}
