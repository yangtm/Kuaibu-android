package com.yibu.kuaibu.utils;



import com.yibu.kuaibu.base.BaseApplication;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class AppUtils {
	
	     private static final String TAG = "AppUtil";
		 public static final String MOBILE_PHONE = "^[1][345678][0-9]{9}$";//手机号码校验
		 private static Toast mtoast;
		 
		 /**
			 * 弹出对话框  是否先登录
			 */
		    public  static void  showLoginDialog(final Context context) 
			{
				 Builder builder = new Builder(context);
				 builder.setMessage("亲 还没登录 ！马上登录，更多惊喜等着你！");
				 builder.setTitle("登录");
				 builder.setPositiveButton("确认", new DialogInterface.OnClickListener() 
				 {
					public void onClick(DialogInterface dialog, int which) 
					{
						// 跳转登录页面
						dialog.dismiss();
//						Intent intent=new Intent(context, LoginActivity.class);
//						context.startActivity(intent);
					}
				});
				 builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) 
					{
						dialog.dismiss();
					}
				});
				builder.create().show();
			}
		    
		    /**
		   	 * 检查手机号码是否正确
		   	 */
		    public static boolean checkPhone(String number,Context context)
			{
				if (number.matches(MOBILE_PHONE)) 
					return true;
				else
				{
					showInfo("手机号码格式不正确");
					return false;
				}
			}
		    
		    /**
		   	 * 判断手机有没有网络
		   	 */
		    public static boolean isNetConnect()
		    {
		    	ConnectivityManager cm=(ConnectivityManager) BaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		    	NetworkInfo ni = cm.getActiveNetworkInfo();
		    	if (ni!=null&&ni.isConnected()) 
		    		return true;
		    	else
		    	{
					AppUtils.showInfo("网络连接错误");
					return false;
				}
		    }
		    //提示信息
		    public static void showInfo(String text)
		    {
		    	if (mtoast!=null) {
					 mtoast.setText(text); 
				}else{ 
					mtoast=Toast.makeText(BaseApplication.getInstance(),text, 0);
				}
				mtoast.show();
		    }

		    
}
