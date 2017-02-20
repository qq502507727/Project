package come.example.weinan.day56_yitingmusic.adapter;

/**
 * Created by weinan on 2017/2/15.
 */

import android.util.SparseArray;
import android.view.View;

/**
 * ViewHolder 为了防止每次都findById 万能的ViewHolder
 */
public class ViewHolder {

public static <T extends View> T getView(View view, int id, View.OnClickListener listener){
    SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
    if(viewHolder==null){
        viewHolder = new SparseArray<>();
        view.setTag(viewHolder);
    }
    View v = viewHolder.get(id);
    if(v==null){
        v = view.findViewById(id);
        if(listener!=null) v.setOnClickListener(listener);
        viewHolder.put(id,v);
    }
    return (T) v;
}
}
