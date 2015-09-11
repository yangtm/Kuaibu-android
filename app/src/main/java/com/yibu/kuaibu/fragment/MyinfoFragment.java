package com.yibu.kuaibu.fragment;



import com.yibu.kuaibu.R;
import com.yibu.kuaibu.base.BaseFragment;
import com.yibu.kuaibu.app.LoginActivity;
import com.yibu.kuaibu.app.MainActivity;
import com.yibu.kuaibu.app.SettingActivity;
import com.yibu.kuaibu.utils.SharedPreferencesUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
/**
 * Fragment
 * @author zjc
 *
 * 2015-9-9
 */

public class MyinfoFragment extends Fragment implements OnClickListener{
	
	private Button moreclick;
	private Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.myinfo_layout, container, false);
		
		isHadLogin();
		
		initinfo();
		
		moreclick = (Button) view.findViewById(R.id.myinfo_moreclick);
		moreclick.setOnClickListener(this);
		return view;
		
		
	}
	
	private void isHadLogin() {
		if (MainActivity.islogin.equals("false")) {
			Intent intent = new Intent(getActivity(),LoginActivity.class);
			startActivity(intent);
		}

	}
	
	//进入页面初始化信息
	private void initinfo() {
		//已登录获取用户信息
		if (MainActivity.islogin.equals("true")){ 
			
			
		}else{ 
			Intent intent = new Intent(getActivity(),LoginActivity.class);
			startActivity(intent);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.myinfo_moreclick:
			Intent intent = new Intent(getActivity(),SettingActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}
}
