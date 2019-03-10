package com.phungthanhquan.bookapp.View.Activity;

import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.phungthanhquan.bookapp.Adapter.RecycleView_ItemBook_Adapter;
import com.phungthanhquan.bookapp.Object.ItemBook;
import com.phungthanhquan.bookapp.Presenter.Activity.PresenterLogicListDanhMucTatCa;
import com.phungthanhquan.bookapp.R;
import com.phungthanhquan.bookapp.View.InterfaceView.InterfaceViewActivityListBookDanhMucTatCa;

import java.util.ArrayList;
import java.util.List;

public class ListBookDanhMucTatCa extends AppCompatActivity implements InterfaceViewActivityListBookDanhMucTatCa {

    private Toolbar toolbar;
    private RecyclerView recyclerView_ds;
    private RecycleView_ItemBook_Adapter recycleView_itemBook_adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ItemBook> itemBookList;
    private PresenterLogicListDanhMucTatCa presenterLogicListDanhMucTatCa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book_danh_muc_tat_ca);
        initControls();
        refresh();
    }

    private void initControls() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView_ds = findViewById(R.id.recycle_danhmucchitiet);
        swipeRefreshLayout = findViewById(R.id.refresh_danhmuc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        itemBookList = new ArrayList<>();
        recycleView_itemBook_adapter = new RecycleView_ItemBook_Adapter(this,itemBookList,3);
        recyclerView_ds.setAdapter(recycleView_itemBook_adapter);
        recyclerView_ds.setLayoutManager(new GridLayoutManager(this,2));
        presenterLogicListDanhMucTatCa = new PresenterLogicListDanhMucTatCa(this);
        presenterLogicListDanhMucTatCa.xuliHienThiChiTietDanhMuc();
    }

    @Override
    public void hienthiDanhSachChitiet(List<ItemBook> itemBooks) {
        itemBookList.addAll(itemBooks);
        recycleView_itemBook_adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    public void refresh(){
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_dark)
                , getResources().getColor(android.R.color.holo_blue_light)
                , getResources().getColor(android.R.color.holo_orange_light));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        itemBookList.clear();
                        presenterLogicListDanhMucTatCa.xuliHienThiChiTietDanhMuc();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }
}
