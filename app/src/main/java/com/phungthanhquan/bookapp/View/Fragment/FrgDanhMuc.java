package com.phungthanhquan.bookapp.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phungthanhquan.bookapp.Adapter.DanhMuc_Adapter;
import com.phungthanhquan.bookapp.Object.DanhMuc;
import com.phungthanhquan.bookapp.Presenter.Fragment.PresenterInterfaceDanhMuc;
import com.phungthanhquan.bookapp.Presenter.Fragment.PresenterLogicDanhMuc;
import com.phungthanhquan.bookapp.R;
import com.phungthanhquan.bookapp.View.InterfaceView.InterfaceViewFragmentDanhMuc;

import java.util.ArrayList;
import java.util.List;

public class FrgDanhMuc extends Fragment implements InterfaceViewFragmentDanhMuc {

    private Toolbar toolbar;
    private RecyclerView recyclerView_dsSach;
    private List<DanhMuc> danhsachSach;
    private DanhMuc_Adapter danhMuc_adapter;
    private PresenterLogicDanhMuc presenterLogicDanhMuc;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danhmuc,container,false);
       initControls(view);
        return view;
    }

    private void initControls(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        recyclerView_dsSach = view.findViewById(R.id.recycle_danhmuc);
        danhsachSach = new ArrayList<>();
        danhMuc_adapter = new DanhMuc_Adapter(getContext(),danhsachSach);
        recyclerView_dsSach.setAdapter(danhMuc_adapter);
        recyclerView_dsSach.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        presenterLogicDanhMuc = new PresenterLogicDanhMuc(this);
        presenterLogicDanhMuc.xuliHienThiDanhMuc();
    }

    @Override
    public void hienThiDanhMuc(List<DanhMuc> danhMucList) {
        danhsachSach.addAll(danhMucList);
        danhMuc_adapter.notifyDataSetChanged();
    }
}
