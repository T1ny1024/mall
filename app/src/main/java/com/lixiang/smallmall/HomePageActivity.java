package com.lixiang.smallmall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import com.lixiang.smallmall.Bean.ShouYeInfo;
import com.lixiang.smallmall.http.IOkCallBack;
import com.lixiang.smallmall.http.OkHttpTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    public ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean> arrayList;
    public static final String URL="http://api-v2.mall.hichao.com/category/list?ga=%2Fcategory%2Flist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        OkHttpTool.newInstance(HomePageActivity.this).okGet(URL, ShouYeInfo.class, "string", new IOkCallBack<String>() {
            @Override
            public void onSucess(String resultInfo) {

            }

            @Override
            public void onStringSuccess(String resultArray) {
//                try {
//                    JSONObject jsonObject = new JSONObject(resultArray);
//                    JSONObject data = jsonObject.getJSONObject("data");
//                    JSONArray items = data.getJSONArray("items");
//                    arrayList=new ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean>();
//                    ShouYeInfo.DataBean.ItemsBean.ComponentBean componentBean=null;
//                    for (int i=0;i<items.length();i++){
//                        JSONObject jsonObject2 = items.getJSONObject(i);
//                        JSONObject component = jsonObject2.getJSONObject("component");
//                        String title1 = component.getString("title");
//                        JSONArray items1 = component.getJSONArray("items");
//                        ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2> itemsBean2s
//                                = new ArrayList<ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2>();
//                        ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2 itemsBean21=null;
//                        componentBean= new ShouYeInfo.DataBean.ItemsBean.ComponentBean();
//                        for (int j=0;j<items1.length();j++){
//                            ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2 itemsBean2 =
//                                    new ShouYeInfo.DataBean.ItemsBean.ComponentBean.ItemsBean2();
//                            JSONObject jsonObject1 = items1.getJSONObject(j);
//                            JSONObject component1 = jsonObject1.getJSONObject("component");
//                            String word = component1.getString("word");
//                            String picUrl = component1.getString("picUrl");
//                            itemsBean2s.add(itemsBean2);
//                        }
//                        componentBean.setTitle(title1);
//                        componentBean.setItems(itemsBean2s);
//                        arrayList.add(componentBean);
//                    }
//                    Log.i("wangdong", "onStringSuccess: "+arrayList.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//

            }
        },100);

    }
}
