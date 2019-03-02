package com.phungthanhquan.bookapp.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.phungthanhquan.bookapp.Object.ItemBook;
import com.phungthanhquan.bookapp.R;
import com.phungthanhquan.bookapp.View.Activity.BookDetail;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecycleView_ItemBook_Adapter extends RecyclerView.Adapter<RecycleView_ItemBook_Adapter.ViewHolder> {
    private Context context;
    private List<ItemBook> dsSach;
    private List<ItemBook> dsRandom;
    private Random randomc;

    public RecycleView_ItemBook_Adapter(Context context, List<ItemBook> dsSach) {
        this.context = context;
        this.dsSach = dsSach;
        randomc = new Random();
        dsRandom = new ArrayList<>();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book_recycleview,viewGroup,false);
        ViewHolder viewHolder =  new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        viewHolder.titleSach.setText(dsSach.get(position).getTitle());
        Picasso.get().load(dsSach.get(position).getUrlImage()).into(viewHolder.imageSach, new Callback() {
            @Override
            public void onSuccess() {
                viewHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        //click itembook go to detail book
        viewHolder.imageSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookDetail.class);
                intent.putExtra("image",dsSach.get(position).getUrlImage());
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                            viewHolder.imageSach,"sharedName");
                context.startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageSach;
        private TextView titleSach;
        private ProgressBar progressBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSach = itemView.findViewById(R.id.item_imagebook);
            titleSach = itemView.findViewById(R.id.item_titlebook);
            progressBar = itemView.findViewById(R.id.progress_itembook);
        }
    }
}
