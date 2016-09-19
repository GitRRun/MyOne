package com.example.administrator.myone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    ViewPager viewPager;
    FragmentManager fragmentManager;
    List<Fragment> fragmentList;
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
        MyAdapter myAdapter=new MyAdapter(fragmentManager,fragmentList);
        viewPager.setAdapter(myAdapter);


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
