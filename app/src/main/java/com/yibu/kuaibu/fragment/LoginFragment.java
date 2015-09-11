package com.yibu.kuaibu.fragment;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yibu.kuaibu.R;
import com.yibu.kuaibu.http.HttpRequest;
import com.yibu.kuaibu.app.MainActivity;
import com.yibu.kuaibu.app.SettingActivity;
import com.yibu.kuaibu.utils.AppUtils;
import com.yibu.kuaibu.utils.Constant;
import com.yibu.kuaibu.utils.SharedPreferencesUtil;

import android.app.AlertDialog;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment implements OnClickListener{
	
	private EditText user_name;
	private EditText user_pwd;
	private Button login_click;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
		
        user_name = (EditText) view.findViewById(R.id.uesr_name);
        user_pwd = (EditText) view.findViewById(R.id.user_pwd);
        login_click = (Button) view.findViewById(R.id.login_click);
        login_click.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_click:
			//获取用户登陆信息
			String name = user_name.getText().toString().trim();
			String pass = user_pwd.getText().toString();
			RequestParams params = new RequestParams();
//			params.put("name" , "15000412073");
//			params.put("password" , "123456");
			params.put("memberNameTel", name);
			params.put("password", pass);
			//网络请求登录
			HttpRequest.get(Constant.LOGIN_URL, params, new JsonHttpResponseHandler(){ 
				@Override
				public void onSuccess(int statusCode, Header[] headers,
						JSONObject response) {
					try {
						JSONObject res = response;
						int code = res.getInt("RESPCODE");
						String msg = res.getString("RESPMSG");
						String result = res.getString("RESULT");
						//登录成功保存登录成功信息
						MainActivity.islogin="true";
						switch (code) {
						case 0:
							    Toast.makeText(getActivity(), msg, 0).show();
//							    Intent intent = new Intent(getActivity(),MyinfoFragment.class);
//							    startActivity(intent);
							    LoginFragment.this.getActivity().finish();
							break;

						default:
							Toast.makeText(getActivity(), "登陆失败 请重新登陆", 0).show();
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
					Toast.makeText(getActivity(), "shibai", 0).show();
					super.onFailure(statusCode, headers, responseString, throwable);
				}
				
				@Override
				public void onStart() {
					// TODO Auto-generated method stub
					super.onStart();
				}
				
			});
			break;

		default:
			break;
		}
	}
    
}
