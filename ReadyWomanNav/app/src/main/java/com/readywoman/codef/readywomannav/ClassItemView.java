package com.readywoman.codef.readywomannav;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ClassItemView extends LinearLayout {
    TextView cName;
    TextView cTerm;
    TextView cTime;
    TextView cPrice;
    TextView center;
    ImageView status;

    public ClassItemView(Context context){
        super(context);
        init(context);
    }
    public ClassItemView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.class_item, this, true);
        cName = (TextView) findViewById(R.id.cName);
        cTerm = (TextView) findViewById(R.id.cTerm);
        cTime = (TextView) findViewById(R.id.cTime);
        cPrice = (TextView) findViewById(R.id.cPrice);
        center = (TextView) findViewById(R.id.center);
        status = (ImageView) findViewById(R.id.status);
    }
    public void setcName(String name){
        cName.setText(name);
    }
    public void setcTerm(String period){
        cTerm.setText(period);
    }
    public void setcTime(String dTime){
        cTime.setText(dTime);
    }
    public void setcPrice(int tuit){
        cPrice.setText(String.valueOf(tuit));
    }
    public void setCenter(String cent){
        center.setText(cent);
    }
    public void setStatus(int resId){
        status.setImageResource(resId);
    }
}
