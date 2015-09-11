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

public class UpdatePwdByForgetActivity extends Activity implements OnClickListener{

	private EditText set_pwd,set_pwdagain;
	private Button sure_commit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_pwd_by_forget);
		
		
		set_pwd = (EditText) findViewById(R.id.set_pwd);
		set_pwdagain = (EditText) findViewById(R.id.set_pwdagain);
		sure_commit = (Button) findViewById(R.id.sure_commit);
		
		sure_commit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sure_commit:
			  changePwdByForget();
			break;

		default:
			break;
		}
		
	}
	
	private void changePwdByForget() {
		//获取密码信息
		String pwd = set_pwd.getText().toString().trim();
		String pwdagain = set_pwdagain.getText().toString().trim();
		
		//获取上一页面传递过来的值
		Intent intent=getIntent();
		String mob=intent.getStringExtra("mob");
		
		RequestParams params = new RequestParams();
		params.put("password", pwd);
		params.put("memberNameTel", mob);
		
		if (pwd.equals(pwdagain)) {
			//网络请求重置密码
			HttpRequest.get(Constant.UPDATEPWDBYFORGET_URL, params, new JsonHttpResponseHandler(){ 
				@Override
				public void onSuccess(int statusCode, Header[] headers,
						JSONObject response) {
					try {
						JSONObject res = response;
						int code = res.getInt("RESPCODE");
						String msg = res.getString("RESPMSG");
						switch (code) {
						case 0:
							Toast.makeText(UpdatePwdByForgetActivity.this, msg, 0).show();
							Intent intent = new Intent(UpdatePwdByForgetActivity.this,SettingActivity.class);
							startActivity(intent);
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


}
