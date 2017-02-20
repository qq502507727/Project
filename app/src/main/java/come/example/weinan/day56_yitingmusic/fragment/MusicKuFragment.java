package come.example.weinan.day56_yitingmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import come.example.weinan.day56_yitingmusic.R;
import come.example.weinan.day56_yitingmusic.utils.MessageEventType;
import come.example.weinan.day56_yitingmusic.vo.MessageEvent;

/**
 * Created by weinan on 2017/2/15.
 */

public class MusicKuFragment extends BaseFragment implements View.OnClickListener {
    private Banner banner;
    private LinearLayout linearLayout_ndb;
    private LinearLayout linearLayout_gtb;
    private LinearLayout linearLayout_lxb;
    private HashMap<String, String> bannerMap = new HashMap<>();
    public static MusicKuFragment newInstance() {

        Bundle args = new Bundle();
        MusicKuFragment fragment = new MusicKuFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_music_ku,container,false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        linearLayout_ndb = (LinearLayout) view.findViewById(R.id.linearLayout_ndb);
        linearLayout_gtb = (LinearLayout) view.findViewById(R.id.linearLayout_gtb);
        linearLayout_lxb = (LinearLayout) view.findViewById(R.id.linearLayout_lxb);
        linearLayout_ndb.setOnClickListener(this);
        linearLayout_gtb.setOnClickListener(this);
        linearLayout_lxb.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout_ndb:
                toast("内地音乐");
                EventBus.getDefault().post(new MessageEvent(MessageEventType.SHOW_NET_NDB_FRAGMENT));
                break;
            case R.id.linearLayout_gtb:
                toast("港台榜");
                EventBus.getDefault().post(new MessageEvent(MessageEventType.SHOW_NET_GTB_FRAGMENT));


                break;
            case R.id.linearLayout_lxb:
                toast("流行榜");
                EventBus.getDefault().post(new MessageEvent(MessageEventType.SHOW_NET_LXB_FRAGMENT));

//                EventBus.getDefault().post(new MessageEvent(MessageEventType.SHOW_NET_LXB_FRAGMENT));
                break;
        }
    }
}
