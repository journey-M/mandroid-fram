package dev.guowj.androidfram.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bwang on 2016/5/30.
 */
public abstract class CallBackUpLoadImg implements Callback {

    public final String ERROR3 = "网络访问错误!";


    @Override
    public void onResponse(Call call, Response response) {
        //验证返回结果和最外层Flag数据
        Response<BaseRespPhoto> baseResp = response;
        if (baseResp != null) {
            onResult(baseResp);
        } else {
            onErrr(ERROR3);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        onErrr(t.toString());
    }

    public abstract void onErrr(String errmsg);

    public abstract void onResult(Response<BaseRespPhoto> baseResult);

//    {"flag":"0","msg":["上传成功"],"code":0,"data":[{"imageId":["132"]}]}

    public static class BaseRespPhoto {
        public String flag;
        public String msg;
        public int code;
        public ImageId data;
    }

    public class ImageId {
        public List<String> imageId;
    }

}
