package com.saifulla.server_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<DataAdapter> dataAdapters;
    ImageLoader imageLoader;

    public RecyclerViewAdapter(List<DataAdapter> getDataAdapter, Context context){

        super();
        this.dataAdapters = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_card, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        DataAdapter dataAdapterOBJ =  dataAdapters.get(position);

        Glide.with(Viewholder.itemView)
                .load(dataAdapterOBJ.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(Viewholder.VollyImageView);
//        imageLoader = ImageAdapter.getInstance(context).getImageLoader();
//
//        imageLoader.get(dataAdapterOBJ.getImageUrl(),
//                ImageLoader.getImageListener(
//                        Viewholder.VollyImageView,//Server Image
//                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
//                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
//                )
//        );

//        Viewholder.VollyImageView.setImageUrl(dataAdapterOBJ.getImageUrl(), imageLoader);

        Viewholder.VideoTitleTextView.setText(dataAdapterOBJ.getVideoTitle());
        Viewholder.VideoUserTextView.setText(dataAdapterOBJ.getVideoUser());
      //  Viewholder.ImageDescTextView.setText(dataAdapterOBJ.getVideoDesc());


    //    Viewholder.ImagePriceTextView.setText(dataAdapterOBJ.getVideoURL());
      //  Viewholder.ImageDescTextView.setText(dataAdapterOBJ.getVideoID());

    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView VideoTitleTextView;
        public TextView VideoUserTextView;
        public TextView ImageDescTextView;
        public ImageView VollyImageView ;

        public ViewHolder(View itemView) {

            super(itemView);

            VideoTitleTextView = (TextView) itemView.findViewById(R.id.vTitle) ;
            VideoUserTextView = (TextView) itemView.findViewById(R.id.vUser) ;

            VollyImageView = itemView.findViewById(R.id.VolleyImageView) ;

        }
    }
}