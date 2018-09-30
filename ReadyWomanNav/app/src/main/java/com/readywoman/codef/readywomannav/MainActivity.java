package com.readywoman.codef.readywomannav;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listViewAdapter
        ListViewAdapter adapter;

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
        adapter = new ListViewAdapter();
        contentListView = (ListView) findViewById(R.id.content_listView);
        contentListView.setAdapter(adapter);

        adapter.addItem("[돌봄]영유아 돌봄이(베이비시터)_12월",
                "2018.12.10 ~ 2018.12.21", "월,화,수,목,금 09:30~13:30",
                "80,000원", "12/17", "중랑여성인력개발센터");
        adapter.addItem("[정보화]ITQ엑셀자격증_11월",
                "2018.11.20 ~ 2018.12.13", "화,목 09:30~12:30",
                "80,000원", "12/17", "중랑여성인력개발센터");
        adapter.addItem("개정교과에 맞춰 새로워진 초등수학 지도사(저학년)",
                "2018.11.19 ~ 2018.12.24", "월 09:30~12:00",
                "100,000원", "12/17", "서초여성인력개발센터");
        adapter.addItem("개정교과에 맞춰 새로워진 초등수학 지도사(고학년)",
                "2018.11.15 ~ 2018.12.20", "목 09:30~12:30",
                "120,000원", "12/17", "서초여성인력개발센터");
        adapter.addItem("초등 영어 지도사 과정(오후)",
                "2018.11.13 ~ 2019.01.15", "화 14:00~17:00",
                "80,000원", "12/17", "서초여성인력개발센터");

        editSearch = findViewById(R.id.sEditText);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                ((ListViewAdapter)contentListView.getAdapter()).getFilter().filter(filterText) ;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        }) ;

        contentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                ClassItem item = (ClassItem) adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
//                Toast.makeText(getApplicationContext(), "선택 : " + item.getcName() + position, Toast.LENGTH_LONG).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}



