package com.phungthanhquan.bookapp.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.phungthanhquan.bookapp.R;

public class Read extends AppCompatActivity implements View.OnClickListener {

    private PDFView pdfView;
    private Boolean isUp;
    private LinearLayout bottom, header;
    private Toolbar toolbar;
    Boolean isNightMode,IsVertical;
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
                // allows to draw something on the current page, usually visible in the middle of the screen
//                .onDraw(onDrawListener)
//                // allows to draw something on all pages, separately for every page. Called only for visible pages
//                .onDrawAll(onDrawListener)
//                .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
//                .onPageChange(onPageChangeListener)
//                .onPageScroll(onPageScrollListener)
//                .onError(onErrorListener)
//                .onPageError(onPageErrorListener)
//                .onRender(onRenderListener) // called after document is rendered for the first time
//                // called on single tap, return true if handled, false to toggle scroll handle visibility
//                .onTap(onTapListener)
//                .onLongPress(onLongPressListener)
                .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(false) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .autoSpacing(true) // add dynamic spacing to fit each page on its own on the screen
//                .linkHandler(DefaultLinkHandler)
//                .pageFitPolicy(FitPolicy.WIDTH)
                .pageSnap(true) // snap pages to screen boundaries
                .pageFling(true) // make a fling change only a single page like ViewPager
                .nightMode(false) // toggle night mode
                .load();
        bottom.setVisibility(View.INVISIBLE);
        header.setVisibility(View.INVISIBLE);
        pdfView.setOnClickListener(this);

    }

    private void initControls() {
        pdfView = findViewById(R.id.pdfView);
        bottom = findViewById(R.id.tool_bottom);
        header = findViewById(R.id.tool_header);
        toolbar = findViewById(R.id.toolbar);
        isUp = false;
        isNightMode = true;
        IsVertical = true;
    }


    public void slideHideHeader(View view) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
              0  ,  // fromYDelta
                - view.getHeight());                // toYDelta
        animate.setDuration(100);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    public void slideShowHeader(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                - view.getHeight(),                 // fromYDelta
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
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_read,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nightmode:
                if(isNightMode) {
                    pdfView.setNightMode(isNightMode);
                    item.setIcon(R.drawable.ic_nightmode_24dp);
                   pdfView.loadPages();
                }
                else {
                    pdfView.setNightMode(isNightMode);
                    item.setIcon(R.drawable.ic_sunny_24dp);
                    pdfView.loadPages();
                }
                isNightMode = !isNightMode;
                break;
            case R.id.oritaintion:
                int currentPage = pdfView.getCurrentPage();
                pdfView.setSwipeVertical(IsVertical);
                pdfView.loadPages();
                pdfView.jumpTo(currentPage,true);
                IsVertical = !IsVertical;
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
