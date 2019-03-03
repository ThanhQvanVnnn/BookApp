package com.phungthanhquan.bookapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phungthanhquan.bookapp.Object.AlbumBook;
import com.phungthanhquan.bookapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAlbum_Adapter extends BaseAdapter {

    private Context context;
    private List<AlbumBook> dsAlbum;

    public ListAlbum_Adapter(Context context, List<AlbumBook> dsAlbum) {
        this.context = context;
        this.dsAlbum = dsAlbum;
    }

    @Override
    public int getCount() {
        return dsAlbum.size();
    }

    @Override
    public Object getItem(int position) {
        return dsAlbum.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        AlbumBook albumBook = (AlbumBook) getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_albumbook,null);
            viewHolder = new ViewHolder();
            viewHolder.image_album = convertView.findViewById(R.id.image_album);
            viewHolder.title_album = convertView.findViewById(R.id.label_album);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.get().load(albumBook.getImageAlbum()).into(viewHolder.image_album);
        viewHolder.title_album.setText(albumBook.getTitleAlbum());
        return convertView;
    }
    class ViewHolder{
       TextView title_album;
        ImageView image_album;

        public ViewHolder() {

        }
    }
}
