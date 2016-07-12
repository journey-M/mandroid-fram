package dev.guowj.androidfram.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dev.guowj.androidfram.gutils.ToastUtils;

/**
 * Created by guowj on 2016/7/12.
 */
public class DemoTest {

    private static Tuser tuser;
    private static List<Tuser> listUser;

    private static TuserNorm userNomr;
    private static List<TuserNorm> listNormUser;

    static {
        tuser = new Tuser("李磊", 17);
        listUser = new ArrayList<>();
        listUser.add(tuser);
        listUser.add(new Tuser("小明", 31));
        listUser.add(new Tuser("牛大碗", 41));

        //不继承Serializable接口
        userNomr = new TuserNorm("测试2", 17);
        listNormUser = new ArrayList<>();
        listNormUser.add(new TuserNorm("小明", 31));
//        listNormUser.add(new TuserNorm("牛大碗", 41));
//        listNormUser.add(new TuserNorm("牛三碗", 41));
    }

    /**
     * 测试 SharePreferManager
     */

    public static void testSharePreferManagerSave() {
        SharePreferManager sharePrefer = new SharePreferManager();
        sharePrefer.put("test", "this is a test for str");
        sharePrefer.put("tfloat", 0.74f);

    }

    public static void testSharePreferManagerRead() {
        SharePreferManager sharePrefer = new SharePreferManager();

        String ac = (String) sharePrefer.find("test");
        ToastUtils.displayTextLong("" + ac);

        Float flot = (Float) sharePrefer.find("tfloat");
        ToastUtils.displayTextLong("" + flot);


    }

    /**
     * 测试 ObjectFileManager
     */

    public static void testFileManagerSave() {
        ObjectFileManager<String> objString = new ObjectFileManager<>();
        objString.put("test", "this is a test for str");

        ObjectFileManager<Float> objfloat = new ObjectFileManager<>();
        objfloat.put("tfloat", 0.74f);

        //存储对象  需实现 Serializable接口
        ObjectFileManager<Tuser> objUser = new ObjectFileManager<Tuser>();
        objUser.put("user", tuser);

        //存储list
        ObjectFileManager objListUser = new ObjectFileManager<>();
        objListUser.put("userlist", listNormUser.toArray());

    }


    public static void testFileManagerRead() {
        ObjectFileManager<String> objString = new ObjectFileManager<>();
        String a = objString.find("test");
        ToastUtils.displayTextLong(a);

        ObjectFileManager<Float> objfloat = new ObjectFileManager<>();
        Float ft = objfloat.find("tfloat");
        ToastUtils.displayTextLong("" + ft);

        //读取对象
        ObjectFileManager<Tuser> objUser = new ObjectFileManager<Tuser>();
        Tuser urser = objUser.find("user");
        ToastUtils.displayTextLong("userName=" + urser.name);

        //d读取list
        ObjectFileManager<List<Tuser>> objListUser = new ObjectFileManager<>();
        List<Tuser> list = objListUser.find("userlist");
        ToastUtils.displayTextLong("list size=" + list.size());

    }


    /**
     * 测试 ObjectDBManager
     */

    public static void testDBManagerSave() {
        ObjectDbManager<String> objString = new ObjectDbManager();
        objString.put("test", "test for dbmanager string");

        ObjectDbManager<Float> objfloat = new ObjectDbManager();
        objfloat.put("tfloat", 81.024f);

        //存储对象   不需要实现 Serializable接口
        ObjectDbManager<TuserNorm> objUser = new ObjectDbManager();
        objUser.put("user", userNomr);

        //存储 list
        ObjectDbManager<List<TuserNorm>> objListUser = new ObjectDbManager();
        objListUser.put("userlist", listNormUser);
    }

    public static void testDBManagerRead() {
        ObjectDbManager<String> objString = new ObjectDbManager();
        String a = objString.findObject("test", String.class);
        ToastUtils.displayTextLong(a);

        ObjectDbManager<Float> objfloat = new ObjectDbManager<>();
        Float ft = objfloat.findObject("tfloat", Float.class);
        ToastUtils.displayTextLong("" + ft);

        //读取对象   不需要实现 Serializable接口 必须有无参的构造函数
        ObjectDbManager<TuserNorm> objUser = new ObjectDbManager<TuserNorm>();
        TuserNorm urser = objUser.findObject("user", TuserNorm.class);
        ToastUtils.displayTextLong("userName=" + urser.name);

        //d读取list
        ObjectDbManager<TuserNorm> objListUser = new ObjectDbManager<>();
        List<TuserNorm> list = objListUser.findObjectArray("userlist", TuserNorm.class);
        ToastUtils.displayTextLong("list size=" + list.size());

    }


}

class Tuser implements Serializable {
    public String name;
    public int age;

    public Tuser() {
    }

    public Tuser(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class TuserNorm {


    public String name;
    public int age;

    public TuserNorm() {

    }

    public TuserNorm(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

