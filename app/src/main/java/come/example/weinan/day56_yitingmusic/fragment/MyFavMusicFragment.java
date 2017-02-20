package come.example.weinan.day56_yitingmusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import come.example.weinan.day56_yitingmusic.R;
import come.example.weinan.day56_yitingmusic.adapter.MusicListAdapter;
import come.example.weinan.day56_yitingmusic.vo.Mp3Info;

/**
 * Created by weinan on 2017/2/17.
 */

public class MyFavMusicFragment extends BaseFragment{
    private ListView listView;
    private ArrayList<Mp3Info> likeMp3Infos = new ArrayList<>();
    private MusicListAdapter adapter;
    private Toolbar toolbar;
//    private OnMyLikeMusicListener onMyLikeMusicListener;
    public static MyFavMusicFragment newInstance(){
        Bundle bundle = new Bundle();
        MyFavMusicFragment fragment=new MyFavMusicFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

//    public interface OnMyLikeMusicListener{
//        public void loadMyLikeMusicData();
//        public void playMyLikeMusic(ArrayList<Mp3Info> mp3Infos,int position);
//    }
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onDestroy() {
//        EventBus.getDefault().unregister(this);
//        super.onDestroy();
//    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_like_music_list,container,false);
        initView(view);
        return view;
    }


    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listView_like);
//        listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_layout,null);
        listView.addFooterView(footerView);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        initData();
    }

    @Override
    public void initData() {

        adapter = new MusicListAdapter(likeMp3Infos,getContext());
        listView.setAdapter(adapter);
//        if (onMyLikeMusicListener!=null) {
//            onMyLikeMusicListener.loadMyLikeMusicData();
//        }
    }
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//        onMyLikeMusicListener.playMyLikeMusic(likeMp3Infos,position);
//    }

}
