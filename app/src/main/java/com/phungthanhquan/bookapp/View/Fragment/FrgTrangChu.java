package com.phungthanhquan.bookapp.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.phungthanhquan.bookapp.Adapter.ViewPager_Slider_Adapter;
import com.phungthanhquan.bookapp.Object.Slider;
import com.phungthanhquan.bookapp.Presenter.Fragment.PresenterFragmentTrangChu;
import com.phungthanhquan.bookapp.R;
import com.phungthanhquan.bookapp.View.InterfaceView.InterfaceViewFragmentTrangChu;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FrgTrangChu extends Fragment implements InterfaceViewFragmentTrangChu {

    private List<Slider> sliderList;
    private ViewPager_Slider_Adapter slider_Adapter;
    private TabLayout indicator;
    private ViewPager slider;
    private PresenterFragmentTrangChu presenterFragmentTrangChu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_trangchu,container,false);
        InitControls(view);
        return view;
    }

    private void InitControls(View view) {
        slider = view.findViewById(R.id.pager_slider);
        indicator = view.findViewById(R.id.indicator_slider);
        presenterFragmentTrangChu = new PresenterFragmentTrangChu(this);
        presenterFragmentTrangChu.xulislider();
    }


    @Override
    public void hienthislider(List<Slider> sliderListReturn) {
        sliderList = sliderListReturn;
        slider_Adapter = new ViewPager_Slider_Adapter(getContext(),sliderList);
        slider.setAdapter(slider_Adapter);
        int sizesliderList = sliderList.size();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new FrgTrangChu.TimeWork(sizesliderList),4000,6000);
        indicator.setupWithViewPager(slider,true);
    }

    class TimeWork extends TimerTask{
        int sizesliderList;
        public TimeWork(int sizesliderList) {
            this.sizesliderList = sizesliderList;
        }

        @Override
        public void run(){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int currentpage =slider.getCurrentItem();
                    if(currentpage<sizesliderList-1){
                        slider.setCurrentItem(currentpage+1);
                    }
                    else slider.setCurrentItem(0);
                }
            });
        }
    }
}

