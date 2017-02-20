package come.example.weinan.day56_yitingmusic.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;

import come.example.weinan.day56_yitingmusic.R;
import come.example.weinan.day56_yitingmusic.adapter.DownloadManagerListAdapter;
import come.example.weinan.day56_yitingmusic.utils.Constant;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by weinan on 2017/2/19.
 */

public class DownloadManagerFragment extends BaseFragment {
    private DownloadManagerListAdapter adapter;
    private ListView listView;
    private Toolbar toolbar;
    public static DownloadManagerFragment newInstance(){
        Bundle bundle = new Bundle();
        DownloadManagerFragment fragment=new DownloadManagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_download_manager,container,false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listView_my_music);
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_layout,null);
        listView.addFooterView(footerView);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

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
        adapter = new DownloadManagerListAdapter(getContext(),scanDownloadMusicCount());
        listView.setAdapter(adapter);
    }
    //扫描下载目录里的音乐文件
    private File[] scanDownloadMusicCount(){
        File file = new File(Environment.getExternalStorageDirectory()+ Constant.DIR_MLY_MUSIC);
        File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                if(Constant.DIR_LRC.endsWith(s)){
                    return false;
                }
                return true;
            }
        });
        Log.i(TAG, "scanDownloadMusicCount: ----------");
        return files;
    }
}
