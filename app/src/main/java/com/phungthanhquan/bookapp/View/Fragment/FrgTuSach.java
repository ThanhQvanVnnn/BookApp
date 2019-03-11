package com.phungthanhquan.bookapp.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.phungthanhquan.bookapp.Adapter.Tusach_Adapter;
import com.phungthanhquan.bookapp.Object.ItemBookCase;
import com.phungthanhquan.bookapp.Presenter.Fragment.PresenterLogicTuSach;
import com.phungthanhquan.bookapp.R;
import com.phungthanhquan.bookapp.View.InterfaceView.InterfaceViewFragmentTuSach;

import java.util.ArrayList;
import java.util.List;

public class FrgTuSach extends Fragment implements InterfaceViewFragmentTuSach {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private Tusach_Adapter tusach_adapter;
    private List<ItemBookCase> itemBookCaseList;
    private PresenterLogicTuSach presenterLogicTuSach;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tusach,container,false);
        initControls(view);
        return view;
    }

    private void initControls(View view) {
        recyclerView = view.findViewById(R.id.recycle_tusach);
        swipeRefreshLayout = view.findViewById(R.id.refresh_tusach);
        itemBookCaseList = new ArrayList<>();
        tusach_adapter = new Tusach_Adapter(getContext(),itemBookCaseList);
        recyclerView.setAdapter(tusach_adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        presenterLogicTuSach = new PresenterLogicTuSach(this);
        presenterLogicTuSach.xulihienthiDSCuaTuSach();
    }

    @Override
    public void hienthiDsSach(List<ItemBookCase> itemBookCases) {
        itemBookCaseList.addAll(itemBookCases);
        tusach_adapter.notifyDataSetChanged();
        Toast.makeText(getContext(), itemBookCases.size()+"", Toast.LENGTH_SHORT).show();
    }
}
