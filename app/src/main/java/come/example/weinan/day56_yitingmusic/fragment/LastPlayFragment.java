package come.example.weinan.day56_yitingmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import come.example.weinan.day56_yitingmusic.R;

/**
 * Created by weinan on 2017/2/17.
 */

public class LastPlayFragment extends BaseFragment {
    public static LastPlayFragment newInstance(){
        Bundle bundle = new Bundle();
        LastPlayFragment fragment=new LastPlayFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_lately_play_list,container,false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }
}
