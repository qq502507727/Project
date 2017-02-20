package come.example.weinan.day56_yitingmusic.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FilenameFilter;

import come.example.weinan.day56_yitingmusic.R;
import come.example.weinan.day56_yitingmusic.utils.Constant;
import come.example.weinan.day56_yitingmusic.utils.MediaUtils;
import come.example.weinan.day56_yitingmusic.utils.MessageEventType;
import come.example.weinan.day56_yitingmusic.vo.MessageEvent;

/**
 * Created by weinan on 2017/2/15.
 */

public class MyMusicFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "MyMusicFragment";
    private LinearLayout linearLayout_local_music;
    private LinearLayout linearLayout_lately_play;
    private LinearLayout linearLayout_download_manager;
    private RelativeLayout relativeLayout_like;
    private LinearLayout linearLayout_new_songsheet;
    private TextView textView_local_music_count;
    private TextView textView_recent_play;
    private TextView textView_download;
    private TextView textView_like;
//    public UpdateListener updateListener;
//    public static interface UpdateListener {
//        public void updateData();
//    }
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        updateListener = (UpdateListener) getActivity();
//    }
//
//    @Override
//    public void onDetach() {
//        updateListener = null;
//        super.onDetach();
//
//    }

    public static MyMusicFragment newInstance() {

        Bundle args = new Bundle();
        MyMusicFragment fragment = new MyMusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_music, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout_local_music:
                EventBus.getDefault().post(new MessageEvent(MessageEventType.
                        SHOW_LOCAL_MUSIC_FRAGMENT));

                toast("本地音乐");
                break;
            case R.id.linearLayout_lately_play:
                EventBus.getDefault().post(new MessageEvent(MessageEventType.
                        SHOW_LATELY_PLAY_LIST_FRAGMENT));

                toast("最近播放");
                break;
            case R.id.linearLayout_download_manager:
                EventBus.getDefault().post(new MessageEvent(MessageEventType.
                        SHOW_DOWNLOAD_MANAGER_FRAGMENT));

                toast("下载管理");
                break;
            case R.id.relativeLayout_like:
                EventBus.getDefault().post(new MessageEvent(MessageEventType.
                        SHOW_MY_FAV_MUSIC_LIST_FRAGMENT));

                toast("我喜欢的单曲");
                break;
            case R.id.linearLayout_new_songsheet:
                EventBus.getDefault().post(new MessageEvent(MessageEventType.
                        SHOW_CREATE_NEW_MUSIC_LIST));

                toast("新建歌单");
                break;


        }
    }


    @Override
    public void initView(View view) {
        linearLayout_local_music = (LinearLayout) view.findViewById(R.id.linearLayout_local_music);
        linearLayout_lately_play = (LinearLayout) view.findViewById(R.id.linearLayout_lately_play);
        linearLayout_download_manager = (LinearLayout) view.findViewById(R.id.linearLayout_download_manager);
        relativeLayout_like = (RelativeLayout) view.findViewById(R.id.relativeLayout_like);
        linearLayout_new_songsheet = (LinearLayout) view.findViewById(R.id.linearLayout_new_songsheet);
        textView_local_music_count = (TextView) view.findViewById(R.id.textView_local_music_count);
        textView_recent_play = (TextView) view.findViewById(R.id.textView_recent_play);
        textView_download = (TextView) view.findViewById(R.id.textView_download);
        textView_like = (TextView) view.findViewById(R.id.textView_like);
        linearLayout_local_music.setOnClickListener(this);
        linearLayout_lately_play.setOnClickListener(this);
        linearLayout_download_manager.setOnClickListener(this);
        relativeLayout_like.setOnClickListener(this);
        linearLayout_new_songsheet.setOnClickListener(this);

    }

    @Override
    public void initData() {
        int count = MediaUtils.getMp3Count(getContext());
        textView_local_music_count.setText(count + " 首");
//        if (updateListener != null) updateListener.updateData();
//        scanDownloadMusicCount();

    }

    private void scanDownloadMusicCount() {
        File file = new File(Environment.getExternalStorageDirectory() + Constant.DIR_MLY_MUSIC);
        String[] fileCount = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (Constant.DIR_LRC.endsWith(name)) {

                    return false;
                }
                return true;
            }


        });
        if (fileCount!=null) {
//        Log.i(TAG, "scanDownloadMusicCount: "+fileCount.length);
            textView_download.setText(fileCount.length + " 首");
        }

    }

    //更新下载管理音乐总数
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void textView_download(MessageEvent event){
        if(event.type == MessageEventType.DOWNLOAD_MUSIC_COUNT) {
            textView_download.setText(event.data + " 首");
        }
    }

    //更新本地音乐总数
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void textView_local_music_count(MessageEvent event){
        if(event.type == MessageEventType.UPDATE_LOCAL_MUSIC_COUNT) {
            textView_local_music_count.setText(event.data + " 首");
        }
    }

    //用户自主播放记录
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setTextView_recent_play(MessageEvent event){
        if(event.type == MessageEventType.UPDATE_PLAY_RECORD_COUNT) {
            textView_recent_play.setText(event.data + " 首");
        }
    }

    //用户喜欢的单曲
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setTextView_like(MessageEvent event){
        if(event.type == MessageEventType.UPDATE_MY_LIKE_MUSIC_COUNT) {
            Log.i(TAG, "setTextView_like: "+event.data);
            textView_like.setText(event.data + " 首");
        }
    }



    }