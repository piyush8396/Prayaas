package com.example.prayaas;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.example.prayaas.Adapter.slider_adapter;
import com.google.firebase.auth.FirebaseAuth;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ViewPager mviewpager;
    private LinearLayout mlinearlayout;
    private slider_adapter mslideadpter;
    private TextView dots[]= new TextView[3];;
    private TextView nextbutton;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mviewpager =(ViewPager)findViewById(R.id.slideViewpager);
        mlinearlayout=(LinearLayout)findViewById(R.id.dots);


        nextbutton=(TextView) findViewById(R.id.next);
        mslideadpter =new slider_adapter(this);
        mviewpager.setAdapter(mslideadpter);
        addDotIndicator(0);
        mviewpager.addOnPageChangeListener(viewListener);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    finish();
            }
        });

    }

    public void addDotIndicator(int position){

        for(int i=0;i<dots.length;i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.grey_white));
            mlinearlayout.addView(dots[i]);

        }
        dots[0].setTextColor(getResources().getColor(R.color.blue_t));



    }
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(dots.length>0)
            {
                dots[position].setTextColor(getResources().getColor(R.color.blue_t));
            }
            currentPage=position;
            if(position==0)
            {  nextbutton.setText("Skip>>");


                dots[position+1].setTextColor(getResources().getColor(R.color.grey_white));
            }
            else if(position==dots.length-1)
            {     nextbutton.setText("Go>>");


                dots[position-1].setTextColor(getResources().getColor(R.color.grey_white));
            }
            else
            {  nextbutton.setText("Skip>>");


                dots[position-1].setTextColor(getResources().getColor(R.color.grey_white));
                dots[position+1].setTextColor(getResources().getColor(R.color.grey_white));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            startActivity(new Intent(MainActivity.this,DashActivity.class));
            finish();
        }
    }
}
