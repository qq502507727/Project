package come.example.weinan.day56_yitingmusic;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.ArrayList;

import come.example.weinan.day56_yitingmusic.utils.Constant;
import come.example.weinan.day56_yitingmusic.vo.Mp3Info;

/**
 * Created by weinan on 2017/2/15.
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    public static Context context;
    public static SharedPreferences sp;
    public DbManager dbManager;
    //当前的播放列表
    public ArrayList<Mp3Info> currPlayList = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        x.Ext.init(this);
        sp = getSharedPreferences(Constant.SP_NAME, Context.MODE_PRIVATE);
        DbManager.DaoConfig config = new DbManager.DaoConfig()
                .setDbName(Constant.DB_NAME)
                .setDbVersion(Constant.VERSION)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        //开启WAL,对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        Log.i(TAG, "onUpgrade: "+oldVersion);
                    }
                });
        dbManager = x.getDb(config);
//        try {
//            dbManager.dropDb();
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
        context = getApplicationContext();
    }
}
