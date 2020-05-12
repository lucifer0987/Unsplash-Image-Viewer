package com.example.android.bipolartask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bipolartask.Models.Image;
import com.example.android.bipolartask.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<Image> mData;

    public ImageAdapter(){
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView ImageNameTextView;
        ImageView ImagePosterImageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageNameTextView = itemView.findViewById(R.id.tv_list_item_image_name);
            ImagePosterImageView = itemView.findViewById(R.id.iv_list_item_image);
        }
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int grid_item = R.layout.image_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttach = false;

        View view = inflater.inflate(grid_item, parent, shouldAttach);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.ImageNameTextView.setText(mData.get(position).getTitle());
        Picasso.get().load(mData.get(position).getImageURL())
                .fit()
                .into(holder.ImagePosterImageView);
    }

    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }else {
            return mData.size();
        }
    }

    public void setImageData(List<Image> param){
        mData = param;
        notifyDataSetChanged();
    }

}
