package com.jiyun.jsonjiexi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.jsonjiexi.adapters.My_Rec_adapter;
import com.jiyun.jsonjiexi.beans.Da;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {
    //  private String url = "https://www.wanandroid.com/article/list/0/json";
    private String url = "https://www.wanandroid.com/article/list/";
    private String post_url = "https://api.github.com/markdown/raw";
    private XRecyclerView mMyrec;
    private int page = 0;
    private List<Da.DataBean.DatasBean> list;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                List<Da.DataBean.DatasBean> datas = (List<Da.DataBean.DatasBean>) msg.obj;
                list.addAll(datas);
                adapter.notifyDataSetChanged();
            }else if (msg.what == 2){
                String string = (String) msg.obj;
                mTvname.setText(string);
            }
            mMyrec.loadMoreComplete();
            mMyrec.refreshComplete();
        }
    };
    private My_Rec_adapter adapter;
    private TextView mTvname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();
        // okhttpAys();// get请求 异步
      //  okhttpAny();// get请求 同步

        okhttpPostAys();
    }

    private void okhttpPostAys() {
        OkHttpClient client = new OkHttpClient();

        MediaType type = MediaType.parse("text/x-markdown;charset:utf-8");
        RequestBody requestBody = RequestBody.create(type, "我是刘健康");

        Request request = new Request.Builder()
                .post(requestBody)
                .url(post_url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                handler.sendMessage(handler.obtainMessage(2,string));

            }
        });
    }

    private void okhttpAny() {
        OkHttpClient client = new OkHttpClient();
        Request build = new Request.Builder()
                .get()
                .url(url + page + "/json")
                .build();
        final Call call = client.newCall(build);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Response response = call.execute();
                    String string = response.body().string();
                    Da da = new Gson().fromJson(string, Da.class);
                    List<Da.DataBean.DatasBean> datas = da.getData().getDatas();
                    handler.sendMessage(handler.obtainMessage(1, datas));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private void okhttpAys() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Da da = new Gson().fromJson(string, Da.class);
                List<Da.DataBean.DatasBean> datas = da.getData().getDatas();
                handler.sendMessage(handler.obtainMessage(1, datas));
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        mMyrec.setLayoutManager(new LinearLayoutManager(this));
        mMyrec.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        adapter = new My_Rec_adapter(this, list);
        mMyrec.setAdapter(adapter);

        adapter.setA(new My_Rec_adapter.A() {
            @Override
            public void onClick(View view, int position) {
                String link = list.get(position).getLink();
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("url", link);
                startActivity(intent);
            }
        });
        mMyrec.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                page = 0;
                okhttpAny();
            }

            @Override
            public void onLoadMore() {
                page++;
                okhttpAny();
            }
        });

    }

    private void initView() {
        mMyrec = (XRecyclerView) findViewById(R.id.myrec);


        mTvname = (TextView) findViewById(R.id.tvname);
    }
}
