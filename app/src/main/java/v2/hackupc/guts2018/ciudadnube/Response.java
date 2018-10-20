package v2.hackupc.guts2018.ciudadnube;

public class Response {
    // the response object from the database

    String resp;

//    public String getDescription() {
//        return description;
//    }
//
//    public void setLng(double lng) {
//        this.lng = lng;
//    }
//
//    public void setLat(double lat) {
//        this.lat = lat;
//    }
//
//    public String getUrl() {
//        return url;
//    }

//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public double getLng() {
//        return lng;
//    }
//
//    public double getLat() {
//        return lat;
//    }


    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public Response(String resp){
        this.resp = resp;
    }

    public Response(){

    }
}
