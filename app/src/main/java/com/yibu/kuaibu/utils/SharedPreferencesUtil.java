package com.yibu.kuaibu.utils;



import com.yibu.kuaibu.base.BaseApplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

	private static SharedPreferences mSharedPreferences;
	public  static final String MOBILE_PHONE = "mobilePhone";
	private static SharedPreferences.Editor mEditor;
	private static SharedPreferencesUtil mInstance;
	private static final String SHAREDPREFERENCE_NAME = "yb";
	public  static final String CUSTOMER_NAME = "customerName";
	
	//是否第一次登录
	private static final String IS_FIRST_USE = "_is_first_usr";
	//是否已经登陆
	public  static final String IS_HAD_LOGIN = "is_had_login";
	//保存token标识变量
	public  static final String TOKEN = "token";
	
	private SharedPreferencesUtil(Context context) 
	{
        mSharedPreferences = context.getSharedPreferences(SHAREDPREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }
	
	public synchronized static SharedPreferencesUtil getInstance() 
	{
        if (mInstance == null) 
        {
            mInstance = new SharedPreferencesUtil(BaseApplication.getInstance());
        }
        return mInstance;
    }
	
	
	/**
	 * 是否是第一次使用该应用
	 * @return
	 */
	public boolean isFirstUse()
	{
        boolean isUse=mSharedPreferences.getBoolean(IS_FIRST_USE, true);//
        return isUse;
	}
	
	/**
	 * 记录已经使用该app
	 */
	public void  writeFirstUse()
	{
        mEditor.putBoolean(IS_FIRST_USE, false);
        mEditor.commit();
    }
	
	/**
	 * 判断用户是否已经登录
	 */
	public boolean isHadLogin()
	{
		return mSharedPreferences.getBoolean(IS_HAD_LOGIN, false);
	}
	
	/**
	 * 登录成功
	 */
	public void  writeLogin()
	{
        mEditor.putBoolean(IS_HAD_LOGIN, true);
        mEditor.commit();
    }
	
	/**
	 * 获取token
	 */
	public String getToken()
	{
		return mSharedPreferences.getString(TOKEN, "");
	}
	/**
	 * 写入token
	 */
	public void  writeToken(String token)
	{
        mEditor.putString(TOKEN, token);
        mEditor.commit();
    }
	
	/**
	 * 获取用户名
	 */
	public String getCustomerName()
	{
		String str=mSharedPreferences.getString(CUSTOMER_NAME, "");
		if (str.length()>=2) 
		{
			String old=str.substring(1);
			str="*"+old;
		}
		return str;
	}
	
	/**
	 * 写入用户名
	 */
	public void  writeCustomerName(String customerName)
	{
		mEditor.putString(CUSTOMER_NAME, customerName);
		mEditor.commit();
	}
	
	/**
	 * 获取手机号码
	 */
	public String getMobilePhone()
	{
		String str = mSharedPreferences.getString(MOBILE_PHONE, "");
		if (str.length()>=11) 
			str = str.replace(str.subSequence(3, 7), "****");
		return str;
	}
	/**
	 * 写入手机号码
	 */
	public void  writeMobilePhone(String mobilePhone)
	{
		mEditor.putString(MOBILE_PHONE, mobilePhone);
		mEditor.commit();
	}
}
