package com.phungthanhquan.bookapp.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.phungthanhquan.bookapp.R;
import com.squareup.picasso.Picasso;

public class BookDetail extends AppCompatActivity {
    private ImageView detailbook_image;

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
    }
}
