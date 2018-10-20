package v2.hackupc.guts2018.ciudadnube;

public class UploadImageRequest {

    String encodedString;
    String timeStamp;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public UploadImageRequest(String encodedString, String timeStamp){
        this.encodedString = encodedString;
        this.timeStamp = timeStamp;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }

    public UploadImageRequest(){

    }
}
