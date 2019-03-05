package com.phungthanhquan.bookapp.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.phungthanhquan.bookapp.R;
import com.squareup.picasso.Picasso;

public class ListBookToChoice extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView listBookToChoice;
    private TextView title;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listbooktochoice);
        initControls();

    }

    private void initControls() {
        toolbar = findViewById(R.id.toolbar_listtochoice);
        listBookToChoice = findViewById(R.id.recycle_listallchoice);
        title = findViewById(R.id.listbooktochoice_Title);
        image = findViewById(R.id.image_listbooktochoice);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent =getIntent();
        title.setText(intent.getStringExtra("title"));
        Picasso.get().load(intent.getStringExtra("image")).into(image);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
