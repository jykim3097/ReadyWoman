package com.readywoman.codef.readywomannav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements Filterable {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList. (원본 데이터 리스트)
    private ArrayList<ClassItem> listViewItemList = new ArrayList<ClassItem>() ;
    // 필터링된 결과 데이터를 저장하기 위한 ArrayList. 최초에는 전체 리스트 보유.
    private ArrayList<ClassItem> filteredItemList = listViewItemList ;

    Filter listFilter ;
    TextView cName;
    TextView cTerm;
    TextView cTime;
    TextView cPrice;
    TextView status;
    TextView center;

    // ListViewAdapter의 생성자
    public ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return filteredItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.class_item, parent, false);

            cName = (TextView) convertView.findViewById(R.id.cName);
            cTerm = (TextView) convertView.findViewById(R.id.cTerm);
            cTime = (TextView) convertView.findViewById(R.id.cTime);
            cPrice = (TextView) convertView.findViewById(R.id.cPrice);
            status = (TextView) convertView.findViewById(R.id.status);
            center = (TextView) convertView.findViewById(R.id.center);
        }

        // Data Set(filteredItemList)에서 position에 위치한 데이터 참조 획득
        ClassItem listViewItem = filteredItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        cName.setText(listViewItem.getcName());
        cTerm.setText(listViewItem.getcTerm());
        cTime.setText(listViewItem.getcTime());
        cPrice.setText(listViewItem.getcPrice());
        status.setText(listViewItem.getStatus());
        center.setText(listViewItem.getCenter());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String name, String term, String time, String price, String stat, String cent) {
        ClassItem item = new ClassItem();

        item.setcName(name);
        item.setcTerm(term);
        item.setcTime(time);
        item.setcPrice(price);
        item.setStatus(stat);
        item.setCenter(cent);

        listViewItemList.add(item);
    }
    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter() ;
        }

        return listFilter ;
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;

            if (constraint == null || constraint.length() == 0) {
                results.values = listViewItemList ;
                results.count = listViewItemList.size() ;
            } else {
                ArrayList<ClassItem> itemList = new ArrayList<ClassItem>() ;

                for (ClassItem item : listViewItemList) {
                    if(item.getcName().contains(constraint.toString())){
                        itemList.add(item) ;
                    }
                }

                results.values = itemList ;
                results.count = itemList.size() ;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            // update listview by filtered data list.
            filteredItemList = (ArrayList<ClassItem>) results.values ;

            // notify
            if (results.count > 0) {
                notifyDataSetChanged() ;
            } else {
                notifyDataSetInvalidated() ;
            }
        }
    }
}