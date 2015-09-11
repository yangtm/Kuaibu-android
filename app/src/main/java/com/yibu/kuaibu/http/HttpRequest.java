package com.yibu.kuaibu.http;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yibu.kuaibu.utils.Constant;
import com.yibu.kuaibu.utils.MD5;
import com.yibu.kuaibu.tools.MyTools;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

/**
 * 采用httpURLConnection连接方式区请求网络数据
 * 
 * @author Administrator
 * 
 */
@SuppressLint("ShowToast")
public class HttpRequest {
	
//	public static ShrefUtil userName;
	
	/**t
	 * 请求方式：Get
	 */

//	public static byte[] urlConnGet(String path,Context context) {
//		InputStream inputStream = null;
//		byte[] buffer = null;
//		HttpURLConnection conn = null;
//		try {
//			URL url = new URL(path);
//
//			// 通过URL打开连接，获取连接
//			conn = (HttpURLConnection) url.openConnection();
//			// 请求方式 GET/POST
//			// 超时时间(ms),超过时间连接断开
//			conn.setConnectTimeout(5000);
//			// conn.setReadTimeout(2000);
//			conn.setDoInput(true);
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Content-Type", "text/html");
//			conn.setRequestProperty("Accept-Charset", "utf-8");
//			conn.setRequestProperty("contentType", "utf-8");
//			conn.setRequestProperty("Cookie", Constans.key_name + "=" + Constans.key_value);
//			// 通过连接获取数据(InputStream)
//			inputStream = conn.getInputStream();
//			if (conn.getResponseCode() == 200) {
//				buffer = new byte[1024];
//				ByteArrayOutputStream out = new ByteArrayOutputStream();
//				int len;
//				while ((len = inputStream.read(buffer)) != -1) {
//					out.write(buffer, 0, len);
//				}
//				buffer = out.toByteArray();
//				return buffer;
//			}
//		} catch (ConnectTimeoutException e) {// 超时或网络连接出错
//		//	buffer = "咦？数据加载失败了,请检查一下您的网络,重新加载吧".getBytes();
//			Toast.makeText(context, "咦？数据加载失败了,请检查一下您的网络,重新加载吧", 1000).show();
//		}catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			Log.i("123", "url不正确");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (inputStream != null) {
//					inputStream.close();
//				}
//				if (conn != null) {
//					conn.disconnect();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return buffer;
//
//	}

