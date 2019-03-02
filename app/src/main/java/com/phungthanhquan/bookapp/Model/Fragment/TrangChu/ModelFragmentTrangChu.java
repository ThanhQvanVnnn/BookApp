package com.phungthanhquan.bookapp.Model.Fragment.TrangChu;

import com.phungthanhquan.bookapp.Object.ItemBook;
import com.phungthanhquan.bookapp.Object.Slider;

import java.util.ArrayList;
import java.util.List;

public class ModelFragmentTrangChu {
    private List<Slider> sliderList ;
    private List<ItemBook> dsSachMois ;
    //lấy danh sách slider từ api
    public List<Slider> getDataSlider(){
        sliderList = new ArrayList<>();
        Slider slider1 = new Slider("https://znews-photo.zadn.vn/w660/Uploaded/jobunwj/2015_10_01/hoa_vang_co_xanh.jpg","https://webtruyen.com/public/images/toithayhoavangtrencoxanh1woCMXi6Ln.jpg");
        Slider slider2 = new Slider("https://isachhay.net/wp-content/uploads/2018/04/nha-gia-kim-sach-van-hoc-hay-nen-doc-03.jpg","https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/8/9/8935235213746.jpg");
        Slider slider3 = new Slider("https://isachhay.net/wp-content/uploads/2018/11/quang-ganh-lo-di-va-vui-song-review-sach-hay-isachhay.net_.jpg","https://nhanvan.vn/images/detailed/8/8935086828410.jpg");
        Slider slider4 = new Slider("https://vnwriter.net/wp-content/uploads/2017/07/sach-khi-loi-thuoc-ve-nhung-vi-sao-nxb-tre.jpg","https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/k/h/khi-loi-thuoc-ve-nhung-vi-sao-b.jpg");
        sliderList.add(slider1);
        sliderList.add(slider2);
        sliderList.add(slider3);
        sliderList.add(slider4);
         return sliderList;
    }

    //lấy ds sách mới
    public List<ItemBook> getDataDsSachMoi(){
        dsSachMois = new ArrayList<>();
        ItemBook itemBook1 = new ItemBook("Hoa vàng trên đồi cỏ",
                "https://webtruyen.com/public/images/toithayhoavangtrencoxanh1woCMXi6Ln.jpg",
                "15");
        ItemBook itemBook2 = new ItemBook("Nhà Giả Kim",
                "https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/8/9/8935235213746.jpg",
                "15");
        ItemBook itemBook3 = new ItemBook("Quảng gánh lo vui mà sống",
                "https://nhanvan.vn/images/detailed/8/8935086828410.jpg",
                "15");
        ItemBook itemBook4 = new ItemBook("Khi lỗi thuộc về những vì sao",
                "https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/k/h/khi-loi-thuoc-ve-nhung-vi-sao-b.jpg",
                "15");
        dsSachMois.add(itemBook1);
        dsSachMois.add(itemBook2);
        dsSachMois.add(itemBook3);
        dsSachMois.add(itemBook4);
        dsSachMois.add(itemBook1);
        dsSachMois.add(itemBook2);
        dsSachMois.add(itemBook3);
        dsSachMois.add(itemBook4);
        dsSachMois.add(itemBook1);
        dsSachMois.add(itemBook2);
        dsSachMois.add(itemBook3);
        dsSachMois.add(itemBook4);
        dsSachMois.add(itemBook1);
        dsSachMois.add(itemBook2);
        dsSachMois.add(itemBook3);
        dsSachMois.add(itemBook4);
        return dsSachMois;
    }

    //lấy ds sách khuyên đọc
    public List<ItemBook> getDataDsSachKhuyenDoc(){
        dsSachMois = new ArrayList<>();
        ItemBook itemBook1 = new ItemBook("Hoa vàng trên đồi cỏ",
                "https://webtruyen.com/public/images/toithayhoavangtrencoxanh1woCMXi6Ln.jpg",
                "15");
        ItemBook itemBook2 = new ItemBook("Nhà Giả Kim",
                "https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/8/9/8935235213746.jpg",
                "15");
        ItemBook itemBook3 = new ItemBook("Quảng gánh lo vui mà sống",
                "https://nhanvan.vn/images/detailed/8/8935086828410.jpg",
                "15");
        ItemBook itemBook4 = new ItemBook("Khi lỗi thuộc về những vì sao",
                "https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/k/h/khi-loi-thuoc-ve-nhung-vi-sao-b.jpg",
                "15");
        dsSachMois.add(itemBook1);
        dsSachMois.add(itemBook2);
        dsSachMois.add(itemBook3);
        dsSachMois.add(itemBook4);
        dsSachMois.add(itemBook1);
        dsSachMois.add(itemBook2);
        dsSachMois.add(itemBook3);
        dsSachMois.add(itemBook4);
        dsSachMois.add(itemBook1);
        dsSachMois.add(itemBook2);
        dsSachMois.add(itemBook3);
        dsSachMois.add(itemBook4);
        dsSachMois.add(itemBook1);
        dsSachMois.add(itemBook2);
        dsSachMois.add(itemBook3);
        dsSachMois.add(itemBook4);
        return dsSachMois;
    }

    //lấy ds sách văn học trong nước
    public List<ItemBook> getDataDsSachVanHocTrongNuoc(){
        dsSachMois = new ArrayList<>();
        ItemBook itemBook1 = new ItemBook("Hoa vàng trên đồi cỏ",
                "https://webtruyen.com/public/images/toithayhoavangtrencoxanh1woCMXi6Ln.jpg",
                "15");
        ItemBook itemBook2 = new ItemBook("Nhà Giả Kim",
                "https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/8/9/8935235213746.jpg",
                "15");
        ItemBook itemBook3 = new ItemBook("Quảng gánh lo vui mà sống",
                "https://nhanvan.vn/images/detailed/8/8935086828410.jpg",
                "15");
        ItemBook itemBook4 = new ItemBook("Khi lỗi thuộc về những vì sao",
                "https://www.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/k/h/khi-loi-thuoc-ve-nhung-vi-sao-b.jpg",
                "15");
        dsSachMois.add(itemBook1);
        dsSachMois.add(itemBook2);
        dsSachMois.add(itemBook3);
        dsSachMois.add(itemBook4);
        dsSachMois.add(itemBook1);
        dsSachMois.add(itemBook2);
        dsSachMois.add(itemBook3);
        dsSachMois.add(itemBook4);
        dsSachMois.add(itemBook1);
        dsSachMois.add(itemBook2);
        dsSachMois.add(itemBook3);
        dsSachMois.add(itemBook4);
        dsSachMois.add(itemBook1);
        dsSachMois.add(itemBook2);
        dsSachMois.add(itemBook3);
        dsSachMois.add(itemBook4);
        return dsSachMois;
    }

}
