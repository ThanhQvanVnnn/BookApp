package com.phungthanhquan.bookapp.View.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.phungthanhquan.bookapp.R;
import com.phungthanhquan.bookapp.View.Fragment.FrgCaNhan;
import com.phungthanhquan.bookapp.View.Fragment.FrgDanhMuc;
import com.phungthanhquan.bookapp.View.Fragment.FrgHoatDong;
import com.phungthanhquan.bookapp.View.Fragment.FrgTrangChu;
import com.phungthanhquan.bookapp.View.Fragment.FrgTuSach;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private Fragment FrgTrangChu = new FrgTrangChu();
    private Fragment FrgDanhMuc = new FrgDanhMuc();
    private Fragment FrgTuSach = new FrgTuSach();
    private Fragment FrgHoatDong = new FrgHoatDong();
    private Fragment FrgCaNhan = new FrgCaNhan();
    private Fragment FrgActive = FrgTrangChu;

    private int back = 1 ;

    final FragmentManager fragmentManager =getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitControls();

    }

    private void InitControls() {
        navigationView = findViewById(R.id.navigation);

        fragmentManager.beginTransaction().add(R.id.frame_container,FrgCaNhan,"5").hide(FrgCaNhan).commit();
        fragmentManager.beginTransaction().add(R.id.frame_container,FrgHoatDong,"4").hide(FrgHoatDong).commit();
        fragmentManager.beginTransaction().add(R.id.frame_container,FrgTuSach,"3").hide(FrgTuSach).commit();
        fragmentManager.beginTransaction().add(R.id.frame_container,FrgDanhMuc,"2").hide(FrgDanhMuc).commit();
        fragmentManager.beginTransaction().add(R.id.frame_container,FrgTrangChu,"1").commit();

        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }
    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.menu_trangchu:
                    back = 1;
                    fragmentManager.beginTransaction().hide(FrgActive).show(FrgTrangChu).commit();
                    FrgActive = FrgTrangChu;
                    return true;
                case R.id.menu_danhmuc:
                    back = 1;
                    fragmentManager.beginTransaction().hide(FrgActive).show(FrgDanhMuc).commit();
                    FrgActive = FrgDanhMuc;
                    return true;
                case R.id.menu_tusach:
                    back = 1;
                    fragmentManager.beginTransaction().hide(FrgActive).show(FrgTuSach).commit();
                    FrgActive = FrgTuSach;
                    return true;
                case R.id.menu_hoatdong:
                    back = 1;
                    fragmentManager.beginTransaction().hide(FrgActive).show(FrgHoatDong).commit();
                    FrgActive = FrgHoatDong;
                    return true;
                case R.id.menu_canhan:
                    back = 1;
                    fragmentManager.beginTransaction().hide(FrgActive).show(FrgCaNhan).commit();
                    FrgActive = FrgCaNhan;
                    return true;
            }
            return false;
        }
    };



    @Override
    public void onBackPressed() {
        if (back == 1) {
            Toast.makeText(getApplicationContext(), R.string.nhan_back,
                    Toast.LENGTH_SHORT).show();
        } else if (back > 1) {
            finish();
        }
        back++;
    }
}
