package com.yibu.kuaibu.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.yibu.kuaibu.R;
public class Options {
	public static DisplayImageOptions getListOptions() 
	{
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.considerExifParams(true)
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//
				.bitmapConfig(Bitmap.Config.RGB_565)// 
				// .decodingOptions(android.graphics.BitmapFactory.Options
				// decodingOptions)//
				.considerExifParams(true)
				// .delayBeforeLoading(int delayInMillis)//int
				.resetViewBeforeLoading(true)// 
				// .displayer(new RoundedBitmapDisplayer(20))//
				.displayer(new FadeInBitmapDisplayer(100))//
				.build();
		return options;
	}
}
