package com.yibu.kuaibu.base;

import java.io.File;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class BaseApplication extends Application
{
	
	private static BaseApplication sInstance;
	@Override
	public void onCreate()
	{
		super.onCreate();
		sInstance=this;
		initImageLoader(getApplicationContext());
	}
	
	public static synchronized BaseApplication getInstance() 
	{
		return sInstance; 
	}
	private void initImageLoader(Context context)
	{
		File cacheDir = StorageUtils.getOwnCacheDirectory(context, "news/Cache");//获取到缓存的目录地址
		Log.d("cacheDir", cacheDir.getPath());
		//创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
		ImageLoaderConfiguration config = new ImageLoaderConfiguration
				.Builder(context)
				.threadPoolSize(3)//线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
				.writeDebugLogs() // Remove for release app
				.build();
		         ImageLoader.getInstance().init(config);//全局初始化此配置
	}

}
