package com.lixiang.smallmall.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiang.smallmall.Bean.ShouYeInfo;
import com.lixiang.smallmall.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by wd794 on 2016/9/16 0016.
 */
public class GradViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2> arrayList;
    private LayoutInflater layoutInflater;

    public GradViewAdapter(Context context, ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.layoutInflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView =null;
        if(convertView==null){
            holdView=new HoldView();
            convertView=layoutInflater.inflate(R.layout.gv_item,null);
            holdView.imageView= (ImageView) convertView.findViewById(R.id.iv_gv);
            holdView.textView= (TextView) convertView.findViewById(R.id.tv_gv);
            convertView.setTag(holdView);
        }else {
            holdView= (HoldView) convertView.getTag();
        }
        holdView.textView.setText(arrayList.get(position).getComponent().getWord());
        Picasso.with(context).load(arrayList.get(position).getComponent().getPicUrl())
                .placeholder(R.mipmap.dota)
                .into(holdView.imageView);
        return convertView;
    }
    class HoldView{
        ImageView imageView;
        TextView textView;
    }
}
