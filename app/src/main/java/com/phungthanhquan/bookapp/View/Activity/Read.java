package com.phungthanhquan.bookapp.View.Activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.phungthanhquan.bookapp.Object.BookRead;
import com.phungthanhquan.bookapp.R;

public class Read extends AppCompatActivity implements View.OnClickListener {

    private PDFView pdfView;
    private Boolean isUp;
    private LinearLayout bottom, header;
    private Toolbar toolbar;
    private SeekBar seekBar;
    private TextView tenSach;
    private TextView phanTramDoc;
    private TextView trangHienTai;
    private TextView tenChuong;
    private ImageButton next, previous;
    private BookRead bookRead;
    Boolean isNightMode, IsVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        initControls();
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        pdfView.fromAsset("clean_code.pdf")
                .enableSwipe(false) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(false)
                .defaultPage(0)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .enableAntialiasing(false) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .autoSpacing(true) // add dynamic spacing to fit each page on its own on the screen
                .pageSnap(true) // snap pages to screen boundaries
                .pageFling(true) // make a fling change only a single page like ViewPager
                .nightMode(false) // toggle night mode
                .load();
        bottom.setVisibility(View.INVISIBLE);
        header.setVisibility(View.INVISIBLE);
        pdfView.setOnClickListener(this);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);
    }

    private void initControls() {
        pdfView = findViewById(R.id.pdfView);
        bottom = findViewById(R.id.tool_bottom);
        header = findViewById(R.id.tool_header);
        toolbar = findViewById(R.id.toolbar);
        next = findViewById(R.id.imageNext);
        previous = findViewById(R.id.imagePreVious);
        tenSach = findViewById(R.id.tensach);
        tenChuong = findViewById(R.id.tenChuong);
        seekBar = findViewById(R.id.seekBar);
        trangHienTai = findViewById(R.id.trang);
        phanTramDoc = findViewById(R.id.phantram);
        isUp = false;
        isNightMode = true;
        IsVertical = true;
    }


    public void slideHideHeader(View view) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,  // fromYDelta
                -view.getHeight());                // toYDelta
        animate.setDuration(100);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    public void slideShowHeader(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                -view.getHeight(),                 // fromYDelta
                0); // toYDelta
        animate.setDuration(100);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }


    public void slideUpBottom(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(100);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    public void slideDownBottom(View view) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(100);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    @Override
    public void onClick(View v) {
        int currentPage = pdfView.getCurrentPage();
        switch (v.getId()) {
            case R.id.pdfView:
                if (isUp) {
                    slideDownBottom(bottom);
                    slideHideHeader(header);
                } else {
                    slideShowHeader(header);
                    slideUpBottom(bottom);
                }
                isUp = !isUp;
                break;
            case R.id.imageNext:
                if(currentPage!=pdfView.getPageCount()){
                    currentPage++;
                    pdfView.jumpTo(currentPage,true);
                }
                break;
            case R.id.imagePreVious:
                if(currentPage!=0){
                    currentPage--;
                    pdfView.jumpTo(currentPage,true);
                }
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_read, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nightmode:
                if (isNightMode) {
                    pdfView.setNightMode(isNightMode);
                    item.setIcon(R.drawable.ic_nightmode_24dp);
                    next.setImageResource(R.drawable.next_white_24);
                    previous.setImageResource(R.drawable.previous_white_24);

                    pdfView.loadPages();
                } else {
                    pdfView.setNightMode(isNightMode);
                    item.setIcon(R.drawable.ic_sunny_24dp);
                    next.setImageResource(R.drawable.next_black_24);
                    previous.setImageResource(R.drawable.previous_black_24);
                    pdfView.loadPages();
                }
                isNightMode = !isNightMode;
                break;
            case R.id.oritaintion:
                int currentPage = pdfView.getCurrentPage();
                pdfView.setSwipeVertical(IsVertical);
                pdfView.loadPages();
                pdfView.jumpTo(currentPage, true);
                IsVertical = !IsVertical;
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
