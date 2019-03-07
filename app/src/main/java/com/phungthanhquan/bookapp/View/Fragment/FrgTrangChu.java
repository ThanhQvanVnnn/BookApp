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
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.phungthanhquan.bookapp.Adapter.ListAlbum_Adapter;
import com.phungthanhquan.bookapp.Adapter.RecycleView_ItemBook_Adapter;
import com.phungthanhquan.bookapp.Adapter.RecycleView_NXB_Adapter;
import com.phungthanhquan.bookapp.Adapter.ViewPager_Slider_Adapter;
import com.phungthanhquan.bookapp.Model.LoadMore.InterfaceLoadMore;
import com.phungthanhquan.bookapp.Model.LoadMore.LoadMoreScroll;
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

    private TabLayout indicator;
    private ViewPager slider;
    private RecyclerView hienthiDSSachMoi;
    private RecyclerView hienthiDSSachKhuyenDoc;
    private RecyclerView hienthiDSSachVanHocTrongNuoc;
    private RecyclerView hienthiDSNhaXuatBan;
    private TextView allSachMoi;
    private TextView allSachVanHocTrongNuoc;
    private ImageButton search;
    private ProgressBar progressBarLoadMoreKhuyenDoc;
    private NestedScrollView nestedScrollView;


    private SwipeRefreshLayout swipeRefreshLayout;
    private PresenterFragmentTrangChu presenterFragmentTrangChu;


    private List<Slider> sliderList;
    private List<AlbumBook> albumBook;
    private List<NXB> danhSachNXB;
    private List<ItemBook> danhSachVanHocTrongNuoc;
    private List<ItemBook> danhSachKhuyenDoc;
    private List<ItemBook> danhSachSachMoi;

    private ListAlbum_Adapter adapterAlbum;
    private ViewPager_Slider_Adapter slider_Adapter;
    private HorizontalInfiniteCycleViewPager pager_album;
    private RecycleView_NXB_Adapter adapterNXB;
    private RecycleView_ItemBook_Adapter adapterVanHocTrongNuoc;
    private  RecycleView_ItemBook_Adapter adapterSachKhuyenDoc;
    private  RecyclerView.LayoutManager layoutManagerSachKhuyenDoc;
    private   RecycleView_ItemBook_Adapter adapterSachMoi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        InitControls(view);
        CreateAdapterAddView();
        ActivePresenter();
        RefresherLayout();
        OnsCroll();
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
        progressBarLoadMoreKhuyenDoc = view.findViewById(R.id.progressLoadMoreKhuyenDoc);
        nestedScrollView = view.findViewById(R.id.scroll_trangchu);
        //onclick
        allSachMoi.setOnClickListener(this);
        allSachVanHocTrongNuoc.setOnClickListener(this);
        search.setOnClickListener(this);
        //presenter logic
        presenterFragmentTrangChu = new PresenterFragmentTrangChu(this);
    }


    @Override
    public void hienthislider(List<Slider> sliderListReturn) {
        sliderList.addAll(sliderListReturn);
        slider_Adapter.notifyDataSetChanged();
    }

    @Override
    public void hienthidsSachmoi(List<ItemBook> dsSachMoi) {
        danhSachSachMoi.addAll(dsSachMoi);
        adapterSachMoi.notifyDataSetChanged();
    }

    @Override
    public void hienthidsSachKhuyenDoc(List<ItemBook> dsSachKhuyenDoc) {
        danhSachKhuyenDoc.addAll(dsSachKhuyenDoc);
        adapterSachKhuyenDoc.notifyDataSetChanged();
    }

    @Override
    public void hienthidsSachVanHocTrongNuoc(List<ItemBook> dsSachVanHocTrongNuoc) {
        danhSachVanHocTrongNuoc.addAll(dsSachVanHocTrongNuoc);
        adapterVanHocTrongNuoc.notifyDataSetChanged();
    }

    @Override
    public void hienthidsNhaXuatBan(List<NXB> dsNXB) {
        danhSachNXB.addAll(dsNXB);
        adapterNXB.notifyDataSetChanged();
    }

    @Override
    public void hienthiAlbumSach(List<AlbumBook> albumBooks) {
        albumBook.addAll(albumBooks);
        adapterAlbum.notifyDataSetChanged();
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
                ActivityOptions options = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation((Activity) getActivity(),
                            search,"sharebutton");
                }
                getActivity().startActivity(intent,options.toBundle());
                break;
        }
    }

    private void CreateAdapterAddView() {
        sliderList = new ArrayList<>();
        albumBook = new ArrayList<>();
        danhSachNXB = new ArrayList<>();
        danhSachVanHocTrongNuoc = new ArrayList<>();
        danhSachKhuyenDoc = new ArrayList<>();
        danhSachSachMoi = new ArrayList<>();

        slider_Adapter = new ViewPager_Slider_Adapter(getContext(), sliderList);
        adapterAlbum = new ListAlbum_Adapter(albumBook,getContext());
        adapterNXB = new RecycleView_NXB_Adapter(getContext(), danhSachNXB);
        adapterVanHocTrongNuoc = new RecycleView_ItemBook_Adapter(getContext(), danhSachVanHocTrongNuoc);
        adapterSachKhuyenDoc = new RecycleView_ItemBook_Adapter(getContext(), danhSachKhuyenDoc);
        adapterSachMoi = new RecycleView_ItemBook_Adapter(getContext(), danhSachSachMoi);

        //slider
        slider.setAdapter(slider_Adapter);
        //album
        pager_album.setAdapter(adapterAlbum);
        //NXB
        hienthiDSNhaXuatBan.setAdapter(adapterNXB);
        hienthiDSNhaXuatBan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hienthiDSNhaXuatBan.setHasFixedSize(true);
        //văn học trong nước
        hienthiDSSachVanHocTrongNuoc.setAdapter(adapterVanHocTrongNuoc);
        hienthiDSSachVanHocTrongNuoc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hienthiDSSachVanHocTrongNuoc.setHasFixedSize(true);
        //khuyên đọc
        hienthiDSSachKhuyenDoc.setAdapter(adapterSachKhuyenDoc);
        layoutManagerSachKhuyenDoc = new GridLayoutManager(getContext(), 3);
        hienthiDSSachKhuyenDoc.setLayoutManager(layoutManagerSachKhuyenDoc);
        hienthiDSSachKhuyenDoc.setHasFixedSize(true);
        //sách mới
        adapterSachMoi.setHasStableIds(true);
        hienthiDSSachMoi.setAdapter(adapterSachMoi);
        hienthiDSSachMoi.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hienthiDSSachMoi.setHasFixedSize(true);

    }

    private void ActivePresenter() {
        presenterFragmentTrangChu.xulislider();
        presenterFragmentTrangChu.xuliHienthiDsSachMoi();
        presenterFragmentTrangChu.xuliHienthiDsSachKhuyenDoc();
        presenterFragmentTrangChu.xuliHienthiDsSachVanHocTrongNuoc();
        presenterFragmentTrangChu.xuliHienThiDsNhaXuatBan();
        presenterFragmentTrangChu.xuliHienThiAlBumSach();
        int sizesliderList = sliderList.size();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new FrgTrangChu.TimeWork(sizesliderList), 4000, 6000);
        indicator.setupWithViewPager(slider, true);
    }

    public void RefresherLayout(){

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_dark)
                ,getResources().getColor(android.R.color.holo_blue_light)
                ,getResources().getColor(android.R.color.holo_orange_light));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sliderList.clear();
                        albumBook.clear();
                        danhSachNXB.clear();
                        danhSachVanHocTrongNuoc.clear();
                        danhSachKhuyenDoc.clear();
                        danhSachSachMoi.clear();
                        ActivePresenter();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }});
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

    public void OnsCroll(){
        if (nestedScrollView != null) {

            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                    String TAG = "nested_sync";
                    int sizeListSachKhuyenDoc = danhSachKhuyenDoc.size();
                    List<ItemBook> dsSachLayVe = new ArrayList<>();
                    if (scrollY > oldScrollY) {
//                        Log.i(TAG, "Scroll DOWN");
                    }
                    if (scrollY < oldScrollY) {
//                        Log.i(TAG, "Scroll UP");
                    }

                    if (scrollY == 0) {
//                        Log.i(TAG, "TOP SCROLL");
                    }

                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                        hienthiDSSachKhuyenDoc.setNestedScrollingEnabled(false);
//                        Log.i(TAG, "BOTTOM SCROLL");
                        progressBarLoadMoreKhuyenDoc.setVisibility(View.VISIBLE);
                        dsSachLayVe = presenterFragmentTrangChu.xuliHienThiDsKhuyenDocLoadMore(sizeListSachKhuyenDoc,
                                progressBarLoadMoreKhuyenDoc,hienthiDSSachKhuyenDoc);
                        if (dsSachLayVe.size()!=0) //check for scroll down
                        {
                            danhSachKhuyenDoc.addAll(dsSachLayVe);
                            adapterSachKhuyenDoc.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }
}

