package com.example.prayaas;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private ViewPager mviewpager;
    private LinearLayout mlinearlayout;
    private slider_adapter mslideadpter;
    private TextView dots[]= new TextView[3];;
    private Button prevbutton;

    private Button nextbutton;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mviewpager =(ViewPager)findViewById(R.id.slideViewpager);
        mlinearlayout=(LinearLayout)findViewById(R.id.dots);
        prevbutton=(Button)findViewById(R.id.prev);
        nextbutton=(Button)findViewById(R.id.next);
        mslideadpter =new slider_adapter(this);
        mviewpager.setAdapter(mslideadpter);
        addDotIndicator(0);
        mviewpager.addOnPageChangeListener(viewListener);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage==2)
                {
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    finish();
                }
                mviewpager.setCurrentItem(currentPage+1);
            }
        });
        prevbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mviewpager.setCurrentItem(currentPage-1);
            }
        });
    }

    public void addDotIndicator(int position){

        for(int i=0;i<dots.length;i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.black));
            mlinearlayout.addView(dots[i]);

        }



    }
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(dots.length>0)
            {
                dots[position].setTextColor(getResources().getColor(R.color.white));
            }
            currentPage=position;
            if(position==0)
            {
                prevbutton.setEnabled(false);
                nextbutton.setEnabled(true);
                prevbutton.setVisibility(View.INVISIBLE);
                nextbutton.setText("NEXT");
                prevbutton.setText("");
                dots[position+1].setTextColor(getResources().getColor(R.color.black));
            }
            else if(position==dots.length-1)
            {
                prevbutton.setEnabled(true);
                nextbutton.setEnabled(true);
                prevbutton.setVisibility(View.VISIBLE);
                nextbutton.setText("FINISH");
                prevbutton.setText("BACK");
                dots[position-1].setTextColor(getResources().getColor(R.color.black));
            }
            else
            {
                prevbutton.setEnabled(true);
                nextbutton.setEnabled(true);
                prevbutton.setVisibility(View.VISIBLE);
                nextbutton.setText("NEXT");
                prevbutton.setText("BACK");
                dots[position-1].setTextColor(getResources().getColor(R.color.black));
                dots[position+1].setTextColor(getResources().getColor(R.color.black));
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
