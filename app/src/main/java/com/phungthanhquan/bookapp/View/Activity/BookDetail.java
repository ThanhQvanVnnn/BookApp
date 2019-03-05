package com.phungthanhquan.bookapp.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.phungthanhquan.bookapp.Object.Book;
import com.phungthanhquan.bookapp.Presenter.Activity.PresenterBookDetail;
import com.phungthanhquan.bookapp.R;
import com.phungthanhquan.bookapp.View.InterfaceView.InterfaceViewActivityDetailBook;
import com.squareup.picasso.Picasso;

public class BookDetail extends AppCompatActivity implements InterfaceViewActivityDetailBook {
    private ImageView detailbook_image;
    private Toolbar toolbar;
    private TextView tenSach;
    private TextView thongTinSach;
    private RatingBar ratingSach;
    private TextView soluongdanhgia;
    private Button muaSach;
    private Button thueSach;
    private ExpandableTextView noidungSach;
    private Button chiaSeCamNhan;
    private PresenterBookDetail presenterBookDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        InitControls();
    }

    private void InitControls() {
        detailbook_image = findViewById(R.id.detail_book_image);
        tenSach = findViewById(R.id.textView_tenbook);
        thongTinSach = findViewById(R.id.textView_thongtinSach);
        ratingSach = findViewById(R.id.raiting_tong);
        soluongdanhgia = findViewById(R.id.soluong_danhgia);
        muaSach = findViewById(R.id.button_muasach);
        thueSach = findViewById(R.id.button_thuesach);
        noidungSach = findViewById(R.id.expand_text_view);
        noidungSach.setText("");
        chiaSeCamNhan = findViewById(R.id.button_chiasecamnhan);
        Intent intent = getIntent();
//        String idSach = intent.getStringExtra("iD");
//        String urlImage = getIntent().getStringExtra("image");
        toolbar = findViewById(R.id.toolbar_bookDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        presenterBookDetail = new PresenterBookDetail(this);

        presenterBookDetail.xuliHienThiSach();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void hienThiNoiDungSach(Book book) {
        Picasso.get().load(book.getHinhanh_sach()).into(detailbook_image);
        tenSach.setText(book.getTen_sach());
        noidungSach.setText(book.getNoidung_sach());
        ratingSach.setRating(book.getSosao_danhgia());
        soluongdanhgia.setText(book.getSoluong_danhgia()+" đánh giá");
        thongTinSach.setText("Tên tác giả :" +book.getTen_tacgia()+"\n" +"\n" +
                              "NXB :" + book.getNXB()+"\n"+"\n" +"Ngày phát hành: "+book.getNgayphathanh()+ "\n"+"\n" +
                              "Số Trang :"+ book.getSo_trang());
    }
}
