package me.yeojoy.testapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.yeojoy.testapp.R;
import me.yeojoy.testapp.dto.Dust;

/**
 * Created by yeojoy on 15. 1. 2..
 */
public class MyAdapter extends BaseAdapter {
    private static final String TAG = MyAdapter.class.getSimpleName();

    private List<Dust> mItems;
    
    private Context mContext;

    public MyAdapter(Context context) {
        mItems = new ArrayList<Dust>();
        mContext = context;
    }

    public MyAdapter(Context context, List<Dust> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Dust getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return new Long(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_layout, null);

            holder = new ViewHolder();
            holder.locality = (TextView) convertView.findViewById(R.id.tv_locality);
            holder.pm10 = (TextView) convertView.findViewById(R.id.tv_pm10);
            holder.index = (TextView) convertView.findViewById(R.id.tv_maxindex);
            holder.box = (CheckBox) convertView.findViewById(R.id.cb);

            convertView.setTag(holder);
        }

        // 나의 잘못을 알게 해준 방법..
        Dust d = getItem(position);
        
        
        holder.locality.setText(d.getLocaltity());
        holder.pm10.setText(d.getPm10());
        holder.index.setText(d.getMaxIndex());
        holder.box.setChecked(d.isChecked());
        
        holder.box.setTag(d);
        
        return convertView;
    }

    public void setItems(List<Dust> items) {
        Log.i(TAG, "setItems()");
        this.mItems = items;
        notifyDataSetChanged();
    }

    public void addItems(List<Dust> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(Dust item) {
        mItems.add(item);
        notifyDataSetChanged();
    }
    
    public void setChecked(int position) {
        mItems.get(position).toggleChecked();
        
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public TextView locality;
        public TextView pm10;
        public TextView index;
        public CheckBox box;
    }
}
