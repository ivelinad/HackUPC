package v2.hackupc.guts2018.ciudadnube;

public class Response {
    // the response object from the database

    String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Response(String response){
        this.response = response;
    }

    public Response(){

    }
}
