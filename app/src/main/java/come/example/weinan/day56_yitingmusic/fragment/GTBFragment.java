package come.example.weinan.day56_yitingmusic.fragment;

import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import come.example.weinan.day56_yitingmusic.adapter.NetMusicListAdapter;
import come.example.weinan.day56_yitingmusic.fragment.Listener.OnNetListener;
import come.example.weinan.day56_yitingmusic.R;
import come.example.weinan.day56_yitingmusic.utils.JsoupUtils;
import come.example.weinan.day56_yitingmusic.vo.NetMusic;

public class GTBFragment extends BaseFragment implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    public static GTBFragment newInstance() {
        Bundle bundle = new Bundle();
        GTBFragment fragment = new GTBFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public GTBFragment() {
    }

    private Toolbar toolbar;
    private ListView listView_gtb;
    private LinearLayout loading_layout;
    private NetMusicListAdapter adapter;
    private ArrayList<NetMusic> netMusics = new ArrayList<>();
    private OnNetListener onNetListener;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gtb, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void initView(View view) {
        loading_layout = (LinearLayout) view.findViewById(R.id.loading_layout);
        loading_layout.setVisibility(View.GONE);
        listView_gtb = (ListView) view.findViewById(R.id.listView_gtb);
        listView_gtb.setVisibility(View.GONE);
        listView_gtb.setOnItemClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(0xff309ca6, 0xfffc9630, 0xfff235ad);
        adapter = new NetMusicListAdapter(getContext(), netMusics);
        listView_gtb.setAdapter(adapter);
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_layout, null);
        listView_gtb.addFooterView(footerView);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("港台榜");
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void initData() {
        if (netMusics.size() == 0) {
            loading_layout.setVisibility(View.VISIBLE);
            listView_gtb.setVisibility(View.GONE);
            new JsoupUtils().getNetMusic(getContext(), JsoupUtils.TYPE_GTB, new JsoupUtils.GetNetMusicListener() {
                @Override
                public void onSuccess(ArrayList<NetMusic> data) {
                    netMusics = data;
                    adapter.setNetMusics(data);
                    loading_layout.setVisibility(View.GONE);
                    listView_gtb.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onError(NetworkErrorException e) {
                    toast(e.getMessage());
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            });
        } else {
            adapter.setNetMusics(netMusics);
            loading_layout.setVisibility(View.GONE);
            listView_gtb.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onRefresh() {
    }
}