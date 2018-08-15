package org.techtown.jsoup_rw;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.lang.String;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //파싱할 홈페이지 주소
    String pageURL = "https://www.seoulwomanup.or.kr/edu/allinfo/womEduAllInfoList.do?reqSystemId=dbc&programId=womEduAllInfo&processId=wom&requestIP=203.237.172.100&systemId=wom&page=";
    private TextView txtArea;
    Button btn;
    private String htmlContentInStringFormat="";

    int cnt =0;

    Program[] arr = new Program[1000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtArea = (TextView)findViewById(R.id.txt1);
        txtArea.setMovementMethod(new ScrollingMovementMethod());  //스크롤 가능한 텍스트뷰
        System.out.println((cnt+1)+"번째 파싱");
        //크롤링 생성 및 시작
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
        cnt++;
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                int j=1;
                while(j<=2) {
                    Document doc = Jsoup.connect(pageURL + j).get();
                    System.out.println("\n"+j+"페이지\n");

                    for (int i = 1; i <= 20; i++) {
                        Elements titles = doc.select("#content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(" + i + ") > td:nth-child(1)");
                        String proCenter = titles.text().trim();
                        titles = doc.select("#content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(" + i + ") > td.subject");
                        String proName = titles.text().trim();
                        titles = doc.select("#content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(" + i + ") > td:nth-child(7)");
                        String proTerm = titles.text().trim();
                        titles = doc.select("#content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(" + i + ") > td:nth-child(8)");
                        String proDate = titles.text().trim();
                        titles = doc.select("#content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(" + i + ") > td:nth-child(10)");
                        String proPrice = titles.text().trim();
                        titles = doc.select("#content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(" + i + ") > td:nth-child(11)");
                        String proState = titles.text().trim();
                        titles = doc.select("#content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(" + i + ") > td.subject > a");
                        String proURL = titles.attr("href");

                        Program p = new Program(proCenter, proName, proTerm, proDate, proPrice, proState, proURL);
                        arr[i] = p;
                        System.out.println("--------------------------------------");
                        System.out.println(j+"페이지"+i+"번" + "센터: " + arr[i].getCenter());
                        System.out.println("강좌명: " + arr[i].getcName());
                        System.out.println("교육기간: " + arr[i].getcTerm());
                        System.out.println("시간/요일: " + arr[i].getcTime());
                        System.out.println("수강료: " + arr[i].getcPrice());
                        System.out.println("강좌상태: " + arr[i].getcState());
                        System.out.println("홈페이지 주소: "+arr[i].getcURL());
                        htmlContentInStringFormat += j+"페이지\n"+arr[i].getCenter() + "\n" + arr[i].getcName() + "\n" + arr[i].getcTerm() + "\n"
                                + arr[i].getcTime() + "\n" + arr[i].getcPrice() + "\n" + arr[i].getcState()+"\n"+arr[i].getcURL()+"\n\n";
                        System.out.println(i+"번째 강좌명의 "+arr[i].getcName()+"의 URL은 "+arr[i].getcURL());
                    }
                    htmlContentInStringFormat += "\n"+(j+1)+"페이지로 이동\n";
                    System.out.println("\n"+(j+1)+"페이지로 이동");
                    j++;
                }
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            txtArea.setText(htmlContentInStringFormat);
        }
    }
}
