package bosunard.aston.com.cs3040cwk.dummy;

public class Meal {


    int _id;

    public String name;
    public String rating;
    public String restaurant;
    public String comment;
    public final int image;

    public Meal(String name, String rating, int image, String restaurant, String comment){

        this.name = name;
        this.rating = rating;
        // this.category = category;
        this.image = image;
        this.restaurant = restaurant;
        this.comment = comment;

    }

    public String getName(){return name;}
    public String getRating(){return rating;}
    // public String getCategory(){return category;}

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getImage() {
        return image;
    }


    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }


}
