package com.readywoman.codef.readywomannav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NationalListViewAdapter extends BaseAdapter {
    //Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ClassItem> classItemList = new ArrayList<ClassItem>();

    //NationalListViewAdapter의 생성자
    public NationalListViewAdapter() {

    }

    //Adapter에 사용되는 데이터의 개수를 리턴
    @Override
    public int getCount() {
        return classItemList.size();
    }

    //getView는 adapter가 가지고 있는 data를 어떻게 보여주 것인가를 정의하는 데 쓰임
    //하나의 list item의 모양을 결정하는 역할... classItemView 같은 건강..
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        //class_item.xml을 inflate 하여 convertView(?) 참조 획득
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.class_item, parent, false);
        }

        //화면에 표시될 Layout(class_item)이 inflate된 View(convertView)로부터 위젯(?)에 대한 참조 획득
        TextView cNameView = (TextView) convertView.findViewById(R.id.cName);
        TextView cTermView = (TextView) convertView.findViewById(R.id.cTerm);
        TextView cTimeView = (TextView) convertView.findViewById(R.id.cTime);
        TextView cPriceView = (TextView) convertView.findViewById(R.id.cPrice);
        TextView centerView = (TextView) convertView.findViewById(R.id.center);
        TextView statusView = (TextView) convertView.findViewById(R.id.status);

        //classItemList(Data Set)에서 position에 위치한 데이터 참조 획득
        ClassItem classItem = classItemList.get(position);

        cNameView.setText(classItem.getcName());
        cTermView.setText(classItem.getcTerm());
        cTimeView.setText(classItem.getcTime());
        cPriceView.setText(classItem.getcPrice());
        centerView.setText(classItem.getCenter());
        statusView.setText(classItem.getStatus());

        return convertView;
    }

    //지정한 위치(position)..?에 있는 데이터와 관계된 아이템의 id를 리턴
    @Override
    public long getItemId(int positon){
        return positon;
    }

    //position에 있는 데이터 리턴
    @Override
    public Object getItem(int position){
        return classItemList.get(position);
    }

    //아이템 데이터 추가를 위한 함수
    public void addItem(String cName, String cTerm, String cTime, String cPrice, String status, String center){
        ClassItem item = new ClassItem(cName, cTerm, cTime, cPrice, status, center);

        item.setcName(cName);
        item.setcTerm(cTerm);
        item.setcTime(cTime);
        item.setcPrice(cPrice);
        item.setStatus(status);
        item.setCenter(center);

        classItemList.add(item);
    }

}
