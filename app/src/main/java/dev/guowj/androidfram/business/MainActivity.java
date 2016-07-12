package dev.guowj.androidfram.business;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import dev.guowj.androidfram.R;
import dev.guowj.androidfram.business.testdb.Album;
import dev.guowj.androidfram.business.testdb.Song;
import dev.guowj.androidfram.business.testdb.User;
import dev.guowj.androidfram.cache.DemoTest;
import dev.guowj.androidfram.cache.LocalDataFactory;
import dev.guowj.androidfram.cache.ObjectFileManager;
import dev.guowj.androidfram.gutils.LogsUtil;
import dev.guowj.androidfram.cache.StorageUtil;
import dev.guowj.androidfram.gutils.ToastUtils;
import dev.guowj.androidfram.net.NetClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private Button btn_add, btn_delete, btn_update, btn_qurrey, btn_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                String extUrl = getExternalCacheDir().getAbsolutePath();
                String interUrl = getCacheDir().getAbsolutePath();
                String sdurl = StorageUtil.getSDCardDiskDir().getAbsolutePath();
                LogsUtil.e("exturl =" + extUrl);
                LogsUtil.e("interUrl =" + interUrl);
                LogsUtil.e("sdcardpath =" + sdurl);

                ToastUtils.displayTextLong("exturl =" + extUrl);
                ToastUtils.displayTextLong("interUrl =" + interUrl);
                ToastUtils.displayTextLong("sdurl =" + sdurl);


            }
        });


//        String result = SecurityUtils.encodeByMD5("hahahahh");
//        ToastUtils.displayTextLong(result);
//        LogsUtil.e(result);
//        LogsUtil.e("appid = " + BuildConfig.APPLICATION_ID);

        initViews();


        testNetwork();


    }


    private void initViews() {
        btn_create = (Button) findViewById(R.id.btn_create);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delet);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_qurrey = (Button) findViewById(R.id.btn_qurry);


        btn_add.setOnClickListener(onclk);
        btn_delete.setOnClickListener(onclk);
        btn_update.setOnClickListener(onclk);
        btn_qurrey.setOnClickListener(onclk);
        btn_create.setOnClickListener(onclk);
    }

    View.OnClickListener onclk = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if (v == btn_create) {
                DemoTest.testSharePreferManagerSave();
            } else if (v == btn_add) {
                DemoTest.testSharePreferManagerRead();
            } else if (v == btn_delete) {

                DemoTest.testFileManagerSave();
                DemoTest.testDBManagerSave();

            } else if (v == btn_update) {
                DemoTest.testFileManagerRead();

            } else if (v == btn_qurrey) {

                DemoTest.testDBManagerRead();

            }

        }
    };


    private void save() {
        Album album = new Album();
        album.setName("album");
        album.setPrice(10.99f);
        album.setCover("this is a test".getBytes());
        album.save();

        Song song1 = new Song();
        song1.setName("song1");
        song1.setDuration(320);
        song1.setAlbum(album);
        song1.save();

        Song song2 = new Song();
        song2.setName("song2");
        song2.setDuration(356);
        song2.setAlbum(album);
        song2.save();
    }


    private void testSave() {

        User user = new User();
        user.name = "李刚";
        user.age = 19;
        user.sex = "男";
        List<User> lsuser = new ArrayList<>();

        ObjectFileManager objfile = (ObjectFileManager) LocalDataFactory.getManager(LocalDataFactory.LocalDataType.CACHE);
        objfile.put("ang", lsuser);
    }

    private void showdata() {

        ObjectFileManager objfile = (ObjectFileManager) LocalDataFactory.getManager(LocalDataFactory.LocalDataType.CACHE);
        User user = (User) objfile.find("ang");

        ToastUtils.displayTextLong("name:" + user.name + "/n" +
                "age:" + user.age + "/n" +
                "sex:" + user.sex);


    }

    private void testNetwork() {

        NetClient.getApiService().findPageInfo().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                LogsUtil.e("" + response.body());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                LogsUtil.e("faild =" + t.toString());

            }
        });
    }


}
