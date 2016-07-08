package dev.guowj.androidfram.net;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import dev.guowj.androidfram.gutils.LogsUtil;
import dev.guowj.androidfram.net.https.GoogleSsl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bwang on 2016/5/28.
 */
public class NetClient {


    //服务器
    public static String BASEURL = "http://172.16.1.3:8765/api/";


    //图片下载的地址
    public static String BASE_PHOTO_URL = BASEURL + "resource/download?resId=";
    private static ApiService apiService = null;
    private static Retrofit retrofit;
    private static OkHttpClient httpClient;

    /**
     * 初始化获取ApiService
     *
     * @return
     */
    public static ApiService getApiService() {

        if (httpClient == null) {
            initOkHttpClient();
        }

        if (apiService == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASEURL).client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }


    private static void initOkHttpClient() {
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(300000, TimeUnit.MILLISECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                //拦截器
                .addInterceptor(new HeaderInterceptor())
                //ssl证书
                .sslSocketFactory(GoogleSsl.test().getSocketFactory()).build();
    }


    /**
     * 下载文件，同步的
     *
     * @param wholeUrl
     * @param file
     */
    public static boolean downFileSync(final String wholeUrl, final File file) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            okhttp3.Request request = new okhttp3.Request.Builder().url(wholeUrl).build();
            okhttp3.Response response = okHttpClient.newCall(request).execute();
            BufferedSink sink = Okio.buffer(Okio.sink(file));
            sink.writeAll(response.body().source());
            sink.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}


class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder()
                .addHeader("device-id", "1234566")
                .addHeader("version", "2.0.1")
                .method(original.method(), original.body());
        Request request = builder.build();
        //添加log日志
        LogsUtil.e(request.toString());
        Response response = chain.proceed(request);
        String content = response.body().string();
        LogsUtil.e(content);
        okhttp3.MediaType mediaType = response.body().contentType();
        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
    }


}
