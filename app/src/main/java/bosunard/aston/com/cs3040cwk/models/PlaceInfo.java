package bosunard.aston.com.cs3040cwk.models;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

public class PlaceInfo {

    private String name;
    private String address;
    private String phoneNumber;
    private String id;
    private Uri websiteUri;
    private float rating;
    private String attributions;
    private int priceLevel;
    private LatLng latLng;
    private String icon;

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public PlaceInfo(String name, String address, String phoneNumber, String id, Uri websiteUri, float rating, String attributions, int priceLevel, LatLng latLng) {
        this.name = name;

        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.websiteUri = websiteUri;
        this.rating = rating;
        this.attributions = attributions;
        this.priceLevel = priceLevel;
        this.latLng = latLng;
    }

    public PlaceInfo() {
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getId() {
        return id;
    }

    public Uri getWebsiteUri() {
        return websiteUri;
    }

    public float getRating() {
        return rating;
    }

    public String getAttributions() {
        return attributions;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWebsiteUri(Uri websiteUri) {
        this.websiteUri = websiteUri;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setAttributions(String attributions) {
        this.attributions = attributions;
    }

    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    @Override
    public String toString() {
        return "PlaceInfo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id='" + id + '\'' +
                ", websiteUri=" + websiteUri +
                ", rating=" + rating +
                ", attributions='" + attributions + '\'' +
                ", priceLevel=" + priceLevel +
                ", latLng=" + latLng +
                '}';
    }
}
