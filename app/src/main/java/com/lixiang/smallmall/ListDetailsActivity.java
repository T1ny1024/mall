package com.lixiang.smallmall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.lixiang.smallmall.Adapter.GradViewAdapter;
import com.lixiang.smallmall.Adapter.GradViewListDetailsAdapter;
import com.lixiang.smallmall.Bean.ListDetailsInfo;
import com.lixiang.smallmall.Tool.GsonTool;
import com.lixiang.smallmall.http.IOkCallBack;
import com.lixiang.smallmall.http.OkHttpTool;

import java.util.List;

public class ListDetailsActivity extends AppCompatActivity {
private String url;
    private GridView gridView;
    private List<ListDetailsInfo.DataBean.ItemsBean> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);
        gridView= (GridView) findViewById(R.id.gv_listdetails);
        url=getIntent().getExtras().getString("url");
        OkHttpTool.newInstance(ListDetailsActivity.this).okGet(url, ListDetailsInfo.class, "string", new IOkCallBack() {


            @Override
            public void onSucess(Object resultInfo) {

            }

            @Override
            public void onStringSuccess(String resultArray) {
                ListDetailsInfo listDetailsInfo = GsonTool.parseJson2Object(resultArray, ListDetailsInfo.class);
                items = listDetailsInfo.getData().getItems();
                GradViewListDetailsAdapter gradViewListDetailsAdapter =new GradViewListDetailsAdapter(ListDetailsActivity.this,items);
                gridView.setAdapter(gradViewListDetailsAdapter);
            }
        },222);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sourceId = items.get(position).getComponent().getAction().getSourceId();
                String url="http://m.hichao.com/lib/interface.php?m=goodsdetail&sid="+sourceId;
                Intent intent =new Intent();
                intent.putExtra("url",url);
                intent.setClass(ListDetailsActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });
    }

}
