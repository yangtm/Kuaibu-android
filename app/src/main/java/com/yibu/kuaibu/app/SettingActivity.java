package com.yibu.kuaibu.app;

import org.apache.http.Header;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yibu.kuaibu.R;
import com.yibu.kuaibu.http.HttpRequest;
import com.yibu.kuaibu.utils.Constant;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingActivity extends Activity implements OnClickListener{

	private Button logi_out;
	private ImageView selfinfo;
	private ImageView changepwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		logi_out = (Button) findViewById(R.id.login_out);
		selfinfo = (ImageView) findViewById(R.id.selfinfo);
		changepwd = (ImageView) findViewById(R.id.changepsw);
		logi_out.setOnClickListener(this);
		selfinfo.setOnClickListener(this);
		changepwd.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_out:
			//退出登录
			LoginOut();
//			Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
//			startActivity(intent);
			break;

		case R.id.selfinfo:
			Intent intent2 = new Intent(SettingActivity.this,SelfInfoActivity.class);
			startActivity(intent2);
			break;
		case R.id.changepsw:
			Intent intent3 = new Intent(SettingActivity.this,ChangePwdActivity.class);
			startActivity(intent3);
			break;
		default:
			break;
		}
	}
	
	private void LoginOut() {
		RequestParams params = new RequestParams();
		//网络请求退出登录
		HttpRequest.get(Constant.LOGINOUT_URL, params, new JsonHttpResponseHandler(){ 
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				try {
					JSONObject res = response;
					int code = res.getInt("RESPCODE");
					String msg = res.getString("RESPMSG");
					MainActivity.islogin="false";
					switch (code) {
					case 0:
						Toast.makeText(SettingActivity.this, "退出成功", 0).show();
						Intent intent = new Intent(SettingActivity.this,MainActivity.class);
						startActivity(intent);
						SettingActivity.this.finish();
						break;

					default:
						break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.onSuccess(statusCode, headers, response);
			}
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
			}
			
			
			
		});

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			 if (MainActivity.islogin.equals("false")) {
				   Intent intent = new Intent(SettingActivity.this,MainActivity.class);
				   startActivity(intent);
			}else{ 
				SettingActivity.this.finish();
			}
			  
		       return true;
		   }
		return super.onKeyDown(keyCode, event);
	}

}
