package come.example.weinan.day56_yitingmusic.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import org.greenrobot.eventbus.EventBus;

import come.example.weinan.day56_yitingmusic.adapter.MyTabViewPagerAdapter;
import come.example.weinan.day56_yitingmusic.R;
import come.example.weinan.day56_yitingmusic.utils.MessageEventType;
import come.example.weinan.day56_yitingmusic.vo.MessageEvent;


/**
 * Created by weinan on 2017/2/15.
 */

public class IndexFragment extends Fragment {
    private  Toolbar toolBar;
    private DrawerLayout drawer;
    private ViewPager viewPager;
    private NavigationView navigationView;
    private Fragment[] fragments;
    private FragmentManager fm;
    private String[] titleList=null;
    private MyTabViewPagerAdapter myTabViewPagerAdapter;
    private TabLayout tab;
    private Menu indexMenu;
    private MenuItem menu_item_fav1;
    private MenuItem exit;
    private MenuItem menu_item_playinglist1;
    private MenuItem menu_item_listmore1;
    String fav = "1";

    public static IndexFragment getInstance(){
        Bundle args = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        IndexFragment indexFragment= new IndexFragment();
        return indexFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index,container,false);
        //实例化ToolBar
        toolBar = (Toolbar) view.findViewById(R.id.toolbar);
        //实例化DrawerLayout
        drawer = (DrawerLayout) view.findViewById(R.id.drawer);
        //实例化ViewPager 用于显示主界面设置
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        //实例化TableLayout
        final TabLayout tab = (TabLayout) view.findViewById(R.id.tab);
        //实例化navigationView
        navigationView = (NavigationView) view.findViewById(R.id.navigationView);
        //为ViewPager里面添加fragment
//        fragments
        //为tableLayout里面添加选项，我的音乐盒音乐库，做完可以与ViewPager同步
        //为Toolbar设置Log
//        toolBar.setLogo(R.mipmap.);
        //为Toolbar设置左按钮 只有Activity（AppCompatActivity）里面有此方法
        //获得Activity 需要强转 快捷键cast
        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolBar);
        //设置返回键可用 并开启关闭监听
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(activity,drawer,
                toolBar,R.string.app_name,R.string.app_name);
        //必须要设置
        
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
//        toolBar.setOverflowIcon(getResources().getDrawable(R.drawable.actionbar_bottom));
        /*设置tab TabLayout 和 ViewPager

         */
        titleList = getResources().getStringArray(R.array.tabs);
        fm = activity.getSupportFragmentManager();
        myTabViewPagerAdapter = new MyTabViewPagerAdapter(fm,initFragment(),titleList);
        viewPager.setAdapter(myTabViewPagerAdapter);
//        tableLayout.addView(child);
        tab.setupWithViewPager(viewPager);
        /**
         * 为NavigationView里面的按钮设置点击设置点击事件
         */
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_item_fav1:
                        //通知mainActivity更新界面
                        EventBus.getDefault().post(new MessageEvent(MessageEventType.SHOW_MY_FAV_MUSIC_LIST_FRAGMENT));
                        Toast.makeText(navigationView.getContext(), "我的收藏", Toast.LENGTH_SHORT).show();
//                        drawer.closeDrawers();
                        break;
                    case R.id.menu_item_playinglist1:
                        //通知mainActivity更新界面
//                        EventBus.getDefault().post(new MessageEvent(MessageEventType.SHOW_MY_PLAY_LIST_FRAGMENT));
                        EventBus.getDefault().post(new MessageEvent(MessageEventType.SHOW_LATELY_PLAY_LIST_FRAGMENT));

//                        Toast.makeText(navigationView.getContext(), "播放列表", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_item_listmore1:
                        //通知mainActivity更新界面
//                        EventBus.getDefault().post(new MessageEvent(MessageEventType.SHOW_LATELY_PLAY_LIST_FRAGMENT));
                        EventBus.getDefault().post(new MessageEvent(MessageEventType.SHOW_LATELY_PLAY_LIST_FRAGMENT));

//                        Toast.makeText(navigationView.getContext(), "最近播放", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.exit:
                        //通知mainActivity更新界面
                        EventBus.getDefault().post(new MessageEvent(MessageEventType.EXIT_APP));
                        Toast.makeText(navigationView.getContext(), "退出", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

//        indexMenu = navigationView.getMenu();
//        menu_item_fav1 = indexMenu.findItem(R.id.menu_item_fav1);
//        exit = indexMenu.findItem(R.id.exit);
//        menu_item_playinglist1 = indexMenu.findItem(R.id.menu_item_playinglist1);
//        menu_item_listmore1 = indexMenu.findItem(R.id.menu_item_listmore1);
//        menu_item_fav1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(navigationView.getContext(), "我的收藏", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//        exit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(navigationView.getContext(), "播放列表", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//        menu_item_playinglist1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(navigationView.getContext(), "最近播放", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//        menu_item_listmore1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(navigationView.getContext(), "退出", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//

        return view;
    }


    private Fragment[] initFragment() {
        return new Fragment[]{
                MyMusicFragment.newInstance() ,
                MusicKuFragment.newInstance()};
    }

    /**
     * Toolbar 分文7个部分从左到右有5个部分1.navigationButton 2.logo,3.title、subtitle4.
     *         toolBar.setBackgroundColor(Color.CYAN);
       设置Toolbar title文字颜色
       toolBar.setSubtitleTextColor(getResources().getColor(R.color.white));
       设置 NavigationIcon 点击事件
       设置 Toolbar menu
        toolBar.inflateMenu(R.menu.navigation_drawer_menu_login);
       设置溢出菜单的图标
     * 5.action menu
     * @param view
     */




}
