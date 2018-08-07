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
    private String pageURL = "https://www.seoulwomanup.or.kr/edu/allinfo/womEduAllInfoList.do";
    private TextView txtArea;
    Button btn;
    private String htmlContentInStringFormat="";

    int cnt =0;
    int i=0;

    public class Program{
        private String pCenter;
        private String pName;
        private String pTerm;
        private String pDate;
        private String pPrice;
        private String pState;

        public Program(String pCenter, String pName, String pTerm, String pDate, String pPrice, String pState) {
            this.pCenter = pCenter;
            this.pName = pName;
            this.pTerm = pTerm;
            this.pDate = pDate;
            this.pPrice = pPrice;
            this.pState = pState;
        }

        public String getpCenter() {
            return pCenter;
        }

        public String getpName() {
            return pName;
        }

        public String getpTerm() {
            return pTerm;
        }

        public String getpDate() {
            return pDate;
        }

        public String getpPrice() {
            return pPrice;
        }

        public String getpState() {
            return pState;
        }
    }
    Program[] arr = new Program[400];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtArea = (TextView)findViewById(R.id.txt1);
        //스크롤 가능한 텍스트뷰
        txtArea.setMovementMethod(new ScrollingMovementMethod());

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println((cnt+1)+"번째 파싱");
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                jsoupAsyncTask.execute();
                cnt++;
            }
        });
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Document doc = Jsoup.connect(pageURL).get();
                /*
                ***서울우먼업>>전체프로그램>>thead***
                담당센터 "#content > div.page_content > div.bbs_skin > table > thead > tr > th:nth-child(1)"
                강좌명 "#content > div.page_content > div.bbs_skin > table > thead > tr > th:nth-child(5)"
                교육기간 "#content > div.page_content > div.bbs_skin > table > thead > tr > th:nth-child(7)"
                요일/시간 "#content > div.page_content > div.bbs_skin > table > thead > tr > th:nth-child(8)"
                수강료 "#content > div.page_content > div.bbs_skin > table > thead > tr > th:nth-child(10)"
                강좌상태 "#content > div.page_content > div.bbs_skin > table > thead > tr > th:nth-child(11)"


                ***서울우먼업>>전체프로그램>>tbody***
                * 담당센터 #content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(1) > td:nth-child(1)
                * 강좌명 #content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(1) > td.subject > a
                * 교육기간 #content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(1) > td:nth-child(7)
                * 요일/시간 #content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(1) > td:nth-child(8)
                * 수강료 #content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(1) > td:nth-child(10)
                * 강좌 상태 #content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(1) > td:nth-child(11) > a
                *
                1페이지
                #content > div.page_content > div.bbs_skin > div.paging > div > a:nth-child(2)
                1페이지 강좌명#content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(1) > td.subject
                2페이지
                #content > div.page_content > div.bbs_skin > div.paging > div > a.on
                2페이지 강좌명
                #content > div.page_content > div.bbs_skin > table > tbody > tr:nth-child(1) > td.subject
                */
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

                    Program p = new Program(proCenter, proName, proTerm, proDate, proPrice, proState);
                    arr[i] = p;
                    System.out.println(i + "센터: " + arr[i].getpCenter());
                    System.out.println("강좌명: " + arr[i].getpName());
                    System.out.println("교육기간: " + arr[i].getpTerm());
                    System.out.println("시간/요일: " + arr[i].getpDate());
                    System.out.println("수강료: " + arr[i].getpPrice());
                    System.out.println("강좌상태: " + arr[i].getpState());
                    System.out.println("--------------------------------------");
                    //htmlContentInStringFormat = p.getpCenter()+" "+p.getpName()+" "+p.getpTerm()+" "+p.getpDate()+" "+p.getpPrice()+" "+p.getpState()+"\n";
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
