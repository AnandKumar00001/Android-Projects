package com.example.android.miwok;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.viewPager);
        categoryAdapter adapter = new categoryAdapter(MainActivity.this , getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tableLayout = findViewById(R.id.tabLayout);
        tableLayout.setupWithViewPager(viewPager);

    }


}





