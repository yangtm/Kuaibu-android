package com.yibu.kuaibu.app;

import org.apache.http.Header;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yibu.kuaibu.R;
import com.yibu.kuaibu.R.id;
import com.yibu.kuaibu.fragment.LoginFragment;
import com.yibu.kuaibu.http.HttpRequest;
import com.yibu.kuaibu.utils.Constant;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.HttpAuthHandler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePwdActivity extends Activity implements OnClickListener{

	private EditText oldpwd,newpwd,change_pwdagain;
	private Button changepwd;
	private Button forgetpwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_pwd);
		
		oldpwd = (EditText) findViewById(R.id.change_oldpwd);
		newpwd = (EditText) findViewById(R.id.change_newpwd);
		change_pwdagain = (EditText) findViewById(R.id.change_pwdagain);
		changepwd = (Button) findViewById(R.id.commit_changepwd);
		forgetpwd = (Button) findViewById(R.id.change_forgetpwd);
		changepwd.setOnClickListener(this);
		forgetpwd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.commit_changepwd:
			changepwd();
			break;
		case R.id.change_forgetpwd:
			Intent intent = new Intent(ChangePwdActivity.this,MakeSureActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}

	private void changepwd() {
		//获取用户密码信息
		String oldpass = oldpwd.getText().toString().trim();
		String newpass = newpwd.getText().toString().trim();
		String againpass = change_pwdagain.getText().toString().trim();
		if (newpass.equals(againpass)) {
			RequestParams params = new RequestParams();
			params.put("oldPassword", oldpass);
			params.put("newPassword", newpass);
			
			//网络请求修改密码
			HttpRequest.get(Constant.UPDATEPWD_URL, params, new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(int statusCode, Header[] headers,
						JSONObject response) {
					try {
						JSONObject res = response;
						int code = res.getInt("RESPCODE");
						String msg = res.getString("RESPMSG");
						
						switch (code) {
						case 0:
							Toast.makeText(ChangePwdActivity.this, msg, 0).show();
							ChangePwdActivity.this.finish();
							break;

						default:
							Toast.makeText(ChangePwdActivity.this, "修改失败 请重新", 0).show();
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
