package com.phungthanhquan.bookapp.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.phungthanhquan.bookapp.R;
import com.squareup.picasso.Picasso;

public class BookDetail extends AppCompatActivity {
    private ImageView detailbook_image;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        InitControls();
    }

    private void InitControls() {
        detailbook_image = findViewById(R.id.detail_book_image);
        String urlImage = getIntent().getStringExtra("image");
        Picasso.get().load(urlImage).into(detailbook_image);
        toolbar = findViewById(R.id.toolbar_bookDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
