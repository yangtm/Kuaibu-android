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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MakeSureActivity extends Activity implements OnClickListener{

	private EditText msg;
	private Button sure_get;
	private Button sure_next;
	private String suremsg;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_sure);
		
		msg = (EditText) findViewById(R.id.sure_msg);
		sure_get = (Button) findViewById(R.id.sure_get);
		sure_next = (Button) findViewById(R.id.sure_next);
		
		sure_get.setOnClickListener(this);
		sure_next.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sure_get:
			//获取验证码
			getSureMsg();
			break;

		default:
			break;
		}
		
	}
	
	/**
	 * 获取验证码
	 */
	private void getSureMsg() {
		suremsg = msg.getText().toString().trim();
		//调用获取验证码的接口
		RequestParams params = new RequestParams();
		params.put("phone", suremsg);
		params.put("zone", "86");
		
		//网络请求获取验证码
		HttpRequest.get(Constant.MAKESURE_URL, params, new JsonHttpResponseHandler(){ 
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				try {
					JSONObject res = response;
					int code = res.getInt("RESPCODE");
					String msg = res.getString("RESPMSG");
					String resulet = res.getString("RESULT");
					switch (code) {
					case 0:
						  if (resulet.equals("1234")) {
							Intent intent = new Intent();
							intent.putExtra("mob", suremsg);
							intent.setClass(MakeSureActivity.this,UpdatePwdByForgetActivity.class);
							startActivity(intent);
						}
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

}
