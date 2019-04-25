package bosunard.aston.com.cs3040cwk.dummy;

import java.util.List;

public class Restaurant {

        private final String name;
        private final String address;
        private final String cuisine;
        private final String category;
        // public final int photoId;
        private final int image;
        public boolean visited;

    public Restaurant(String name, String address, String cuisine, String category, int image,boolean visited) {  //int photoId


        this.name = name;
        this.address = address;
        this.cuisine = cuisine;
        this.category = category;
        //this.photoId = photoId;
        this.image = image;
        this.visited = visited;
    }


    private List<Restaurant> restaurants;
    //public static final Map<String, RestaurantItem> ITEM_MAP = new HashMap<>();

    public String getName(){
        return name;
    }

    public String getAddress(){
        return category;
    }

    public String getCuisine(){
        return cuisine;
    }

    public String getCategory(){
        return category;
    }

    public int getImage(){
        return image;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /** private void initializeData() {
        restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("GAUCHO", "B3 2AA", "Argentinian", "Restaurant"));
        restaurants.add(new Restaurant("CAU", "B1 2JB", "Argentinian", "Restaurant"));
        restaurants.add(new Restaurant("GUSTO", "B3 2BS", "Italian", "Restaurant"));
        restaurants.add(new Restaurant("Andersons", "B3 1RL", "Grill", "Bar"));


    } **/





}


