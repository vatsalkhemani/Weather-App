package com.example.myweatherapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String mLatitude;
    String mLongitude;
    String mName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent=getIntent();

        mLatitude=intent.getStringExtra("latitude");
        mLongitude=intent.getStringExtra("longitude");
        mName=intent.getStringExtra("name");

        Bundle bundle=new Bundle();
        bundle.putString("latitude",mLatitude);
        bundle.putString("longitude",mLongitude);
        bundle.putString("name",mName);


      //  Toast.makeText(getApplicationContext(), "Latitude="+mLatitude+"\n"+"Longitude="+mLongitude+"\n"+"name:"+mName,Toast.LENGTH_SHORT).show();


        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        final ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        Fragment cur=new CurrentFragment();    cur.setArguments(bundle);    viewPagerAdapter.addFragment(cur, "Current");
        //viewPagerAdapter.addFragment(new CurrentFragment(),"Current");
        viewPagerAdapter.addFragment(new DateFragment(), "Date");
        viewPagerAdapter.addFragment(new ForecastFragment(), "Forecast");
        viewPagerAdapter.addFragment(new SettingFragment(), "Settings");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}

class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    ViewPagerAdapter(FragmentManager fm){
        super(fm);
        this.fragments = new ArrayList<>();
        this.titles = new ArrayList<>();
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
    public void addFragment(Fragment fragment, String title){
        fragments.add(fragment);
        titles.add(title);
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
