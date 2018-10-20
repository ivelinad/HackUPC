package v2.hackupc.guts2018.ciudadnube.Lambda;

public class Request {
    // the request that is sent to the database
    double lat;
    double lng;
    String description;
    String url;
    String time;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription(){
        return description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public double getLat(){
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Request(double lat, double lng, String description, String url, String time){
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        this.url = url;
        this.time = time;
    }

    public Request(){

    }
}
