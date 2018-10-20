package v2.hackupc.guts2018.ciudadnube.Lambda;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

public interface MyInterface {
    @LambdaFunction
    Response SaveLocationToDB(Request request);

//    @LambdaFunction
//    UploadImageResponse UploadImage(UploadImageRequest request);

    @LambdaFunction
    AllDataResponse GetAllData(AllDataRequest request);
}
