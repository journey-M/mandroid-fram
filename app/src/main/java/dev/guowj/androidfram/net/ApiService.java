package dev.guowj.androidfram.net;

import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by bwang on 2016/5/24.
 */
public interface ApiService {


    //发送pusuTOken
    @GET("pushtoken/upload")
    Call<JsonObject> sendPushToken(@Query("pushToken") String pushToken);


    //登陆接口
    @FormUrlEncoded
    @POST("user/login")
    Call<JsonObject> loginUser(@Field("userName") String uname, @Field("passWord") String passwd);


//    //单张照片上传
//    @Multipart
//    @POST("resource/upload")
//    Call<BaseResppnse<UploadImg>> uploadImage(@Part("file\"; filename=\"image.png\"") RequestBody imgs);
//
//    //多张上传
//    @Multipart
//    @POST("resource/upload")
//    Call<CallBackUpLoadImg.BaseRespPhoto> uploadImageS(@Part("file\"; filename=\"image.png\"") RequestBody... imgs);


}