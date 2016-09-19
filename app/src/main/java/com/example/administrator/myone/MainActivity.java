package com.example.administrator.myone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.myone.callback.CallBack;
import com.example.administrator.myone.home.EightFragment;
import com.example.administrator.myone.home.FirstFragment;
import com.example.administrator.myone.home.FiveFragment;
import com.example.administrator.myone.home.FourFragment;
import com.example.administrator.myone.home.MyAdapter;
import com.example.administrator.myone.home.NineFragment;
import com.example.administrator.myone.home.SecondFragment;
import com.example.administrator.myone.home.SevenFragment;
import com.example.administrator.myone.home.SixFragment;
import com.example.administrator.myone.home.TenFragment;
import com.example.administrator.myone.home.ThirdFragment;
import com.example.administrator.myone.http.HttpUtils;
import com.example.administrator.myone.parse.Info;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    ViewPager viewPager;
    ImageView imageView;
    ImageView imageView2;
    FragmentManager fragmentManager;
    List<Fragment> fragmentList;
    String path="http://v3.wufazhuce.com:8000/api/hp/detail/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager= (ViewPager) findViewById(R.id.vp);
        fragmentManager =getSupportFragmentManager();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(this);
        fragmentList=new ArrayList<>();
        addFragmentToList();
        imageView= (ImageView) findViewById(R.id.search);
        imageView2 = (ImageView) findViewById(R.id.people);
        MyAdapter myAdapter=new MyAdapter(fragmentManager,fragmentList);
        viewPager.setAdapter(myAdapter);
        //点击事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //标题图片的点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"被点击了一下",Toast.LENGTH_SHORT).show();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"被点击了一下",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    //把fragment添加到集合中
    public void addFragmentToList(){
        fragmentList.add(new FirstFragment());
        fragmentList.add(new SecondFragment());
        fragmentList.add(new ThirdFragment());
        fragmentList.add(new FourFragment());
        fragmentList.add(new FiveFragment());
        fragmentList.add(new SixFragment());
        fragmentList.add(new SevenFragment());
        fragmentList.add(new EightFragment());
        fragmentList.add(new NineFragment());
        fragmentList.add(new TenFragment());

    }
}
