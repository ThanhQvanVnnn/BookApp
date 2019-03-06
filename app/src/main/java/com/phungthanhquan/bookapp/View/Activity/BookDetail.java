package com.phungthanhquan.bookapp.View.Activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.phungthanhquan.bookapp.Adapter.RecycleView_noidungbinhluan_Adapter;
import com.phungthanhquan.bookapp.Object.BinhLuan;
import com.phungthanhquan.bookapp.Object.Book;
import com.phungthanhquan.bookapp.Presenter.Activity.PresenterBookDetail;
import com.phungthanhquan.bookapp.R;
import com.phungthanhquan.bookapp.View.InterfaceView.InterfaceViewActivityDetailBook;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public class BookDetail extends AppCompatActivity implements InterfaceViewActivityDetailBook, View.OnClickListener {
    private ImageView detailbook_image;
    private Toolbar toolbar;
    private TextView tenSach;
    private RatingBar ratingSach;
    private TextView soluongdanhgia;
    private TextView tentacgia;
    private TextView nhaxuatban;
    private TextView ngayphathanh;
    private TextView sotrang;
    private TextView giatien;
    private TextView menhgia;
    private Button docsach;
    private ExpandableTextView noidungSach;
    private Button chiaSeCamNhan;
    private PresenterBookDetail presenterBookDetail;
    private RecyclerView recycle_DsDanhGia;
    private List<BinhLuan> dsBinhLuan;
    private Dialog dialogCamNhan;
    private LinearLayout xemThemDanhGia;
    RecycleView_noidungbinhluan_Adapter recycleView_noidungbinhluan_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        InitControls();
        chiaSeCamNhan.setOnClickListener(this);
        xemThemDanhGia.setOnClickListener(this);
    }

    private void InitControls() {
        detailbook_image = findViewById(R.id.image_detailbook);
        tenSach = findViewById(R.id.textview_tensach);
        ratingSach = findViewById(R.id.raiting_tong);
        soluongdanhgia = findViewById(R.id.textview_soluongdanhgia);
        docsach = findViewById(R.id.button_docsach);
        noidungSach = findViewById(R.id.expand_text_view);
        tentacgia = findViewById(R.id.texview_tentacgia);
        ngayphathanh = findViewById(R.id.textview_ngayphathanh);
        nhaxuatban = findViewById(R.id.textview_tenNXB);
        sotrang = findViewById(R.id.textview_sotrang);
        giatien = findViewById(R.id.textview_giatien);
        menhgia = findViewById(R.id.textview_menhgia);
        chiaSeCamNhan = findViewById(R.id.button_chiasecamnhan);
        recycle_DsDanhGia = findViewById(R.id.recycle_danhsachdanhgia);
        xemThemDanhGia = findViewById(R.id.xemthemdanhgia);
        Intent intent = getIntent();
//        String idSach = intent.getStringExtra("iD");
//        String urlImage = getIntent().getStringExtra("image");
        toolbar = findViewById(R.id.toolbar_bookDetail);
        toolbar.setTitle(R.string.chi_tiet_sach);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenterBookDetail = new PresenterBookDetail(this);

        presenterBookDetail.xuliHienThiSach();
        presenterBookDetail.xuliHienThiDsDanhGia();
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
        tentacgia.setText(book.getTen_tacgia());
        nhaxuatban.setText(book.getNXB());
        ngayphathanh.setText(book.getNgayphathanh());
        sotrang.setText(book.getSo_trang()+"");
        DecimalFormat df = new DecimalFormat("#,##0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
        String giatien_format = df.format(book.getGiatien_sach());
        giatien.setText(giatien_format+"");
        menhgia.setPaintFlags(menhgia.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void hienThiDsDanhGia(List<BinhLuan> dsDanhGia) {
        dsBinhLuan = dsDanhGia;
        recycleView_noidungbinhluan_adapter = new RecycleView_noidungbinhluan_Adapter(this,dsBinhLuan);
        recycle_DsDanhGia.setAdapter(recycleView_noidungbinhluan_adapter);
        recycle_DsDanhGia.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recycle_DsDanhGia.setHasFixedSize(false);
        recycle_DsDanhGia.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_chiasecamnhan:
                dialogCamNhan = new Dialog(this);
                dialogCamNhan.setContentView(R.layout.dialog_danhgia);
                TextView txtClose = dialogCamNhan.findViewById(R.id.textview_cancel);
                txtClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogCamNhan.dismiss();
                    }
                });
                dialogCamNhan.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogCamNhan.show();
                break;
            case R.id.xemthemdanhgia:
                Intent intent = new Intent(this,XemThemDanhGia.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                        chiaSeCamNhan,"chiasecamnhan");
                startActivity(intent,options.toBundle());
                break;
        }
    }
}
