package come.example.weinan.day56_yitingmusic.ui;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLog;
import android.util.SparseArray;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import come.example.weinan.day56_yitingmusic.MyApplication;
import come.example.weinan.day56_yitingmusic.service.PlayService;

/**
 * Created by weinan on 2017/2/15.
 */
/**
 * 退出 ，记录每个Activity引用位置 退出的时候统一退出
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG="BaseActivity";
    private SparseArray sparseArray=new SparseArray();
    private ArrayList<Activity> list= new ArrayList();
    public MyApplication app;
    protected PlayService playService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add(this);
        initView();//模板方法设计模式 写一个抽象方法自己调用
        app= (MyApplication) getApplication();

    }

        @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    public void exit(){

        for (int i = 0;i<list.size();i++){
            list.get(i).finish();
        }
        list=null;
    }

    public abstract void initView();

    public void showToast(String text){
        Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
