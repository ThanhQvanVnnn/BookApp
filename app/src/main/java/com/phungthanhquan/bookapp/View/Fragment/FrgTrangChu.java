package com.phungthanhquan.bookapp.View.Fragment;


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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.phungthanhquan.bookapp.View.Activity.MarketingChiTiet;
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
    private RecycleView_ItemBook_Adapter adapterSachKhuyenDoc;
    private RecyclerView.LayoutManager layoutManagerSachKhuyenDoc;
    private RecycleView_ItemBook_Adapter adapterSachMoi;
    private  Timer timer;
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
    public void hienthislider(final List<Slider> sliderListReturn) {
        sliderList.addAll(sliderListReturn);
        slider_Adapter.notifyDataSetChanged();
        indicator.setupWithViewPager(slider, true);
        int sizesliderList = sliderList.size();
        timer = new Timer();
        timer.scheduleAtFixedRate(new FrgTrangChu.TimeWork(sizesliderList), 4000, 6000);
//        new Thread() {
//            @Override
//            public void run() {
//                //If there are stories, add them to the table
//                sliderList.addAll(sliderListReturn);
//                try {
//                    // code runs in a thread
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            slider_Adapter.notifyDataSetChanged();
//                            indicator.setupWithViewPager(slider, true);
//                            int sizesliderList = sliderList.size();
//                            timer = new Timer();
//                            timer.scheduleAtFixedRate(new FrgTrangChu.TimeWork(sizesliderList), 4000, 6000);
//                        }
//                    });
//                } catch (final Exception ex) {
//                }
//            }
//        }.start();


    }

    @Override
    public void hienthidsSachmoi(final List<ItemBook> dsSachMoi) {
        new Thread() {
            @Override
            public void run() {
                //If there are stories, add them to the table
                danhSachSachMoi.addAll(dsSachMoi);
                try {
                    // code runs in a thread
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapterSachMoi.notifyDataSetChanged();
                        }
                    });
                } catch (final Exception ex) {
                }
            }
        }.start();


    }

    @Override
    public void hienthidsSachKhuyenDoc(final List<ItemBook> dsSachKhuyenDoc) {
        new Thread() {
            @Override
            public void run() {
                //If there are stories, add them to the table
                danhSachKhuyenDoc.addAll(dsSachKhuyenDoc);
                try {
                    // code runs in a thread
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapterSachKhuyenDoc.notifyDataSetChanged();
                        }
                    });
                } catch (final Exception ex) {
                }
            }
        }.start();


    }

    @Override
    public void hienthidsSachVanHocTrongNuoc(final List<ItemBook> dsSachVanHocTrongNuoc) {
        new Thread() {
            @Override
            public void run() {
                //If there are stories, add them to the table
                danhSachVanHocTrongNuoc.addAll(dsSachVanHocTrongNuoc);
                try {
                    // code runs in a thread
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapterVanHocTrongNuoc.notifyDataSetChanged();
                        }
                    });
                } catch (final Exception ex) {
                }
            }
        }.start();


    }

    @Override
    public void hienthidsNhaXuatBan(final List<NXB> dsNXB) {
        new Thread() {
            @Override
            public void run() {
                //If there are stories, add them to the table
                danhSachNXB.addAll(dsNXB);
                try {
                    // code runs in a thread
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapterNXB.notifyDataSetChanged();
                        }
                    });
                } catch (final Exception ex) {
                }
            }
        }.start();


    }

    @Override
    public void hienthiAlbumSach(final List<AlbumBook> albumBooks) {
        albumBook.addAll(albumBooks);
        adapterAlbum.notifyDataSetChanged();
//        new Thread() {
//            @Override
//            public void run() {
//                //If there are stories, add them to the table
//                albumBook.addAll(albumBooks);
//                try {
//                    // code runs in a thread
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            adapterAlbum.notifyDataSetChanged();
//                        }
//                    });
//                } catch (final Exception ex) {
//                }
//            }
//        }.start();


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.xemtatca_sachmoi:
                intent = new Intent(getContext(), MarketingChiTiet.class);
                intent.putExtra("Title","Sách mới");
                startActivity(intent);
                break;
            case R.id.xemtatca_vanhoctrongnuoc:
                intent = new Intent(getContext(), MarketingChiTiet.class);
                intent.putExtra("Title","Văn học trong nước");
                startActivity(intent);
                break;
            case R.id.search_book:
                intent = new Intent(getContext(), SearchBook.class);
                getActivity().startActivity(intent);
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
        adapterAlbum = new ListAlbum_Adapter(albumBook, getContext());
        adapterNXB = new RecycleView_NXB_Adapter(getContext(), danhSachNXB);
        adapterVanHocTrongNuoc = new RecycleView_ItemBook_Adapter(getContext(), danhSachVanHocTrongNuoc, 0);
        adapterSachKhuyenDoc = new RecycleView_ItemBook_Adapter(getContext(), danhSachKhuyenDoc, 0);
        adapterSachMoi = new RecycleView_ItemBook_Adapter(getContext(), danhSachSachMoi, 0);

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
        presenterFragmentTrangChu.xuliHienThiAlBumSach();
        presenterFragmentTrangChu.xuliHienthiDsSachVanHocTrongNuoc();
        presenterFragmentTrangChu.xuliHienThiDsNhaXuatBan();
        presenterFragmentTrangChu.xuliHienthiDsSachKhuyenDoc();
//       final Thread threadSlider = new Thread(new Runnable() {
//           @Override
//           public void run() {
//               presenterFragmentTrangChu.xulislider();
//               int sizesliderList = sliderList.size();
//               Timer timer = new Timer();
//               timer.scheduleAtFixedRate(new FrgTrangChu.TimeWork(sizesliderList), 4000, 6000);
//               indicator.setupWithViewPager(slider, true);
//           }
//       });
//       threadSlider.start();
//        final Thread threadDsSachMoi = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    threadSlider.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                presenterFragmentTrangChu.xuliHienthiDsSachMoi();
//            }
//        });
//        threadDsSachMoi.start();
//        final Thread threadDsAlBumBook = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    threadDsSachMoi.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                presenterFragmentTrangChu.xuliHienThiAlBumSach();
//            }
//        });
//        threadDsAlBumBook.start();
//        final Thread threadDsVanHocTrongNuoc = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    threadDsAlBumBook.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                presenterFragmentTrangChu.xuliHienthiDsSachVanHocTrongNuoc();
//            }
//        });
//        threadDsVanHocTrongNuoc.start();
//        final Thread threadNhaXuatBan = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    threadDsVanHocTrongNuoc.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                presenterFragmentTrangChu.xuliHienThiDsNhaXuatBan();
//            }
//        });
//        threadNhaXuatBan.start();
//        final Thread threadKhuyenDoc = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    threadNhaXuatBan.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                presenterFragmentTrangChu.xuliHienthiDsSachKhuyenDoc();
//            }
//        });
//        threadKhuyenDoc.start();
//        Thread threadOnScroll = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    threadKhuyenDoc.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                OnsCroll();
//            }
//        });
//        threadOnScroll.start();
    }

    public void RefresherLayout() {

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
                        timer.cancel();
                        sliderList.clear();
                        albumBook.clear();
                        danhSachNXB.clear();
                        danhSachVanHocTrongNuoc.clear();
                        danhSachKhuyenDoc.clear();
                        danhSachSachMoi.clear();
                        ActivePresenter();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
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

    public void OnsCroll() {
        if (nestedScrollView != null) {

            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                    String TAG = "nested_sync";
                    final int sizeListSachKhuyenDoc = danhSachKhuyenDoc.size();

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
                        progressBarLoadMoreKhuyenDoc.setVisibility(View.VISIBLE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                List<ItemBook> dsSachLayVe = new ArrayList<>();
                                dsSachLayVe = presenterFragmentTrangChu.xuliHienThiDsKhuyenDocLoadMore(sizeListSachKhuyenDoc,
                                        progressBarLoadMoreKhuyenDoc, hienthiDSSachKhuyenDoc);
                                if (dsSachLayVe.size() != 0) //check for scroll down
                                {
                                    danhSachKhuyenDoc.addAll(dsSachLayVe);
                                    adapterSachKhuyenDoc.notifyDataSetChanged();
                                }

                            }
                        }, 1000);
//                        Log.i(TAG, "BOTTOM SCROLL");


                    }
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}

