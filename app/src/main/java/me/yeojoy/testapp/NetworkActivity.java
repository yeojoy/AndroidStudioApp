package me.yeojoy.testapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import me.yeojoy.testapp.dto.Item;

/**
 * Created by yeojoy on 14. 12. 12..
 */
public class NetworkActivity extends Activity {

    // ID가 D00220A01 인 문항의 정보를 가져옴.
    private static final String URL = "http://cmath.jei.com/item/D00220A01";

    private Context mContext;

    private OkHttpClient client;

    private TextView mTvResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        mContext = this;

        mTvResult = (TextView) findViewById(R.id.tv_result);
        if (client == null) client = new OkHttpClient();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_get) {
            mTvResult.setText("");
            getQuestion();
        }
    }


    private void getQuestion() {
        Request request = new Request.Builder().url(URL).build();

        // Synchronous GET... OTL
        // Sync use execute() method
//            Response response = client.newCall(request).execute();
//            mTvResult.setText(response.body().string());

        // Async
        // Async use enqueue() method.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(mContext, "onFailure", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onResponse(final Response response) {

                // Gson으로 처리. Long형인 milliseconds를 처리하기 위해서 추가해 줘야할 것임 있음.
                GsonBuilder gb = new GsonBuilder();
                gb.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    @Override
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                });
                Gson gson = gb.create();

                Item item = gson.fromJson(response.body().charStream(),
                        Item.class);

                if (item != null) setResultTextView(item);

                // 일반적인 json string 으로 처리하는 방식.
                /*
                StringBuilder sb = null;
                try {
                    sb = new StringBuilder();
                    Headers responseHeaders = response.headers();
                    for (int i = 0; i < responseHeaders.size(); i++) {
                        sb.append(responseHeaders.name(i) + ": "
                                + responseHeaders.value(i));
                    }
                    sb.append("\n\n=================================\n");
                    sb.append(response.body().string());
                } catch (IOException e) {
                    e.getStackTrace();
                }

                if (sb != null) setResultTextView(sb.toString());
                */
            }
        });
    }

    private void setResultTextView(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvResult.append("params : string\n");
                mTvResult.append(msg);
            }
        });
    }

    private void setResultTextView(final Item item) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvResult.append("params : item\n");
                mTvResult.append(item.toString());
            }
        });
    }
}
