package com.readywoman.codef.readywomannav;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class NationalSeoulsupportActivity extends AppCompatActivity {

    NationalSeoulsupportActivity_tab1 fragment1;
    NationalSeoulsupportActivity_tab2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar);

        //1. 툴바 설정
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //2. xml 프래그먼트를 보여주기
        fragment1 = new NationalSeoulsupportActivity_tab1();
        fragment2 = new NationalSeoulsupportActivity_tab2();

        //프레그먼트를 메니져로 보여줌
        getSupportFragmentManager().beginTransaction().add(R.id.container,fragment1).commit();

        //3. 탭기능 구성
        TabLayout tabs=(TabLayout)findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("서울시 지원 사업"));
        tabs.addTab(tabs.newTab().setText("검색"));


        //탭버튼을 클릭했을 때 프레그먼트 동작
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //선택된 탭 번호 반환
                int position =tab.getPosition();

                Fragment selected = null;

                if(position == 0 ){

                    selected = fragment1;

                }else if(position == 1 ){

                    selected =fragment2;

                }

                //선택된 프레그먼트로 바꿔줌
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
