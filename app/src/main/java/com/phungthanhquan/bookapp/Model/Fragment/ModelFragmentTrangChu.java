package com.phungthanhquan.bookapp.Model.Fragment;

import com.phungthanhquan.bookapp.Object.Slider;

import java.util.ArrayList;
import java.util.List;

public class ModelFragmentTrangChu {
    private List<Slider> sliderList = new ArrayList<>();

    public List<Slider> getDataSlider(){
        Slider slider1 = new Slider("https://www.androidhive.info/wp-content/uploads/2016/05/android-welcome-intro-slider-with-bottom-dots.png");
        Slider slider2 = new Slider("https://i.stack.imgur.com/ToF5i.png");
        Slider slider3 = new Slider("http://alltutorial.in/wp-content/uploads/2016/12/image-slider.png");
        Slider slider4 = new Slider("https://i.ytimg.com/vi/SX8l9vv-N_4/maxresdefault.jpg");
        sliderList = new ArrayList<>();
        sliderList.add(slider1);
        sliderList.add(slider2);
        sliderList.add(slider3);
        sliderList.add(slider4);
         return sliderList;
    }
}
