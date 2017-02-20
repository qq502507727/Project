package come.example.weinan.day56_yitingmusic.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import come.example.weinan.day56_yitingmusic.R;
import come.example.weinan.day56_yitingmusic.utils.MediaUtils;
import come.example.weinan.day56_yitingmusic.vo.Mp3Info;

/**
 * Created by weinan on 2017/2/17.
 */

public class MusicListAdapter extends BaseAdapter {
    private List<Mp3Info>  mp3Infos;
    private Context context;

    public MusicListAdapter(List<Mp3Info> mp3Infos, Context context) {
        this.mp3Infos = mp3Infos;
        this.context = context;
    }

    public void setMp3Infos(ArrayList<Mp3Info> mp3Infos) {
        this.mp3Infos = mp3Infos;
    }
    @Override
    public int getCount() {
        if (mp3Infos==null){
            return 0;
        }else {
            return mp3Infos.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return mp3Infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music_list,null);
            vh = new ViewHolder();
            vh.textView1_title = (TextView) convertView.findViewById(R.id.textView1_title);
            vh.textView2_singer = (TextView) convertView.findViewById(R.id.textView2_singer);
            vh.textView3_time = (TextView) convertView.findViewById(R.id.textView3_time);
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
        Mp3Info mp3Info = mp3Infos.get(position);
        vh.textView1_title.setText(mp3Info.getTitle());
        vh.textView2_singer.setText(mp3Info.getArtist());
        vh.textView3_time.setText(MediaUtils.formatTime(mp3Info.getDuration()));
        return convertView;
    }
    class ViewHolder{
        TextView textView1_title;
        TextView textView2_singer;
        TextView textView3_time;
    }
}
