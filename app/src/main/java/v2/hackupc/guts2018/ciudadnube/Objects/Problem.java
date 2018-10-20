package v2.hackupc.guts2018.ciudadnube.Objects;

import android.location.Location;

import java.io.Serializable;

public class Problem{
    private final double lat;
    private final double lng;
    private String description;

    public Problem(Location location){
        this.lat = location.getLatitude();
        this.lng = location.getLongitude();
        this.description = "";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Double getLat(){
        return lat;
    }

    public Double getLng(){
        return lng;
    }
}
