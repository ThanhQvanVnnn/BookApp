package com.phungthanhquan.bookapp.Presenter.Fragment;

import com.phungthanhquan.bookapp.Model.Fragment.ModelFragmentTuSach;
import com.phungthanhquan.bookapp.Object.ItemBookCase;
import com.phungthanhquan.bookapp.View.InterfaceView.InterfaceViewFragmentTuSach;

import java.util.List;

public class PresenterLogicTuSach implements PresenterInterfaceTuSach {
    private InterfaceViewFragmentTuSach interfaceViewFragmentTuSach;
    private ModelFragmentTuSach modelFragmentTuSach;

    public PresenterLogicTuSach(InterfaceViewFragmentTuSach interfaceViewFragmentTuSach) {
        this.interfaceViewFragmentTuSach = interfaceViewFragmentTuSach;
        modelFragmentTuSach = new ModelFragmentTuSach();
    }

    @Override
    public void xulihienthiDSCuaTuSach() {
        List<ItemBookCase> itemBookCases = modelFragmentTuSach.layDsSachTrongTuSach();
        if(itemBookCases.size()!=0){
            interfaceViewFragmentTuSach.hienthiDsSach(itemBookCases);
        }
    }
}
