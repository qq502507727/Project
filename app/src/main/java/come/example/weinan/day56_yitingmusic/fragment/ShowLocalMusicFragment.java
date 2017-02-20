package come.example.weinan.day56_yitingmusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import come.example.weinan.day56_yitingmusic.adapter.MusicListAdapter;
import come.example.weinan.day56_yitingmusic.R;
import come.example.weinan.day56_yitingmusic.utils.MediaUtils;
import come.example.weinan.day56_yitingmusic.utils.MessageEventType;
import come.example.weinan.day56_yitingmusic.vo.MessageEvent;
import come.example.weinan.day56_yitingmusic.vo.Mp3Info;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by weinan on 2017/2/16.
 */

public class ShowLocalMusicFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView lv;
    private List<Mp3Info> mp3Infos=new ArrayList<>();

    private MusicListAdapter adapter;
    private OnMyLikeMusicListener onMyLikeMusicListener;
    //通过反射得到showLocalMusicFragment对象
    public static ShowLocalMusicFragment newInstance() {
        Bundle args = new Bundle();
        ShowLocalMusicFragment fragment = new ShowLocalMusicFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public interface OnMyLikeMusicListener{
            public void loadMyLikeMusicData();
        public void playMyLikeMusic(List<Mp3Info> mp3Infos, int position);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_music_list,container,false);

        initView(view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onMyLikeMusicListener = (OnMyLikeMusicListener) getActivity();
    }

    @Override
    public void onDetach() {
        onMyLikeMusicListener = null;
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void showMyLikeMusicList(MessageEvent event){
        if (event.type == MessageEventType.LOAD_MY_LIKE_MUSIC_LIST){
            Log.i(TAG, "showMyLikeMusicList: "+event.type);
            mp3Infos = (ArrayList<Mp3Info>) event.data;
            adapter.setMp3Infos((ArrayList<Mp3Info>) mp3Infos);
            adapter.notifyDataSetChanged();
            EventBus.getDefault().removeStickyEvent(event);
        }
    }
    @Override
    public void initView(View view) {
        lv = (ListView) view.findViewById(R.id.listView_like);
        lv.setOnItemClickListener(this);
        View v=LayoutInflater.from(getContext()).inflate(R.layout.footer_layout,null);
        lv.addFooterView(v);
        adapter=new MusicListAdapter(mp3Infos,getContext());
        MusicListAdapter adapter = new MusicListAdapter(getMpInfos(), getActivity());
        lv.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
        }
        return true;
    }
    @Override
    public void initData() {
        if (onMyLikeMusicListener!=null) {
            onMyLikeMusicListener.loadMyLikeMusicData();
        }
    }

    public List<Mp3Info> getMpInfos() {

        mp3Infos= MediaUtils.getMp3Infos(getActivity());

        return mp3Infos;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onMyLikeMusicListener.playMyLikeMusic(mp3Infos,position);
    }

}
