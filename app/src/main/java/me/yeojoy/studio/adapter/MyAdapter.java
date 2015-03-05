package me.yeojoy.studio.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.yeojoy.studio.R;
import me.yeojoy.studio.dto.Dust;

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

        return convertView;
    }

    public void setItems(List<Dust> items) {
        Log.i(TAG, "setItems()");
        mItems.clear();

        this.mItems = items;
        notifyDataSetChanged();
    }

    public void addItems(List<Dust> items) {
        Log.i(TAG, "addItems()");
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(Dust item) {
        Log.i(TAG, "addItem()");
        mItems.add(item);
        notifyDataSetChanged();
    }

    /**
     * 선택한 data isChecked 값을 바꾸고 refresh 한다.
     * @param position
     */
    public void setChecked(int position) {
        Log.i(TAG, "setChecked()");
        // Checkbox 류의 on/off는 data에 toggle을 구현하면 좀 더 쉽다.
        mItems.get(position).toggleChecked();

        notifyDataSetChanged();
    }

    /**
     * 전체 선택 or 전체 선택 해제*
     * @param isAllChecked
     */
    public void setAllChecked(boolean isAllChecked) {
        Log.i(TAG, "setAllChecked()");
        for (Dust d : mItems)
            d.setChecked(isAllChecked);
        
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public TextView locality;
        public TextView pm10;
        public TextView index;
        public CheckBox box;
    }
}
