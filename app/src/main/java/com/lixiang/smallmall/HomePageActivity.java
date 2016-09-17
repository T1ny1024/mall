package com.lixiang.smallmall;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lixiang.smallmall.Adapter.GradViewAdapter;
import com.lixiang.smallmall.Bean.ShouYeInfo;
import com.lixiang.smallmall.Tool.GsonTool;
import com.lixiang.smallmall.Tool.URLtoUTF8;
import com.lixiang.smallmall.http.IOkCallBack;
import com.lixiang.smallmall.http.OkHttpTool;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

public class HomePageActivity extends AppCompatActivity {
    public ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean> arrayList;
    private ListView lvLeftTitle;
    private GridView gridView;
    private String[] title;
    private DrawerLayout drawerLayout;
    private Button btn;
    private Button btnTuiChu;
    public static final String URL="http://api-v2.mall.hichao.com/category/list?ga=%2Fcategory%2Flist";
    private ArrayList<LinkedHashMap<String,ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2>>> gradViewData;
    LinkedHashMap<String, ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2>> stringArrayListLinkedHashMap=
            new  LinkedHashMap<String, ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2>>();
    ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2> itemsBean2s=new ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2>();
    GradViewAdapter gradViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        lvLeftTitle= (ListView) findViewById(R.id.lv_left_title);
        gridView= (GridView) findViewById(R.id.gv_right);
        btn = (Button) findViewById(R.id.btn_shouye);
        btnTuiChu= (Button) findViewById(R.id.btn_tuichu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        this, drawerLayout, R.string.open, R.string.close);
        //设置抽屉监听
        drawerLayout.setDrawerListener(toggle);
        //重要:同步ActionBar上抽屉开关不同状态
        toggle.syncState();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent();
                intent.setClass(HomePageActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(HomePageActivity.this,HomePageActivity.class);
                startActivity(intent);
            }
        });
        OkHttpTool.newInstance(HomePageActivity.this).okGet(URL, ShouYeInfo.class, "string", new IOkCallBack<String>() {
            @Override
            public void onSucess(String resultInfo) {

            }

            @Override
            public void onStringSuccess(String resultArray) {
                //通过Gson直接获取ShouYeInfo对象
                ShouYeInfo shouYeInfo = GsonTool.parseJson2Object(resultArray, ShouYeInfo.class);
                List<ShouYeInfo.DataBean.ItemsBean> items = shouYeInfo.getData().getItems();
                //定义一个数组，用来存放左边title字符
                title=new String[items.size()];
                gradViewData=new ArrayList<LinkedHashMap<String,ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2>>>();
                for(int i=0;i<items.size();i++){
                    title[i]= items.get(i).getComponent().getTitle();
                    ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2> items1 =
                            items.get(i).getComponent().getItems();
                    LinkedHashMap<String,ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2>> map=
                            new LinkedHashMap<String, ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2>>();
                    map.put(title[i],items1);
                    gradViewData.add(map);
                }
                final ArrayAdapter arrayAdapter=new ArrayAdapter(HomePageActivity.this,R.layout.listview_item,title);
                lvLeftTitle.setAdapter(arrayAdapter);
                stringArrayListLinkedHashMap.putAll(gradViewData.get(0));
               itemsBean2s.addAll( stringArrayListLinkedHashMap.get(title[0]));
               gradViewAdapter=new GradViewAdapter(HomePageActivity.this,itemsBean2s);
                gridView.setAdapter(gradViewAdapter);
            }
        },100);
        lvLeftTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stringArrayListLinkedHashMap.clear();
                itemsBean2s.clear();
                stringArrayListLinkedHashMap.putAll(gradViewData.get(position));
                itemsBean2s.addAll( stringArrayListLinkedHashMap.get(title[position]));
                Log.i("wangdong", "onItemClick: "+itemsBean2s);
                gradViewAdapter.notifyDataSetChanged();

            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String word = itemsBean2s.get(position).getComponent().getWord();
                String s = URLtoUTF8.toUtf8String(word);
                String url="http://api-v2.mall.hichao.com/search/skus?query="+s+"%20%20" +
                        "&sort=all&ga=%252Fsearch%252Fskus&flag=&cat=&asc=1";
                Intent intent=new Intent();
                intent.putExtra("url",url);
                intent.setClass(HomePageActivity.this,ListDetailsActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //左边的抽屉是否打开
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    //打开状态,要关闭
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
//            case R.id.iv_share:
//                Toast.makeText(HomePageActivity.this, "aaaaaaaaa", Toast.LENGTH_SHORT).show();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
