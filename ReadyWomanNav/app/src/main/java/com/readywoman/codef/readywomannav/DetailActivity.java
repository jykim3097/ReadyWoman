package com.readywoman.codef.readywomannav;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    private String pageURL = "https://www.seoulwomanup.or.kr/womanup/edu/selectProgramPageListAll.do";
    private String detailsURL= "";

    private String cName="";

    private TextView center;
    private TextView cTerm;
    private TextView cTime;
    private TextView cPrice;
    private TextView cATerm;
    private TextView cRMethod;

    private TextView cQual;
    private TextView cGoal;
    private TextView cOverview;
    private TextView cCost;

    private String linkString = "";
    private String htmlString=""; //초기화를 해주지 않으면 null이 반환됨
    private String temp="";

    private String[] htmlStringList = new String[7];
    private String[] htmlStringList2 = new String[5];
    private String htmlStringRemover ="";

    private String[] parts = new String[3];
    private String part1; //http ~ jsp
    private String part2; //C~
    private String part3; //center name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //상단
        center = (TextView) findViewById(R.id.center);
        cTerm = (TextView) findViewById(R.id.cTerm);
        cTime = (TextView) findViewById(R.id.cTime);
        cPrice = (TextView) findViewById(R.id.cPrice);
        cATerm = (TextView) findViewById(R.id.cATerm);
        cRMethod = (TextView) findViewById(R.id.cRMethod);

        //하단
        cQual = (TextView) findViewById(R.id.cQual);
        cGoal = (TextView) findViewById(R.id.cGoal);
        cOverview = (TextView) findViewById(R.id.cOverview);
        cCost = (TextView) findViewById(R.id.cCost);

        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                //crawling course name
                Document doc = Jsoup.connect(pageURL).get();
                Elements name = doc.select("#sub > section > section > div > ul > li:nth-child(1) > div > dl > dt");

                for(Element n:name) {
                    cName += n.text().trim();
                }

                if(cName.contains("관계없음")){
                    cName = cName.substring(0, cName.length()-4);
                }
                else{
                    cName = cName.substring(0, cName.length()-2);
                }
                //crawling a tag
                Elements links = doc.select("#sub > section > section > div > ul > li:nth-child(1) > div > a[onclick]");
                for(Element link : links ) {
                    linkString += link.attr("onclick");
                }
                linkString = linkString.replace("application('", "");
                parts = linkString.split("','");

                part1 = parts[0]; //http ~ jsp
                part2 = parts[1]; //C~
                part3 = parts[2]; //center name

                detailsURL = part1 + "?class_code=" + part2;

                /*crawling details window*/
                Document doc2 = Jsoup.connect(detailsURL).get();


                for(int i = 2; i<7; i++){
                    Elements term = doc2.select("#inConts > div.boxmodel1 > div > div > div > div.edu-contents > ul > li:nth-child("+ i +")");
                    if(i==2) {
                        for(Element t : term) {
                            htmlString += t.text().trim();
                        }
                        htmlStringRemover = htmlString.replace("교육기간 : ","");
                        htmlStringList[i] = htmlStringRemover;
                        htmlString = temp;
                        htmlStringRemover = temp;
                    }
                    else if (i==3) {
                        for(Element t : term) {
                            htmlString += t.text().trim();
                        }
                        htmlStringRemover = htmlString.replace("교육시간 : ","");
                        htmlStringList[i] = htmlStringRemover;
                        htmlString = temp;
                        htmlStringRemover = temp;
                    }
                    else if (i==4) {
                        for(Element t : term) {
                            htmlString += t.text().trim();
                        }
                        htmlStringRemover = htmlString.replace("수강료 : ","");
                        htmlStringList[i] = htmlStringRemover;
                        htmlString = temp;
                        htmlStringRemover = temp;
                    }
                    else if (i==5) {
                        for(Element t : term) {
                            htmlString += t.text().trim();
                        }
                        htmlStringRemover = htmlString.replace("수강신청기간 : ","");
                        htmlStringList[i] = htmlStringRemover;
                        htmlString = temp;
                        htmlStringRemover = temp;
                    }
                    else if (i==6) {
                        for(Element t : term) {
                            htmlString += t.text().trim();
                        }
                        htmlStringRemover = htmlString.replace("접수방법 : ","");
                        htmlStringList[i] = htmlStringRemover;
                        htmlString = temp;
                        htmlStringRemover = temp;
                    }
                }

                for(int j=1;j<5;j++){
                    Elements elements = doc2.select("#inConts > div.edu-wrap > div.edu-detailbox > div:nth-child("+2*j+")");
                    for(Element e:elements) {
                        htmlString += e.text().trim();
                    }
                    htmlStringList2[j] = htmlString;
                    htmlString = temp;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            setTitle(cName);
            center.setText(part3);
            cTerm.setText(htmlStringList[2]);
            cTime.setText(htmlStringList[3]);
            cPrice.setText(htmlStringList[4]);
            cATerm.setText(htmlStringList[5]);
            cRMethod.setText(htmlStringList[6]);

            cQual.setText(htmlStringList2[1]);
            cGoal.setText(htmlStringList2[2]);
            cOverview.setText(htmlStringList2[3]);
            cCost.setText(htmlStringList2[4]);
        }
    }

}
