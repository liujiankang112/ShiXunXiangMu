package com.jiyun.jsonjiexi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiyun.jsonjiexi.beans.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * Hello World!
     */
    private TextView mTvname;
    /**
     * Hello World!
     */
    private TextView mTvcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mTvname = (TextView) findViewById(R.id.tvname);
        mTvcode = (TextView) findViewById(R.id.tvcode);

    }

    private void initData() {
        String s = "{code:'1';name:'张三'}";
        String s1 = "[{code:'1';name:'张三'}{code:'2';name:'李四'}]";

     
        try {
            JSONObject json = new JSONObject(s);
            String code = json.getString("code");
            String name = json.getString("name");
            mTvcode.setText(code);
            mTvname.setText(name);

            JSONArray jsonArray = new JSONArray(s1);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
