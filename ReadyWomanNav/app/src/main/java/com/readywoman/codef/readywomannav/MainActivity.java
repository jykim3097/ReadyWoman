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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView contentListView;
    ClassAdapter adapter;
    String[] centers={"전체","중부여성발전센터", "북부여성발전센터", "남부여성발전센터", "서부여성발전센터", "동부여성발전센터", "동작여성인력개발센터", "송파여성인력개발센터",
            "서초여성인력개발센터", "용산여성인력개발센터", "중랑여성인력개발센터", "성동여성인력개발센터", "장애여성인력개발센터", "강동여성인력개발센터", "서울시여성능력개발원"};
    String[] statuses = {"전체","신청","마감", "준비", "대기", "방문"};
    String centerChoice;
    String statusChoice;
    EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //app_bar_main의 toolbar
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
                "120,000원", "12/17", "서초여성인력개발센터"));
        adapter.addItems(new ClassItem("초등 영어 지도사 과정(오후)",
                "2018.11.13 ~ 2019.01.15", "화 14:00~17:00",
                "80,000원", "12/17", "서초여성인력개발센터"));

        contentListView.setAdapter(adapter);
        contentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ClassItem item = (ClassItem) adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                Toast.makeText(getApplicationContext(), "선택 : " + item.getcName() + position, Toast.LENGTH_LONG).show();
            }
        });
    }

  //search icon in ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

        if (id == R.id.national1) {
            Intent intent = new Intent(getApplicationContext(), NationalTomorrowActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } else if (id == R.id.national2) {
            Intent intent = new Intent(getApplicationContext(), NationalEmployeecardActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } else if (id == R.id.national3) {
            Intent intent = new Intent(getApplicationContext(), NationalSeoulsupportActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } else if (id == R.id.national4) {
            Intent intent = new Intent(getApplicationContext(), NationalMogefsupportActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } else if (id == R.id.national5) {
            Intent intent = new Intent(getApplicationContext(), NationalEtcsupportActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } else if (id == R.id.national6) {
            Intent intent = new Intent(getApplicationContext(), NationalCollectiveconsultationActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //listView
    class ClassAdapter extends BaseAdapter {
        ArrayList<ClassItem> items = new ArrayList<ClassItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItems(ClassItem item) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}



