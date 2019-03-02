package com.phungthanhquan.bookapp.View.InterfaceView;

import com.phungthanhquan.bookapp.Object.ItemBook;
import com.phungthanhquan.bookapp.Object.Slider;

import java.util.List;

public interface InterfaceViewFragmentTrangChu {
    void hienthislider(List<Slider> sliderList);
    void hienthidsSachmoi(List<ItemBook> dsSachMoi);
    void hienthidsSachKhuyenDoc(List<ItemBook> dsSachKhuyenDoc);
    void hienthidsSachVanHocTrongNuoc(List<ItemBook> dsSachVanHocTrongNuoc);
}
