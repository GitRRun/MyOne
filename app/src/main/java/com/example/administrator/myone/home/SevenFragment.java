package com.example.administrator.myone.home;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myone.LoginActivity;
import com.example.administrator.myone.R;
import com.example.administrator.myone.http.HttpUtils;
import com.example.administrator.myone.parse.Info;
import com.example.administrator.myone.parse.InfoMes;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SevenFragment extends Fragment {

    View view;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    String path="http://v3.wufazhuce.com:8000/api/hp/detail/";
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg!=null&&msg.what==0){
                String url= (String) msg.obj;
                Picasso.with(getActivity()).load(url).resize(670,500).centerCrop().into(imageView);
            }
            if (msg!=null&&msg.what==1){
                String title= (String) msg.obj;
                textView3.setText(title);
            }
            if (msg!=null&&msg.what==2){
                String author= (String) msg.obj;
                textView.setText(author);
            }
            if (msg!=null&&msg.what==3){
                String content= (String) msg.obj;
                textView2.setText(content);
            }
            if (msg!=null&&msg.what==4){
                String time= (String) msg.obj;
                textView4.setText(time);
            }
        }
    };

    public SevenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_seven, container, false);
        init();
        //图片点击事件
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json= HttpUtils.getData("http://v3.wufazhuce.com:8000/api/hp/idlist/0");
                Gson gson=new Gson();
                Info info=gson.fromJson(json, Info.class);
                List<String> list =info.getData();
                /*for (int i=0;i<list.size();i++){
                    String s =path+list.get(0);
                    Log.e("===","ss"+s);
                }*/
                String s =path+list.get(6);
                String str= HttpUtils.getData(s);
                InfoMes infoMes= gson.fromJson(str, InfoMes.class);
                String url = infoMes.getData().getHp_img_url();
                Log.e("===","ss"+url);
                String title = infoMes.getData().getHp_title();
                Log.e("===","ss"+title);
                String author = infoMes.getData().getHp_author();
                Log.e("===","ss"+author);
                String content = infoMes.getData().getHp_content();
                Log.e("===","ss"+content);
                String time = infoMes.getData().getHp_makettime();
                Log.e("===","ss"+time);
                handler.obtainMessage(0,url).sendToTarget();
                handler.obtainMessage(1,title).sendToTarget();
                handler.obtainMessage(2,author).sendToTarget();
                handler.obtainMessage(3,content).sendToTarget();
                handler.obtainMessage(4,time).sendToTarget();


            }

        }).start();

        return view;
    }

    //初始化控件
    public void init(){
        imageView= (ImageView) view.findViewById(R.id.imageView);
        imageView2= (ImageView) view.findViewById(R.id.imageView2);
        imageView3= (ImageView) view.findViewById(R.id.share);
        textView = (TextView) view.findViewById(R.id.hp_author);
        textView2 = (TextView) view.findViewById(R.id.hp_content);
        textView3 = (TextView) view.findViewById(R.id.hp_title);
        textView4 = (TextView) view.findViewById(R.id.time);
    }


}
