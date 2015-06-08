package me.yeojoy.studio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import me.yeojoy.studio.adapter.MyAdapter;
import me.yeojoy.studio.dto.Dust;

/**
 * Created by yeojoy on 15. 1. 2..
 */
public class MyListActivity extends Activity {

    private static final String TAG = MyListActivity.class.getSimpleName();
//    private static final String URL = "http://cleanair.seoul.go.kr/air_city.htm?method=airPollutantInfoMeasureXml";

    private Context mContext;

    private OkHttpClient client;

    private ListView mLv;
    private MyAdapter mAdapter;
    
    private boolean mIsAllChecked = false;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mContext = this;

        mLv = (ListView) findViewById(R.id.lv);
        mAdapter = new MyAdapter(mContext);
        mLv.setAdapter(mAdapter);
        
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick()");
                
                mAdapter.setChecked(position);
            }
        });
    }
    
    public void onClick(View view) {
        if (view.getId() == R.id.btn_work) {
            /*
            Request request = new Request.Builder().url(URL).build();
            
            client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Toast.makeText(mContext, "onFailure", Toast.LENGTH_SHORT)
                            .show();
                }
    
                @Override
                public void onResponse(final Response response) throws IOException {
                    Log.i(TAG, "onResponse()");
                    final List<Dust> items = parseRawXmlString(response.body().string());
                    
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "run()");
                            mAdapter.setItems(items);
                        }
                    });
                }
            });
            */
            List<Dust> items = getDummyData();

            // 지역이름(Locality) 오름차순 정렬
            Collections.sort(items, new DustLocalityAscComparer());

            mAdapter.setItems(items);

        } else if (view.getId() == R.id.btn_all_check) {
            mIsAllChecked = !mIsAllChecked;

            mAdapter.setAllChecked(mIsAllChecked);
        }
    }

    public List<Dust> parseRawXmlString(String str) {
        Log.i(TAG, "parseRawXmlString()");
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(str));
            int eventType = xpp.getEventType();
            List<Dust> list = new ArrayList<Dust>();
            String startTagName = null;
            String text = null;
            Dust dto = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    startTagName = xpp.getName();
                } else if (eventType == XmlPullParser.TEXT) {
                    text = xpp.getText().trim();
                    
                    if (startTagName != null && text != null
                            && !TextUtils.isEmpty(text)) {
                        if (startTagName.equals("msrdate")) {
                            dto = new Dust();
                            dto.setChecked(false);
                        } else if (startTagName.equals("msrstename")) {
                            dto.setLocaltity(text);
                        } else if (startTagName.equals("maxindex")) {
                            dto.setMaxIndex(text);
                        } else if (startTagName.equals("pm10")) {
                            dto.setPm10(text);
                        } else if (startTagName.equals("sulfurousindex")) {
                            list.add(dto);
                        }
                    }
                }
                eventType = xpp.next();
            }
            Log.i(TAG, "Dust length : " + list.size());
            
            // 지역이름(Locality) 오름차순 정렬
            Collections.sort(list, new DustLocalityAscComparer());

            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    static class DustLocalityAscComparer implements Comparator<Dust> {
        @Override
        public int compare(Dust lhs, Dust rhs) {
            return lhs.getLocaltity().compareTo(rhs.getLocaltity());
        }
    }


    private List<Dust> getDummyData() {
        List<Dust> items = new ArrayList<Dust>();
        Random random = new Random();
        Dust d = new Dust("중랑구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("종로", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("노원구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("은평구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("서대문구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("마포구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("양천구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("강서구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("구로", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("금천구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("영등포구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("동작구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("송파구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("강동구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("영등포", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("청계천", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("신사동", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("청량리", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("서울역", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("길동", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("종로구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("중", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("용산구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("성동구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("광진구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("동대문구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("성북구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("강북구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("도봉구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("관악구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("서초구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("강남구", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("신촌", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("동대문", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("공항로", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("동작대로", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("양재대로", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("태릉", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("강변북로", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);
        d = new Dust("내부순환", String.valueOf(random.nextInt(100)),
                String.valueOf(random.nextInt(100)));
        items.add(d);

        return items;
    }

}
