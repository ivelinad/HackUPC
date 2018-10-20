package v2.hackupc.guts2018.ciudadnube;

public class Request {
    // the request that is sent to the database
    double lat;
    double lng;
    String description;
    String url;
    String method;
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public Request(double lat, double lng, String description, String url, String method, String time){
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        this.url = url;
        this.method = method;
        this.time = time;
    }

    public Request(){

    }
}
