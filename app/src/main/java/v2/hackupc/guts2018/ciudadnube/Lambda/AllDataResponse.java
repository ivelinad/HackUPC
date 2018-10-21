package v2.hackupc.guts2018.ciudadnube.Lambda;

import java.util.ArrayList;

public class AllDataResponse {

    ArrayList locations;

    public ArrayList getResponse() {
        return locations;
    }

    public void setResponse(ArrayList locations) {
        this.locations = locations;
    }

    public AllDataResponse(ArrayList locations){
        this.locations = locations;
    }

    public AllDataResponse(){

    }

}
