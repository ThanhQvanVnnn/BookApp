package com.phungthanhquan.bookapp.Presenter.Fragment;

import com.phungthanhquan.bookapp.Model.Fragment.ModelFragmentTrangChu;
import com.phungthanhquan.bookapp.Object.Slider;
import com.phungthanhquan.bookapp.View.InterfaceView.InterfaceViewFragmentTrangChu;

import java.util.List;

public class PresenterFragmentTrangChu implements PresenterInterfaceFragmentTrangChu{

    InterfaceViewFragmentTrangChu interfaceViewFragmentTrangChu;
    ModelFragmentTrangChu modelFragmentTrangChu;

    public PresenterFragmentTrangChu(InterfaceViewFragmentTrangChu interfaceViewFragmentTrangChu) {
        this.interfaceViewFragmentTrangChu = interfaceViewFragmentTrangChu;
         this.modelFragmentTrangChu = new ModelFragmentTrangChu();
    }

    @Override
    public void xulislider() {
        List<Slider> sliderList = modelFragmentTrangChu.getDataSlider();
        if(sliderList.size()>0){
            interfaceViewFragmentTrangChu.hienthislider(sliderList);
        }
    }
}
