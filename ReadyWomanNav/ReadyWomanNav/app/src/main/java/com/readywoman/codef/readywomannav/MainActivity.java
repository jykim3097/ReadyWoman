package com.readywoman.codef.readywomannav;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

import com.readywoman.codef.readywomannav.db.DBOpenHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView contentListView;
    ClassAdapter adapter;

    //DB관련
    private DBOpenHelper mDBOpenHelper;
    private Cursor mCursor;
    private InfoClass mInfoClass;
    private ArrayList<InfoClass> mInfoArr;

    //크롤링할 웹페이지
    String pageURL1 = "https://www.seoulwomanup.or.kr/womanup/edu/selectProgramPageListAll.do?currentPage=";
    String pageURL2 = "&organPgName=&schOrganCode=&schCourseCode=&schDomainCode=&schGroupCode=&schEduSt=&schDayOfWeek=&schEduFee=&schLecturerName=&schClassName=";
    ClassItem[] arrClass = new ClassItem[1000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //데이터베이스 생성(파라메터 Context) 및 오픈
        mDBOpenHelper = new DBOpenHelper(this);
        try {
            mDBOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //크롤링 생성 및 시작
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();

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
                "80,000원", "정원 : 12/17", "중랑여성인력개발센터"));
        adapter.addItems(new ClassItem("[정보화]ITQ엑셀자격증_11월",
                "2018.11.20 ~ 2018.12.13", "화,목 09:30~12:30",
                "80,000원", "정원 : 12/17", "중랑여성인력개발센터"));
        adapter.addItems(new ClassItem("개정교과에 맞춰 새로워진 초등수학 지도사(저학년)",
                "2018.11.19 ~ 2018.12.24", "월 09:30~12:00",
                "100,000원", "정원 : 12/17", "서초여성인력개발센터"));
        adapter.addItems(new ClassItem("개정교과에 맞춰 새로워진 초등수학 지도사(고학년)",
                "2018.11.15 ~ 2018.12.20", "목 09:30~12:30",
                "120,000원", "정원 : 12/17", "서초여성인력개발센터"));
        adapter.addItems(new ClassItem("초등 영어 지도사 과정(오후)",
                "2018.11.13 ~ 2019.01.15", "화 14:00~17:00",
                "80,000원", "정원 : 12/17", "서초여성인력개발센터"));

        contentListView.setAdapter(adapter);
        contentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ClassItem item = (ClassItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : " + item.getcName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                int j = 1;
                while (j <= 2) {
                    Document doc = Jsoup.connect(pageURL1 + j + pageURL2).get();
                    System.out.println("\n" + j + "페이지\n");

                    for (int i = 1; i <= 9; i++) {
                        Elements titles = doc.select("#sub > section > section > div > ul > li:nth-child(" + i + ") > div > dl > dt");
                        String proName = titles.text().trim();

                        if (proName.contains("관계없음"))
                            proName = proName.substring(0, proName.length() - 4);
                        else proName = proName.substring(0, proName.length() - 2);

                        titles = doc.select("#sub > section > section > div > ul > li:nth-child(" + i + ") > div > dl > dd:nth-child(6) > em");
                        String proTerm = titles.text().trim();

                        titles = doc.select("#sub > section > section > div > ul > li:nth-child(" + i + ") > div > dl > dd:nth-child(7) > em");
                        String proTime = titles.text().trim();

                        titles = doc.select("#sub > section > section > div > ul > li:nth-child(" + i + ") > div > dl > dd.line_bottom > em");
                        String proPrice = titles.text().trim();

                        titles = doc.select("#sub > section > section > div > ul > li:nth-child(" + i + ") > div > dl > dd:nth-child(4) > em");
                        String proStatus = titles.text().trim();

                        titles = doc.select("#sub > section > section > div > ul > li:nth-child(" + i + ") > div > h2");
                        String proCenter = titles.text().trim();

                        ClassItem p = new ClassItem(proName, proTerm, proTime, proPrice, proStatus, proCenter);
                        arrClass[i] = p;
                        //saveInput(arr[i].getcName(), arr[i].getcTerm(), arr[i].getcTime(), arr[i].getcPrice(), arr[i].getCenter(), arr[i].getcState());
                        System.out.println("--------------------------------------");
                        System.out.println(j + "페이지");
                        System.out.println(+i + "번" + "강좌명: " + arrClass[i].getcName());
                        System.out.println("교육기간: " + arrClass[i].getcTerm());
                        System.out.println("시간/요일: " + arrClass[i].getcTime());
                        System.out.println("수강료: " + arrClass[i].getcPrice());
                        System.out.println("강좌상태: " + arrClass[i].getStatus());
                        System.out.println("센터: " + arrClass[i].getCenter());
//                        htmlContentInStringFormat += j+"페이지\n"+arrClass[i].getcName() +"\n" + arrClass[i].getcTerm()+"  "+ arrClass[i].getcTime() +"\n"
//                                + arrClass[i].getcPrice() + "원"+"  "+"정원: "+arrClass[i].getcStatus()+"\n" + arrClass[i].getCenter()+"\n\n";
                        mDBOpenHelper.insertColumn(arrClass[i].cName, arrClass[i].cTerm, arrClass[i].cTime, arrClass[i].cPrice, arrClass[i].status, arrClass[i].center);
                        /*
                        doWhileCursorToArray();

                        //값이 제대로 입력되었는지 확인하기 위해 로그
                        for (InfoClass ic : mInfoArr) {
                            Log.i("값이 제대로 입력됐는지 테스트", "id = " + ic._id);
                            Log.i("값이 제대로 입력됐는지 테스트", "name = " + ic.name);
                            Log.i("값이 제대로 입력됐는지 테스트", "term = " + ic.term);
                            Log.i("값이 제대로 입력됐는지 테스트", "time = " + ic.time);
                            Log.i("값이 제대로 입력됐는지 테스트", "price = " + ic.price);
                            Log.i("값이 제대로 입력됐는지 테스트", "status = " + ic.status);
                            Log.i("값이 제대로 입력됐는지 테스트", "center = " + ic.center);
                        }
                        */
                    }
//                    htmlContentInStringFormat += "\n"+(j+1)+"페이지로 이동\n";
                    System.out.println("\n" + (j + 1) + "페이지로 이동");
                    j++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //txtArea.setText(htmlContentInStringFormat);

            mInfoArr = new ArrayList<InfoClass>();

            doWhileCursorToArray();

            //값이 제대로 입력되었는지 확인하기 위해 로그
            for (InfoClass ic : mInfoArr) {
                Log.i("값이 제대로 입력됐는지 테스트", "id = " + ic._id);
                Log.i("값이 제대로 입력됐는지 테스트", "name = " + ic.name);
                Log.i("값이 제대로 입력됐는지 테스트", "term = " + ic.term);
                Log.i("값이 제대로 입력됐는지 테스트", "time = " + ic.time);
                Log.i("값이 제대로 입력됐는지 테스트", "price = " + ic.price);
                Log.i("값이 제대로 입력됐는지 테스트", "status = " + ic.status);
                Log.i("값이 제대로 입력됐는지 테스트", "center = " + ic.center);
            }
        }
    }

    //search icon in ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (itemId) {
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

        } else if (id == R.id.national6) {

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

    private void doWhileCursorToArray() {
        mCursor = null;
        //db에 있는 모든 칼럼을 가져옴
        mCursor = mDBOpenHelper.getAllColumns();
        //컬럼의 갯수 확인
        Log.i("Column 개수 확인 ", "Count = " + mCursor.getCount());

        while (mCursor.moveToNext()) {
            //InfoClass에 입력된 값을 압력
            mInfoClass = new InfoClass(
                    mCursor.getInt(mCursor.getColumnIndex("_id")),
                    mCursor.getString(mCursor.getColumnIndex("className")),
                    mCursor.getString(mCursor.getColumnIndex("classTerm")),
                    mCursor.getString(mCursor.getColumnIndex("classTime")),
                    mCursor.getString(mCursor.getColumnIndex("classPrice")),
                    mCursor.getString(mCursor.getColumnIndex("classStatus")),
                    mCursor.getString(mCursor.getColumnIndex("classCenter"))
            );
            //입력된 값을 가지고 있는 InfoClass를 InfoArray에 add
            mInfoArr.add(mInfoClass);
        }
        //Cursor 닫기
        mCursor.close();
    }

    @Override
    protected void onDestroy() {
        mDBOpenHelper.close();
        super.onDestroy();
    }
}