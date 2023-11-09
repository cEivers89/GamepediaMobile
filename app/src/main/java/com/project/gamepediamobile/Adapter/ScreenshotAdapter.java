package com.project.gamepediamobile.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.project.gamepediamobile.R;

import java.util.List;

public class ScreenshotAdapter extends RecyclerView.Adapter<ScreenshotAdapter.ViewHolder> {
    List<String> screenshots;
    private OnItemClickListener listener;
    Context context;



    public ScreenshotAdapter(List<String> screenshots, OnItemClickListener listener) {

        this.screenshots = screenshots;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClicked(String imageUrl);
    }

    @NonNull
    @Override
    public ScreenshotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_detail_images, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ScreenshotAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(screenshots.get(position))
                .into(holder.images);

        holder.images.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClicked(screenshots.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return screenshots.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.screenshotItem);
        }
    }
}
