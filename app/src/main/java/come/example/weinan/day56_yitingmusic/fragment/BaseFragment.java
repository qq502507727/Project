package come.example.weinan.day56_yitingmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by weinan on 2017/2/15.
 */

public abstract class BaseFragment extends Fragment {
    public abstract void initView(View view);
    public abstract void initData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void toast(String info){
        Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
    }

}
