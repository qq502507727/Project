package come.example.weinan.day56_yitingmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import come.example.weinan.day56_yitingmusic.R;

/**
 * Created by weinan on 2017/2/16.
 */

public class MySingLocalMusicFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout linearLayout_local_music;
    private LinearLayout linearLayout_lately_play;
    private LinearLayout linearLayout_download_manager;
    private RelativeLayout relativeLayout_like;
    private LinearLayout linearLayout_new_songsheet;
    private TextView textView_local_music_count;
    private TextView textView_recent_play;
    private TextView textView_download;
    private TextView textView_like;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_music_list,container,false);
        initView(view);
        initData();
        return view;
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
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
