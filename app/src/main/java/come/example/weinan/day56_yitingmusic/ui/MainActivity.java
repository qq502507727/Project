package come.example.weinan.day56_yitingmusic.ui;



import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import come.example.weinan.day56_yitingmusic.fragment.DownloadManagerFragment;
import come.example.weinan.day56_yitingmusic.fragment.GTBFragment;
import come.example.weinan.day56_yitingmusic.fragment.IndexFragment;
import come.example.weinan.day56_yitingmusic.fragment.LXBFragment;
import come.example.weinan.day56_yitingmusic.fragment.LastPlayFragment;
import come.example.weinan.day56_yitingmusic.fragment.MyFavMusicFragment;
import come.example.weinan.day56_yitingmusic.fragment.NDBFragment;
import come.example.weinan.day56_yitingmusic.fragment.ShowLocalMusicFragment;
import come.example.weinan.day56_yitingmusic.R;
import come.example.weinan.day56_yitingmusic.service.PlayService;
import come.example.weinan.day56_yitingmusic.utils.MessageEventType;
import come.example.weinan.day56_yitingmusic.vo.MessageEvent;
import come.example.weinan.day56_yitingmusic.vo.Mp3Info;

/**
 * Created by weinan on 2017/2/15.
 */

/**
 * ToolBar 的属性设置
 * toolbar:navigationIcon 设置navigation button
 * toolbar:logo 设置logo 图标
 * toolbar:title 设置标题
 * toolbar:titleTextColor 设置标题文字颜色
 * toolbar:subtitle 设置副标题
 * toolbar:subtitleTextColor 设置副标题文字颜色
 * toolbar:popupTheme Reference to a theme that should be used to inflate popups shown by widgets in the toolbar.
 * toolbar:titleTextAppearance 设置title text 相关属性，如：字体,颜色，大小等等
 * toolbar:subtitleTextAppearance 设置subtitle text 相关属性，如：字体,颜色，大小等等
 * toolbar:logoDescription logo 描述
 * android:background Toolbar 背景
 * android:theme 主题
 */

