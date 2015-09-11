package com.yibu.kuaibu.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 

import org.apache.http.Header;
import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
 

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyTools {
	public static final String ENCODING = "GBK";
	public static void showToast(Context context,String text){
		Toast.makeText(context, text, 500).show();
	}
	
	//读取本地文件
	public static String getFromAssets(String fileName,Context mContext) {
		String result = "";
		try {
			InputStream in = mContext.getResources().getAssets().open(fileName);
			// 获取文件的字节数
			int lenght = in.available();
			// 创建byte数组
			byte[] buffer = new byte[lenght];
			// 将文件中的数据读到byte数组中
			in.read(buffer);
			result = EncodingUtils.getString(buffer, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	

	/**
	 * 检查是否存在SDCard
	 * 
	 * @return
	 */
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	// 判断文件是否存在
	public static boolean hasFile(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	// 判断手机格式是否正确
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);

		return m.matches();
	}

	//判断邮箱格式是否正确
	 public static boolean isEmail(String email) {
		 String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		 Pattern p = Pattern.compile(str);
		 Matcher m = p.matcher(email); 
		 return m.matches();
		 }
	// 判断有误网络连接
	public static boolean checkNetWorkStatus(Context context) {
		boolean netSataus = false;
		ConnectivityManager cwjManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		cwjManager.getActiveNetworkInfo();
		if (cwjManager.getActiveNetworkInfo() != null) {
			netSataus = cwjManager.getActiveNetworkInfo().isAvailable();
		}
		return netSataus;
	}
	// 头像圆形
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		// 保证是方形，并且从中心画
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int w;
		int deltaX = 0;
		int deltaY = 0;
		if (width <= height) {
			w = width;
			deltaY = height - w;
		} else {
			w = height;
			deltaX = width - w;
		}
		final Rect rect = new Rect(deltaX, deltaY, w, w);
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		// 圆形，所有只用一个

		int radius = (int) (Math.sqrt(w * w * 2.0d) / 2);
		canvas.drawRoundRect(rectF, radius, radius, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}
	// 下载图片
	public static void downloadLy(String url) throws Exception {
		Bitmap bitmap1 = null;
		byte[] data1 = getImage(url);
		if (data1 != null) {
			bitmap1 = BitmapFactory.decodeByteArray(data1, 0, data1.length);// bitmap
			saveImg(bitmap1);
		}

	}

	public static byte[] getImage(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		InputStream inStream = conn.getInputStream();
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return readStream(inStream);
		}
		return null;
	}

	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}

	//
	public static void saveImg(Bitmap bitmap) {
		if (bitmap != null) {
			FileOutputStream fos = null;
			try {
				String path = Environment
						.getExternalStorageDirectory() + "/Zhongyibao/pic/";
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
				File file2 = null;
				file2 = new File(path + "Myshare.jpg");

				fos = new FileOutputStream(file2);
				if (null != fos) {
					bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
					fos.flush();
					fos.close();
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	/**
	 * 得到自定义的progressDialog 
	 */
//	public static Dialog createLoadingDialog(Context context, String msg) {
//		LayoutInflater inflater = LayoutInflater.from(context);
//		View v = inflater.inflate(R.layout.all_dialog, null);// 得到加载view
//		RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.dialog_view);// 加载布局
//		// main.xml中的ImageView
//		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.loadingImageView);
//		TextView tipTextView = (TextView) v.findViewById(R.id.id_tv_loadingmsg);// 提示文字
//		  // 加载动画显示
//	    //Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
//		// 使用ImageView显示动画
//	   //spaceshipImage.startAnimation(hyperspaceJumpAnimation);
//
//		//轮播图片显示
//		 spaceshipImage.setBackgroundResource(R.anim.progress_round);
//		 AnimationDrawable animationDrawable = (AnimationDrawable)spaceshipImage.getBackground();
//		 animationDrawable.start();
//
//		tipTextView.setText(msg);// 设置加载信息
//
//		Dialog loadingDialog = new Dialog(context, R.style.CustomDialog);// 创建自定义样式dialog
//
//		// loadingDialog.setCancelable(false);// 不可以用“返回键”取消
//		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
//				LinearLayout.LayoutParams.WRAP_CONTENT,
//				LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
//		return loadingDialog; 
//	}
	
	/** 
     * Layout动画 
     *  
     * @return 
     */  
    public static LayoutAnimationController getAnimationController() {  
        int duration=300;  
        AnimationSet set = new AnimationSet(true);  
  
        Animation animation = new AlphaAnimation(0.0f, 1.0f);  
        animation.setDuration(duration);  
        set.addAnimation(animation);  
  
        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,  
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,  
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);  
        animation.setDuration(duration);  
        set.addAnimation(animation);  
  
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);  
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);  
        return controller;  
    } 
    
    //webview 注册
    public static void webviewRegister(WebView webView){
		// 设置支持JavaScript脚本
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		// 只用于缓存/不适用缓存
		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);// LOAD_CACHE_ONLY,LOAD_NO_CACHE
		// 设置可以访问文件
		webSettings.setAllowFileAccess(true); 
		//不支持放大缩小
 		webSettings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL); 
//		NORMAL：正常显示，没有渲染变化。
//		SINGLE_COLUMN：把所有内容放到WebView组件等宽的一列中。   //这个是强制的，把网页都挤变形了
//		NARROW_COLUMNS：可能的话，使所有列的宽度不超过屏幕宽度。 //好像是默认的 
		// 自动适应屏幕
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		
		webView.setVerticalScrollBarEnabled(false);  //取消Vertical ScrollBar显示
		webView.setHorizontalScrollBarEnabled(false); //取消Horizontal ScrollBar显示
		//webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET) ;
    }

    
    
    
	// 动态设置控件高度(长)
	public static void setLayoutHight(RelativeLayout layout, int width, int height,
			Context mContext) { 
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) layout
				.getLayoutParams(); // 取控件mLayout当前的布局参数

		if (width == 800 && height == 1280) {
			linearParams.height = MyTools.dip2px(mContext, 145);// 手机
			layout.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件
		} else if (width == 480 && height == 800) {
			linearParams.height = MyTools.dip2px(mContext, 110);
			layout.setLayoutParams(linearParams);
		} else if (width == 480 && height == 854) {
			linearParams.height = MyTools.dip2px(mContext, 116);
			layout.setLayoutParams(linearParams);
		} else if (width == 800 && height == 1232) {
			linearParams.height = MyTools.dip2px(mContext, 270);// 平板
			layout.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件
		} else if (width == 720 && height == 1280) {
			linearParams.height = MyTools.dip2px(mContext, 125);
			layout.setLayoutParams(linearParams);
		} else if (width == 640 && height == 960) {
			linearParams.height = MyTools.dip2px(mContext, 110);
			layout.setLayoutParams(linearParams);
		} else if (width == 1600 && height == 2560) {
			linearParams.height = MyTools.dip2px(mContext, 255);
			layout.setLayoutParams(linearParams);
		} else if (width == 1080 && height == 1812) {
			linearParams.height = MyTools.dip2px(mContext, 125);
			layout.setLayoutParams(linearParams);
		} else if (width == 1080 && height == 1920) {
			linearParams.height = MyTools.dip2px(mContext, 126);
			layout.setLayoutParams(linearParams);
		} else if (width == 1440 && height == 2392) {
			linearParams.height = MyTools.dip2px(mContext, 150);
			layout.setLayoutParams(linearParams);
		} else if (width == 768 && height == 1184) {
			linearParams.height = MyTools.dip2px(mContext, 135);
			layout.setLayoutParams(linearParams);
		} else if (width == 1440 && height == 2560) {
			linearParams.height = MyTools.dip2px(mContext, 165);
			layout.setLayoutParams(linearParams);
		} else if (width == 1200 && height == 1824) {
			linearParams.height = MyTools.dip2px(mContext, 165);
			layout.setLayoutParams(linearParams);
		}
	}
	// 动态设置控件高度(正)
		public static void setHight(RelativeLayout layout, int width, int height,
				Context mContext) { 
			LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) layout
					.getLayoutParams(); // 取控件mLayout当前的布局参数

			if (width == 800 && height == 1280) {
				linearParams.height = MyTools.dip2px(mContext, 265);// 手机
				layout.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件
			} else if (width == 480 && height == 800) {
				linearParams.height = MyTools.dip2px(mContext, 230);
				layout.setLayoutParams(linearParams);
			} else if (width == 480 && height == 854) {
				linearParams.height = MyTools.dip2px(mContext, 235);
				layout.setLayoutParams(linearParams);
			} else if (width == 800 && height == 1232) {
				linearParams.height = MyTools.dip2px(mContext, 442);// 平板
				layout.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件
			} else if (width == 720 && height == 1280) {
				linearParams.height = MyTools.dip2px(mContext, 257);
				layout.setLayoutParams(linearParams);
			} else if (width == 1080 && height == 1812) {
				linearParams.height = MyTools.dip2px(mContext, 245);
				layout.setLayoutParams(linearParams);
			} else if (width == 1080 && height == 1920) {
				linearParams.height = MyTools.dip2px(mContext, 245);
				layout.setLayoutParams(linearParams);
			} else if (width == 1440 && height == 2392) {
				linearParams.height = MyTools.dip2px(mContext, 271);
				layout.setLayoutParams(linearParams);
			} else if (width == 768 && height == 1184) {
				linearParams.height = MyTools.dip2px(mContext, 251);
				layout.setLayoutParams(linearParams);
			} else if (width == 1440 && height == 2560) {
				linearParams.height = MyTools.dip2px(mContext, 285);
				layout.setLayoutParams(linearParams);
			} else if (width == 1200 && height == 1824) {
				linearParams.height = MyTools.dip2px(mContext, 305);
				layout.setLayoutParams(linearParams);
			}
		}
		
		// 动态设置控件高度(长)
		public static void set_ShiLiao_LayoutHight(RelativeLayout layout, int width, int height,
				Context mContext) { 
			LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) layout
					.getLayoutParams(); // 取控件mLayout当前的布局参数

			if (width == 800 && height == 1280) {
				linearParams.height = MyTools.dip2px(mContext, 112);// 手机
				layout.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件
			} else if (width == 480 && height == 800) {
				linearParams.height = MyTools.dip2px(mContext, 105);
				layout.setLayoutParams(linearParams);
			} else if (width == 800 && height == 1232) {
				linearParams.height = MyTools.dip2px(mContext, 230);// 平板
				layout.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件
			} else if (width == 720 && height == 1280) {
				linearParams.height = MyTools.dip2px(mContext, 102);
				layout.setLayoutParams(linearParams);
			} else if (width == 640 && height == 960) {
				linearParams.height = MyTools.dip2px(mContext, 85);
				layout.setLayoutParams(linearParams);
			} else if (width == 1600 && height == 2560) {
				linearParams.height = MyTools.dip2px(mContext, 215);
				layout.setLayoutParams(linearParams);
			} else if (width == 1080 && height == 1812) {
				linearParams.height = MyTools.dip2px(mContext, 97);
				layout.setLayoutParams(linearParams);
			} else if (width == 1080 && height == 1920) {
				linearParams.height = MyTools.dip2px(mContext, 98);
				layout.setLayoutParams(linearParams);
			} else if (width == 1440 && height == 2392) {
				linearParams.height = MyTools.dip2px(mContext, 116);
				layout.setLayoutParams(linearParams);
			} else if (width == 768 && height == 1184) {
				linearParams.height = MyTools.dip2px(mContext, 102);
				layout.setLayoutParams(linearParams);
			} else if (width == 1440 && height == 2560) {
				linearParams.height = MyTools.dip2px(mContext, 142);
				layout.setLayoutParams(linearParams);
			} else if (width == 1200 && height == 1824) {
				linearParams.height = MyTools.dip2px(mContext, 142);
				layout.setLayoutParams(linearParams);
			}
		}
		public static DisplayImageOptions createOptions(int imgId) {
			@SuppressWarnings("deprecation")
			DisplayImageOptions options = new DisplayImageOptions.Builder()
	        .showImageOnLoading(imgId)
	        .showImageForEmptyUri(imgId)
	        .showImageOnFail(imgId)
	        .cacheInMemory(true)//设置下载的图片是否缓存在内存中  
	        .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中  
	        //.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
	        .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
	        /*设置图片以如何的编码方式显示mageScaleType的选择值:
	              EXACTLY :图像将完全按比例缩小的目标大小
	              EXACTLY_STRETCHED:图片会缩放到目标大小完全
	              IN_SAMPLE_INT:图像将被二次采样的整数倍
	              IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
	              NONE:图片不会调整*/
	        .bitmapConfig(Config.RGB_565)//设置图片的解码类型
	        .delayBeforeLoading(30)//int delayInMillis为你设置的下载前的延迟时间
	        //对图片进行处理
	        .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
	        //.displayer(new RoundedBitmapDisplayer(20))//不推荐用！！！！是否设置为圆角，弧度为多少  
	        //.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间，可能会出现闪动
	        
	        .build();
			return options;
		}
		public static String replace(String str){ 
			if (str.contains("<p>")) {
				str = str.replace("<p>", "");
			}
			if (str.contains("</p>")) {
				str = str.replace("</p>", "");
			}
			if (str.contains("<br/>")) {
				str = str.replace("<br/>", "");
			} 
			return str;
		}
		
}
