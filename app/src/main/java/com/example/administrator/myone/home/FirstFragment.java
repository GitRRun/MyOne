package com.example.administrator.myone.home;


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
public class FirstFragment extends Fragment {
    View view;
    ImageView imageView;
    TextView author;
    TextView content;
    TextView title;
    String s;
    String path="http://v3.wufazhuce.com:8000/api/hp/detail/";
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg!=null&&msg.what==0){
               String url= (String) msg.obj;
                Picasso.with(getActivity()).load(url).into(imageView);
            }
        }
    };
    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_first, container, false);

         init();
        new Thread(new Runnable() {
            @Override
            public void run() {
               String json= HttpUtils.getData("http://v3.wufazhuce.com:8000/api/hp/idlist/0");
                Gson gson=new Gson();
                Info info=gson.fromJson(json, Info.class);
                List<String> list =info.getData();
                for (int i=0;i<list.size();i++){
                    String s =path+list.get(0);
                    Log.e("===","ss"+s);
                }
               String str= HttpUtils.getData(s);
               InfoMes infoMes= gson.fromJson(s, InfoMes.class);
                String url = infoMes.getData().getHp_img_url();
                Log.e("===","ss"+url);
                String title = infoMes.getData().getHp_title();
                Log.e("===","ss"+title);
                String author = infoMes.getData().getHp_author();
                Log.e("===","ss"+author);
                String content = infoMes.getData().getHp_content();
                Log.e("===","ss"+content);
                handler.obtainMessage(0,url).sendToTarget();
                handler.obtainMessage(1,title).sendToTarget();
                handler.obtainMessage(2,author).sendToTarget();
                handler.obtainMessage(3,content).sendToTarget();


            }

        }).start();

        return view;
    }

    //初始化控件
    public void init(){
        imageView= (ImageView) view.findViewById(R.id.imageView);
        author = (TextView) view.findViewById(R.id.hp_author);
        content = (TextView) view.findViewById(R.id.hp_content);
        title = (TextView) view.findViewById(R.id.hp_title);
    }

}