public class MainActivity extends BaseActivity implements View.OnClickListener,ShowLocalMusicFragment.OnMyLikeMusicListener {
    private TextView viewById;
    private FrameLayout frame_layout_main;
    private android.support.v4.app.FragmentManager fm1;
    private ShowLocalMusicFragment showLocalMusicFragment;
    private IndexFragment indexFragment;
    private Toolbar toolBar;
    private android.support.v4.app.FragmentTransaction ft;
    private NDBFragment ndbFragment;
    private LXBFragment lxbFragment;
    private GTBFragment gtbFragment;
    private LastPlayFragment lastPlayFragment;
    private DownloadManagerFragment downloadManagerFragment;
    private MyFavMusicFragment myFavMusicFragment;
    private ImageView imageView1_album;
    private TextView textView1_song_name;
    private TextView textView2_singer;
    private ImageButton imageButton1_play_list;
    private ImageButton imageButton1_play_pause;
    private ImageButton imageButton2_next;
    private LinearLayout inc_bar;
    private NotificationManager nm;


//    private NDBFragment ndbFragment;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        imageView1_album = (ImageView) findViewById(R.id.imageView1_album);
        textView1_song_name = (TextView) findViewById(R.id.textView1_song_name);
        textView2_singer = (TextView) findViewById(R.id.textView2_singer);
        imageButton1_play_list = (ImageButton) findViewById(R.id.imageButton1_play_list);
        imageButton1_play_pause = (ImageButton) findViewById(R.id.imageButton1_play_pause);
        imageButton2_next = (ImageButton) findViewById(R.id.imageButton2_next);
        inc_bar = (LinearLayout) findViewById(R.id.inc_bar);
        imageView1_album.setOnClickListener(this);
        imageButton1_play_list.setOnClickListener(this);
        imageButton1_play_pause.setOnClickListener(this);
        imageButton2_next.setOnClickListener(this);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        initEvent();
        //获得v4包下的FragmentManager
        fm1= getSupportFragmentManager();
        //给FrameLayout设置Fragment
        setFrameLayout();

    }
    //退出
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void exitApp(MessageEvent event) {
        if (event.type == MessageEventType.EXIT_APP) {
            nm.cancelAll();
            stopService(new Intent(this, PlayService.class));
            exit();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showMusicList(MessageEvent event){
//        switch (event.type){
//
//
//            case MessageEventType
//                break;
//        }
        //根据事件类型跳转页面  set方法可以优化的 现在先不做。
        switch (event.type){
            case SHOW_LOCAL_MUSIC_FRAGMENT:
                if (showLocalMusicFragment ==null) showLocalMusicFragment = ShowLocalMusicFragment.newInstance();
                setShowLocalMusicFragment();
                break;

            case SHOW_LATELY_PLAY_LIST_FRAGMENT:
                if (lastPlayFragment==null) {


                    lastPlayFragment = lastPlayFragment.newInstance();
                }
                setShowLateltPlayListFragment();
                break;
            case SHOW_DOWNLOAD_MANAGER_FRAGMENT:
                if (downloadManagerFragment==null) {

                    downloadManagerFragment = downloadManagerFragment.newInstance();
                }
                setDownManagerFragment();
                break;
            case SHOW_MY_FAV_MUSIC_LIST_FRAGMENT:
                if (showLocalMusicFragment==null) {

                    myFavMusicFragment = myFavMusicFragment.newInstance();
                }
                setMyFavMusicListFragment();
                break;
            case SHOW_CREATE_NEW_MUSIC_LIST:
                if (showLocalMusicFragment==null) {

                    showLocalMusicFragment = showLocalMusicFragment.newInstance();
                }
                setShowNetLxbFragment();
                break;

            case SHOW_NET_NDB_FRAGMENT:
                if (ndbFragment==null) {

                    ndbFragment = NDBFragment.newInstance();
                }

                setShowNetNdbFragment();
                break;
            case SHOW_NET_GTB_FRAGMENT:
                if (gtbFragment==null) {

                    gtbFragment = GTBFragment.newInstance();
                }
                setShowNetGtbFragment();
                break;
            case SHOW_NET_LXB_FRAGMENT:
                if (lxbFragment==null) {

                    lxbFragment = LXBFragment.newInstance();
                }
                setShowNetLxbFragment();
                break;



        }


    }

    private void setMyFavMusicListFragment() {

        ft = fm1.beginTransaction();
        ft.add(R.id.frame_layout_main, myFavMusicFragment);
        ft.addToBackStack(null);
        ft.commit();
        if (indexFragment!=null){
            ft.hide(indexFragment);
        }
    }

    private void setDownManagerFragment() {

        ft = fm1.beginTransaction();
        ft.add(R.id.frame_layout_main, downloadManagerFragment);
        ft.addToBackStack(null);
        ft.commit();
        if (indexFragment!=null){
            ft.hide(indexFragment);
        }
    }

    private void setShowLateltPlayListFragment() {
        System.out.println("---------------------------------------------");
        ft = fm1.beginTransaction();
        ft.add(R.id.frame_layout_main, lastPlayFragment);
        ft.addToBackStack(null);
        ft.commit();
        if (indexFragment!=null){
            ft.hide(indexFragment);
        }
    }

    private void setShowNetLxbFragment() {
        System.out.println("---------------------------------------------");
        ft = fm1.beginTransaction();
        ft.add(R.id.frame_layout_main, lxbFragment);
        ft.addToBackStack(null);
        ft.commit();
        if (indexFragment!=null){
            ft.hide(indexFragment);
        }
    }

    private void setShowNetGtbFragment() {
        System.out.println("---------------------------------------------");
        ft = fm1.beginTransaction();
        ft.add(R.id.frame_layout_main, gtbFragment);
        ft.addToBackStack(null);
        ft.commit();
        if (indexFragment!=null){
            ft.hide(indexFragment);
        }
    }

    private void setShowNetNdbFragment() {
        System.out.println("---------------------------------------------");
        ft = fm1.beginTransaction();
        ft.add(R.id.frame_layout_main, ndbFragment);
        ft.addToBackStack(null);
        ft.commit();
        if (indexFragment!=null){
            ft.hide(indexFragment);
        }

    }

    public void setShowLocalMusicFragment() {
        System.out.println("---------------------------------------------");
        ft = fm1.beginTransaction();
        ft.add(R.id.frame_layout_main, showLocalMusicFragment);
        ft.addToBackStack(null);
        ft.commit();
        if (indexFragment!=null){
            ft.hide(indexFragment);
        }
    }

    private void initEvent() {

    }

    private void setFrameLayout() {
        //获得indexFragment 对象
        indexFragment=IndexFragment.getInstance();
        ft = fm1.beginTransaction();
        ft.add(R.id.frame_layout_main,indexFragment);
        System.out.println("------------------------------");
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView1_album:
                startPlayMusicUI();
                break;
            case R.id.imageButton1_play_list:
                imageButton1_play_list();
                break;
            case R.id.imageButton1_play_pause:
                imageButton1_play_pause();
                break;
            case R.id.imageButton2_next:
                imageButton2_next();
                break;
        }
    }
    //打开播放界面
    private void startPlayMusicUI() {
//        Intent intent = new Intent(this, PlayActivity.class);
//        startActivity(intent);
    }

    public void imageButton1_play_list() {
//        new CardPopupWindow(this).showAsDropDown(inc_bar,0,0);
    }
    public void imageButton1_play_pause() {
        if (playService.isPlaying()) {
            playService.pause();
        } else {
            playService.play();
        }
    }

    public void imageButton2_next() {
        playService.next();
    }

    @Override
    public void loadMyLikeMusicData() {

    }

    @Override
    public void playMyLikeMusic(List<Mp3Info> mp3Infos, int position) {
        playService.setType(PlayService.TYPE_LOCAL);
        playService.setMp3Infos((ArrayList<Mp3Info>) mp3Infos);
        playService.play(position);
        savePlayRecord(position);
    }

    private void savePlayRecord(int position) {
        //保存用户自主点击的播放记录
        Mp3Info mp3Info = playService.getMp3Infos().get(position);
        try {
            Mp3Info playRecord = app.dbManager.selector(Mp3Info.class)
                    .where("mp3InfoId", "=", mp3Info.getMp3InfoId())
                    .findFirst();

            if (playRecord == null) {
                mp3Info.setPlayTime(System.currentTimeMillis());

                app.dbManager.save(mp3Info);
            } else {
                playRecord.setPlayTime(System.currentTimeMillis());
                app.dbManager.update(playRecord, "playTime");
            }
            queryPlayRecordCount();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void queryPlayRecordCount() {
        try {
            long count = app.dbManager.selector(Mp3Info.class).where("playTime", "<>", "0").count();
            MessageEvent event = new MessageEvent(MessageEventType.UPDATE_PLAY_RECORD_COUNT);
            event.data = count;
            EventBus.getDefault().post(event);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
