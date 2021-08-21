package com.example.prayaas.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.prayaas.R;

public class slider_adapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public slider_adapter(Context context)
    {
        this.context=context;
    }

    public int[] slideImage={R.drawable.slider1,R.drawable.slider2,R.drawable.slider3};
    public String slide_heading[]={"Vision","Mission","Achievements"};
    public String slide_des[]={"Our Vision is give wing to children to break the chain of poverty ","To educate the children of labourers and those socially deprived; who are devoid of education. ","PRAYAAS now have three teaching centers: NITJ, Maqsudan and Amanatpur " +
            "Selection of students in Jawahar Navodaya Vidyalaya."};
    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==(RelativeLayout)o;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view;

        view = layoutInflater.inflate(R.layout.slide,container,false);
        ImageView slideImage=(ImageView) view.findViewById(R.id.image);
        TextView slideHeading=(TextView) view.findViewById(R.id.heading);
        TextView slideDescription=(TextView) view.findViewById(R.id.description);
        slideImage.setImageResource(this.slideImage[position]);
        slideHeading.setText(slide_heading[position]);
        slideDescription.setText(slide_des[position]);
        container.addView(view);
        return  view;
    }
    @Override
    public void destroyItem(ViewGroup container,int position,Object o)
    {
        container.removeView((RelativeLayout)o);
    }
}