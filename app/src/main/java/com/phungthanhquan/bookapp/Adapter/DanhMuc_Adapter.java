package com.phungthanhquan.bookapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phungthanhquan.bookapp.Object.DanhMuc;
import com.phungthanhquan.bookapp.R;
import com.phungthanhquan.bookapp.View.Activity.ListBookDanhMucTatCa;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DanhMuc_Adapter extends RecyclerView.Adapter<DanhMuc_Adapter.ViewHolder> {
    private Context context;
    private List<DanhMuc> danhMucList;

    public DanhMuc_Adapter(Context context, List<DanhMuc> danhMucList) {
        this.context = context;
        this.danhMucList = danhMucList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_danhmuc_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final DanhMuc danhMuc = danhMucList.get(position);
        viewHolder.tendanhMuc.setText(danhMuc.getTenDanhMuc());
        Picasso.get().load(danhMuc.getBackGround()).into(viewHolder.background);
        viewHolder.layoutDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListBookDanhMucTatCa.class);
                intent.putExtra("idDanhMuc",danhMuc.getIdDanhMuc());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhMucList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView background;
        private TextView tendanhMuc;
        private CardView layoutDanhMuc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.image_background_danhmuc);
            tendanhMuc = itemView.findViewById(R.id.textview_tendanhmuc);
            layoutDanhMuc = itemView.findViewById(R.id.layout_item_danhmuc);
        }
    }
}
