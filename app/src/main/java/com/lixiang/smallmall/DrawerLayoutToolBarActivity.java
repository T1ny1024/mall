package com.lixiang.smallmall;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class DrawerLayoutToolBarActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout_tool_bar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayoutToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.open,R.string.close
        );
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();


    }
}
