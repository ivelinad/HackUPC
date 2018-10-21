package v2.hackupc.guts2018.ciudadnube.Lambda;

import v2.hackupc.guts2018.ciudadnube.Objects.Problem;

public class SolveIssueRequest {

    double lat;
    double lng;
    String description;
    String url;
    String time;

    public double getLat() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public SolveIssueRequest(double lat, double lng, String description, String url, String time) {
        this.lat = lat;
        this.lng = lng;
        this.description = description;
        this.url = url;
        this.time = time;
    }

    public SolveIssueRequest(){

    }

}
