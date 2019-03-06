package com.phungthanhquan.bookapp.View.Fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.phungthanhquan.bookapp.Adapter.ListAlbum_Adapter;
import com.phungthanhquan.bookapp.Adapter.RecycleView_ItemBook_Adapter;
import com.phungthanhquan.bookapp.Adapter.RecycleView_NXB_Adapter;
import com.phungthanhquan.bookapp.Adapter.ViewPager_Slider_Adapter;
import com.phungthanhquan.bookapp.Object.AlbumBook;
import com.phungthanhquan.bookapp.Object.ItemBook;
import com.phungthanhquan.bookapp.Object.NXB;
import com.phungthanhquan.bookapp.Object.Slider;
import com.phungthanhquan.bookapp.Presenter.Fragment.PresenterFragmentTrangChu;
import com.phungthanhquan.bookapp.R;
import com.phungthanhquan.bookapp.View.Activity.BookDetail;
import com.phungthanhquan.bookapp.View.Activity.ListBookToChoice;
import com.phungthanhquan.bookapp.View.Activity.SearchBook;
import com.phungthanhquan.bookapp.View.InterfaceView.InterfaceViewFragmentTrangChu;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class FrgTrangChu extends Fragment implements InterfaceViewFragmentTrangChu, View.OnClickListener {

    private List<Slider> sliderList;
    private ViewPager_Slider_Adapter slider_Adapter;
    private TabLayout indicator;
    private ViewPager slider;
    private PresenterFragmentTrangChu presenterFragmentTrangChu;
    private RecyclerView hienthiDSSachMoi;
    private RecyclerView hienthiDSSachKhuyenDoc;
    private RecyclerView hienthiDSSachVanHocTrongNuoc;
    private RecyclerView hienthiDSNhaXuatBan;
    private TextView allSachMoi;
    private TextView allSachVanHocTrongNuoc;
    private ImageButton search;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<AlbumBook> albumBook = new ArrayList<>();
     private HorizontalInfiniteCycleViewPager pager_album;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        InitControls(view);
        return view;
    }

    private void InitControls(View view) {
        //binding id
        slider = view.findViewById(R.id.pager_slider);
        indicator = view.findViewById(R.id.indicator_slider);
        hienthiDSSachMoi = view.findViewById(R.id.recycle_sachmoi);
        hienthiDSSachKhuyenDoc = view.findViewById(R.id.recycle_sachkhuyendoc);
        hienthiDSSachVanHocTrongNuoc = view.findViewById(R.id.recycle_vanhoctrongnuoc);
        hienthiDSNhaXuatBan = view.findViewById(R.id.recycle_nhaxuatban);
        allSachMoi = view.findViewById(R.id.xemtatca_sachmoi);
        allSachVanHocTrongNuoc = view.findViewById(R.id.xemtatca_vanhoctrongnuoc);
         pager_album = view.findViewById(R.id.horizontal_cycle);
         search = view.findViewById(R.id.search_book);
         swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
         swipeRefreshLayout.setColorSchemeColors(android.R.color.holo_blue_dark,android.R.color.holo_blue_light,android.R.color.holo_green_dark);
         swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 swipeRefreshLayout.setRefreshing(true);
                 (new Handler()).postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         swipeRefreshLayout.setRefreshing(false);
                         presenterFragmentTrangChu.xulislider();
                         presenterFragmentTrangChu.xuliHienthiDsSachMoi();
                         presenterFragmentTrangChu.xuliHienthiDsSachKhuyenDoc();
                         presenterFragmentTrangChu.xuliHienthiDsSachVanHocTrongNuoc();
                         presenterFragmentTrangChu.xuliHienThiDsNhaXuatBan();
                         presenterFragmentTrangChu.xuliHienThiAlBumSach();
                     }
                 },3000);
         }});
        //onclick
        allSachMoi.setOnClickListener(this);
        allSachVanHocTrongNuoc.setOnClickListener(this);
        search.setOnClickListener(this);

        //presenter logic
        presenterFragmentTrangChu = new PresenterFragmentTrangChu(this);
        presenterFragmentTrangChu.xulislider();
        presenterFragmentTrangChu.xuliHienthiDsSachMoi();
        presenterFragmentTrangChu.xuliHienthiDsSachKhuyenDoc();
        presenterFragmentTrangChu.xuliHienthiDsSachVanHocTrongNuoc();
        presenterFragmentTrangChu.xuliHienThiDsNhaXuatBan();
        presenterFragmentTrangChu.xuliHienThiAlBumSach();
    }


    @Override
    public void hienthislider(List<Slider> sliderListReturn) {
        sliderList = sliderListReturn;
        slider_Adapter = new ViewPager_Slider_Adapter(getContext(), sliderList);
        slider.setAdapter(slider_Adapter);
        int sizesliderList = sliderList.size();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new FrgTrangChu.TimeWork(sizesliderList), 4000, 6000);
        indicator.setupWithViewPager(slider, true);

    }

    @Override
    public void hienthidsSachmoi(List<ItemBook> dsSachMoi) {
        RecycleView_ItemBook_Adapter dsSachMoiAdapter = new RecycleView_ItemBook_Adapter(getContext(), dsSachMoi);
        hienthiDSSachMoi.setAdapter(dsSachMoiAdapter);
        hienthiDSSachMoi.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hienthiDSSachMoi.setHasFixedSize(true);
    }

    @Override
    public void hienthidsSachKhuyenDoc(List<ItemBook> dsSachKhuyenDoc) {
        RecycleView_ItemBook_Adapter dsSachKhuyenDocAdapter = new RecycleView_ItemBook_Adapter(getContext(), dsSachKhuyenDoc);
        hienthiDSSachKhuyenDoc.setAdapter(dsSachKhuyenDocAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        hienthiDSSachKhuyenDoc.setLayoutManager(layoutManager);
        hienthiDSSachKhuyenDoc.setHasFixedSize(true);
    }

    @Override
    public void hienthidsSachVanHocTrongNuoc(List<ItemBook> dsSachVanHocTrongNuoc) {
        RecycleView_ItemBook_Adapter dsSachVanHocTrongNuocAdapter = new RecycleView_ItemBook_Adapter(getContext(), dsSachVanHocTrongNuoc);
        hienthiDSSachVanHocTrongNuoc.setAdapter(dsSachVanHocTrongNuocAdapter);
        hienthiDSSachVanHocTrongNuoc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hienthiDSSachVanHocTrongNuoc.setHasFixedSize(true);
    }

    @Override
    public void hienthidsNhaXuatBan(List<NXB> dsNXB) {
        RecycleView_NXB_Adapter dsNXBAdapter = new RecycleView_NXB_Adapter(getContext(), dsNXB);
        hienthiDSNhaXuatBan.setAdapter(dsNXBAdapter);
        hienthiDSNhaXuatBan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hienthiDSNhaXuatBan.setHasFixedSize(true);
    }

    @Override
    public void hienthiAlbumSach(List<AlbumBook> albumBooks) {
        albumBook = albumBooks;
        ListAlbum_Adapter adapter = new ListAlbum_Adapter(albumBook,getContext());
        pager_album.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.xemtatca_sachmoi:
                intent = new Intent(getContext(), ListBookToChoice.class);
                startActivity(intent);
                break;
            case R.id.xemtatca_vanhoctrongnuoc:
                intent = new Intent(getContext(), ListBookToChoice.class);
                startActivity(intent);
                break;
            case R.id.search_book:
                 intent = new Intent(getContext(), SearchBook.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getActivity(),
                        search,"sharebutton");
                getActivity().startActivity(intent,options.toBundle());
                break;
        }
    }

    class TimeWork extends TimerTask {
        int sizesliderList;

        public TimeWork(int sizesliderList) {
            this.sizesliderList = sizesliderList;
        }

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int currentpage = slider.getCurrentItem();
                    if (currentpage < sizesliderList - 1) {
                        slider.setCurrentItem(currentpage + 1);
                    } else slider.setCurrentItem(0);
                }
            });
        }
    }

}

