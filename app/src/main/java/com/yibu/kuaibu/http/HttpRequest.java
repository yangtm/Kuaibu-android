package com.yibu.kuaibu.http;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yibu.kuaibu.utils.Constant;

import static com.yibu.kuaibu.utils.MD5.Md5;


/**
 * 
 * @author 张界川
 *时间2015年9月9日上午10:17:34
 *网络请求
 */
public class HttpRequest 
{

	private HttpRequest(){}
	private static HttpRequest httpRequest=null;
	public synchronized static HttpRequest getInstance()
	{
		if (httpRequest==null) 
		{
			return new HttpRequest();
		}
		return httpRequest;
	}
	
	
	
	/* 设置登录密码
	 * 
	 */
	public static void setLoginPwd(int type ,Map<String, String> map,RequestCallBack<String> requestCallBack )
	{
		
		switch (type) 
		{
			case 1:
				//resetLoginPwd(map, requestCallBack);
				break;
	
			default:
				//setLoginPwd(map, requestCallBack);
				break;
		}
	}
	
	/**
	 * xutil网络请求 post
	 */
	public static void http_post(String url,Map<String, String> map,RequestCallBack<String> requestCallBack)
	{
		map=new HashMap<String, String>();
		map.put("app_id", Constant.APPID);
		map.put("nonce", new Random(10000)+"");
		map.put("timestamp", (new Date().getTime()+"").substring(0, 10));
		map.put("sign",Md5(Constant.APPID + "||" + new Random(10000) + "" + "||" + (new Date().getTime() + "").substring(0, 10) + "||" + Constant.APPKEY) .substring(12,20));
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000); // 设置缓存10秒，10秒内直接返回上次成功请求的结果。
		//http.configCurrRequestExpiry(1000 * 10); // 设置缓存10秒，10秒内直接返回上次成功请求的结果。jar包2.6.14
		RequestParams params=new RequestParams();
		if (map!=null) 
		{
			for(Map.Entry<String, String> entry:map.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				params.addBodyParameter(key, value);
			}
		}
		params.addHeader("appid", Constant.APPID);
		params.addHeader("appkey", Constant.APPKEY);
		http.send(HttpMethod.POST, url,params, requestCallBack);
	}
	
	
	/**
	 * xutil网络请求 get
	 */
	private  static void http_get(String url,Map<String, String> map,RequestCallBack<String> requestCallBack)
	{
		String s="";
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000);
		RequestParams params = new RequestParams();
		if (map!=null) 
		{
			for(Map.Entry<String, String> entry:map.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				s+=key+"="+value;
				s+="&";
			}
		}
		String u="";
		if (s!=null&&s!="") 
		{
			u=url+"?"+s.substring(0, s.length()-1);
		}
		//System.out.println(u);
		params.addHeader("appid", Constant.APPID);
		params.addHeader("appkey", Constant.APPKEY);
   		http.send(HttpMethod.GET, u,params,requestCallBack);
	}
	
	
}
