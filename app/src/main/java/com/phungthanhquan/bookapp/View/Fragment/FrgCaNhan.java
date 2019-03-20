package com.phungthanhquan.bookapp.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phungthanhquan.bookapp.Object.User;
import com.phungthanhquan.bookapp.Presenter.Fragment.PresenterLogicCaNhan;
import com.phungthanhquan.bookapp.R;
import com.phungthanhquan.bookapp.View.Activity.Login;
import com.phungthanhquan.bookapp.View.InterfaceView.InterfaceViewFragmentCaNhan;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FrgCaNhan extends Fragment implements View.OnClickListener, InterfaceViewFragmentCaNhan {
    private ImageView anhdaidienBackGround;
    private CircleImageView anhdaidien;
    private TextView tenNguoidung;
    private TextView sotientrongTaiKhoan;
    private TextView tongsoSach;
    private LinearLayout doianhdaidien;
    private LinearLayout nguoitheodoi;
    private LinearLayout nguoidangtheodoi;
    private LinearLayout sachdadoc;
    private LinearLayout sachyeuthich;
    private LinearLayout naptaikhoan;
    private LinearLayout caidat;
    private LinearLayout dangxuat;
    private TextView songuoitheodoi;
    private TextView songuoidangtheodoi;
    private TextView sosachdadoc;
    private TextView sosachyeuthich;

    private PresenterLogicCaNhan presenterLogicCaNhan;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_canhan,container,false);
        initControls(view);
        setOnclickEvent();
        presenterLogicCaNhan.hienThiThongTinCaNhan();
        return view;
    }

    private void setOnclickEvent() {
        doianhdaidien.setOnClickListener(this);
        nguoitheodoi.setOnClickListener(this);
        nguoidangtheodoi.setOnClickListener(this);
        sachdadoc.setOnClickListener(this);
        sachyeuthich.setOnClickListener(this);
        naptaikhoan.setOnClickListener(this);
        caidat.setOnClickListener(this);
        dangxuat.setOnClickListener(this);
    }

    private void initControls(View view) {
        anhdaidienBackGround = view.findViewById(R.id.image_background_anhdaidien);
        anhdaidien = view.findViewById(R.id.image_anhdaidien);
        tenNguoidung = view.findViewById(R.id.tenAccount);
        sotientrongTaiKhoan = view.findViewById(R.id.sotientrongtaikhoan);
        tongsoSach = view.findViewById(R.id.tongsosach);
        doianhdaidien = view.findViewById(R.id.thaydoichandung);
        nguoitheodoi = view.findViewById(R.id.nguoitheodoi);
        nguoidangtheodoi = view.findViewById(R.id.nguoidangtheodoi);
        sachdadoc = view.findViewById(R.id.sachdadoc);
        sachyeuthich = view.findViewById(R.id.sachyeuthich);
        naptaikhoan = view.findViewById(R.id.naptaikhoan);
        caidat = view.findViewById(R.id.caidat);
        dangxuat = view.findViewById(R.id.dangxuat);
        songuoitheodoi = view.findViewById(R.id.soluong_nguoitheodoi);
        songuoidangtheodoi = view.findViewById(R.id.soluong_nguoidangtheodoi);
        sosachdadoc = view.findViewById(R.id.soluong_sachdadoc);
        sosachyeuthich = view.findViewById(R.id.soluong_sachyeuthich);

        presenterLogicCaNhan = new PresenterLogicCaNhan(this);
    }
    @Override
    public void hienThiThongTinCaNhan(User user) {
        Picasso.get().load(user.getAnhDaiDien()).into(anhdaidienBackGround);
        Picasso.get().load(user.getAnhDaiDien()).into(anhdaidien);
        tenNguoidung.setText(user.getTenUser());
        sotientrongTaiKhoan.setText(user.getTongSoTien()+"");
        tongsoSach.setText(user.getTongSoSach()+"");
        songuoitheodoi.setText(user.getSoNguoiTheoDoi()+"");
        songuoidangtheodoi.setText(user.getSoNguoiDangTheoDoi()+"");
        sosachdadoc.setText(user.getSoSachDaDoc()+"");
        sosachyeuthich.setText(user.getSoSachYeuThich()+"");
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.thaydoichandung:

                break;
            case R.id.nguoitheodoi:

                break;
            case R.id.nguoidangtheodoi:

                break;
            case R.id.sachdadoc:

                break;
            case R.id.sachyeuthich:

                break;
            case R.id.naptaikhoan:

                break;
            case R.id.caidat:

                break;
            case R.id.dangxuat:
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }


}
