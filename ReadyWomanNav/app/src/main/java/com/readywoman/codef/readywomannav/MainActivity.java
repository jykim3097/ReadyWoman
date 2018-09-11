package com.readywoman.codef.readywomannav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView contentListView;
    ClassAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //listView
        contentListView = (ListView) findViewById(R.id.content_listView);

        adapter = new ClassAdapter();

        adapter.addItems(new ClassItem("[돌봄]영유아 돌봄이(베이비시터)_12월",
                "2018.12.10 ~ 2018.12.21", "월,화,수,목,금 09:30~13:30",
                "80,000원", "12/17", "중랑여성인력개발센터"));
        adapter.addItems(new ClassItem("[정보화]ITQ엑셀자격증_11월",
                "2018.11.20 ~ 2018.12.13", "화,목 09:30~12:30",
                "80,000원", "12/17", "중랑여성인력개발센터"));
        adapter.addItems(new ClassItem("개정교과에 맞춰 새로워진 초등수학 지도사(저학년)",
                "2018.11.19 ~ 2018.12.24", "월 09:30~12:00",
                "100,000원", "12/17", "서초여성인력개발센터"));
        adapter.addItems(new ClassItem("개정교과에 맞춰 새로워진 초등수학 지도사(고학년)",
                "2018.11.15 ~ 2018.12.20", "목 09:30~12:30",
                "120,000원",  "12/17", "서초여성인력개발센터"));
        adapter.addItems(new ClassItem("초등 영어 지도사 과정(오후)",
                "2018.11.13 ~ 2019.01.15", "화 14:00~17:00",
                "80,000원",  "12/17", "서초여성인력개발센터"));

        contentListView.setAdapter(adapter);
        contentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ClassItem item = (ClassItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : " + item.getcName(), Toast.LENGTH_LONG).show();
            }
        });
    }
    //search icon in ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int itemId = menuItem.getItemId();
        switch (itemId){
            case R.id.action_search:
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_program_list) {
            // Handle the camera action
        } else if (id == R.id.national1) {

        } else if (id == R.id.national2) {

        } else if (id == R.id.national3) {

        } else if (id == R.id.national4) {

        } else if (id == R.id.national5) {

        } else if(id == R.id.national6){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //listView
    class ClassAdapter extends BaseAdapter {
        ArrayList<ClassItem> items = new ArrayList<ClassItem>();

        @Override
        public int getCount(){
            return items.size();
        }

        public void addItems(ClassItem item){
            items.add(item);
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
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ClassItemView view = new ClassItemView(getApplicationContext());
            ClassItem item = items.get(position);
            view.setcName(item.getcName());
            view.setcTerm(item.getcTerm());
            view.setcTime(item.getcTime());
            view.setcPrice(item.getcPrice());
            view.setStatus(item.getStatus());
            view.setCenter(item.getCenter());
            return view;
        }
    }
}
