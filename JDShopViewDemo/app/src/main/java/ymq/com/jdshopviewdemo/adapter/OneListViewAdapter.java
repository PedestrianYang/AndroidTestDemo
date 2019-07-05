package ymq.com.jdshopviewdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ymq.com.jdshopviewdemo.R;

/**
 * Created by iyunshu on 2019/7/4.
 */

public class OneListViewAdapter extends BaseAdapter {
    ArrayList<String> dataArray;
    Context mContext;

    public OneListViewAdapter(Context context, ArrayList<String> dataArray) {
        mContext = context;
        this.dataArray = dataArray;
    }

    @Override
    public int getCount() {
        if (dataArray == null)
            return 0;
        return dataArray.size();
    }

    @Override
    public Object getItem(int position) {
        if (dataArray == null)
            return position;
        return dataArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_one_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        String str = dataArray.get(position);
        holder.itemContent.setText(str);
        holder.itemContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("item", String.valueOf(position));
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_content)
        TextView itemContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
