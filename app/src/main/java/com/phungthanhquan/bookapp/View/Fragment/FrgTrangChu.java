package com.phungthanhquan.bookapp.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phungthanhquan.bookapp.Adapter.ViewPager_Slider_Adapter;
import com.phungthanhquan.bookapp.Object.Slider;
import com.phungthanhquan.bookapp.R;

import java.util.ArrayList;
import java.util.List;

public class FrgTrangChu extends Fragment {

    private List<Slider> sliderList;
    private ViewPager_Slider_Adapter slider_Adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_trangchu,container,false);
        InitControls(view);
        return view;

    }

    private void InitControls(View view) {
        ViewPager slider = view.findViewById(R.id.pager_slider);
        Slider slider1 = new Slider("https://www.androidhive.info/wp-content/uploads/2016/05/android-welcome-intro-slider-with-bottom-dots.png");
        Slider slider2 = new Slider("https://i.stack.imgur.com/ToF5i.png");
        Slider slider3 = new Slider("http://alltutorial.in/wp-content/uploads/2016/12/image-slider.png");
        Slider slider4 = new Slider("https://i.ytimg.com/vi/SX8l9vv-N_4/maxresdefault.jpg");
        sliderList = new ArrayList<>();
        sliderList.add(slider1);
        sliderList.add(slider2);
        sliderList.add(slider3);
        sliderList.add(slider4);
        slider_Adapter = new ViewPager_Slider_Adapter(getContext(),sliderList);
        slider.setAdapter(slider_Adapter);
    }
}
