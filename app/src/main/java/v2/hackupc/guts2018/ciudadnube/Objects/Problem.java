package v2.hackupc.guts2018.ciudadnube.Objects;

import android.location.Location;

import java.io.Serializable;

public class Problem{
    private final Location location;
    private String description;

    public Problem(Location location){
        this.location = location;
        this.description = "";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Double getLat(){
        return location.getLatitude();
    }

    public Double getLng(){
        return location.getLongitude();
    }
}
