package com.phungthanhquan.bookapp.Presenter.Activity;

import com.phungthanhquan.bookapp.Model.Activity.ModelActivityDetailBook;
import com.phungthanhquan.bookapp.Object.Book;
import com.phungthanhquan.bookapp.View.InterfaceView.InterfaceViewActivityDetailBook;

public class PresenterBookDetail implements InPresenterBookDetail {
    InterfaceViewActivityDetailBook interfaceViewActivityDetailBook;
    ModelActivityDetailBook modelActivityDetailBook;

    public PresenterBookDetail(InterfaceViewActivityDetailBook interfaceViewActivityDetailBook) {
        this.interfaceViewActivityDetailBook = interfaceViewActivityDetailBook;
        modelActivityDetailBook = new ModelActivityDetailBook();
    }


    @Override
    public void xuliHienThiSach() {
        Book book = modelActivityDetailBook.getBook();
        if(book!=null)
        interfaceViewActivityDetailBook.hienThiNoiDungSach(book);
    }
}
