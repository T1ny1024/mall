package com.lixiang.smallmall.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiang.smallmall.Bean.ListDetailsInfo;
import com.lixiang.smallmall.R;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by wd794 on 2016/9/17 0017.
 */
public class GradViewListDetailsAdapter extends BaseAdapter {
    private Context context;
    private List<ListDetailsInfo.DataBean.ItemsBean> items;
    private LayoutInflater layoutInflater;

    public GradViewListDetailsAdapter(Context context, List<ListDetailsInfo.DataBean.ItemsBean> items) {
        this.context = context;
        this.items = items;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.gv_listdetails_item,null);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.iv_listdetails);
            viewHolder.tvTitle= (TextView) convertView.findViewById(R.id.tv_title_listdetails);
            viewHolder.tvNowPrice= (TextView) convertView.findViewById(R.id.tv_nowprice_listdetails);
            viewHolder.tvPrice= (TextView) convertView.findViewById(R.id.tv_price_listdetails);
            viewHolder.tvSale= (TextView) convertView.findViewById(R.id.tv_sales_listdetails);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        Picasso.with(context).load(items.get(position).getComponent().getPicUrl()).into(viewHolder.imageView);
        viewHolder.tvTitle.setText(items.get(position).getComponent().getDescription());
        viewHolder.tvNowPrice.setText(items.get(position).getComponent().getPrice());
        viewHolder.tvPrice.setText(items.get(position).getComponent().getOrigin_price());
        viewHolder.tvSale.setText(items.get(position).getComponent().getSales());
        return convertView;
    }
    class ViewHolder{
        private ImageView imageView;
        private TextView tvTitle;
        private TextView tvNowPrice;
        private TextView tvPrice;
        protected TextView tvSale;
    }
}
