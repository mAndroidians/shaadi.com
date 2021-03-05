package com.shaadi.data.util;


import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shaadi.R;
import com.shaadi.di.module.GlideApp;

public class AppBindingAdapters {

    @BindingAdapter("imageUrl")
    public static void loadImage(final ImageView imageView, final String url) {
        GlideApp.with(imageView.getContext())
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.IMMEDIATE)
            .into(imageView);
    }
}
