package bosunard.aston.com.cs3040cwk.dummy;

import java.io.Serializable;

public class Bookings implements Serializable {

    int _id;
    private String name;
    private String date;
    private String time;
    private String phoneNumber;
    private String email;
    private int noOfGuests;

    private boolean isVisited;

    public int getNoOfGuests() {
        return noOfGuests;
    }

    public void setNoOfGuests(int noOfGuests) {
        this.noOfGuests = noOfGuests;
    }

    public Bookings(int _id,String name, String phoneNumber, String email, String date) {
        this._id = _id;
        this.name = name;
       this.date = date;
//        this.time = time;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}
