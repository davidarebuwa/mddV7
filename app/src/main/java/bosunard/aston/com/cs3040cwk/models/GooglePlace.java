package bosunard.aston.com.cs3040cwk.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beaumoaj on 10/02/15.
 */
public class GooglePlace implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -4041502421563593320L;
    //@Key
    private String name;
    //@Key
    private String vicinity;
    //@Key
    private String formatted_address;
    //@Key
    private String formatted_phone_number;

    //@Key
    private List<String> types;

    public static class Geometry implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 2946649576104623502L;

        public static class Location implements Serializable {
            /**
             *
             */
            private static final long serialVersionUID = -1861462299276634548L;
            //@Key
            private double lat;
            //@Key
            private double lng;

            /**
             * @return the lat
             */
            public double getLat() {
                return lat;
            }

            /**
             * @param lat the lat to set
             */
            public void setLat(double lat) {
                this.lat = lat;
            }

            /**
             * @return the lng
             */
            public double getLng() {
                return lng;
            }

            /**
             * @param lng the lng to set
             */
            public void setLng(double lng) {
                this.lng = lng;
            }
        }

        //@Key
        private Location location;

        /**
         * @param location the location to set
         */
        public void setLocation(Location location) {
            this.location = location;
        }

        /**
         * @return the location
         */
        public Location getLocation() {
            return location;
        }
    }

//    public static class Photos implements Serializable{
//
//        /**
//         *
//         */
//       // private static final long serialVersionUID = -1861462299276634548L;
//        //@Key
//        private int height;
//        //@Key
//        private String html_attributions;
//        //@Key
//        private String photo_reference;
//        //@Key
//        private int width;
//
//        public int getHeight() {
//            return height;
//        }
//
//        public void setHeight(int height) {
//            this.height = height;
//        }
//
//        public String getHtml_attributions() {
//            return html_attributions;
//        }
//
//        public void setHtml_attributions(String html_attributions) {
//            this.html_attributions = html_attributions;
//        }
//
//        public String getPhoto_reference() {
//            return photo_reference;
//        }
//
//        public void setPhoto_reference(String photo_reference) {
//            this.photo_reference = photo_reference;
//        }
//
//        public int getWidth() {
//            return width;
//        }
//
//        public void setWidth(int width) {
//            this.width = width;
//        }
//    }

//    //@Key
//    private Photos photos;

    //@Key
    private Geometry geometry;

    //@Key
    private String icon;

    //@Key
    private String id;

    //@Key
    private String reference;

    //@Key
    private float rating;

    //@Key
    private String url;

    //@Key
    private int priceLevel;

    public int getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    private ArrayList<Review> reviews;

    public static class Review implements Serializable {

        public static class Aspect implements Serializable {
            private int rating;
            private String type;

            public void setRating(int r) {
                rating = r;
            }

            public int getRating() {
                return rating;
            }

            public void setType(String t) {
                type = t;
            }

            public String getType() {
                return type;
            }
        }

        private ArrayList<Aspect> aspects;
        private String author_name;
        private int rating;
        private String text;
                /*
                 * For example, the JSON data looks like this...
                 *
                "author_name" : "Little Al",
                "author_url" : "https://plus.google.com/103209428135026695692",
                "language" : "en",
                "rating" : 5,
                "text" : "fabulous experience ..really enjoyed",
                "time" : 1422356740
                *
                */

        public int getRating() { return rating; }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public void setAspects(ArrayList<Aspect> aspects) {
            this.aspects = aspects;
        }

        public List<Aspect> getAspects() {
            return aspects;
        }
    }

    //@Key
    private String website;

    public GooglePlace() {
        types = new ArrayList<String>();
    }

    @Override
    public String toString() {
        String typeList = "";
        for (String type : types) {
            typeList = typeList + type + " ";
        }
        return name + "\n" + vicinity + "\n" + typeList + "\n" +
                this.getGeometry().getLocation().getLat() + ", " +
                this.getGeometry().getLocation().getLng();
    }

   // public Photos getPhotos() {
      //  return photos;
  //  }

    //public void setPhotos(Photos photos) {
     //   this.photos = photos;
 //   }

    /**
     * @param geometry the geometry to set
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * @return the geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public List<String> getTypes() {
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    public void addType(String type) {
        types.add(type);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getFormatted_phone_number() {
        return formatted_phone_number;
    }

    public void setFormatted_phone_number(String formatted_phone_number) {
        this.formatted_phone_number = formatted_phone_number;
    }


}