package com.readywoman.codef.readywomannav;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    String[] centers={"전체","중부여성발전센터", "북부여성발전센터", "남부여성발전센터", "서부여성발전센터", "동부여성발전센터", "동작여성인력개발센터", "송파여성인력개발센터",
            "서초여성인력개발센터", "용산여성인력개발센터", "중랑여성인력개발센터", "성동여성인력개발센터", "장애여성인력개발센터", "강동여성인력개발센터", "서울시여성능력개발원"};
    String[] certifications={"전체","자격", "없음"};
    String[] statuses = {"전체","신청","마감", "준비", "대기", "방문"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_dropdown_item, centers);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_dropdown_item, certifications);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_dropdown_item, statuses);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
//        spinner1.setSelection(adapter1.getPosition(DEFAULT_CURRENCY_TYPE));

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), "선택 : " + centers[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "아무것도 선택 안됨", Toast.LENGTH_LONG).show();

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), "선택 : " + certifications[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "아무것도 선택 안됨", Toast.LENGTH_LONG).show();

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), "선택 : " + statuses[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "아무것도 선택 안됨", Toast.LENGTH_LONG).show();

            }
        });

    }
}
