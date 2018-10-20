package v2.hackupc.guts2018.ciudadnube;

public class UploadImageResponse {

    String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UploadImageResponse(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public UploadImageResponse(){

    }
}