	/**
	 * 采用HttpClient请求方式（GET）
	 */
	public static byte[] httpClientGet(String path,Context context) {
		InputStream inputStream = null;
		byte[] buffer = null;
		HttpURLConnection conn = null;
		try { 
			URL url = new URL(path);
			// 通过URL打开连接，获取连接
			conn = (HttpURLConnection) url.openConnection();
			// 请求方式 GET/POST
			// 超时时间(ms),超过时间连接断开
			conn.setConnectTimeout(5000);
			// conn.setReadTimeout(2000);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "text/html");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8"); 
		//	conn.setRequestProperty("Cookie", Constans.key_name + "="+ Constans.key_value);
			// 通过连接获取数据(InputStream)
			inputStream = conn.getInputStream();
			if (conn.getResponseCode() == 200) {
				// buffer= convertStreamToString(inputStream).getBytes();
				buffer = new byte[1024];
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				int len;
				while ((len = inputStream.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
				buffer = out.toByteArray();
				return buffer;
			}
		}  catch (ConnectTimeoutException e) {// 超时或网络连接出错
			//buffer = "咦？数据加载失败了,请检查一下您的网络,重新加载吧".getBytes();
			Toast.makeText(context, "咦？数据加载失败了,请检查一下您的网络,重新加载吧", 1000).show();
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			Log.i("123", "url不正确");
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer;
	}

	/**
	 * 采用HttpClient请求方式（GET）
	 */
	public static byte[] httpClientGetData(String path,Context context) {
		byte[] data = null;

		try {
			// 构造实例httpClientGet的类DefaultHttpClient
			// 获取连接
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(path);
			// 执行连接（get请求）
			// 返回封装类HttpResponse
			HttpResponse response = client.execute(get);
			// 获得返回码(比如：code码，=200表示成功连接)头协议，数据
			if (response.getStatusLine().getStatusCode() == 200) {
				response.getAllHeaders();
				HttpEntity entity = response.getEntity();
				data = EntityUtils.toByteArray(entity);

			} else {
				// 不同的返回自己具体处理
				// Log.i("Code", "http code"
				// + response.getStatusLine().getStatusCode());
			}

		}  catch (ConnectTimeoutException e) {// 超时或网络连接出错
			//data = "咦？数据加载失败了,请检查一下您的网络,重新加载吧".getBytes();
			Toast.makeText(context, "咦？数据加载失败了,请检查一下您的网络,重新加载吧", 1000).show();
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	// 获取成功的cookie
	@SuppressWarnings("unused")
	public static byte[] getRequest(String url) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		byte[] data = null;
		int statusCode = 0;
		HttpGet getMethod = new HttpGet(url);
		try {
			HttpResponse httpResponse = client.execute(getMethod);
			statusCode = httpResponse.getStatusLine().getStatusCode();
			// 处理返回的httpResponse信息
			if (statusCode == 200) {
				HttpEntity entity = httpResponse.getEntity();
				data = EntityUtils.toByteArray(entity);

				Cookie cookie;
				String cookname=null, cookvalue=null;
				List<Cookie> cookies = client.getCookieStore().getCookies();
				if (cookies.isEmpty()) {
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						// 保存cookie
						cookie = cookies.get(i);
						// Log.i("11111", cookie.toString());
						cookname = cookie.getName().trim();
						cookvalue = cookie.getValue().trim();
//						if (cookname.equals(Constans.key_name)) {
//							Constans.key_value = cookvalue;
//						}
					}
				}
			} else {
			}
			// result = "networkerror";
		} catch (ConnectTimeoutException e) {// 超时或网络连接出错
			// result = "timeouterror";
		} catch (ClientProtocolException e) {
			// result = "networkerror";
		} catch (Exception e) {
			// result = "readerror";
			throw new Exception(e);

		}

		finally {
			getMethod.abort();
		}

		return data;

	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	public static void saveImg(Bitmap bitmap) {
		if (bitmap != null) {
			FileOutputStream fos = null;
			try { 
				String path = android.os.Environment
						.getExternalStorageDirectory() + "/sanjiang/pic/";
				File file = new File(path);
				if (!file.exists()) {
					file.mkdir();
				}
				File file2 = new File(path + "faceImage");
				if (!file2.exists()) {
					fos = new FileOutputStream(file2);
					if (null != fos) {
						bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
						fos.flush();
						fos.close();
					}
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
	
	
	
	// 新框架
	private static AsyncHttpClient client = new AsyncHttpClient(); // 实例话对象
	static {
		client.setTimeout(5000); // 设置链接超时，如果不设置，默认为10s
	}

//	public static void get(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象
//	{
//		client.get(urlString, res);
//	}

//	public static void get(String urlString, RequestParams params,
//			AsyncHttpResponseHandler res) // url里面带参数
//	{
//		client.get(urlString, params, res);
//	}

//	* RequestParams params = new RequestParams();
//	* params.put("username", "james");
//	* params.put("password", "123456");
//	* params.put("email", "my@email.com");
//	* params.put("profile_picture", new File("pic.jpg")); // Upload a File
//	* params.put("profile_picture2", someInputStream); // Upload an InputStream
//	* params.put("profile_picture3", new ByteArrayInputStream(someBytes)); // Upload some bytes
	
	
	/*private static String encodingURL(String urlString){
		//String urlString = "http://www.zhongyi-bao.com/api/User.ashx?model=addconsult&age=20&content=吐了了了了了       了了了了了了了了了&keshiId=1&name=胖坨坨&sex=2&relation=自己&Uid=201533&careers=好";
		
		int position = urlString.indexOf("&") + 1;
		String urlBase = urlString.substring(0, position);
		String urlBody = urlString.substring(position);
		String[] query = urlBody.split("&");
		String queryString = "";
		for (String s : query) {
			if(s!=""){
			if(s.indexOf("=")<0) continue;
			int p = s.indexOf("=")+1;
			try {
				queryString =queryString + s.substring(0,p)+ URLEncoder.encode(s.substring(p),"utf-8") + "&";
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		String encodedString = (urlBase +queryString).replace("+", "%20");
		return encodedString;
		//System.out.println(encodedString);
	}*/
	
	public static void get(String urlString,Context context,Dialog dialog,JsonHttpResponseHandler res) // 不带参数，获取json对象或者数组
	{     
//		urlString = encodingURL(urlString);
		
		if(MyTools.checkNetWorkStatus(context)){
			client.get(urlString, res);
		}else{
			Toast.makeText(context, "抱歉,您的网络无法使用", 500).show();
			if (dialog != null) {
				dialog.dismiss();
			}
		}
		
	}

	public static void get(String urlString, RequestParams params,
			JsonHttpResponseHandler res) // 带参数，获取json对象或者数组
	{
		int random = new Random(1000).nextInt(1000);
		String rand = random+"";
		params.put("app_id", Constant.APPID);
		params.put("nonce", rand);
		params.put("timestamp", (new Date().getTime()+"").substring(0, 10));
		params.put("sign",MD5.Md5(Constant.APPID + "||" + rand + "||" + (new Date().getTime() + "").substring(0, 10) + "||" + Constant.APPKEY) .substring(12,20));

		client.post(urlString, params, res);
		 
	}
//
//	public static void get(String uString, BinaryHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据
//	{
//		client.get(uString, bHandler);
//	}

	public static AsyncHttpClient getClient() {
		return client;
	}
}
