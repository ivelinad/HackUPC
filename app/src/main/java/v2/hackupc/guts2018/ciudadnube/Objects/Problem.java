package v2.hackupc.guts2018.ciudadnube.Objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Problem{
    private final double lat;
    private final double lng;
    private String description;
    private String imageUrl;
    private String imagePath;

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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageAsString(){
        Bitmap bm = BitmapFactory.decodeFile(imagePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return  encodedImage;
    }

    public Double getLng(){
        return lng;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
