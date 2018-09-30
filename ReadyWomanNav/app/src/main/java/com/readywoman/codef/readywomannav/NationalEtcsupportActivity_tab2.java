package com.readywoman.codef.readywomannav;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class NationalEtcsupportActivity_tab2 extends ListFragment {

    NationalListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Adapter 생성 및 Adapter 지정
        adapter = new NationalListViewAdapter();
        setListAdapter(adapter);

        //첫번째 아이템 추가
        adapter.addItem("[돌봄]영유아 돌봄이(베이비시터)_12월",
                "2018.12.10 ~ 2018.12.21", "월,화,수,목,금 09:30~13:30",
                "80,000원", "12/17", "중랑여성인력개발센터");

        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onListItemClick (ListView l, View v, int position, long id){
        //get TextView's Text
        ClassItem item = (ClassItem) l.getItemAtPosition(position);

        String cNameStr = item.getcName();
        String cTermStr = item.getcTerm();
        String cTimeStr = item.getcTime();
        String cPriceStr = item.getcPrice();
        String statusStr = item.getStatus();
        String centerStr = item.getCenter();
    }

    public void addItem(String cName, String cTerm, String cTime, String cPrice, String status, String center){
        adapter.addItem(cName, cTerm, cTime, cPrice, status, center);
    }
}