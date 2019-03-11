package com.phungthanhquan.bookapp.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.phungthanhquan.bookapp.Object.ItemBookCase;
import com.phungthanhquan.bookapp.R;
import com.phungthanhquan.bookapp.View.Activity.BookDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class Tusach_Adapter extends RecyclerView.Adapter<Tusach_Adapter.ViewHolder> {
    private Context context;
    private List<ItemBookCase> itemBookCaseList;

    public Tusach_Adapter(Context context, List<ItemBookCase> itemBookCaseList) {
        this.context = context;
        this.itemBookCaseList = itemBookCaseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book_tusach,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final ItemBookCase itemBookCase = itemBookCaseList.get(position);
        final int[] progresss = {0};
        Picasso.get().load(itemBookCase.getUrlImage()).into(viewHolder.imageSach);
        viewHolder.tentacgia.setText(itemBookCase.getTenTacGia());
       viewHolder.phantram.setProgress((int) itemBookCase.getPhantramdoc());
        new Thread(new Runnable() {
            @Override
            public void run() {
                viewHolder.layout_tusachItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, BookDetail.class);
                        intent.putExtra("image",itemBookCase.getUrlImage());
                        ActivityOptions options = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                            options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                                    viewHolder.imageSach,"sharedName");
                        }
                        context.startActivity(intent,options.toBundle());
                    }
                });
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return itemBookCaseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageSach;
        private TextView tentacgia;
        private RingProgressBar phantram;
        private FrameLayout download;
        private CardView layout_tusachItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSach = itemView.findViewById(R.id.imageview_sach);
            tentacgia = itemView.findViewById(R.id.textview_tentacgia);
            phantram = itemView.findViewById(R.id.progress);
            download = itemView.findViewById(R.id.taichua);
            layout_tusachItem = itemView.findViewById(R.id.layout_item_tusach);
        }
    }
}
