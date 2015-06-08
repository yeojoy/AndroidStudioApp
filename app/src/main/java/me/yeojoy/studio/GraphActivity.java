package me.yeojoy.studio;

import android.app.Activity;
import android.content.pm.LabeledIntent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by yeojoy on 14. 12. 17..
 */
public class GraphActivity extends Activity {

    private Map<Integer, Integer> map;

    private LineChart lineChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineChart = (LineChart) findViewById(R.id.chart);
        lineChart.setLogEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRandomNumbers();

        LineData data = new LineData();
        for (int i = 0, j = 10; i < j; i++) {
            Entry e = new Entry(map.get(i), i);
            data.addEntry(e, i);
        }

        lineChart.setData(data);
        lineChart.invalidate();
    }
//
//    private void printAll() {
//        // TODO Auto-generated method stub
//        for (int i = 0, j = 10; i < j; i++) {
//            int count = map.get(i);
//            String percent = "";
//            for (int k = 0, l = count; k < l; k++) {
//                percent += "*";
//            }
//
//            System.out.println(String.format("%3d : %s", i, percent));
//        }
//    }

    private void setRandomNumbers() {
        // TODO Auto-generated method stub
        if (map != null) map.clear();
        init();

        Random r = new Random();
        for (int i = 0, j = 100; i < j; i++) {
            int randomNumber = r.nextInt(10);
            int count = map.get(randomNumber) + 1;
            map.put(randomNumber, count);
        }
    }

    private void init() {
        if (map == null)
            map = new HashMap<Integer, Integer>();

        for (int i = 0, j = 10; i < j; i++) {
            map.put(i, 0);
        }
    }
}
